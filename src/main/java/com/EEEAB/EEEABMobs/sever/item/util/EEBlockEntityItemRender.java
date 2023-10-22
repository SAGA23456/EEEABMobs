package com.EEEAB.EEEABMobs.sever.item.util;

import com.EEEAB.EEEABMobs.EEEABMobs;
import com.EEEAB.EEEABMobs.client.render.util.EEItemStackRenderProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class EEBlockEntityItemRender extends BlockItem {

    public EEBlockEntityItemRender(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) EEEABMobs.PROXY.getISTERProperties());
    }
}
