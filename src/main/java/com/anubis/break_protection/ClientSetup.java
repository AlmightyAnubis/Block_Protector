package com.anubis.break_protection;

import java.util.ArrayList;
import java.util.Optional;

import com.anubis.break_protection.configs.ItemRefillOptions;

import net.minecraft.core.Holder.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = Bock_Protector.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

	public static ItemRefillOptions breakingOptions;
	public static ArrayList<Reference<Block>> protBlocks;
	
	@SubscribeEvent
	public static void onClientSetupEvent(FMLClientSetupEvent event) {			
		breakingOptions = new ItemRefillOptions();
		breakingOptions.load();
		protBlocks = new ArrayList<>();
		ArrayList<String> list = ItemRefillOptions.protectedBlocks;
		for (String string : list) {
			Optional<Reference<Block>> blockOptional = ForgeRegistries.BLOCKS.getDelegate(new ResourceLocation(string));
			if(blockOptional.isPresent()){
				protBlocks.add(blockOptional.get());
			}
		}
		MinecraftForge.EVENT_BUS.register(new BlockBreakListener());	
		MinecraftForge.EVENT_BUS.register(new RegisterCommands());
	}



}
