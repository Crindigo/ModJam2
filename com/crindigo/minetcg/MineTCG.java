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
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;
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
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Config.init(event.getSuggestedConfigurationFile());
		TCGItems.init();
		
		
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new EntityLivingHandler());
		
		GameRegistry.addShapedRecipe(new ItemStack(TCGItems.deck), "wbw", "wpw", "w w", 
				'w', Block.woodSingleSlab, 'b', Block.fenceIron, 'p', Item.paper);
		
		WeightedRandomChestContent content = new WeightedRandomChestContent(new ItemStack(TCGItems.card), 1, 1, 5);
		ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, content);
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, content);

		CardList.init();
	}
	
	@EventHandler
	public void handleIMC(IMCEvent event)
	{
		// perhaps allow other mods to register cards (images could be a problem)
		
	}
}
