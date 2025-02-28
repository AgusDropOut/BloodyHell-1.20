package net.agusdropout.bloodyhell.entity.projectile;

import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.agusdropout.bloodyhell.entity.custom.BloodSlashEntity;
import net.agusdropout.bloodyhell.particle.ModParticles;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import java.util.List;
import java.util.UUID;


public class BloodProjectileEntity extends Projectile implements GeoEntity {
    private int lifeTicks = 40; // Duración antes de desaparecer
    private float damage;
    private AnimatableInstanceCache factory = new SingletonAnimatableInstanceCache(this);

    public BloodProjectileEntity(EntityType<? extends BloodProjectileEntity> type, Level level) {
        super(type, level);
    }

    public BloodProjectileEntity(Level level, double x, double y, double z, float damage, LivingEntity owner, float yaw, float pitch) {
        this(ModEntityTypes.BLOOD_PROJECTILE.get(), level);
        this.damage = damage;
        this.setOwner(owner);
        this.setPos(x, y, z);

        float speed = 1.3f;
        float xMotion = -Mth.sin(yaw * (float) Math.PI / 180F) * Mth.cos(pitch * (float) Math.PI / 180F);
        float yMotion = -Mth.sin(pitch * (float) Math.PI / 180F);
        float zMotion = Mth.cos(yaw * (float) Math.PI / 180F) * Mth.cos(pitch * (float) Math.PI / 180F);

        this.setDeltaMovement(new Vec3(xMotion * speed, yMotion * speed, zMotion * speed));
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide) {
            this.move(MoverType.SELF, this.getDeltaMovement());
        }
        if(this.level().isClientSide && this.lifeTicks == 1){
            this.spawnImpactParticles();
        }


        if (this.lifeTicks == 0) {
            this.discard();
        }

        checkCollisions();
        spawnFlyingParticles(); // Partículas en el aire
        this.lifeTicks--;
    }

    private void checkCollisions() {
        if (!this.level().isClientSide) {
            AABB boundingBox = this.getBoundingBox().inflate(0.3);
            List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, boundingBox,
                    e -> e != this.getOwner() && e.isAlive());

            for (LivingEntity entity : entities) {
                entity.hurt(this.damageSources().generic(), this.damage);
            }
        }

        if (!level().noCollision(this, getBoundingBox().inflate(0.05))) {
            this.playSound(SoundEvents.GENERIC_SPLASH, 1.0F, 1.0F);
        }
    }

    private void spawnFlyingParticles() {
        if (this.level().isClientSide) {
            for (int i = 0; i < 5; i++) { // Más partículas en el aire
                double offsetX = (this.random.nextDouble() - 0.5) * 0.1;
                double offsetY = (this.random.nextDouble() - 0.5) * 0.1 + 0.02; // Ligera elevación
                double offsetZ = (this.random.nextDouble() - 0.5) * 0.1;

                // Velocidad aleatoria
                double speed = 1 + this.random.nextDouble() * 0.05;
                double vx = (this.random.nextDouble() - 0.5) * speed;
                double vy = (this.random.nextDouble() - 0.5) * speed;
                double vz = (this.random.nextDouble() - 0.5) * speed;

                this.level().addParticle(ModParticles.BLOOD_PARTICLES.get(),
                        this.getX() + offsetX,
                        this.getY() + offsetY + 1,
                        this.getZ() + offsetZ,
                        vx, vy, vz);
            }
        }
    }

    private void spawnImpactParticles() {
        if (this.level().isClientSide) {
            for (int i = 0; i < 100; i++) {
                double speed = 0.2 + this.random.nextDouble() * 0.2;
                double angle = this.random.nextDouble() * Math.PI * 2;
                double vx = Math.cos(angle) * speed;
                double vy = (this.random.nextDouble() - 0.5) * 0.4;
                double vz = Math.sin(angle) * speed;

                this.level().addParticle(ModParticles.BLOOD_PARTICLES.get(),
                        this.getX(), this.getY(), this.getZ(), vx, vy, vz);
            }
        }
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }

    private PlayState predicate(software.bernie.geckolib.core.animation.AnimationState animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("flying", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }
}
