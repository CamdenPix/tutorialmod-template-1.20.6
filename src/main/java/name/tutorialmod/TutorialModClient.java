package name.tutorialmod;

import name.tutorialmod.client.keybinding.TutorialModClientEntrypoint;
import name.tutorialmod.components.CADComponent;
import name.tutorialmod.networking.ModMessages;
import name.tutorialmod.util.IEntityCAD;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import name.tutorialmod.gui.CADStationScreen;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;

import static net.minecraft.server.command.CommandManager.*;

@Environment(EnvType. CLIENT)
public class TutorialModClient implements ClientModInitializer{
    public static final TutorialModClientEntrypoint CLIENT_ENTRYPOINT = new TutorialModClientEntrypoint();
    private static final ComponentKey<CADComponent> KEY = ComponentRegistry.getOrCreate(new Identifier("tutorialmod", "cad"), CADComponent.class);

    @Override
    public void onInitializeClient(){
        CLIENT_ENTRYPOINT.onInitializeClient();
        ModMessages.registerS2CPackets();

        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(literal("setcad")
                .executes(context -> {
                    ServerPlayerEntity player = context.getSource().getPlayer();
                    assert player != null;
                    ((IEntityCAD)player).setArmor();
                    player.sendMessage(Text.literal("New CAD set"), true);
                    return 1;
                }))));
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(literal("clearcad")
                .executes(context -> {
                    ServerPlayerEntity player = context.getSource().getPlayer();
                    assert player != null;
                    ((IEntityCAD)player).clearArmor();
                    player.sendMessage(Text.literal("CAD cleared"), true);
                    return 1;
                }))));
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(literal("increasestats")
                .executes(context -> {
                    ServerPlayerEntity player = context.getSource().getPlayer();
                    assert player != null;
                    ((IEntityCAD)player).stepStats();
                    player.sendMessage(Text.literal("Stats Stepped"), true);
                    return 1;
                }))));
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(literal("clearstats")
                .executes(context -> {
                    ServerPlayerEntity player = context.getSource().getPlayer();
                    assert player != null;
                    ((IEntityCAD)player).clearStats();
                    player.sendMessage(Text.literal("Stats Stepped"), true);
                    return 1;
                }))));

        HandledScreens.register(TutorialMod.CAD_STATION_SCREEN_HANDLER_TYPE, CADStationScreen::new);
    }
}
