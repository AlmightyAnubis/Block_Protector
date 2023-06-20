package com.anubis.break_protection;

import org.slf4j.Logger;

import com.anubis.break_protection.configs.Config;
import com.mojang.logging.LogUtils;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Bock_Protector.MODID)
public class Bock_Protector
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "bock_protector";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    
    public static Bock_Protector instance;
    
    
    
    public Bock_Protector()
    {
    	ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.spec);
  		// Register ourselves for server and other game events we are interested in
  		MinecraftForge.EVENT_BUS.register(this);
  		instance = this;
  	}
    
}
