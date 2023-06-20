package com.anubis.break_protection.configs;

import java.util.ArrayList;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ItemRefillOptions {

	public ItemRefillOptions() {
	}

	//public static Integer breakSpeedSlower = 20;
	public static ArrayList<String> protectedBlocks = new ArrayList<>();



		
	public void save() {
		//Config.GENERAL.breakSpeedSlower.set(breakSpeedSlower);
		Config.GENERAL.protectedBlocks.set(protectedBlocks);
	}
	
	public void load() {
		protectedBlocks = new ArrayList<>(Config.GENERAL.protectedBlocks.get());
	}
	

}
