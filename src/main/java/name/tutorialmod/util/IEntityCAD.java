package name.tutorialmod.util;

import net.minecraft.nbt.NbtCompound;

public interface IEntityCAD {
    NbtCompound getPersistentData();

    void swapArmor();

    void setArmor();
}
