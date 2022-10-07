package com.anubis.bock_protector;

import com.anubis.bock_protector.configs.ItemRefillOptions;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Bock_Protector.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

	public static ItemRefillOptions breakingOptions;
	
	@SubscribeEvent
	public static void onClientSetupEvent(FMLClientSetupEvent event) {			
		breakingOptions = new ItemRefillOptions();
		breakingOptions.load();
		MinecraftForge.EVENT_BUS.register(new BlockBreakListener());	
		MinecraftForge.EVENT_BUS.register(new RegisterCommands());
	}



}
