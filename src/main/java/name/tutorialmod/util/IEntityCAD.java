package name.tutorialmod.util;

import net.minecraft.nbt.NbtCompound;

public interface IEntityCAD {
    NbtCompound getPersistentData();

    void swapArmor();

    void setArmor();

    void clearArmor();

    void increaseStrength(int str);

    void increaseEndurance(int hp);

    void increaseSpeed(int speed);

    void increaseCognition(int cog);

    void increaseDefense(int def);

    void stepStats();

    void clearStats();

    String evolveCAD(int damageDealt, int damageTaken, int distanceSprinted);
}
