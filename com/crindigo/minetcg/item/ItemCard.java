package com.crindigo.minetcg.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.crindigo.minetcg.card.Card;
import com.crindigo.minetcg.card.CardRegistry;

public class ItemCard extends ItemTCG
{
	public ItemCard(int itemId)
	{
		super(itemId);
		
		this.setMaxStackSize(1);
		this.setUnlocalizedName("tcgCard");
	}

	@Override
	public EnumRarity getRarity(ItemStack itemStack)
	{
		NBTTagCompound comp = itemStack.stackTagCompound;
		if ( comp != null && comp.hasKey("tcgCard") && comp.getCompoundTag("tcgCard").hasKey("Id") ) {
			Card card = CardRegistry.get(comp.getCompoundTag("tcgCard").getString("Id"));
			if ( card != null ) {
				return card.getRarity().mcRarity;
			}
		}
		
		return EnumRarity.common;
	}
}
