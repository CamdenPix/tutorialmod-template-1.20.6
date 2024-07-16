package name.tutorialmod;

import name.tutorialmod.networking.ModMessages;
import name.tutorialmod.util.IEntityCAD;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.*;

public class TutorialModClient implements ClientModInitializer{
    @Override
    public void onInitializeClient(){
        ModMessages.registerS2CPackets();

        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(literal("setcad")
                .executes(context -> {
                    ServerPlayerEntity player = context.getSource().getPlayer();
                    assert player != null;
                    ((IEntityCAD)player).setArmor();
                    player.sendMessage(Text.literal("New CAD set"), true);
                    return 1;
                }))));
    }
}
