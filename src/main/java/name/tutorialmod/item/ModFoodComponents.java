package name.tutorialmod.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent DUBIOUS_STEW = new FoodComponent.Builder().nutrition(3).saturationModifier(.25f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 100), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 3), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 3), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 100, 3), 1.0f).build();
}
