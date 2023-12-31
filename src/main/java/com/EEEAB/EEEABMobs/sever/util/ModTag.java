package com.EEEAB.EEEABMobs.sever.util;

import com.EEEAB.EEEABMobs.EEEABMobs;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.levelgen.structure.Structure;

public class ModTag {
    //StructureTags
    public static TagKey<Structure> EYE_OF_ANCIENT_TOMB = registerStructKey("eye_of_ancient_tomb");

    //DamageTypeTags
    public static final TagKey<DamageType> GENERAL_UNRESISTANT_TO = registerDamageTypeKey("general_unresistant_to");
    public static final TagKey<DamageType> MAGIC_UNRESISTANT_TO = registerDamageTypeKey("magic_unresistant_to");



    private static TagKey<DamageType> registerDamageTypeKey(String key) {
        return TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(EEEABMobs.MOD_ID, key));
    }

    private static TagKey<Structure> registerStructKey(String key) {
        return TagKey.create(Registries.STRUCTURE, new ResourceLocation(EEEABMobs.MOD_ID, key));
    }
}
