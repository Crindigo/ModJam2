package com.crindigo.minetcg.card;

import java.util.HashMap;
import java.util.Map;

public class CardRegistry
{
	public static CardRegistry instance = new CardRegistry();
	
	private Map<String, Card> cards = new HashMap<String, Card>();
	
	public static void add(Card card)
	{
		if ( card == null ) {
			throw new IllegalArgumentException("card must not be null");
		}
		instance.cards.put(card.getId(), card);
	}
	
	public static Card get(String id)
	{
		return instance.cards.get(id);
	}
}
