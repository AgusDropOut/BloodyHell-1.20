package net.agusdropout.bloodyhell.entity.projectile;

import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.agusdropout.bloodyhell.entity.custom.BlasphemousArmEntity;
import net.agusdropout.bloodyhell.particle.ModParticles;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import java.util.UUID;



public class BlasphemousWhirlwindEntity extends Entity implements TraceableEntity, GeoEntity {
    private final AnimatableInstanceCache factory = new SingletonAnimatableInstanceCache(this);

    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;

    private int warmupDelayTicks;
    private int lifeTicks = 300;
    public static final float DEFAULT_DAMAGE = 3.0F;
    private float damage = DEFAULT_DAMAGE;
    private BlasphemousSmallWhirlwindEntity smallRing;

    private int spawnCooldown = 0;

    // === Synced Data ===
    private static final EntityDataAccessor<Boolean> MINOR_RING_ACTIVE =
            SynchedEntityData.defineId(BlasphemousWhirlwindEntity.class, EntityDataSerializers.BOOLEAN);

    public BlasphemousWhirlwindEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public BlasphemousWhirlwindEntity(Level level, double d, double e, double f , LivingEntity livingEntity) {
        this(ModEntityTypes.BLASPHEMOUS_WHIRLWIND_ENTITY.get(), level);
        this.setOwner(livingEntity);
        this.setPos(d, e, f);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(MINOR_RING_ACTIVE, false); // por defecto oculto
    }

    // === Owner ===
    public void setOwner(@Nullable LivingEntity livingEntity) {
        this.owner = livingEntity;
        this.ownerUUID = livingEntity == null ? null : livingEntity.getUUID();
    }

    @Override
    @Nullable
    public LivingEntity getOwner() {
        Entity entity;
        if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel serverLevel) {
            entity = serverLevel.getEntity(this.ownerUUID);
            if (entity instanceof LivingEntity living) {
                this.owner = living;
            }
        }
        return this.owner;
    }

    // === Save / Load NBT ===
    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        if (compoundTag.hasUUID("Owner")) {
            this.ownerUUID = compoundTag.getUUID("Owner");
        }
        if (compoundTag.contains("MinorRingActive")) {
            this.setMinorRingActive(compoundTag.getBoolean("MinorRingActive"));
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putInt("LifeTicks", this.lifeTicks);
        if (this.ownerUUID != null) {
            compoundTag.putUUID("Owner", this.ownerUUID);
        }
        compoundTag.putBoolean("MinorRingActive", this.isMinorRingActive());
    }

    // === Tick ===
    @Override
    public void tick() {
        super.tick();

        if(lifeTicks % 5 == 0) {
            spawnAuraParticles();
            hurtNearbyEntities();
        }

        if (  spawnCooldown-- <= 0) {
            spawnMinorRing();
            spawnCooldown = 100; // 2s aprox
        }

        if (!level().isClientSide) {
            if (this.owner == null || !this.owner.isAlive()) {
                this.discard();
                return;
            }
            this.setPos(getOwner().getX(), getOwner().getY() - 0.3, getOwner().getZ());
        }

        if (lifeTicks == 0) {
            this.discard();
        }

        if (lifeTicks % 10 == 0) {
            level().addParticle(ModParticles.MAGIC_WAVE_PARTICLE.get(),
                    this.getX(), this.getY() , this.getZ(),
                    40, 0.0D, 0.0D);
        }

        lifeTicks--;
    }

    private void spawnMinorRing() {
        smallRing = new BlasphemousSmallWhirlwindEntity(level(), getX(), getY(), getZ(), owner);
        smallRing.setParentRing(this);
        level().addFreshEntity(smallRing);
        setMinorRingActive(true); // sincronizado
    }

    private void spawnAuraParticles() {
        if (level().isClientSide) {
            int count = 16; // cuántas partículas por tick
            double radius = 3; // radio máximo del anillo
            double verticalSpeed = 0.02; // velocidad base en Y

            for (int i = 0; i < count; i++) {
                // Ángulo y distancia radial aleatoria (mantiene distribución circular)
                double angle = this.random.nextDouble() * 2 * Math.PI;
                double dist = radius * (0.7 + this.random.nextDouble() * 0.3); // entre 70% y 100% del radio

                // Coordenadas de spawn
                double px = this.getX() + Math.cos(angle) * dist;
                double py = this.getY() + 0.3 + this.random.nextDouble() * 3; // altura entre +0.3 y +1.5
                double pz = this.getZ() + Math.sin(angle) * dist;

                // Movimiento: solo vertical leve (puede ser hacia arriba o abajo)
                double dx = 0;
                double dz = 0;
                double dy = (this.random.nextDouble() - 0.5) * verticalSpeed; // -0.01 a +0.01

                // Spawnear partícula
                this.level().addParticle(ModParticles.MAGIC_LINE_PARTICLE.get(),
                        px, py, pz,
                        dx, dy, dz);
            }
        }
    }

   public void  hurtNearbyEntities() {
        if (level().isClientSide) return;

        double radius = 3.0;
        for (LivingEntity entity : level().getEntitiesOfClass(LivingEntity.class,
                this.getBoundingBox().inflate(radius),
                this::isValidTarget)) {
            entity.hurt(this.damageSources().magic(), this.damage);
        }
    }

    private boolean isValidTarget(LivingEntity candidate) {
        if (candidate == null || !candidate.isAlive()) return false;

        // No atacar al owner
        if (candidate == getOwner()) return false;

        // Si es una entidad domesticable y tiene mismo dueño, no atacarla
        if (candidate instanceof TamableAnimal tamable && tamable.getOwner() == this.getOwner()) {
            return false;
        }

        // Si es otra entidad de tu mod que quieras excluir
        if (candidate instanceof BlasphemousArmEntity) {
            if(((BlasphemousArmEntity) candidate).getOwner() == this.getOwner()) return false;
        }

        // Más condiciones a futuro acá

        return true;
    }



    // === Boolean synced ===
    public boolean isMinorRingActive() {
        return this.entityData.get(MINOR_RING_ACTIVE);
    }

    public void setMinorRingActive(boolean active) {
        this.entityData.set(MINOR_RING_ACTIVE, active);
    }

    public void onMinorRingReturned() {
        setMinorRingActive(false);
    }

    // === Damage ===
    @Override
    public boolean hurt(DamageSource damageSource, float f) {
        if (damageSource.getEntity() instanceof Arrow) return false;
        return super.hurt(damageSource, f);
    }

    // === Animations ===
    private PlayState predicate(software.bernie.geckolib.core.animation.AnimationState animationState) {
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }


}

