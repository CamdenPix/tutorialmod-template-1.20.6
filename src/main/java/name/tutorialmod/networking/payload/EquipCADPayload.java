package name.tutorialmod.networking.payload;

import name.tutorialmod.TutorialMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record EquipCADPayload(UUID player) implements CustomPayload {
    public static final CustomPayload.Id<EquipCADPayload> PACKET_ID = new CustomPayload.Id<>(new Identifier(TutorialMod.MOD_ID, "equipcad"));
    public static final PacketCodec<RegistryByteBuf, EquipCADPayload> PACKET_CODEC =
            Uuids.PACKET_CODEC.xmap(EquipCADPayload::new, EquipCADPayload::equip).cast();

    //TODO: return actual equipment info
    public UUID equip(){
        return player;
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}
