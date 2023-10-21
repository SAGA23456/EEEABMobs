package com.EEEAB.EEEABMobs.sever.world.datagen;

import com.EEEAB.EEEABMobs.EEEABMobs;
import com.EEEAB.EEEABMobs.sever.init.BlockInit;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

//方块物品
public class EEBlockStateProvider extends BlockStateProvider {
    public EEBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, EEEABMobs.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(),cubeAll(blockRegistryObject.get()));
    }
}
