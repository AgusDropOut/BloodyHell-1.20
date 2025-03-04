package net.agusdropout.bloodyhell.entity.custom;

import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.agusdropout.bloodyhell.entity.projectile.SmallCrimsonDagger;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
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

public class BloodPortalEntity extends Entity implements TraceableEntity, GeoEntity {
    private AnimatableInstanceCache factory = new SingletonAnimatableInstanceCache(this);
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;
    private int warmupDelayTicks;
    private int lifeTicks = 200;
    public static final float DEFAULT_DAMAGE = 3.0F;
    private float damage = DEFAULT_DAMAGE;

    public BloodPortalEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public BloodPortalEntity(Level level, double d, double e, double f ,LivingEntity livingEntity) {
        this(ModEntityTypes.BLOOD_PORTAL_ENTITY.get(), level);
        this.setOwner(livingEntity);
        this.setPos(d, e, f);
    }


    @Override
    protected void defineSynchedData() {

    }


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
        compoundTag.putInt("LifeTicks", this.lifeTicks);
        if (this.ownerUUID != null) {
            compoundTag.putUUID("Owner", this.ownerUUID);
        }
    }



    @Override
    public void tick() {
        if(!level().isClientSide){
            if (this.owner == null || !this.owner.isAlive()) {
                this.discard();
                return;
            }
        }
        if(this.lifeTicks % 3 == 0) {
            double centerX = this.getX();
            double centerY = this.getY();
            double centerZ = this.getZ();


            float xMotion = (float) (Math.random() * 2 - 1);
            float yMotion = -(float) 0.5;
            float zMotion = (float) (Math.random() * 2 - 1);

            // Crear y lanzar la daga
            SmallCrimsonDagger dagger = new SmallCrimsonDagger(this.level(), centerX, centerY, centerZ, xMotion, yMotion, zMotion, 15.0F, getOwner());
            this.level().addFreshEntity(dagger);
        }
        if (lifeTicks == 0) {
            this.discard();
        }

        if(!level().isClientSide()){
            this.setPos(getOwner().getX(), getOwner().getY()+5, getOwner().getZ());
        }



        lifeTicks--;

    }




    @Override
    public boolean hurt(DamageSource damageSource, float f) {
        if (damageSource.getEntity() instanceof Arrow) return false;
        return super.hurt(damageSource, f);
    }
    private PlayState predicate(software.bernie.geckolib.core.animation.AnimationState animationState) {


        return PlayState.CONTINUE;
    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, "controller",
                0, this::predicate));

    }



    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }



}

