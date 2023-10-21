package com.EEEAB.EEEABMobs.sever.world.datagen;

import com.EEEAB.EEEABMobs.EEEABMobs;
import com.EEEAB.EEEABMobs.sever.init.BlockInit;
import com.EEEAB.EEEABMobs.sever.init.ItemInit;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class EEItemModelProvider extends ItemModelProvider {
    public EEItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, EEEABMobs.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
    }


    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(EEEABMobs.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(EEEABMobs.MOD_ID, "item/" + item.getId().getPath()));
    }

}
