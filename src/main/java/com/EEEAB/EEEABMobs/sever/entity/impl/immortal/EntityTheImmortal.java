package com.EEEAB.EEEABMobs.sever.entity.impl.immortal;

import com.EEEAB.EEEABMobs.sever.config.EEConfigHandler;
import com.EEEAB.EEEABMobs.sever.entity.IBoss;
import com.github.alexthe666.citadel.animation.Animation;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

//create by 2023/1/17
public class EntityTheImmortal extends EntityImmortal implements IBoss {
    public EntityTheImmortal(EntityType<? extends EntityImmortal> type, Level level) {
        super(type, level);
        this.xpReward = 30;
    }

    @Override
    protected EEConfigHandler.AttributeConfig getAttributeConfig() {
        return EEConfigHandler.COMMON.MOB.IMMORTAL.THE_IMMORTAL.combatConfig;
    }

    @Override
    protected boolean showBossBloodBars() {
        return EEConfigHandler.COMMON.OTHER.enableShowBloodBars.get();
    }

    @Override
    protected BossEvent.BossBarColor bossBloodBarsColor() {
        return BossEvent.BossBarColor.BLUE;
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[0];
    }

    @Override
    public Animation getDeathAnimation() {
        return null;
    }

    @Override
    public Animation getHurtAnimation() {
        return null;
    }

//    @Override
//    public void registerControllers(AnimationData data) {
//
//    }
//
//    @Override
//    public AnimationFactory getFactory() {
//        return factory;
//    }
}
