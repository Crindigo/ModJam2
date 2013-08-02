package com.crindigo.minetcg.common;

import java.io.File;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class Config
{
	private static Configuration cfg;
	
	public static int ITEMID_CARD = 23801;
	public static int ITEMID_DECK = 23802;
	
	public static int BLOCKID_GAMETABLE = 2380;
	
	public static double CARD_DROP_CHANCE = 0.05;
	
	public static void init(File configFile)
	{
		cfg = new Configuration(configFile);
		
		ITEMID_CARD = cfg.getItem("card", ITEMID_CARD).getInt();
		ITEMID_DECK = cfg.getItem("deck", ITEMID_DECK).getInt();
		BLOCKID_GAMETABLE = cfg.getBlock("gameTable", BLOCKID_GAMETABLE).getInt();
		
		CARD_DROP_CHANCE = cfg.get("general", "cardDropChance", CARD_DROP_CHANCE).getDouble(CARD_DROP_CHANCE);
	}
}
