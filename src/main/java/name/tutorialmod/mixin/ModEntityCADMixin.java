package name.tutorialmod.mixin;

import java.util.Iterator;
import name.tutorialmod.components.CADComponent;
import name.tutorialmod.item.ModItems;
import name.tutorialmod.util.IEntityCAD;
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
    private static final ComponentKey<CADComponent> KEY = ComponentRegistry.getOrCreate(new Identifier("tutorialmod", "cad"), CADComponent.class);
    
    private NbtCompound persistentData;
    
    protected ModEntityCADMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    

    @Unique
    public void swapArmor() {
        Iterable<ItemStack> getArmor = this.getArmorItems();
        Iterator<ItemStack> armorList = getArmor.iterator();
        this.equipStack(EquipmentSlot.FEET, KEY.get(this).swapBoots(armorList.next()));
        this.equipStack(EquipmentSlot.LEGS, KEY.get(this).swapLeggings(armorList.next()));
        this.equipStack(EquipmentSlot.CHEST, KEY.get(this).swapChestplate(armorList.next()));
        this.equipStack(EquipmentSlot.HEAD, KEY.get(this).swapHelmet(armorList.next()));
    }

    @Unique
    public void setArmor() {
        KEY.get(this).setArmor(ModItems.SHIDO_HELMET.getDefaultStack(), ModItems.SHIDO_CHESTPLATE.getDefaultStack(),
                ModItems.SHIDO_LEGGINGS.getDefaultStack(), ModItems.SHIDO_BOOTS.getDefaultStack());
    }

    @Unique
    public NbtCompound getPersistentData() {
        if(this.persistentData == null){
            this.persistentData = new NbtCompound();
        }
        return persistentData;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfo info) {
        KEY.get(this).writeToNbt(nbt, this.getRegistryManager());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        KEY.get(this).readFromNbt(nbt, this.getRegistryManager());
    }
}
