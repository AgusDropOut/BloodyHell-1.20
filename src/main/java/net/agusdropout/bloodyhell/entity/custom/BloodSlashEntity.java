package net.agusdropout.bloodyhell.entity.custom;

import net.agusdropout.bloodyhell.datagen.ModTags;
import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.CatVariant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import javax.swing.plaf.SeparatorUI;
import java.util.List;
import java.util.UUID;

public class BloodSlashEntity extends Entity implements TraceableEntity, GeoEntity {

    private AnimatableInstanceCache factory = new SingletonAnimatableInstanceCache(this);
    private int lifeTicks = 15;
    public static final float DEFAULT_DAMAGE = 30.0F;
    private float damage = DEFAULT_DAMAGE;
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;
    private static final EntityDataAccessor<Float> YAW = SynchedEntityData.defineId(BloodSlashEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> PITCH = SynchedEntityData.defineId(BloodSlashEntity.class, EntityDataSerializers.FLOAT);
    private float yaw;
    private float pitch;

    public BloodSlashEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(YAW, 0F);
        this.entityData.define(PITCH, 0F);
    }

    // Constructor modificado para aceptar yaw
    public BloodSlashEntity(Level level, double d, double e, double f, float damage, LivingEntity livingEntity, float yaw, float pitch) {
        this(ModEntityTypes.BLOOD_SLASH_ENTITY.get(), level);
        this.damage = damage;
        this.setOwner(livingEntity);
        this.setPos(d, e, f);
        this.setNoGravity(true);
        if(!this.level().isClientSide()) {
            this.pitch = pitch;
            this.setPitch(pitch);
            this.yaw = yaw;
            this.setYaw(yaw);
        } else {
            this.yaw = entityData.get(YAW);
            this.pitch = entityData.get(PITCH);
        }
        level().playLocalSound(d, e, f, SoundEvents.AMETHYST_BLOCK_PLACE, SoundSource.BLOCKS, 1.0f, random.nextFloat() * 0.2f + 0.85f, false);

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
    public void tick() {
        super.tick();
        if(this.level().isClientSide()) {
            this.yaw = this.entityData.get(YAW);
            this.pitch = this.entityData.get(PITCH);
        }

        this.setYRot(yaw);





        moveEntityForward();

        List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.2, 0.0, 0.2));
        for (LivingEntity livingEntity : list) {
            this.dealDamageTo(livingEntity);
        }

        if(lifeTicks == 0){
            this.discard();
        }
        lifeTicks--;
    }

    private void moveEntityForward() {
        if (this.owner != null) {
            float radianYaw = (float) Math.toRadians(this.yaw);

            double offsetX = Math.sin(radianYaw) * 0.3;
            double offsetZ = Math.cos(radianYaw) * 0.3;

            this.setPos(this.getX() + offsetX, this.getY(), this.getZ() + offsetZ);
        }
    }

    // Método para aplicar daño a los enemigos cercanos
    private void dealDamageTo(LivingEntity livingEntity) {
        LivingEntity livingEntity2 = this.getOwner();
        if (livingEntity instanceof Player player && livingEntity2 != null && player.getUUID().equals(livingEntity2.getUUID())) {
            return;
        } else if (!livingEntity.isAlive() || livingEntity.isInvulnerable() || livingEntity == livingEntity2) {
            return;
        }
        if (livingEntity2 == null) {
            livingEntity.hurt(this.damageSources().magic(), this.damage);
        } else {
            if (livingEntity2.isAlliedTo(livingEntity)) return;
            livingEntity.hurt(this.damageSources().indirectMagic(this, livingEntity2), this.damage);
        }
    }

    @Override
    public boolean hurt(DamageSource damageSource, float f) {
        if (damageSource.getEntity() instanceof Arrow) return false;
        return super.hurt(damageSource, f);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        if (yaw != 0) {
            this.setYaw(yaw);
        }
        if (pitch != 0) {
            this.setPitch(pitch);
        }
    }
    public void setYaw(float yaw) {
        this.entityData.set(YAW, this.yaw);
    }
    public void setPitch(float pitch) {
        this.entityData.set(PITCH, this.pitch);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putFloat("yaw", this.yaw);
    }

    // Controlador de animaciones (se puede ajustar según sea necesario)
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

    // Método para generar partículas en un círculo expansivo
    public void spawnExpandingCircleParticles(Level level, Entity entity, double radius, int particleCount, double speed) {
        for (int i = 0; i < particleCount; i++) {
            double angle = 2 * Math.PI * i / particleCount; // Ángulo para cada partícula
            double offsetX = radius * Math.cos(angle);
            double offsetZ = radius * Math.sin(angle);

            double particleX = entity.getX() + offsetX;
            double particleY = entity.getY() + 1.0; // Un poco arriba de la entidad
            double particleZ = entity.getZ() + offsetZ;

            // Velocidad radial (dirigida hacia afuera)
            double velocityX = speed * Math.cos(angle);
            double velocityZ = speed * Math.sin(angle);

            level.addParticle(ParticleTypes.FIREWORK, particleX, particleY-1, particleZ, velocityX, 0, velocityZ);
        }
    }

    // Método para generar partículas de impacto
    public void spawnImpactParticles(Level level, Entity entity) {
        BlockState blockState = Blocks.DIRT.defaultBlockState(); // Simula partículas de tierra

        for (int i = 0; i < 30; i++) {
            double offsetX = (random.nextDouble() - 0.5) * 1.5;
            double offsetZ = (random.nextDouble() - 0.5) * 1.5;
            double velocityY = random.nextDouble() * 0.5 + 0.2;

            level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockState),
                    entity.getX() + offsetX, entity.getY(), entity.getZ() + offsetZ,
                    0, velocityY, 0);
        }
    }

}

