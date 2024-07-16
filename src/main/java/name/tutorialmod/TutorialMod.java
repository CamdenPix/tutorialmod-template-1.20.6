package name.tutorialmod;

import name.tutorialmod.block.ModBlocks;
import name.tutorialmod.client.keybinding.TutorialModClientEntrypoint;
import name.tutorialmod.item.ModItemGroups;
import name.tutorialmod.item.ModItems;
import name.tutorialmod.networking.ModMessages;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final TutorialModClientEntrypoint CLIENT_ENTRYPOINT = new TutorialModClientEntrypoint();

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		CLIENT_ENTRYPOINT.onInitializeClient();
		ModMessages.registerC2SPackets();

		FuelRegistry.INSTANCE.add(ModItems.COAL2, 12800);
	}
}