package com.crindigo.minetcg.util;

import java.util.Random;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;

public class RandomCardChestContent extends WeightedRandomChestContent
{

	public RandomCardChestContent(ItemStack par1ItemStack, int par2, int par3,
			int par4)
	{
		super(par1ItemStack, par2, par3, par4);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ItemStack[] generateChestContent(Random random,
			IInventory newInventory)
	{
		// TODO Auto-generated method stub
		return super.generateChestContent(random, newInventory);
	}
}
