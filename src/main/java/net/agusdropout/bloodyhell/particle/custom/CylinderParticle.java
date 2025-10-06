package net.agusdropout.bloodyhell.particle.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.agusdropout.bloodyhell.BloodyHell;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.*;

import javax.annotation.Nullable;
import java.lang.Math;

@OnlyIn(Dist.CLIENT)
public class CylinderParticle extends Particle {
    private final ResourceLocation texture = new ResourceLocation(BloodyHell.MODID, "textures/particle/yellow.png");
    private final float radius = 1.0f;
    private final float height = 2.0f;
    private final int segments = 32; // cuanto más, más suave el cilindro

    protected CylinderParticle(ClientLevel level, double x, double y, double z, int maxLifeTime) {
        super(level, x, y, z);
        this.lifetime = maxLifeTime;
        if (maxLifeTime == 0) {
            this.lifetime = 100;
        }
    }

    @Override
    public void tick() {
        if (age++ >= lifetime) {
            remove();
        }

    }

    @Override
    public void render(VertexConsumer ignored, Camera camera, float partialTicks) {
        Vec3 camPos = camera.getPosition();
        double px = Mth.lerp(partialTicks, xo, x) - camPos.x;
        double py = Mth.lerp(partialTicks, yo, y) - camPos.y;
        double pz = Mth.lerp(partialTicks, zo, z) - camPos.z;

        // setup shader
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableCull();
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.setShaderTexture(0, texture);

        Tesselator tess = Tesselator.getInstance();
        BufferBuilder buffer = tess.getBuilder();
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX);

        float time = age + partialTicks; // animación con tiempo
        int segments = 32;
        float radius = 1.5f;
        float baseHeight = height;
        float maxExtraHeight = 1.0f;

        for (int i = 0; i < segments; i++) {
            double angle1 = (2 * Math.PI * i) / segments;
            double angle2 = (2 * Math.PI * (i + 1)) / segments;

            float x1 = (float) (px + Math.cos(angle1) * radius);
            float z1 = (float) (pz + Math.sin(angle1) * radius);
            float x2 = (float) (px + Math.cos(angle2) * radius);
            float z2 = (float) (pz + Math.sin(angle2) * radius);

            // altura suavemente variable arriba y abajo de la base
            float h1 = baseHeight + 1.2f * (float) Perlin.noise(i * 0.1, time * 0.05);
            float h2 = baseHeight + 1.2f * (float) Perlin.noise((i + 1) * 0.1, time * 0.05);

            // alpha dinámico, atenuado por altura, nunca menor a 0.15
            float baseAlpha1 = 0.2f + 0.6f * (float) Perlin.noise(i * 0.1, time * 0.07 + 100);
            float baseAlpha2 = 0.2f + 0.6f * (float) Perlin.noise((i + 1) * 0.1, time * 0.07 + 100);

            float attenuation1 = 1.0f - ((h1 - baseHeight + maxExtraHeight * 0.5f) / (maxExtraHeight * 2)); // más suave, centrado
            float attenuation2 = 1.0f - ((h2 - baseHeight + maxExtraHeight * 0.5f) / (maxExtraHeight * 2));

            attenuation1 = Math.max(0.15f, (float) Math.pow(Mth.clamp(attenuation1, 0f, 1f), 2));
            attenuation2 = Math.max(0.15f, (float) Math.pow(Mth.clamp(attenuation2, 0f, 1f), 2));

            float alpha1 = baseAlpha1 * attenuation1;
            float alpha2 = baseAlpha2 * attenuation2;

            // dibujar quad
            buffer.vertex(x1, py, z1).color(1f, 1f, 1f, alpha1).uv(0f, 1f).endVertex();
            buffer.vertex(x1, py + h1, z1).color(1f, 1f, 1f, alpha1).uv(0f, 0f).endVertex();
            buffer.vertex(x2, py + h2, z2).color(1f, 1f, 1f, alpha2).uv(1f, 0f).endVertex();
            buffer.vertex(x2, py, z2).color(1f, 1f, 1f, alpha2).uv(1f, 1f).endVertex();
        }

        tess.end();
        RenderSystem.disableBlend();
        RenderSystem.enableCull();
    }
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.CUSTOM;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world,
                                       double x, double y, double z,
                                       double vx, double vy, double vz) {
            return new CylinderParticle(world, x, y, z,(int)vx);
        }
    }


    public static class Perlin {
        private static final int[] perm = new int[512];
        private static final int[] p = new int[256];

        static {
            for (int i = 0; i < 256; i++) p[i] = i;
            java.util.Random rand = new java.util.Random(1234);
            for (int i = 0; i < 256; i++) {
                int j = rand.nextInt(256 - i) + i;
                int tmp = p[i]; p[i] = p[j]; p[j] = tmp;
                perm[i] = perm[i + 256] = p[i];
            }
        }

        private static double fade(double t) { return t * t * t * (t * (t * 6 - 15) + 10); }
        private static double lerp(double t, double a, double b) { return a + t * (b - a); }
        private static double grad(int hash, double x, double y) {
            int h = hash & 15;
            double u = h < 8 ? x : y;
            double v = h < 4 ? y : h == 12 || h == 14 ? x : 0;
            return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
        }

        public static double noise(double x, double y) {
            int X = (int)Math.floor(x) & 255;
            int Y = (int)Math.floor(y) & 255;
            x -= Math.floor(x);
            y -= Math.floor(y);
            double u = fade(x);
            double v = fade(y);
            int A = perm[X] + Y, B = perm[X + 1] + Y;
            return lerp(v, lerp(u, grad(perm[A], x, y), grad(perm[B], x - 1, y)),
                    lerp(u, grad(perm[A + 1], x, y - 1), grad(perm[B + 1], x - 1, y - 1)));
        }
    }
}

