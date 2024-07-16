package name.tutorialmod.components;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
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

    private static final ComponentKey<CADComponent> KEY = ComponentRegistry.getOrCreate(new Identifier("tutorialmod", "cad"), CADComponent.class);

    public CADComponent() {
        this.helmet = ItemStack.EMPTY;
        this.chestplate = ItemStack.EMPTY;
        this.leggings = ItemStack.EMPTY;
        this.boots = ItemStack.EMPTY;
    }

    public CADComponent(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
    }

    public void setArmor(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
    }

    public ItemStack getHelmet() {
        return helmet;
    }

    public ItemStack getChestplate() {
        return chestplate;
    }

    public ItemStack getLeggings() {
        return leggings;
    }

    public ItemStack getBoots() {
        return boots;
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
        boots = ItemStack.fromNbt(registryLookup, list.getCompound(0)).orElse(ItemStack.EMPTY);
        leggings = ItemStack.fromNbt(registryLookup, list.getCompound(1)).orElse(ItemStack.EMPTY);
        chestplate = ItemStack.fromNbt(registryLookup, list.getCompound(2)).orElse(ItemStack.EMPTY);
        helmet = ItemStack.fromNbt(registryLookup, list.getCompound(3)).orElse(ItemStack.EMPTY);
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        NbtList nbtList = new NbtList();
        NbtCompound nbtCompound = new NbtCompound();
        if(boots != ItemStack.EMPTY) {
            nbtCompound.putByte("Slot", (byte) 0);
            nbtList.add(boots.encode(registryLookup, nbtCompound));
        }

        if(leggings != ItemStack.EMPTY) {
            nbtCompound.putByte("Slot", (byte) 1);
            nbtList.add(leggings.encode(registryLookup, nbtCompound));
        }

        if(chestplate != ItemStack.EMPTY) {
            nbtCompound.putByte("Slot", (byte) 2);
            nbtList.add(chestplate.encode(registryLookup, nbtCompound));
        }

        if(helmet != ItemStack.EMPTY) {
            nbtCompound.putByte("Slot", (byte) 3);
            nbtList.add(helmet.encode(registryLookup, nbtCompound));
        }
        tag.put(KEY.toString(), nbtList);
    }
}
