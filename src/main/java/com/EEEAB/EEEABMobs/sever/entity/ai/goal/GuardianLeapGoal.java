package com.EEEAB.EEEABMobs.sever.entity.ai.goal;

import com.EEEAB.EEEABMobs.sever.entity.ai.goal.animation.base.AnimationCommonGoal;
import com.EEEAB.EEEABMobs.sever.entity.impl.namelessguardian.EntityNamelessGuardian;
import com.github.alexthe666.citadel.animation.Animation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class GuardianLeapGoal extends AnimationCommonGoal<EntityNamelessGuardian> {
    public GuardianLeapGoal(EntityNamelessGuardian entity, Animation animation) {
        super(entity, animation);
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
    }

    @Override
    public void tick() {
        LivingEntity target = entity.getTarget();
        int tick = entity.getAnimationTick();
        if (target != null) {
            if (tick < 12) {
                entity.getLookControl().setLookAt(target, 30F, 30F);
                entity.lookAt(target, 30F, 30F);
            } else {
                entity.setYRot(entity.yRotO);
                if (tick == 12) {
                    Vec3 vec3 = findTargetPoint(entity, target);
                    //double x, y, z;
                    //x = target.getX() - entity.getX();
                    //y = target.getY() - entity.getY();
                    //z = target.getZ() - entity.getZ();
                    entity.setDeltaMovement(vec3.x * 0.155D, 1.2 + Mth.clamp(vec3.y * 0.055D, 0D, 10D), vec3.z * 0.155D);
                }
            }
        } else if (tick == 12) {
            float radians = (float) Math.toRadians(entity.yBodyRot + 90);
            entity.setDeltaMovement(3.0 * Math.cos(radians), 1.2, 3.0 * Math.sin(radians));
        }

        if (tick > 12 && entity.onGround()) {
            entity.playAnimation(EntityNamelessGuardian.SMASH_DOWN_ANIMATION);
        }
    }

    public static Vec3 findTargetPoint(Entity attacker, Entity target) {
        Vec3 vec3 = target.position();
        return (new Vec3(vec3.x - attacker.getX(), vec3.y - attacker.getY(), vec3.z - attacker.getZ()));
    }
}
