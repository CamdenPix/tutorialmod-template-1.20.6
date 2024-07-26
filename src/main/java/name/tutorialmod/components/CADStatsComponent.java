package name.tutorialmod.components;

import net.minecraft.nbt.*;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class CADStatsComponent implements Component, AutoSyncedComponent {
    public static final ComponentKey<CADStatsComponent> KEY =
            ComponentRegistry.getOrCreate(new Identifier("tutorialmod", "cadstats"), CADStatsComponent.class);

    int strength;
    int endurance;
    float speed;
    float cognition;
    int defense;
    long abilitiesBitFlags;

    public CADStatsComponent () {
        strength = 0;
        endurance = 0;
        speed = 0;
        cognition = 0;
        defense = 0;
        abilitiesBitFlags = 0;
    }

    public int getStrength(){
        return strength;
    }

    public int getHp(){
        return endurance;
    }

    public float getToughness(){
        return (float) Math.floor((float)endurance/5.);
    }

    public int getArmor(){
        return defense;
    }

    public int getProtection(){
        return (int) Math.floor((float)defense/5.);
    }

    public float getAttackSpeed(){
        return cognition;
    }

    public float getSpeed(){
        return speed;
    }

    public void setStrength(int strength){
        this.strength = strength;
    }

    public void setEndurance(int endurance){
        this.endurance = endurance;
    }

    public void setSpeed(float speed){
        this.speed = speed;
    }

    public void setCognition(float attackSpeed){
        this.cognition = attackSpeed;
    }

    public void setDefense(int defense){
        this.defense = defense;
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        strength = tag.getInt("strength");
        endurance = tag.getInt("endurance");
        speed = tag.getFloat("speed");
        cognition = tag.getFloat("cognition");
        defense = tag.getInt("defense");
        abilitiesBitFlags = tag.getLong("abilities");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putInt("strength", strength);
        tag.putInt("endurance", endurance);
        tag.putFloat("speed", speed);
        tag.putFloat("cognition", cognition);
        tag.putInt("defense", defense);
        tag.putLong("abilities", abilitiesBitFlags);
    }
}
