package com.EEEAB.EEEABMobs.sever.entity.ai.control;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.BodyRotationControl;

public class EEBodyRotationControl extends BodyRotationControl {
    private static final float MAX_ROTATE = 75;
    private final Mob mob;
    private int rotateTime;
    private float targetYawHead;

    public EEBodyRotationControl(Mob mob) {
        super(mob);
        this.mob = mob;
    }

    @Override
    public void clientTick() {
        double dx = this.mob.getX() - this.mob.xo;
        double dz = this.mob.getZ() - this.mob.zo;
        //double d0 = this.mob.getX() - this.mob.xo;
        //double d1 = this.mob.getZ() - this.mob.zo;
        double dsq = dx * dx + dz * dz;
        //实体正在移动
        if (dsq > (double) 2.5000003E-7F) {
            double moveAngle = (float) Mth.atan2(dz, dx) * (180 / (float) Math.PI) - 90;
            mob.yBodyRot += Mth.wrapDegrees(moveAngle - mob.yBodyRot) * 0.6F;
            this.rotateHeadIfNecessary();
            this.targetYawHead = this.mob.yHeadRot;
            this.rotateTime = 0;
            //this.mob.yBodyRot = this.mob.getYRot();
            //this.targetYawHead = this.mob.yHeadRot;
            //this.rotateTime = 0;
        } else {
            if (this.notCarryingMobPassengers()) {
                float limit = MAX_ROTATE;
                if (Math.abs(this.mob.yHeadRot - this.targetYawHead) > 15) {
                    this.rotateTime = 0;
                    this.targetYawHead = this.mob.yHeadRot;
                    this.rotateBodyIfNecessary();
                } else {
                    rotateTime++;
                    final int speed = 10;
                    if (rotateTime > speed) {
                        limit = Math.max(1 - (rotateTime - speed) / (float) speed, 0) * MAX_ROTATE;
                    }
                }
                mob.yBodyRot = approach(mob.yHeadRot, mob.yBodyRot, limit);
            }
        }
    }

    public static float approach(float target, float current, float limit) {
        float delta = Mth.wrapDegrees(current - target);
        if (delta < -limit) {
            delta = -limit;
        } else if (delta >= limit) {
            delta = limit;
        }
        return target + delta * 0.55F;
    }

    private boolean notCarryingMobPassengers() {
        return mob.getPassengers().isEmpty() || !(mob.getPassengers().get(0) instanceof Mob);
    }

    private boolean isMoving() {
        double d0 = this.mob.getX() - this.mob.xo;
        double d1 = this.mob.getZ() - this.mob.zo;
        return d0 * d0 + d1 * d1 > (double) 2.5000003E-7F;
    }

    private void rotateBodyIfNecessary() {
        this.mob.yBodyRot = Mth.rotateIfNecessary(this.mob.yBodyRot, this.mob.yHeadRot, (float) this.mob.getMaxHeadYRot());
    }

    private void rotateHeadIfNecessary() {
        this.mob.yHeadRot = Mth.rotateIfNecessary(this.mob.yHeadRot, this.mob.yBodyRot, (float) this.mob.getMaxHeadYRot());
    }
}
