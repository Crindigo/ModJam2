package com.crindigo.cardforge;

import java.io.File;

import com.crindigo.cardforge.common.CommonProxy;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(
		modid = "cardforge",
		name = "Cardforge",
		version = "0.1.0"
)
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = false
)
public class Cardforge
{
	@Instance
	public static Cardforge instance;
	
	@SidedProxy(clientSide = "com.crindigo.cardforge.client.ClientProxy", serverSide = "com.crindigo.cardforge.common.CommonProxy")
	public static CommonProxy proxy;
	
	private Configuration config;
	
	public void preInit(FMLPreInitializationEvent event)
	{
		this.loadConfiguration(event.getSuggestedConfigurationFile());
		
	}
	
	public void load(FMLInitializationEvent event)
	{
		
	}
	
	private void loadConfiguration(File file)
	{
		this.config = new Configuration(file);
		this.config.load();
	}
}
