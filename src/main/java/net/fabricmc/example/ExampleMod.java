package net.fabricmc.example;


import jdk.jfr.Category;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.GUI.AutoFisching;
import net.fabricmc.example.GUI.HackMenuScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
	public static boolean autoFishingEnabled;
	public static boolean flyingEnabled;
	public static boolean noFallDamage;
	private HackMenuScreen GUIScreen;
	private static ExampleMod instance;
	private AutoFisching autofisching;
	private Category category;

	public final Category getCategory()
	{
		return category;
	}
	public AutoFisching getAutoFisching() {

			return autofisching;

	}
	protected final void setCategory(Category category)
	{
		this.category = category;
	}

	public static ExampleMod getInstance() {
		return instance;
	}
	@Override
	public void onInitialize() {

			MinecraftClient client = MinecraftClient.getInstance();
			ClientTickEvents.START_CLIENT_TICK.register(client1 -> NoFallDamage.tick());
			ClientTickEvents.START_CLIENT_TICK.register(client1 -> Flyhack.tick(client));
			LOGGER.info("Für eine bessere Zukunft");
		}
	}

