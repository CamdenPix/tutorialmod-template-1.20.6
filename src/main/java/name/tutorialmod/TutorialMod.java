package name.tutorialmod;

import name.tutorialmod.block.ModBlocks;
import name.tutorialmod.block.custom.CADStation;
import name.tutorialmod.block.entity.CADStationBlockEntity;
import name.tutorialmod.components.CADComponent;
import name.tutorialmod.gui.CADStationScreenHandler;
import name.tutorialmod.item.ModItemGroups;
import name.tutorialmod.item.ModItems;
import name.tutorialmod.networking.ModMessages;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.StonecutterScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	public static final Identifier CAD_STATION = new Identifier(MOD_ID, "cad_station");
	public static final Identifier CAD_STATION_SCREEN_HANDLER = new Identifier(MOD_ID, "cad_station_screen_handler");
	public static final ScreenHandlerType<CADStationScreenHandler> CAD_STATION_SCREEN_HANDLER_TYPE;

	public static final Block CAD_STATION_BLOCK;
	public static final BlockItem CAD_STATION_ITEM;
	public static BlockEntityType<CADStationBlockEntity> CAD_STATION_ENTITY = null;

	static {
		CAD_STATION_BLOCK = Registry.register(Registries.BLOCK, CAD_STATION, new CADStation(AbstractBlock.Settings.copy(Blocks.CHEST)));
		CAD_STATION_ITEM = Registry.register(Registries.ITEM, CAD_STATION, new BlockItem(CAD_STATION_BLOCK, new Item.Settings()));
		CAD_STATION_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, CAD_STATION,
				BlockEntityType.Builder.create(
						(pos, state) -> new CADStationBlockEntity(CAD_STATION_ENTITY, pos, state), 
						CAD_STATION_BLOCK).build(null)
		);
        CAD_STATION_SCREEN_HANDLER_TYPE = Registry.register(Registries.SCREEN_HANDLER, CAD_STATION_SCREEN_HANDLER.toString(),
				new ExtendedScreenHandlerType<>(CADStationScreenHandler::new, BlockPos.PACKET_CODEC));
	}

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModMessages.registerC2SPackets();

		FuelRegistry.INSTANCE.add(ModItems.COAL2, 12800);
	}
}