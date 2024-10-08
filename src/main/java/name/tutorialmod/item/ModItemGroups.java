package name.tutorialmod.item;

import name.tutorialmod.TutorialMod;
import name.tutorialmod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup RUBY_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TutorialMod.MOD_ID, "ruby"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ruby"))
                    .icon(() -> new ItemStack(ModItems.RUBY)).entries((displayContext, entries) -> {
                        entries.add(ModItems.RAW_RUBY);
                        entries.add(ModItems.RUBY);
                        entries.add(ModItems.DUBIOUS_STEW);
                        entries.add(ModItems.COAL2);
                        entries.add(ModBlocks.RUBY_BLOCK);
                        entries.add(ModBlocks.RAW_RUBY_BLOCK);
                        entries.add(ModItems.METAL_DETECTOR);
                        entries.add(ModBlocks.JUMP_BLOCK);
                        entries.add(ModItems.SHIDO_HELMET);
                        entries.add(ModItems.SHIDO_CHESTPLATE);
                        entries.add(ModItems.SHIDO_LEGGINGS);
                        entries.add(ModItems.SHIDO_BOOTS);
                        entries.add(ModItems.HONORIS_HELMET);
                        entries.add(ModItems.HONORIS_CHESTPLATE);
                        entries.add(ModItems.HONORIS_LEGGINGS);
                        entries.add(ModItems.HONORIS_BOOTS);
                        entries.add(ModItems.MERLIN_HELMET);
                        entries.add(ModItems.MERLIN_CHESTPLATE);
                        entries.add(ModItems.MERLIN_LEGGINGS);
                        entries.add(ModItems.MERLIN_BOOTS);
                        entries.add(ModItems.VYSETRIUM_CRYSTAL);
                    }).build());

    public static void registerItemGroups() {
        TutorialMod.LOGGER.info("Registering Item Groups for " + TutorialMod.MOD_ID);
    }
}
