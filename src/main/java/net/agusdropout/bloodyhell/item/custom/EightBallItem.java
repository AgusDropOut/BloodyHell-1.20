package net.agusdropout.bloodyhell.item.custom;

import net.agusdropout.bloodyhell.client.ClientMagicData;
import net.agusdropout.bloodyhell.entity.ModEntityTypes;
import net.agusdropout.bloodyhell.entity.custom.BlasphemousArmEntity;
import net.agusdropout.bloodyhell.entity.effects.EntityCameraShake;
import net.agusdropout.bloodyhell.entity.effects.EntityFallingBlock;
import net.agusdropout.bloodyhell.entity.projectile.BlasphemousSmallWhirlwindEntity;
import net.agusdropout.bloodyhell.entity.projectile.BlasphemousWhirlwindEntity;
import net.agusdropout.bloodyhell.particle.ModParticles;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;



public class EightBallItem extends Item {
    public EightBallItem(Properties properties) {
        super(properties);
    }


    @Override
    public void onUseTick(Level p_41428_, LivingEntity player, ItemStack p_41430_, int p_41431_) {
        super.onUseTick(p_41428_, player, p_41430_, p_41431_);
        if (Math.random() < 0.5) {
                     double offsetX = Math.random() - 0.5;
                     double offsetZ = Math.random() - 0.5;
            p_41428_.addParticle(
                             ModParticles.MAGIC_LINE_PARTICLE.get(),
                             player.getX() + offsetX, player.getY(), player.getZ() + offsetZ,
                             0, 0.05, 0
                     );
                 }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        //if (level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
        //    EntityCameraShake cameraShake = new EntityCameraShake(level, player.position(), 10, 1, 20, 20);
        //    level.addFreshEntity(cameraShake);
        //    outputRandomNumber(player);
        //    player.getCooldowns().addCooldown(this, 20);
        //    level.addParticle(ModParticles.STAR_EXPLOSION_PARTICLE.get(), player.getX(), player.getY(), player.getZ(), 200, 0, 0);
        //}
        //LivingEntity entity = new BlasphemousArmEntity(ModEntityTypes.BLASPHEMOUS_ARM_ENTITY.get(), level, player);
        //level.addFreshEntity(entity);
        //entity.setPos(player.getX(), player.getY(), player.getZ());

        level.addFreshEntity(new BlasphemousWhirlwindEntity(level, player.getX(), player.getY(), player.getZ(), player));




        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()){
            components.add(Component.literal("Right click to get random number!").withStyle(ChatFormatting.RED));
        } else {
            components.add(Component.literal("Press shift for mor info!").withStyle(ChatFormatting.DARK_RED));
        }


        super.appendHoverText(stack, level, components, flag);
    }

    private void outputRandomNumber(Player player){
        player.sendSystemMessage(Component.literal("Your number is:" + getRandomNumber()));
    }
    private int getRandomNumber() {
        return RandomSource.createNewThreadLocalInstance().nextInt(10);
    }
}
