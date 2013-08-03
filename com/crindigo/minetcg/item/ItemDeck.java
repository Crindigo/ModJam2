package com.crindigo.minetcg.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDeck extends ItemTCG
{
	public ItemDeck(int itemId)
	{
		super(itemId);
		
		this.setUnlocalizedName("tcgDeck");
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10)
	{
		// par4/5/6 = x/y/z, 7 = orientation?
		
		// open the deck gui once i figure out how lol
		return true;
	}
	
	
}
