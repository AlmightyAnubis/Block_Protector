package com.anubis.bock_protector.configs;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ItemRefillOptions {

	public ItemRefillOptions() {
	}

	public static Integer breakSpeedSlower = 20;




		
	public void save() {
		Config.GENERAL.breakSpeedSlower.set(breakSpeedSlower);
	}
	
	public void load() {
		breakSpeedSlower = Config.GENERAL.breakSpeedSlower.get();
	}
	

}
