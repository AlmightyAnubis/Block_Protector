package com.anubis.break_protection.configs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class Config {

	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final General GENERAL = new General(BUILDER);

	public static class General {

		public final ForgeConfigSpec.ConfigValue<List<? extends String>> protectedBlocks;

		public General(ForgeConfigSpec.Builder builder) {
			builder.push("General");

			ArrayList<String> protBlockDefault = new ArrayList<String>();
			IForgeRegistry<Block> registry = ForgeRegistries.BLOCKS;
			Block block = Blocks.BUDDING_AMETHYST;
			Optional<ResourceKey<Block>> key = registry.getResourceKey(block);
			protBlockDefault.add(key.get().location().toString());
			protectedBlocks = builder.comment("List of protected blocks").translation("config.protectedBlocks")
					.define("protectedBlocks", protBlockDefault);

			builder.pop();
		}
	}

	public static final ForgeConfigSpec spec = BUILDER.build();
}

//piglin_loved
