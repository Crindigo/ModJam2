package com.crindigo.minetcg.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.DamageSource;

public class EntityUtil
{
	public static boolean isPlayerDamage(DamageSource source)
	{
		if ( source.getDamageType().equals("player") ) {
			return true;
		}
		
		if ( source.getSourceOfDamage() instanceof EntityArrow ) {
			EntityArrow arrow = (EntityArrow) source.getSourceOfDamage();
			if ( arrow.shootingEntity != null && arrow.shootingEntity instanceof EntityPlayer ) {
				return true;
			}
		}
		
		return false;
	}
}
