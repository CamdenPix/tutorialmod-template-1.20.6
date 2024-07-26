package name.tutorialmod.components;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class CADComponent implements Component, AutoSyncedComponent {
    ItemStack helmet;
    ItemStack chestplate;
    ItemStack leggings;
    ItemStack boots;
    boolean armorEquipped;
    boolean hasCAD;

    private static final ComponentKey<CADComponent> KEY = ComponentRegistry.getOrCreate(new Identifier("tutorialmod", "cad"), CADComponent.class);

    public CADComponent() {
        this.helmet = ItemStack.EMPTY;
        this.chestplate = ItemStack.EMPTY;
        this.leggings = ItemStack.EMPTY;
        this.boots = ItemStack.EMPTY;
        armorEquipped = false;
        hasCAD = false;
    }

    public CADComponent(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        armorEquipped = false;
        hasCAD = true;
    }

    public void setArmor(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        armorEquipped = false;
        hasCAD = true;
    }

    public void clearArmor() {
        this.helmet = ItemStack.EMPTY;
        this.chestplate = ItemStack.EMPTY;
        this.leggings = ItemStack.EMPTY;
        this.boots = ItemStack.EMPTY;
        armorEquipped = false;
        hasCAD = false;
    }

    public void setHasCAD(Boolean haveCad){
        this.hasCAD = haveCad;
    }

    public boolean returnHasCAD(){
        return hasCAD;
    }

    public boolean getArmorEquipped(){
        return armorEquipped;
    }

    public void swapArmorEquipped(){
        armorEquipped = !armorEquipped;
    }

    public ItemStack swapHelmet(ItemStack helmet) {
        ItemStack tempHelmet = this.helmet;
        this.helmet = helmet;
        return tempHelmet;
    }

    public ItemStack swapChestplate(ItemStack chestplate) {
        ItemStack tempChestplate = this.chestplate;
        this.chestplate = chestplate;
        return tempChestplate;
    }

    public ItemStack swapLeggings(ItemStack leggings) {
        ItemStack tempLeggings = this.leggings;
        this.leggings = leggings;
        return tempLeggings;
    }

    public ItemStack swapBoots(ItemStack boots) {
        ItemStack tempBoots = this.boots;
        this.boots = boots;
        return tempBoots;
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        NbtList list = tag.getList(KEY.toString(), 10);
        for (NbtElement element : list) {
            if(element instanceof NbtCompound) {
                NbtCompound compound = (NbtCompound) element;
                byte type = compound.getByte("Type");
                switch (type) {
                    case 1:
                        boots = ItemStack.fromNbtOrEmpty(registryLookup, compound.getCompound("boots"));
                        leggings = ItemStack.fromNbtOrEmpty(registryLookup, compound.getCompound("leggings"));
                        chestplate = ItemStack.fromNbtOrEmpty(registryLookup, compound.getCompound("chestplate"));
                        helmet = ItemStack.fromNbtOrEmpty(registryLookup, compound.getCompound("helmet"));
                        break;
                    case 2:
                        armorEquipped = compound.getBoolean("armorequipped");
                        hasCAD = compound.getBoolean("hascad");
                        break;
                }

            }
        }
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        NbtList nbtList = new NbtList();

        NbtCompound armorNBTCompound = new NbtCompound();
        armorNBTCompound.putByte("Type", (byte) 1);//Conveys armor info is contained
        if(boots != ItemStack.EMPTY) {
            armorNBTCompound.put("boots", boots.encode(registryLookup));
        }
        if(leggings != ItemStack.EMPTY) {
            armorNBTCompound.put("leggings", leggings.encode(registryLookup));
        }
        if(chestplate != ItemStack.EMPTY) {
            armorNBTCompound.put("chestplate", chestplate.encode(registryLookup));
        }
        if(helmet != ItemStack.EMPTY) {
            armorNBTCompound.put("helmet", helmet.encode(registryLookup));
        }
        nbtList.add(armorNBTCompound);

        NbtCompound boolsNBTCompound = new NbtCompound();
        boolsNBTCompound.putByte("Type", (byte) 2); //Contains the bools
        boolsNBTCompound.putBoolean("armorequipped", armorEquipped);
        boolsNBTCompound.putBoolean("hascad", hasCAD);
        nbtList.add(boolsNBTCompound);

        tag.put(KEY.toString(), nbtList);
    }
}
