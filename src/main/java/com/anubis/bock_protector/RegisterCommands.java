package com.anubis.bock_protector;

import com.anubis.bock_protector.configs.ItemRefillOptions;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(bus = Bus.FORGE, value = Dist.CLIENT)
public class RegisterCommands {

	
	@SubscribeEvent
	public static void onClientSetupEvent(RegisterClientCommandsEvent event) {
		CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
		dispatcher.register(Commands.literal("blockprotection")
				.then(Commands.argument("seconds", IntegerArgumentType.integer(1, 1000000)).executes((ctx) -> {
					ItemRefillOptions.breakSpeedSlower = IntegerArgumentType.getInteger(ctx, "seconds");
					ClientSetup.breakingOptions.save();
					return 1;
				})));

	}
}
