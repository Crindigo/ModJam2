package com.crindigo.minetcg.card;

import com.crindigo.minetcg.card.Card.Dir;
import com.crindigo.minetcg.card.Card.Element;
import com.crindigo.minetcg.card.Card.Rarity;
import com.crindigo.minetcg.card.Card.Stats;


public class CardList
{
	public static void init()
	{
		// should look into defining cards in a json file, perhaps?
		
		CardRegistry.add(new Card("FE-001", "Crindigo", "The author of this mod. Obviously receives the first slot.",
				Rarity.Secret, Dir.ALL, Element.Supreme, new Stats(30, 25, 80)));
						
		CardRegistry.add(new Card("FE-002", "Searge", "Creator of MCP and the ModJam.",
				Rarity.Rare, Dir.ALL_DIAG, Element.Supreme, new Stats(20, 15, 60)));
		
		CardRegistry.add(new Card("FE-003", "Oak Wood", "The original type of wood, the first thing you punch", 
				Rarity.Common, Dir.L | Dir.R, Element.Nature, new Stats(3, 4, 10)));
	}
}
