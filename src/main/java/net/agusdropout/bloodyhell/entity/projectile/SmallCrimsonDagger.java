package net.agusdropout.bloodyhell.entity.projectile;

import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.agusdropout.bloodyhell.entity.custom.UnknownEyeEntity;
import net.agusdropout.bloodyhell.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Random;
import java.util.UUID;


public class SmallCrimsonDagger extends Entity implements GeoEntity, TraceableEntity {
    private int lifeTicks = 60;
    private int ticksOnTheGround = 0;
    private boolean isOnGround = false;
    private float damage;
    private final AnimatableInstanceCache factory =  new SingletonAnimatableInstanceCache(this);

    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;

    private static final EntityDataAccessor<Float> LOOK_YAW = SynchedEntityData.defineId(SmallCrimsonDagger.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> LOOK_PITCH = SynchedEntityData.defineId(SmallCrimsonDagger.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Boolean> ON_GROUND = SynchedEntityData.defineId(SmallCrimsonDagger.class, EntityDataSerializers.BOOLEAN);

    public void setOwner(@Nullable LivingEntity livingEntity) {
        this.owner = livingEntity;
        this.ownerUUID = livingEntity == null ? null : livingEntity.getUUID();
    }

    @Override
    @Nullable
    public LivingEntity getOwner() {
        Entity entity;
        if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel && (entity = ((ServerLevel)this.level()).getEntity(this.ownerUUID)) instanceof LivingEntity) {
            this.owner = (LivingEntity)entity;
        }
        return this.owner;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        if (compoundTag.hasUUID("Owner")) {
            this.ownerUUID = compoundTag.getUUID("Owner");
        }

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        if (this.ownerUUID != null) {
            compoundTag.putUUID("Owner", this.ownerUUID);
        }
    }

    public SmallCrimsonDagger(EntityType<? extends SmallCrimsonDagger> type, Level level) {
        super(type, level);
    }

    public SmallCrimsonDagger(Level level, double x, double y, double z,double sx,double sy,double sz, float damage, LivingEntity owner) {
        this(ModEntityTypes.SMALL_CRIMSON_DAGGER.get(), level);
        this.damage = damage;
        this.setOwner(owner);
        this.setPos(x, y, z);
        this.setDeltaMovement(new Vec3(sx, sy, sz));
        initializeRotation();
    }
    public float getPitch() {
        return entityData.get(LOOK_PITCH);
    }
    public float getYaw() {
        return entityData.get(LOOK_YAW);
    }
    public boolean getIsOnGround() {
        return entityData.get(ON_GROUND);
    }


    private void initializeRotation() {
        Vec3 motion = this.getDeltaMovement();
        float yaw = (float) Math.toDegrees(Math.atan2(motion.x, motion.z));
        float pitch = (float) Math.toDegrees(Math.atan2(motion.y, Math.sqrt(motion.x * motion.x + motion.z * motion.z)));

        pitch -= 40;
        yaw += 180;

            this.setYRot(yaw);
            this.setXRot(pitch);
        //if (!this.level().isClientSide) {
        //    this.entityData.set(LOOK_YAW, yaw);
        //    this.entityData.set(LOOK_PITCH, pitch);
        //}


    }

    @Override
    public void tick() {
        if(!level().isClientSide) {
            if (this.owner == null || !this.owner.isAlive()) {
                this.discard();
                return;
            }
        }
        if(!level().isClientSide){
            entityData.set(ON_GROUND, this.onGround());
            this.isOnGround = this.onGround();
        } else {
            this.isOnGround = entityData.get(ON_GROUND);
        }

        if(!this.isOnGround){
            this.move(MoverType.SELF, this.getDeltaMovement());
        }
        if (ticksOnTheGround == 5){
            this.discard();
        }
        if (ticksOnTheGround == 3){
            checkExplosionCollisions();
        }
        if (this.isOnGround) {
            this.setDeltaMovement(0,0,0);
            this.ticksOnTheGround++;
            System.out.println("On ground");
            this.spawnImpactParticles();
            this.playSound(SoundEvents.ALLAY_THROW, 1.0F, 1.0F);
        }
        lifeTicks--;
        if (lifeTicks <= 0) {
            this.discard();
        }

        checkCollisions();
    }

    private void checkCollisions() {
        if (!this.level().isClientSide) {
            AABB boundingBox = this.getBoundingBox().inflate(0.3);
            List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, boundingBox,
                    e -> e != this.getOwner() && e.isAlive());

            for (LivingEntity entity : entities) {
                entity.hurt(this.damageSources().generic(), this.damage);
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1));
                this.discard();
                return;
            }
        }

    }
    private void checkExplosionCollisions() {
        if (!this.level().isClientSide) {
            AABB boundingBox = this.getBoundingBox().inflate(3);
            List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, boundingBox,
                    e -> e != this.getOwner() && e.isAlive());

            for (LivingEntity entity : entities) {
                entity.hurt(this.damageSources().generic(), this.damage);
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1));
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
        //this.entityData.define(LOOK_YAW, 0f);
        //this.entityData.define(LOOK_PITCH, 0f);
        this.entityData.define(ON_GROUND, false);
    }
    public float getLookYaw() {
        return this.entityData.get(LOOK_YAW);
    }
    public float getLookPitch() {
        return this.entityData.get(LOOK_PITCH);
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
    @Override
    public boolean onGround() {
        // Verificar si la entidad tiene colisiones debajo
        Vec3 position = this.position();
        BlockPos belowPos = new BlockPos(this.getBlockX(), this.getBlockY() - 1, this.getBlockZ()); // Chequea justo debajo de la entidad
        BlockState blockBelow = level().getBlockState(belowPos);

        // Si el bloque debajo no es aire, entonces la entidad está en el suelo
        return !blockBelow.isAir();
    }

    private PlayState predicate(software.bernie.geckolib.core.animation.AnimationState animationState) {

        return PlayState.STOP;
    }
}



