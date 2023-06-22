package com.anubis.break_protection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.anubis.break_protection.configs.ItemRefillOptions;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraftforge.registries.ForgeRegistries;

public class CurrentProtArgument implements ArgumentType<String> {
	private static final Collection<String> EXAMPLES = Arrays.asList("minecraft:stone", "minecraft:dirt");

	public CurrentProtArgument() {

	}

	public static CurrentProtArgument create() {
		return new CurrentProtArgument();
	}

	@Override
	public String parse(StringReader reader) throws CommandSyntaxException {
		String text = reader.getRemaining();
		ArrayList<String> copyBlocks = new ArrayList<String>(ClientSetup.protBlocks.stream()
				.map((reference) -> ForgeRegistries.BLOCKS.getResourceKey(reference.get()).get().location().toString())
				.collect(Collectors.toList()));
		if (copyBlocks.contains(text)) {
			reader.setCursor(reader.getTotalLength());
		}
		return text;
	}

	public static String getBlock(CommandContext<CommandSourceStack> ctx, String string) {
		return ctx.getArgument(string, String.class);
	}

	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> p_116128_, SuggestionsBuilder p_116129_) {
		CompletableFuture<Suggestions> completableFuture = p_116128_.getSource() instanceof SharedSuggestionProvider
				? SharedSuggestionProvider.suggest(ItemRefillOptions.protectedBlocks.stream(), p_116129_)
				: Suggestions.empty();
		return completableFuture;
	}

	public Collection<String> getExamples() { return EXAMPLES; }

}
