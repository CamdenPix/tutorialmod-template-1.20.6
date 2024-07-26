package name.tutorialmod.networking.payload;

import name.tutorialmod.TutorialMod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record SetCADPayload(UUID player) implements CustomPayload {
    public static final CustomPayload.Id<SetCADPayload> PACKET_ID = new CustomPayload.Id<>(new Identifier(TutorialMod.MOD_ID, "setcad"));
    public static final PacketCodec<RegistryByteBuf, SetCADPayload> PACKET_CODEC =
            Uuids.PACKET_CODEC.xmap(SetCADPayload::new, SetCADPayload::equip).cast();

    public UUID equip(){
        return player;
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}
