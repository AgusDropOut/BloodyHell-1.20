package net.agusdropout.bloodyhell.item.custom.OnlyAppendHoverText;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrimsonWardRing  extends Item {
    public CrimsonWardRing(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        components.add(Component.literal("Prevents you from bleeding").withStyle(ChatFormatting.RED));
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
