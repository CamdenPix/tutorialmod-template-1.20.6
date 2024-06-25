package name.tutorialmod.item;

import name.tutorialmod.TutorialMod;
import name.tutorialmod.item.custom.MetalDetectorItem;
import name.tutorialmod.item.custom.ModArmorItem;
import name.tutorialmod.item.custom.ModArmorMaterials;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
public class ModItems {

    //Create item
    //remember to add it to the en_us.json file for localization
    //and a new json file in the models/item to point to a texture to use in the textures/item folder
    public static final Item RUBY = registerItem("ruby", new Item(new Item.Settings()));
    public static final Item RAW_RUBY = registerItem("raw_ruby", new Item(new Item.Settings()));

    public static final Item METAL_DETECTOR = registerItem("metal_detector",
            new MetalDetectorItem(new Item.Settings().maxDamage(64)));

    public static final Item DUBIOUS_STEW = registerItem("dubious_stew", new Item(new Item.Settings().food(ModFoodComponents.DUBIOUS_STEW)));
    public static final Item COAL2 = registerItem("coal2", new Item(new Item.Settings()));

    public static final Item SHIDO_HELMET = registerItem("shido_helmet", (Item)new ModArmorItem(ModArmorMaterials.SHIDO, ModArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(33))));
    public static final Item SHIDO_CHESTPLATE = registerItem("shido_chestplate", (Item)new ModArmorItem(ModArmorMaterials.SHIDO, ModArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(33))));
    public static final Item SHIDO_LEGGINGS = registerItem("shido_leggings", (Item)new ModArmorItem(ModArmorMaterials.SHIDO, ModArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(33))));
    public static final Item SHIDO_BOOTS = registerItem("shido_boots", (Item)new ModArmorItem(ModArmorMaterials.SHIDO, ModArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(33))));

    //Helper function to add an item to an ingredient tab
    private static void addItemsToIngredientTab(FabricItemGroupEntries entries){
        entries.add(RUBY);
        entries.add(RAW_RUBY);
        entries.add(METAL_DETECTOR);
        entries.add(DUBIOUS_STEW);
        entries.add(COAL2);
        entries.add(SHIDO_HELMET);
        entries.add(SHIDO_CHESTPLATE);
        entries.add(SHIDO_LEGGINGS);
        entries.add(SHIDO_BOOTS);
        //add entries here to add more items
    }

    //Helper function to register the item into Minecraft
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(TutorialMod.MOD_ID, name), item);
    }

    //Driver program, Called when mod is initialized
    public static void registerModItems() {
        TutorialMod.LOGGER.info("Registering Mod Items for " + TutorialMod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTab);
    }
}
