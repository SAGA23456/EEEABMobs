package com.EEEAB.EEEABMobs.sever.ability.abilities;

import com.EEEAB.EEEABMobs.sever.ability.Ability;
import com.EEEAB.EEEABMobs.sever.ability.AbilityPeriod;
import com.EEEAB.EEEABMobs.sever.ability.AbilityType;
import com.EEEAB.EEEABMobs.sever.entity.impl.projectile.EntityShamanBomb;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

public class ImmortalStaffAbility extends Ability<Player> {

    public ImmortalStaffAbility(AbilityType<Player, ? extends Ability<Player>> abilityType, Player user) {
        super(abilityType, user, new AbilityPeriod[]{
                new AbilityPeriod.AbilityPeriodInstant(AbilityPeriod.AbilityPeriodType.ACTIVE)
        }, 0);
    }

    @Override
    public void start() {
        super.start();
        Player user = this.getUser();
        if (!user.level().isClientSide) {
            ServerLevel serverLevel = (ServerLevel) user.level();
            EntityShamanBomb shamanBomb = new EntityShamanBomb(user.level(), user, user.getLookAngle().x, user.getLookAngle().y, user.getLookAngle().z);
            shamanBomb.setOwner(user);
            shamanBomb.setIsPlayer(true);
            shamanBomb.absMoveTo(shamanBomb.getX(), shamanBomb.getY(0.5) + 0.5, shamanBomb.getZ());
            serverLevel.addFreshEntity(shamanBomb);
        }
    }
}
