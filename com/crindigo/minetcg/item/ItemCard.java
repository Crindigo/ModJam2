package com.crindigo.minetcg.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
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

	@Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
	{
		Card card = Card.createFromItemStack(par1ItemStack);
		par3List.add(String.format("Level %d (%d)", card.getStats().level, card.getStats().experience));
		par3List.add("Element: " + card.getElement().name);
		par3List.add(String.format("HP: %d, Pow: %d, Def: %d", card.getStats().health,
				card.getStats().power, card.getStats().defense));
	}	
}
