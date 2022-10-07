package com.anubis.bock_protector;

import com.anubis.bock_protector.configs.ItemRefillOptions;

import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(bus = Bus.FORGE, value = Dist.CLIENT)
public class BlockBreakListener {

	@SubscribeEvent
	public void onBreakBlock(BreakSpeed event) {
		// System.out.println("Client BreakSpeed");
		if (event.getState().getBlock() == Blocks.BUDDING_AMETHYST) {
			float speed = event.getOriginalSpeed();
			event.setNewSpeed(speed / ((float) ItemRefillOptions.breakSpeedSlower));
			// event.setCanceled(true);
		}
	}

}
