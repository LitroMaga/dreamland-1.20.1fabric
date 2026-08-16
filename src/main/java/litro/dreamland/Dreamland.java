package litro.dreamland;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.ResourceLocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import litro.dreamland.world.gen.structure.DreamlandStructures;

public class Dreamland implements ModInitializer {
	public static final String MOD_ID = "dreamland";

	
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModSounds.registerSounds();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModBlocks.registerModBlocks();

		ModCreativeTabs.registerCreativeTabs();

		ModDimensions.registerDimensions();

		DreamlandStructures.registerStructures();
		ModEvents.registerEvents();

		LOGGER.info("Hello Fabric world!");
	}

	

	public static ResourceLocation id(String path) {
		return new ResourceLocation(MOD_ID, path);
	}
}
