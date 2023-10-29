package com.EEEAB.EEEABMobs.sever.entity.impl.immortal;

import com.EEEAB.EEEABMobs.sever.entity.ai.goal.EELookAtGoal;
import com.EEEAB.EEEABMobs.sever.config.EEConfigHandler;
import com.EEEAB.EEEABMobs.sever.entity.IEntity;
import com.EEEAB.EEEABMobs.sever.entity.ai.goal.animation.*;
import com.EEEAB.EEEABMobs.sever.entity.ai.goal.animation.base.AnimationCommonGoal;
import com.EEEAB.EEEABMobs.sever.init.ItemInit;
import com.EEEAB.EEEABMobs.sever.init.SoundInit;
import com.EEEAB.EEEABMobs.sever.entity.util.ModEntityUtils;
import com.EEEAB.EEEABMobs.sever.util.ModDamageSource;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import com.github.alexthe666.citadel.animation.Animation;

import javax.annotation.Nullable;
import java.util.Arrays;

//基本AI完成
public class EntityImmortalSkeleton extends AbstractImmortalSkeleton implements IEntity {
    public EntityImmortalSkeleton(EntityType<? extends EntityImmortalSkeleton> type, Level level) {
        super(type, level);
        this.xpReward = 5;
        this.dropAfterDeathAnim = true;
        this.active = true;
    }

    @Override
    protected EEConfigHandler.AttributeConfig getAttributeConfig() {
        return EEConfigHandler.COMMON.MOB.IMMORTAL.SKELETON.combatConfig;
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        super.registerGoals();
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource randomSource, DifficultyInstance difficultyIn) {
        if (randomSource.nextInt(10) <= difficultyIn.getDifficulty().getId()) {
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
        } else {
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ItemInit.IMMORTAL_AXE.get()));
        }
    }

    @Nullable
    @Override
    //在初始生成时调用
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        RandomSource randomsource = worldIn.getRandom();
        this.populateDefaultEquipmentSlots(randomsource, difficultyIn);
        this.populateDefaultEquipmentEnchantments(randomsource, difficultyIn);
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.88f;
    }

    public static AttributeSupplier.Builder setAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0D).
                add(Attributes.MOVEMENT_SPEED, 0.3D).
                add(Attributes.FOLLOW_RANGE, 16.0D).
                add(Attributes.ATTACK_DAMAGE, 3.0D).
                add(Attributes.ATTACK_KNOCKBACK, 0.1D).
                add(Attributes.KNOCKBACK_RESISTANCE, 0.1D);
    }

}
