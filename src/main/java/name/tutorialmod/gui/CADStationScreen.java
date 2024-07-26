package name.tutorialmod.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import name.tutorialmod.TutorialMod;
import name.tutorialmod.components.CADComponent;
import name.tutorialmod.networking.payload.SetCADPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;


public class CADStationScreen extends HandledScreen<CADStationScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(TutorialMod.MOD_ID, "textures/gui/cad_selection_station.png");
    private static final ComponentKey<CADComponent> KEY = ComponentRegistry.getOrCreate(new Identifier("tutorialmod", "cad"), CADComponent.class);


    public ButtonWidget shido = ButtonWidget.builder(Text.literal("Shido"), (button) -> {
        MinecraftClient mc = MinecraftClient.getInstance();
        if(!KEY.get(mc.player).returnHasCAD()) {
            this.client.player.sendMessage(Text.literal("Set Shido Armor"));
            ClientPlayNetworking.send(
                    new SetCADPayload(mc.player.getUuid())
            );
        }
        else {
            this.client.player.sendMessage(Text.literal("Already Has Shido Armor"));
        }
        button.active = false;
    }).build();

    public ButtonWidget honoris = ButtonWidget.builder(Text.literal("Honoris"), (button) -> {
        this.client.player.sendMessage(Text.literal("Set Honoris Armor"));
        button.active = false;
    }).build();

    public ButtonWidget merlin = ButtonWidget.builder(Text.literal("Merlin"), (button) -> {
        this.client.player.sendMessage(Text.literal("Set Merlin Armor"));
        button.active = false;
    }).build();

    public CADStationScreen(CADStationScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    @Override
    protected void init() {
        super.init();
        shido.setPosition((width - backgroundWidth) / 2 + 7, (height - backgroundHeight) / 2 + 5);
        shido.setDimensions(50, 20);
        honoris.setPosition((width - backgroundWidth) / 2 + 7, (height - backgroundHeight) / 2 + 27);
        honoris.setDimensions(50, 20);
        merlin.setPosition((width - backgroundWidth) / 2 + 7, (height - backgroundHeight) / 2 + 49);
        merlin.setDimensions(50, 20);
        addDrawableChild(shido);
        addDrawableChild(honoris);
        addDrawableChild(merlin);
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2 + 20;

        MinecraftClient mc = MinecraftClient.getInstance();
        if(KEY.get(mc.player).returnHasCAD()) {
           shido.active = false;
           honoris.active = false;
           merlin.active = false;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
    }
}
