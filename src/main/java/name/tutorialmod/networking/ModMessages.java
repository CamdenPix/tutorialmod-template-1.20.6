package name.tutorialmod.networking;

import name.tutorialmod.components.CADComponent;
import name.tutorialmod.networking.payload.EquipCADPayload;
import name.tutorialmod.networking.payload.EvolveCADPayload;
import name.tutorialmod.networking.payload.SetCADPayload;
import name.tutorialmod.util.IEntityCAD;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.StatHandler;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;

public class ModMessages {
    private static final ComponentKey<CADComponent> KEY =
            ComponentRegistry.getOrCreate(new Identifier("tutorialmod", "cad"), CADComponent.class);

    public static void registerC2SPackets() {
        PayloadTypeRegistry.playC2S().register(EquipCADPayload.PACKET_ID, EquipCADPayload.PACKET_CODEC);
        PayloadTypeRegistry.playC2S().register(SetCADPayload.PACKET_ID, SetCADPayload.PACKET_CODEC);
        PayloadTypeRegistry.playC2S().register(EvolveCADPayload.PACKET_ID, EvolveCADPayload.PACKET_CODEC);

        registerC2SEvents();
    }

    public static void registerS2CPackets() {
        PayloadTypeRegistry.playS2C().register(EquipCADPayload.PACKET_ID, EquipCADPayload.PACKET_CODEC);
        PayloadTypeRegistry.playS2C().register(SetCADPayload.PACKET_ID, SetCADPayload.PACKET_CODEC);
        PayloadTypeRegistry.playS2C().register(EvolveCADPayload.PACKET_ID, EvolveCADPayload.PACKET_CODEC);

        registerS2CEvents();
    }

    private static void registerC2SEvents() {
        ServerPlayNetworking.registerGlobalReceiver(EquipCADPayload.PACKET_ID, (payload, context) -> {
            var world = context.player().getServerWorld();
            PlayerEntity playerEntity = (PlayerEntity) world.getEntity(payload.player());
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) world.getEntity(payload.player());
            StatHandler stats = serverPlayer.getStatHandler();
            if(KEY.get(playerEntity).returnHasCAD()) {
                if(KEY.get(playerEntity).getArmorEquipped()){
                    String evoText = ((IEntityCAD)playerEntity).evolveCAD(
                            stats.getStat(Stats.CUSTOM, Stats.DAMAGE_DEALT),
                            stats.getStat(Stats.CUSTOM, Stats.DAMAGE_TAKEN),
                            stats.getStat(Stats.CUSTOM, Stats.SPRINT_ONE_CM));
                    serverPlayer.sendMessage(Text.literal("Unequipped armor"), true);
                    //TODO: Print a proper output message similar to the book
                    serverPlayer.sendMessage(Text.literal(evoText), false);

                }
                else{
                    serverPlayer.sendMessage(Text.literal("Equipped armor"), true);
                }
                ((IEntityCAD) serverPlayer).swapArmor();
            }
            else {
                serverPlayer.sendMessage(Text.literal("No CAD obtained"),true);
            }
        });

        ServerPlayNetworking.registerGlobalReceiver(SetCADPayload.PACKET_ID, ((payload, context) -> {
            var world = context.player().getServerWorld();
            ServerPlayerEntity player = (ServerPlayerEntity) world.getEntity(payload.player());
            ((IEntityCAD)player).setArmor();
            player.sendMessage(Text.literal("Set armor"), true);
        }));

//        ServerPlayNetworking.registerGlobalReceiver(EvolveCADPayload.PACKET_ID, ((payload, context) -> {
//            var world = context.player().getServerWorld();
//            ServerPlayerEntity player = (ServerPlayerEntity) world.getEntity(payload.player());
//            StatHandler stats = player.getStatHandler();
//            ((IEntityCAD)player).evolveCAD(
//                    stats.getStat(Stats.CUSTOM, Stats.DAMAGE_DEALT),
//                    stats.getStat(Stats.CUSTOM, Stats.DAMAGE_TAKEN),
//                    stats.getStat(Stats.CUSTOM, Stats.SPRINT_ONE_CM));
//            player.sendMessage(Text.literal("New Armor Evolution"), true);
//        }));
    }

    private static void registerS2CEvents() {

    }
}
