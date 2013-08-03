package com.crindigo.minetcg;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.MinecraftForge;

import com.crindigo.minetcg.card.CardList;
import com.crindigo.minetcg.common.CommonProxy;
import com.crindigo.minetcg.common.Config;
import com.crindigo.minetcg.common.handler.EntityLivingHandler;
import com.crindigo.minetcg.item.TCGItems;
import com.crindigo.minetcg.util.RandomCardChestContent;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(
		modid = "minetcg",
		name = "MineTCG",
		version = "0.1.0"
)
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = false
)
public class MineTCG
{
	@Instance
	public static MineTCG instance;
	
	@SidedProxy(clientSide = "com.crindigo.minetcg.client.ClientProxy", serverSide = "com.crindigo.minetcg.common.CommonProxy")
	public static CommonProxy proxy;
	
	private Configuration config;
	
	public void preInit(FMLPreInitializationEvent event)
	{
		Config.init(event.getSuggestedConfigurationFile());
		TCGItems.init();
		
		GameRegistry.addShapedRecipe(new ItemStack(TCGItems.deck), "wbw", "wpw", "w w", 
				'w', Block.woodSingleSlab, 'b', Block.fenceIron, 'p', Item.paper);
	}
	
	public void load(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new EntityLivingHandler());
		
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, 
				new RandomCardChestContent(null, 0, 0, 0));
		
		CardList.init();
	}
}
