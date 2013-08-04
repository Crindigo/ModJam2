package com.crindigo.minetcg.item;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

import com.crindigo.minetcg.card.Card;
import com.crindigo.minetcg.card.CardRegistry;

import cpw.mods.fml.common.FMLLog;

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
		if ( card == null ) {
			par3List.add("Invalid Card!");
			return;
		}
		
		par3List.add(card.getId());
		par3List.add(String.format("Level %d (%d)", card.getStats().level, card.getStats().experience));
		par3List.add("Element: " + card.getElement().name);
		par3List.add(String.format("HP: %d, Pow: %d, Def: %d", card.getStats().health,
				card.getStats().power, card.getStats().defense));
	}

	@Override
	public WeightedRandomChestContent getChestGenBase(ChestGenHooks chest,
			Random rnd, WeightedRandomChestContent original)
	{	
		Card card = CardRegistry.getRandom();
		FMLLog.info("[MineTCG] Generated card %s", card.getName());
		return new WeightedRandomChestContent(card.getItemStack(), original.theMinimumChanceToGenerateItem,
				original.theMaximumChanceToGenerateItem, original.itemWeight);
	}
}
