package com.anubis.break_protection;

import java.util.ArrayList;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder.Reference;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(bus = Bus.FORGE, value = Dist.CLIENT)
public class BlockBreakListener {

	@SubscribeEvent
	public void onBreakBlock(InputEvent.InteractionKeyMappingTriggered event) {
		KeyMapping key = event.getKeyMapping();
		Minecraft mc = Minecraft.getInstance();
		if (key != mc.options.keyAttack) {
			return;
		}
		HitResult hitresult = mc.hitResult;
		if (!(hitresult instanceof BlockHitResult)) {
			return;
		}
		BlockHitResult blockHitResult = (BlockHitResult) hitresult;

		BlockPos pos = blockHitResult.getBlockPos();
		Block block = mc.level.getBlockState(pos).getBlock();

		if (checkBlock(block)) {
			event.setCanceled(true);
			event.setSwingHand(false);
		}

	}
	
	public static boolean checkBlock(Block block) {
		ArrayList<Reference<Block>> copyBlocks = new ArrayList<>();
		copyBlocks.addAll(ClientSetup.protBlocks);
		copyBlocks.removeIf((element) -> {
			if (element.get().equals(block)) {
				return false;
			}
			return true;
		});
		if (!copyBlocks.isEmpty()) {
			return true;
		}
		return false;
	}


}
