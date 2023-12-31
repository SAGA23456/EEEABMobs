package com.EEEAB.EEEABMobs.sever.world.datagen;

import com.EEEAB.EEEABMobs.EEEABMobs;
import com.EEEAB.EEEABMobs.sever.util.ModDamageSource;
import com.EEEAB.EEEABMobs.sever.util.ModTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EEDamageTypeProvider extends DamageTypeTagsProvider {
    public EEDamageTypeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> future, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, future, EEEABMobs.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //super.addTags(provider);
        this.tag(DamageTypeTags.IS_PROJECTILE).add(ModDamageSource.SHAMAN_BOMBING);
        this.tag(DamageTypeTags.BYPASSES_ARMOR).add(ModDamageSource.GUARDIAN_LASER);
        this.tag(ModTag.GENERAL_UNRESISTANT_TO).add(DamageTypes.FELL_OUT_OF_WORLD).add(DamageTypes.GENERIC_KILL);
        this.tag(ModTag.MAGIC_UNRESISTANT_TO).add(DamageTypes.MAGIC, DamageTypes.INDIRECT_MAGIC, DamageTypes.SONIC_BOOM, DamageTypes.THORNS);
    }
}
