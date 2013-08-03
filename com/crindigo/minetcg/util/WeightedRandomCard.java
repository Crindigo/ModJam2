package com.crindigo.minetcg.util;

import com.crindigo.minetcg.card.Card;

import net.minecraft.util.WeightedRandomItem;

public class WeightedRandomCard extends WeightedRandomItem
{
	public String cardId;
	
	public WeightedRandomCard(Card card)
	{
		this(card.getRarity().weight);
		this.cardId = card.getId();
	}
	
	public WeightedRandomCard(int par1)
	{
		super(par1);
	}
}
