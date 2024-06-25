package name.tutorialmod.client.keybinding;

import name.tutorialmod.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class TutorialModClientEntrypoint implements ClientModInitializer {
    private boolean wasTestKeyPressed = false;
    private boolean armorOn = false;

    @Override
    public void onInitializeClient() {
        KeyBinding testKey = KeyBindingHelper.registerKeyBinding(new KeyBinding (
                "key.tutorialmod.test",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                "category.tutorialmod.keybinds"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (testKey.isPressed()) {
                if (!wasTestKeyPressed) {
                    //Gets head slot rn
                    String armorName = client.player.getInventory().getArmorStack(0).getName().getString();
                    if(!armorOn){
                        client.player.getInventory().setStack(39, ModItems.SHIDO_HELMET.getDefaultStack());
                        client.player.getInventory().setStack(38, ModItems.SHIDO_CHESTPLATE.getDefaultStack());
                        client.player.getInventory().setStack(37, ModItems.SHIDO_LEGGINGS.getDefaultStack());
                        client.player.getInventory().setStack(36, ModItems.SHIDO_BOOTS.getDefaultStack());
                        client.player.sendMessage(Text.literal(armorName + " Armor On"), false);
                    }
                    else {
                        client.player.sendMessage(Text.literal(armorName + " Armor Off"), false);
                        client.player.getInventory().setStack(39, ItemStack.EMPTY);
                        client.player.getInventory().setStack(38, ItemStack.EMPTY);
                        client.player.getInventory().setStack(37, ItemStack.EMPTY);
                        client.player.getInventory().setStack(36, ItemStack.EMPTY);
                    }
                    armorOn = !armorOn;
                    wasTestKeyPressed = true;
                }
            } else {
                wasTestKeyPressed = false;
            }
        });
    }
}
