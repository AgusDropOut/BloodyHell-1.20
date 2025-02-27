package net.agusdropout.bloodyhell.particle.custom;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.agusdropout.bloodyhell.particle.ModParticles;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;
public class SlashParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    public SlashParticle(ClientLevel clientLevel, double d, double e, double f, SpriteSet sprites) {
        super(clientLevel, d, e, f);
        this.alpha = 0.8F;
        this.quadSize = 1.3F;
        this.lifetime = 48;
        this.sprites = sprites;
        this.rCol = 1.0F;
        this.gCol = 1.0F;
        this.bCol = 1.0F;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        quadSize = Mth.lerp(0.25F, quadSize, 6);
        if (age++ >= lifetime) {
            remove();
        } else {
            alpha = Mth.lerp(0.08F, alpha, 0);
        }
        if (alpha <= 0.01F) remove();
        setSpriteFromAge(sprites);
    }

    @Override
    public void render(VertexConsumer consumer, Camera camera, float delta) {
        // Simple render logic: no rotation applied.
        super.render(consumer, camera, delta);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType particleOptions, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            // No yaw calculations, just create the particle at a fixed offset
            return new SlashParticle(clientLevel, d, e, f, this.sprites);
        }
    }
}