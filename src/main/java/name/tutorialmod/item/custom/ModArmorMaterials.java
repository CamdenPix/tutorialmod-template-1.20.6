package name.tutorialmod.item.custom;

import name.tutorialmod.TutorialMod;
import name.tutorialmod.item.ModItems;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final RegistryEntry<ArmorMaterial> SHIDO = ModArmorMaterials.register("shido",
            Util.make(new EnumMap(ModArmorItem.Type.class), map -> {
                map.put(ModArmorItem.Type.BOOTS, 3);
                map.put(ModArmorItem.Type.LEGGINGS, 6);
                map.put(ModArmorItem.Type.CHESTPLATE, 8);
                map.put(ModArmorItem.Type.HELMET, 3);
                map.put(ModArmorItem.Type.BODY, 11);
    }), 0, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2.0f, 0.05f, () -> Ingredient.ofItems(ModItems.RUBY));





    private static RegistryEntry<ArmorMaterial> register(String id, EnumMap<ModArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(new Identifier(TutorialMod.MOD_ID, id)));
        return ModArmorMaterials.register(id, defense, enchantability, equipSound, toughness, knockbackResistance, repairIngredient, list);
    }

    private static RegistryEntry<ArmorMaterial> register(String id, EnumMap<ModArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient, List<ArmorMaterial.Layer> layers) {
        EnumMap<ModArmorItem.Type, Integer> enumMap = new EnumMap<ArmorItem.Type, Integer>(ModArmorItem.Type.class);
        for (ModArmorItem.Type type : ModArmorItem.Type.values()) {
            enumMap.put(type, defense.get(type));
        }
        return Registry.registerReference(Registries.ARMOR_MATERIAL, new Identifier(TutorialMod.MOD_ID, id),
                new ArmorMaterial(enumMap, enchantability, equipSound, repairIngredient, layers, toughness, knockbackResistance));
    }
}
