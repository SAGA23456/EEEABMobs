package com.EEEAB.EEEABMobs.sever.item.util;

import com.EEEAB.EEEABMobs.sever.init.ItemInit;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public abstract class EEToolTipItem extends Item {
    public EEToolTipItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, level, tooltip, flagIn);
        if (!InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 340)) {
            tooltip.add(Component.translatable("key_down.tip").setStyle(ItemInit.TIPS_GREEN));
        } else {
            detailsTooltip(tooltip);
        }
    }

     protected abstract void detailsTooltip(List<Component> tooltip);
}
