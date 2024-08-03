package name.tutorialmod.item.custom;

import name.tutorialmod.TutorialMod;
import name.tutorialmod.components.CADStatsComponent;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;
import java.util.UUID;

public class CADArmorItem extends ArmorItem {
    private static final ComponentKey<CADStatsComponent> STATS =
            ComponentRegistry.getOrCreate(new Identifier("tutorialmod", "cadstats"), CADStatsComponent.class);
    AttributeModifiersComponent armorAttributes;
    private static final EnumMap<Type, UUID> MODIFIERS = (EnumMap)Util.make(new EnumMap(Type.class), (uuidMap) -> {
        uuidMap.put(ArmorItem.Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        uuidMap.put(ArmorItem.Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        uuidMap.put(ArmorItem.Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        uuidMap.put(ArmorItem.Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
        uuidMap.put(ArmorItem.Type.BODY, UUID.fromString("C1C72771-8B8E-BA4A-ACE0-81A93C8928B2"));
    });


    public CADArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!world.isClient && entity instanceof PlayerEntity){
            CADStatsComponent cad = STATS.get(entity);
            updateValues(cad.getArmor(), cad.getToughness(), cad.getHp(), cad.getSpeed(), cad.getStrength());
        }
    }

    public void updateValues(int armor, float toughness, int hp, float speed, int attack){
        AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();
        AttributeModifierSlot attributeModifierSlot = AttributeModifierSlot.forEquipmentSlot(type.getEquipmentSlot());
        UUID uUID = (UUID) MODIFIERS.get(type);
        //TODO extend this builder structure to have all EntityAttributes types for more maliabilty?
        builder.add(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uUID, "Armor modifier", armor, EntityAttributeModifier.Operation.ADD_VALUE), attributeModifierSlot);
        builder.add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uUID, "Armor toughness", toughness, EntityAttributeModifier.Operation.ADD_VALUE), attributeModifierSlot);
        builder.add(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(uUID, "Health", hp, EntityAttributeModifier.Operation.ADD_VALUE), attributeModifierSlot);
        builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(uUID, "Attack", attack, EntityAttributeModifier.Operation.ADD_VALUE), attributeModifierSlot);
        builder.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(uUID, "Speed", speed, EntityAttributeModifier.Operation.ADD_VALUE), attributeModifierSlot);

        armorAttributes = builder.build();
    }


    @Override
    public AttributeModifiersComponent getAttributeModifiers() {
        if(armorAttributes == null || type != Type.CHESTPLATE) {
            return super.getAttributeModifiers();
        } else{
            return armorAttributes;
        }
    }
}
