package com.anubis.break_protection;

import java.util.ArrayList;
import java.util.Optional;

import com.anubis.break_protection.configs.ItemRefillOptions;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.blocks.BlockInput;
import net.minecraft.commands.arguments.blocks.BlockStateArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder.Reference;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientCommandSourceStack;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Bus.FORGE, value = Dist.CLIENT)
public class RegisterCommands {

	@SubscribeEvent
	public static void onClientSetupEvent(RegisterClientCommandsEvent event) {
		Minecraft mc = Minecraft.getInstance();
		CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
		dispatcher.register(Commands.literal("blockprotection").then(
				Commands.literal("add").then(Commands.argument("targetblock", BlockPosArgument.blockPos()).executes((ctx) -> {
					BlockPos pos = BlockPosArgument.getBlockPos(ctx, "targetblock");
					if (ctx.getSource() instanceof ClientCommandSourceStack) {
						Block block = mc.level.getBlockState(pos).getBlock();
						String reg = ForgeRegistries.BLOCKS.getResourceKey(block).get().location().toString();

						ArrayList<Reference<Block>> copyBlocks = new ArrayList<>();

						copyBlocks.addAll(ClientSetup.protBlocks);
						copyBlocks.removeIf((element) -> {
							if (element.get().equals(block)) {
								return false;
							}
							return true;
						});
						if (copyBlocks.isEmpty()) {
							Optional<Reference<Block>> blockOptional = ForgeRegistries.BLOCKS.getDelegate(block);
							if (blockOptional.isPresent()) {
								ClientSetup.protBlocks.add(blockOptional.get());
								ItemRefillOptions.protectedBlocks.add(reg);
							}

						}
					}
					ClientSetup.breakingOptions.save();
					return 1;
				}))).then(Commands.literal("remove")
						.then(Commands.argument("targetblock", BlockPosArgument.blockPos()).executes((ctx) -> {
							BlockPos pos = BlockPosArgument.getBlockPos(ctx, "targetblock");
							if (ctx.getSource() instanceof ClientCommandSourceStack) {
								Block block = mc.level.getBlockState(pos).getBlock();
								String reg = ForgeRegistries.BLOCKS.getResourceKey(block).get().location().toString();

								ArrayList<Reference<Block>> copyBlocks = new ArrayList<>();

								copyBlocks.addAll(ClientSetup.protBlocks);
								copyBlocks.removeIf((element) -> {
									if (element.get().equals(block)) {
										return false;
									}
									return true;
								});
								if (!copyBlocks.isEmpty()) {
									ClientSetup.protBlocks.removeIf((element) -> {
										if (!element.get().equals(block)) {
											return false;
										}
										return true;
									});
									ItemRefillOptions.protectedBlocks.remove(reg);
								}
							}
							ClientSetup.breakingOptions.save();
							return 1;
						})))
				.then(Commands.literal("addName")
						.then(Commands.argument("block", BlockStateArgument.block(event.getBuildContext())).executes((ctx) -> {
							if (ctx.getSource() instanceof ClientCommandSourceStack) {
								BlockInput pos = BlockStateArgument.getBlock(ctx, "block");
								Block block = pos.getState().getBlock();
								String reg = ForgeRegistries.BLOCKS.getResourceKey(block).get().location().toString();

								ArrayList<Reference<Block>> copyBlocks = new ArrayList<>();

								copyBlocks.addAll(ClientSetup.protBlocks);
								copyBlocks.removeIf((element) -> {
									if (element.get().equals(block)) {
										return false;
									}
									return true;
								});
								if (copyBlocks.isEmpty()) {
									Optional<Reference<Block>> blockOptional = ForgeRegistries.BLOCKS.getDelegate(block);
									if (blockOptional.isPresent()) {
										ClientSetup.protBlocks.add(blockOptional.get());
										ItemRefillOptions.protectedBlocks.add(reg);
									}

								}
							}
							ClientSetup.breakingOptions.save();
							return 1;
						})))
				.then(Commands.literal("removeName")
						.then(Commands.argument("block", CurrentProtArgument.create()).executes((ctx) -> {
							if (ctx.getSource() instanceof ClientCommandSourceStack) {
								String reg = CurrentProtArgument.getBlock(ctx, "block");
								try {
									Block block = ForgeRegistries.BLOCKS.getDelegate(new ResourceLocation(reg)).get().get();

									ArrayList<Reference<Block>> copyBlocks = new ArrayList<>();

									copyBlocks.addAll(ClientSetup.protBlocks);
									copyBlocks.removeIf((element) -> {
										if (element.get().equals(block)) {
											return false;
										}
										return true;
									});
									if (!copyBlocks.isEmpty()) {
										ClientSetup.protBlocks.removeIf((element) -> {
											if (!element.get().equals(block)) {
												return false;
											}
											return true;
										});
										ItemRefillOptions.protectedBlocks.remove(reg);
									}
								} catch (Exception e) {
									mc.player.displayClientMessage(Component.translatable(Bock_Protector.MODID + ":notfound")
											.append(Component.literal(String.valueOf(reg))), true);
								}

							}
							ClientSetup.breakingOptions.save();
							return 1;
						}))));

	}
}
