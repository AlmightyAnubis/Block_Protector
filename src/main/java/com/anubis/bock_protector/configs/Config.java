package com.anubis.bock_protector.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
	
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final General GENERAL = new General(BUILDER);
	
	public static class General {
		
		public final ForgeConfigSpec.ConfigValue<Integer> breakSpeedSlower;

		
		public General(ForgeConfigSpec.Builder builder) {
			builder.push("General");
			breakSpeedSlower = builder
					.comment("Factor that slows down breaking of protected blocks")
					.translation("config.breakSpeedSlower")
					.define("breakSpeedSlower", 20);

			builder.pop();
		}
	}

	public static final ForgeConfigSpec spec = BUILDER.build();
}

//piglin_loved


