package com.crindigo.minetcg.common.handler;

import net.minecraft.entity.monster.IMob;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import com.crindigo.minetcg.card.CardRegistry;
import com.crindigo.minetcg.common.Config;
import com.crindigo.minetcg.item.TCGItems;
import com.crindigo.minetcg.util.EntityUtil;

public class EntityLivingHandler
{
	@ForgeSubscribe
	public void onEntityLivingDeath(LivingDeathEvent event)
	{
		if ( event.entity instanceof IMob && EntityUtil.isPlayerDamage(event.source) ) {
			double chance = Math.random();
			if ( chance <= Config.CARD_DROP_CHANCE ) {
				ItemStack cardStack = CardRegistry.get("combustionEngine").getItemStack();
				event.entity.entityDropItem(cardStack, 0.0f);
			}
		}
	}
}
