package com.crindigo.minetcg.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.util.WeightedRandom;

import com.crindigo.minetcg.util.WeightedRandomCard;

import cpw.mods.fml.common.FMLLog;

public class CardRegistry
{
	public static CardRegistry instance = new CardRegistry();
	
	private Map<String, Card> cards = new HashMap<String, Card>();
	private List<WeightedRandomCard> cardRandom = new ArrayList<WeightedRandomCard>();
	
	private Random random = new Random();
	
	public static void add(Card card)
	{
		if ( card == null ) {
			throw new IllegalArgumentException("card must not be null");
		}
		if ( instance.cards.containsKey(card.getId()) ) {
			Card existing = instance.cards.get(card.getId());
			FMLLog.warning("[MineTCG] Card %s \"%s\" is being overwritten by \"%s\"", 
					existing.getId(), existing.getName(), card.getName());
		}
		instance.cards.put(card.getId(), card);
		instance.cardRandom.add(new WeightedRandomCard(card));
	}
	
	public static Card get(String id)
	{
		return instance.cards.get(id);
	}
	
	public static Card getRandom()
	{
		WeightedRandomCard rndCard = 
				(WeightedRandomCard) WeightedRandom.getRandomItem(instance.random, instance.cardRandom);
		return get(rndCard.cardId);
	}
}
