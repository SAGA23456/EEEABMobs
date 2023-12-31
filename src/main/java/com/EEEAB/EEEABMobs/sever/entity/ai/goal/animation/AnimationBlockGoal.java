package com.EEEAB.EEEABMobs.sever.entity.ai.goal.animation;

import com.EEEAB.EEEABMobs.sever.entity.ai.goal.animation.base.AnimationCommonGoal;
import com.EEEAB.EEEABMobs.sever.entity.impl.EEEABMobLibrary;
import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;

public class AnimationBlockGoal<T extends EEEABMobLibrary & IAnimatedEntity> extends AnimationCommonGoal<T> {
    public AnimationBlockGoal(T entity, Animation animation) {
        super(entity, animation);
    }

    @Override
    public void tick() {
        super.tick();
        if (entity != null && entity.blockEntity != null) {
            entity.lookAt(entity.blockEntity, 90F, 90F);
            entity.getLookControl().setLookAt(entity.blockEntity.getX(), (entity.blockEntity.getBoundingBox().minY + entity.blockEntity.getBoundingBox().maxY) / 2, entity.blockEntity.getZ(), 200F, 30F);
        }
    }
}
