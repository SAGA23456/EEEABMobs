package com.EEEAB.EEEABMobs.sever.entity.ai.goal.animation;

import com.EEEAB.EEEABMobs.sever.entity.ai.goal.animation.base.AnimationCommonGoal;
import com.EEEAB.EEEABMobs.sever.entity.impl.EEEABMobLibrary;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;

public class AnimationHurtGoal<T extends EEEABMobLibrary & IAnimatedEntity> extends AnimationCommonGoal<T> {
    public AnimationHurtGoal(T entity, boolean stopGoal) {
        super(entity, entity.getHurtAnimation(), stopGoal);
    }
}
