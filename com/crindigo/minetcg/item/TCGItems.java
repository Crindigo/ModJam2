package com.crindigo.minetcg.item;

import com.crindigo.minetcg.common.Config;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class TCGItems
{
	public static ItemCard card;
	public static ItemDeck deck;
	
	public static void init()
	{
		card = new ItemCard(Config.ITEMID_CARD);
		deck = new ItemDeck(Config.ITEMID_DECK);
		
		GameRegistry.registerItem(card, "tcgCard");
		GameRegistry.registerItem(deck, "tcgDeck");
		
		LanguageRegistry.addName(card, "TCG Card");
		LanguageRegistry.addName(deck, "TCG Deck");
	}
}
