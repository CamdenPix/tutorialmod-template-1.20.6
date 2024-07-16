package name.tutorialmod.networking;

import name.tutorialmod.networking.payload.EquipCADPayload;
import name.tutorialmod.util.IEntityCAD;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class ModMessages {

    public static void registerC2SPackets() {
        PayloadTypeRegistry.playC2S().register(EquipCADPayload.PACKET_ID, EquipCADPayload.PACKET_CODEC);

        registerC2SEvents();
    }

    public static void registerS2CPackets() {
        PayloadTypeRegistry.playS2C().register(EquipCADPayload.PACKET_ID, EquipCADPayload.PACKET_CODEC);

        registerS2CEvents();
    }

    private static void registerC2SEvents() {
        ServerPlayNetworking.registerGlobalReceiver(EquipCADPayload.PACKET_ID, (payload, context) -> {
            var world = context.player().getServerWorld();
            ServerPlayerEntity player = (ServerPlayerEntity) world.getEntity(payload.player());
            ((IEntityCAD)player).swapArmor();
            player.sendMessage(Text.literal("Equipped armor"), true);
        });
    }

    private static void registerS2CEvents() {

    }
}
