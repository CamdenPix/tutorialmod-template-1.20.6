package name.tutorialmod.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;

import static name.tutorialmod.TutorialMod.CAD_STATION_SCREEN_HANDLER_TYPE;

public class CADStationScreenHandler extends ScreenHandler {
    public CADStationScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    public CADStationScreenHandler(int syncId, PlayerInventory playerInventory, Object o) {
        super(CAD_STATION_SCREEN_HANDLER_TYPE, syncId);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }


}
