package com.crindigo.minetcg;

import java.io.File;

import com.crindigo.minetcg.common.CommonProxy;
import com.crindigo.minetcg.common.Config;
import com.crindigo.minetcg.item.TCGItems;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

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
	}
	
	public void load(FMLInitializationEvent event)
	{
		
	}
}
