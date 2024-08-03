package name.tutorialmod.mixin;

import java.util.Iterator;
import java.util.UUID;

import name.tutorialmod.components.CADComponent;
import name.tutorialmod.components.CADStatsComponent;
import name.tutorialmod.item.ModItems;
import name.tutorialmod.networking.payload.EquipCADPayload;
import name.tutorialmod.networking.payload.EvolveCADPayload;
import name.tutorialmod.util.IEntityCAD;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class ModEntityCADMixin extends LivingEntity implements IEntityCAD {
    private static final ComponentKey<CADComponent> ARMOR =
            ComponentRegistry.getOrCreate(new Identifier("tutorialmod", "cad"), CADComponent.class);
    private static final ComponentKey<CADStatsComponent> STATS =
            ComponentRegistry.getOrCreate(new Identifier("tutorialmod", "cadstats"), CADStatsComponent.class);
    
    protected ModEntityCADMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Unique
    public void swapArmor() {
        Iterable<ItemStack> getArmor = this.getArmorItems();
        Iterator<ItemStack> armorList = getArmor.iterator();

        ARMOR.get(this).swapArmorEquipped();
        this.equipStack(EquipmentSlot.FEET, ARMOR.get(this).swapBoots(armorList.next()));
        this.equipStack(EquipmentSlot.LEGS, ARMOR.get(this).swapLeggings(armorList.next()));
        this.equipStack(EquipmentSlot.CHEST, ARMOR.get(this).swapChestplate(armorList.next()));
        this.equipStack(EquipmentSlot.HEAD, ARMOR.get(this).swapHelmet(armorList.next()));

    }

    @Unique
    public String evolveCAD(int damageDealt, int damageTaken, int distanceSprinted) {
        String evoText = ("--CAD Stats increases--\n");

        int diffDamageDealt = STATS.get(this).setPrevDamageDealt(damageDealt);
        int strengthIncrease = Math.min(diffDamageDealt/(20*(STATS.get(this).getStrength()+1)), 4);
        evoText = evoText + "Strength: " + STATS.get(this).getStrength() + " -> ";
        increaseStrength(strengthIncrease);
        evoText = evoText + STATS.get(this).getStrength() + "\n";


        int diffDamageTaken = STATS.get(this).setPrevDamageTaken(damageTaken);

        int defenseIncrease = Math.min(diffDamageTaken/(5*(STATS.get(this).getProtection()+1)), 4);
        evoText = evoText + "Defense: " + STATS.get(this).getProtection() + " -> ";
        increaseDefense(defenseIncrease);
        evoText = evoText + STATS.get(this).getProtection() + "\n";

        int enduranceIncrease = Math.min(diffDamageTaken/(5*(STATS.get(this).getHp()+1)), 4);
        evoText = evoText + "Endurance: " + STATS.get(this).getHp() + " -> ";
        increaseEndurance(enduranceIncrease);
        evoText = evoText + STATS.get(this).getHp() + "\n";

        int diffDistanceSprinted = STATS.get(this).setPrevDistanceSprinted(distanceSprinted);
        float speedIncrease = (float) Math.min( (diffDistanceSprinted/(10000*(STATS.get(this).getSpeed()+1))), 4.0);
        evoText = evoText + "Speed: " + STATS.get(this).getSpeed() + " -> ";
        increaseSpeed(speedIncrease);
        evoText = evoText + STATS.get(this).getSpeed();


        //TODO Don't infinitely stack protection
        Iterable<ItemStack> getArmor = this.getArmorItems();
        Iterator<ItemStack> armorList = getArmor.iterator();
        int level = STATS.get(this).getProtection();

        armorList.next().addEnchantment(Enchantments.PROTECTION, level);
        armorList.next().addEnchantment(Enchantments.PROTECTION, level);
        armorList.next().addEnchantment(Enchantments.PROTECTION, level);
        armorList.next().addEnchantment(Enchantments.PROTECTION, level);
        return evoText;
    }

    @Unique
    public void setArmor() {
        ItemStack helmet = ModItems.SHIDO_HELMET.getDefaultStack();
        ItemStack chestplate = ModItems.SHIDO_CHESTPLATE.getDefaultStack();
        ItemStack leggings = ModItems.SHIDO_LEGGINGS.getDefaultStack();
        ItemStack boots = ModItems.SHIDO_BOOTS.getDefaultStack();


        helmet.addEnchantment(Enchantments.BINDING_CURSE, 1);
        chestplate.addEnchantment(Enchantments.BINDING_CURSE, 1);
        leggings.addEnchantment(Enchantments.BINDING_CURSE, 1);
        boots.addEnchantment(Enchantments.BINDING_CURSE, 1);



        ARMOR.get(this).setArmor(
                helmet,
                chestplate,
                leggings,
                boots
        );
    }

    @Unique
    public void increaseStrength(int str){
        STATS.get(this).setStrength(STATS.get(this).getStrength()+str);
    }

    @Unique
    public void increaseEndurance(int hp){
        STATS.get(this).setEndurance(STATS.get(this).getHp()+hp);
    }

    @Unique
    public void increaseSpeed(float speed){
        STATS.get(this).setSpeed(STATS.get(this).getSpeed()+speed/10);
    }

    @Unique
    public void increaseCognition(int cog){
        STATS.get(this).setCognition(STATS.get(this).getAttackSpeed()+cog);
    }

    @Unique
    public void increaseDefense(int def){
        STATS.get(this).setDefense(STATS.get(this).getArmor()+def);
    }

    @Unique
    public void stepStats(){
        increaseStrength(1);
        increaseEndurance(1);
        increaseSpeed(1);
        increaseCognition(1);
        increaseDefense(1);
    }

    @Unique
    public void clearStats(){
        STATS.get(this).setStrength(0);
        STATS.get(this).setDefense(0);
        STATS.get(this).setCognition(0);
        STATS.get(this).setSpeed(0);
        STATS.get(this).setEndurance(0);
    }

    @Unique
    public void clearArmor() {
        ARMOR.get(this).clearArmor();
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfo info) {
        ARMOR.get(this).writeToNbt(nbt, this.getRegistryManager());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        ARMOR.get(this).readFromNbt(nbt, this.getRegistryManager());
    }

    @Inject(method = "dropInventory", at = @At("HEAD"))
    protected void injectDeathCadSwap(CallbackInfo info){
        if(ARMOR.get(this).getArmorEquipped()){
            swapArmor();
        }
    }
}
