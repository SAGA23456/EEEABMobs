package com.EEEAB.EEEABMobs.sever.item.eye;

import com.EEEAB.EEEABMobs.sever.util.ModTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class ItemAncientTombEye extends ItemFindStructureEye {
    public ItemAncientTombEye() {
        super(new Properties().rarity(Rarity.RARE), ModTag.EYE_OF_ANCIENT_TOMB, 0.047F, 0.146F, 0.179F);
    }

    @Override
    protected ItemStack getEyeItem(Player player, InteractionHand hand) {
        return player.getItemInHand(hand);
    }

}
