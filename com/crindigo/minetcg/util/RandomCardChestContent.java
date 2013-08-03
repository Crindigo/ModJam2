package com.crindigo.minetcg.util;

import java.util.Random;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;

import com.crindigo.minetcg.card.Card;
import com.crindigo.minetcg.card.CardRegistry;

public class RandomCardChestContent extends WeightedRandomChestContent
{
	public RandomCardChestContent(ItemStack par1ItemStack, int par2, int par3,
			int par4)
	{
		super(par1ItemStack, par2, par3, par4);
	}

	@Override
	protected ItemStack[] generateChestContent(Random random,
			IInventory newInventory)
	{
		Card rand = CardRegistry.getRandom();
		ItemStack cardStack = rand.getItemStack();
		return new ItemStack[] {cardStack};
	}
}
