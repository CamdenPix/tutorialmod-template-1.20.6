package name.tutorialmod.datagen;

import name.tutorialmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.SHIDO_HELMET, ModItems.SHIDO_CHESTPLATE, ModItems.SHIDO_LEGGINGS, ModItems.SHIDO_BOOTS)
                .add(ModItems.HONORIS_HELMET, ModItems.HONORIS_CHESTPLATE, ModItems.HONORIS_LEGGINGS, ModItems.HONORIS_BOOTS)
                .add(ModItems.MERLIN_HELMET, ModItems.MERLIN_CHESTPLATE, ModItems.MERLIN_LEGGINGS, ModItems.MERLIN_BOOTS);
    }
}
