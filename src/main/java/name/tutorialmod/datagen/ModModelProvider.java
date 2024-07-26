package name.tutorialmod.datagen;

import name.tutorialmod.TutorialMod;
import name.tutorialmod.block.ModBlocks;
import name.tutorialmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.VYSETRIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(TutorialMod.CAD_STATION_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SHIDO_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SHIDO_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SHIDO_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SHIDO_BOOTS));

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.HONORIS_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.HONORIS_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.HONORIS_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.HONORIS_BOOTS));

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MERLIN_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MERLIN_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MERLIN_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MERLIN_BOOTS));

        itemModelGenerator.register(ModItems.VYSETRIUM_CRYSTAL, Models.GENERATED);
    }
}
