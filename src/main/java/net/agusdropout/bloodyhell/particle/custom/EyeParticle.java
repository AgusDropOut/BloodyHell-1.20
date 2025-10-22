package net.agusdropout.bloodyhell.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import org.joml.Vector3f;

@OnlyIn(Dist.CLIENT)
public class EyeParticle extends TextureSheetParticle {

    private final TextureAtlasSprite spriteBase;
    private final TextureAtlasSprite spriteIris;
    private final float facingYaw;
    private final float facingPitch;

    // Controla cuánto puede moverse la iris
    private static final float MAX_IRIS_OFFSET = 0.125f;
    private static final float Z_FIGHT_OFFSET = 0.001f;

    protected EyeParticle(ClientLevel pLevel, double pX, double pY, double pZ,
                          TextureAtlasSprite spriteBase, TextureAtlasSprite spriteIris) {
        super(pLevel, pX, pY, pZ);
        this.spriteBase = spriteBase;
        this.spriteIris = spriteIris;

        this.lifetime = 200;
        this.gravity = 0.0F;
        this.quadSize = 0.5F;

        // Calcular orientación hacia el jugador
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            double dx = player.getX() - pX;
            double dy = (player.getEyeY() - pY);
            double dz = player.getZ() - pZ;

            this.facingYaw = (float) Math.toDegrees(Math.atan2(dx, dz));
            double distXZ = Math.sqrt(dx * dx + dz * dz);
            this.facingPitch = (float) Math.toDegrees(Math.atan2(dy, distXZ));
        } else {
            this.facingYaw = 0.0F;
            this.facingPitch = 0.0F;
        }
    }

    @Override
    protected int getLightColor(float tint) {
        // Ignora luz ambiental, siempre máximo
        return 0xF000F0;
    }

    @Override
    public void tick() {
        super.tick();

        // Fade suave al final de la vida
        float fadeStart = 0.85f; // comienza a desaparecer al 85% de la vida
        float lifeRatio = (float)this.age / (float)this.lifetime;

        if (lifeRatio < fadeStart) {
            this.alpha = 1.0f; // completamente visible
        } else {
            this.alpha = 1.0f - (lifeRatio - fadeStart) / (1.0f - fadeStart); // fade al final
        }
    }

    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float partialTicks) {
        Vec3 cameraPos = camera.getPosition();
        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        float pX = (float) (Mth.lerp(partialTicks, this.xo, this.x));
        float pY = (float) (Mth.lerp(partialTicks, this.yo, this.y));
        float pZ = (float) (Mth.lerp(partialTicks, this.zo, this.z));

        float relX = pX - (float) cameraPos.x;
        float relY = pY - (float) cameraPos.y;
        float relZ = pZ - (float) cameraPos.z;

        // Desplazamiento de la iris hacia el jugador
        Vec3 particlePos = new Vec3(pX, pY, pZ);
        Vec3 playerEyes = player.getEyePosition();
        Vec3 lookVec = playerEyes.subtract(particlePos).normalize();

        float xOffset = (float) -lookVec.x;
        float yOffset = (float) -lookVec.y;

        float offsetMagnitude = xOffset * xOffset + yOffset * yOffset;
        float maxOffsetSq = MAX_IRIS_OFFSET * MAX_IRIS_OFFSET;
        if (offsetMagnitude > maxOffsetSq) {
            float scale = (float) (MAX_IRIS_OFFSET / Math.sqrt(offsetMagnitude));
            xOffset *= scale;
            yOffset *= scale;
        }

        Vector3f irisDisplacement = new Vector3f(xOffset, yOffset, -Z_FIGHT_OFFSET);
        rotateVectorYawPitch(irisDisplacement, facingYaw, facingPitch);
        irisDisplacement.mul(this.quadSize);

        // Dibujar base y iris
        drawQuad(vertexConsumer, relX, relY, relZ, this.spriteBase, 1.0F);
        drawQuad(vertexConsumer,
                relX + irisDisplacement.x(),
                relY + irisDisplacement.y(),
                relZ + irisDisplacement.z(),
                this.spriteIris,
                0.5F);
    }

    private static void rotateVectorYawPitch(Vector3f v, float yawDeg, float pitchDeg) {
        float yaw = (float) Math.toRadians(yawDeg);
        float pitch = (float) Math.toRadians(pitchDeg);

        // rotación Yaw (eje Y)
        float cosY = Mth.cos(yaw);
        float sinY = Mth.sin(yaw);
        float x1 = v.x() * cosY + v.z() * sinY;
        float z1 = -v.x() * sinY + v.z() * cosY;

        // rotación Pitch (eje X)
        float cosP = Mth.cos(pitch);
        float sinP = Mth.sin(pitch);
        float y1 = v.y() * cosP - z1 * sinP;
        float z2 = v.y() * sinP + z1 * cosP;

        v.set(x1, y1, z2);
    }

    private void drawQuad(VertexConsumer consumer,
                          float relX, float relY, float relZ,
                          TextureAtlasSprite sprite, float sizeScale) {

        float size = this.getQuadSize(0) * sizeScale;

        Vector3f[] vertices = new Vector3f[]{
                new Vector3f(-1.0F, -1.0F, 0.0F),
                new Vector3f(1.0F, -1.0F, 0.0F),
                new Vector3f(1.0F, 1.0F, 0.0F),
                new Vector3f(-1.0F, 1.0F, 0.0F)
        };

        for (Vector3f v : vertices) {
            rotateVectorYawPitch(v, facingYaw, facingPitch);
            v.mul(size);
            v.add(relX, relY, relZ);
        }

        float u0 = sprite.getU0();
        float u1 = sprite.getU1();
        float v0 = sprite.getV0();
        float v1 = sprite.getV1();

        float[] uvs = new float[]{ u0, v0, u1, v0, u1, v1, u0, v1 };

        int light = 0xF000F0; // luz máxima

        for (int i = 0; i < 4; ++i) {
            Vector3f vertex = vertices[i];
            consumer.vertex(vertex.x(), vertex.y(), vertex.z())
                    .uv(uvs[i * 2], uvs[i * 2 + 1])
                    .color(this.rCol, this.gCol, this.bCol, this.alpha)
                    .uv2(light)
                    .endVertex();
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel,
                                       double pX, double pY, double pZ,
                                       double pXSpeed, double pYSpeed, double pZSpeed) {

            TextureAtlasSprite baseSprite = this.spriteSet.get(0, 1);
            TextureAtlasSprite irisSprite = this.spriteSet.get(1, 1);

            return new EyeParticle(pLevel, pX, pY, pZ, baseSprite, irisSprite);
        }
    }
}
