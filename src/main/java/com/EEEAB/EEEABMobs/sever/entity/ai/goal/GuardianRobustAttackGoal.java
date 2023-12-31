package com.EEEAB.EEEABMobs.sever.entity.ai.goal;

import com.EEEAB.EEEABMobs.sever.util.ModDamageSource;
import com.EEEAB.EEEABMobs.sever.entity.ai.goal.animation.base.AnimationCommonGoal;
import com.EEEAB.EEEABMobs.sever.entity.impl.effect.EntityCameraShake;
import com.EEEAB.EEEABMobs.sever.entity.impl.namelessguardian.EntityNamelessGuardian;
import com.EEEAB.EEEABMobs.sever.init.SoundInit;
import com.EEEAB.EEEABMobs.sever.entity.util.ModEntityUtils;
import com.github.alexthe666.citadel.animation.Animation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;

import java.util.EnumSet;
import java.util.List;

public class GuardianRobustAttackGoal extends AnimationCommonGoal<EntityNamelessGuardian> {

    public GuardianRobustAttackGoal(EntityNamelessGuardian entity, Animation animation) {
        super(entity, animation);
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP, Flag.LOOK));
    }

    @Override
    public void tick() {
        int tick = entity.getAnimationTick();
        LivingEntity target = entity.getTarget();
        entity.setDeltaMovement(0, entity.onGround() ? 0 : entity.getDeltaMovement().y(), 0);
        if (tick < 32 && target != null) {
            //entity.getLookControl().setLookAt(target, 30F, 30F);
            entity.lookAt(target,30F,30F);
        } else {
            entity.setYRot(entity.yRotO);
        }
        if (tick == 1) entity.playSound(SoundInit.NAMELESS_GUARDIAN_CREAK.get(), 1.0F, 1.0F);
        else if (tick == 30) entity.playSound(SoundInit.NAMELESS_GUARDIAN_WHOOSH.get(), 2F, 1.2F);
        else if (tick == 35) {
            final float attackArc = 40F;
            final float range = 4.6F;
            List<LivingEntity> entities = entity.getNearByLivingEntities(range, range - 0.6F, range, range);
            for (LivingEntity livingEntity : entities) {
                float entityRelativeAngle = ModEntityUtils.getTargetRelativeAngle(entity, livingEntity);
                float entityHitDistance = (float) Math.sqrt((livingEntity.getZ() - entity.getZ()) * (livingEntity.getZ() - entity.getZ()) + (livingEntity.getX() - entity.getX()) * (livingEntity.getX() - entity.getX())) - livingEntity.getBbWidth() / 2F;
                if ((entityHitDistance <= range && (entityRelativeAngle <= attackArc / 2F && entityRelativeAngle >= -attackArc / 2F) || (entityRelativeAngle >= 360 - attackArc / 2F || entityRelativeAngle <= -360 + attackArc / 2F))) {
                    entity.guardianHurtTarget(ModDamageSource.guardianRobustAttack(entity), entity, livingEntity, 0.1F, 1.0F, 2.5F, true, true);
                }
            }
        } else if (tick == 36) {
            entity.playSound(SoundInit.GIANT_AXE_HIT.get(), 2F, 0.2F);
            EntityCameraShake.cameraShake(entity.level(), entity.position(), 10, 0.3F, 0, 10);
        } else if (tick == 45) {
            for (int i = 2; i <= 12; i++) {
                entity.robustAttack(i, 0.1F, 1.0F, 1.25F, false);
            }
        } else if (tick == 46) {
            entity.playSound(SoundEvents.GENERIC_EXPLODE, 1.5F, 1F + entity.getRandom().nextFloat() * 0.1F);
            EntityCameraShake.cameraShake(entity.level(), entity.position(), 30, 0.2F, 10, 10);
        } else if (tick == 72) {
            if (entity.getMadnessTick() == 0 && entity.isPowered()) {
                entity.setExecuteWeak(true);
                entity.playAnimation(EntityNamelessGuardian.WEAK_ANIMATION_1);
            }
        }
    }
}
