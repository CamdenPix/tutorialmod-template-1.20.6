package name.tutorialmod.networking;

import name.tutorialmod.components.CADComponent;
import name.tutorialmod.networking.payload.EquipCADPayload;
import name.tutorialmod.networking.payload.SetCADPayload;
import name.tutorialmod.util.IEntityCAD;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;

public class ModMessages {
    private static final ComponentKey<CADComponent> KEY = ComponentRegistry.getOrCreate(new Identifier("tutorialmod", "cad"), CADComponent.class);

    public static void registerC2SPackets() {
        PayloadTypeRegistry.playC2S().register(EquipCADPayload.PACKET_ID, EquipCADPayload.PACKET_CODEC);
        PayloadTypeRegistry.playC2S().register(SetCADPayload.PACKET_ID, SetCADPayload.PACKET_CODEC);

        registerC2SEvents();
    }

    public static void registerS2CPackets() {
        PayloadTypeRegistry.playS2C().register(EquipCADPayload.PACKET_ID, EquipCADPayload.PACKET_CODEC);
        PayloadTypeRegistry.playS2C().register(SetCADPayload.PACKET_ID, SetCADPayload.PACKET_CODEC);

        registerS2CEvents();
    }

    private static void registerC2SEvents() {
        ServerPlayNetworking.registerGlobalReceiver(EquipCADPayload.PACKET_ID, (payload, context) -> {
            var world = context.player().getServerWorld();
            PlayerEntity playerEntity = (PlayerEntity) world.getEntity(payload.player());
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) world.getEntity(payload.player());
            if(KEY.get(playerEntity).returnHasCAD()) {
                ((IEntityCAD) serverPlayer).swapArmor();
                serverPlayer.sendMessage(Text.literal("Equipped armor"), true);
            }
            else {
                serverPlayer.sendMessage(Text.literal("Lol, ur poor"),true);
            }
        });

        ServerPlayNetworking.registerGlobalReceiver(SetCADPayload.PACKET_ID, ((payload, context) -> {
            var world = context.player().getServerWorld();
            ServerPlayerEntity player = (ServerPlayerEntity) world.getEntity(payload.player());
            ((IEntityCAD)player).setArmor();
            player.sendMessage(Text.literal("Set armor"), true);
        }));

    }

    private static void registerS2CEvents() {

    }
}
