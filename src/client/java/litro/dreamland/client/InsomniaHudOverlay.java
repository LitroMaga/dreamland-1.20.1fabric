package litro.dreamland.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class InsomniaHudOverlay implements HudRenderCallback {

    private static final ResourceLocation EYE_NEUTRAL = 
        new ResourceLocation("dreamland", "textures/gui/eye_neutral.png");
    private static final ResourceLocation EYE_TIRED = 
        new ResourceLocation("dreamland", "textures/gui/eye_tired.png");
    private static final ResourceLocation EYE_DEPRIVATION = 
        new ResourceLocation("dreamland", "textures/gui/eye_deprivation.png");
    private static final ResourceLocation EYE_EXHAUSTION = 
        new ResourceLocation("dreamland", "textures/gui/eye_exhaustion.png");

    @Override
    public void onHudRender(GuiGraphics drawContext, float tickDelta) {
        Minecraft client = Minecraft.getInstance();
        
        if (client.player == null || client.level == null || client.options.hideGui) {
            return;
        }

        // Получаем время мира с момента последнего сна. 
        // Метод getDayTime() идеально синхронизирован между клиентом и сервером.
        // Берем остаток от деления на 48000, чтобы счетчик шел в цикле двух дней.
        int ticksSinceRest = client.player.getStats().getValue(
            net.minecraft.stats.Stats.CUSTOM.get(net.minecraft.stats.Stats.TIME_SINCE_REST)
        ) % 48000;


        // Твоя логика распределения фаз по тикам
                    ResourceLocation textureToRender;

            if (ticksSinceRest >= 42000) {
                textureToRender = EYE_EXHAUSTION;      // 42000–48000 тиков
            } else if (ticksSinceRest >= 35000) {
                textureToRender = EYE_DEPRIVATION;     // 35000–41999 тиков
            } else if (ticksSinceRest >= 24000) {
                textureToRender = EYE_TIRED;           // 24000–34999 тиков
            } else {
                textureToRender = EYE_NEUTRAL;         // 0–23999 тиков (начальное состояние)
            }

        if (textureToRender != null) {
            int screenWidth = client.getWindow().getGuiScaledWidth();
            int screenHeight = client.getWindow().getGuiScaledHeight();
            
            int size = 32; 
            int x = (screenWidth / 2) - 16; 
            int y = screenHeight - 60; 

            RenderSystem.setShaderTexture(0, textureToRender);
            drawContext.blit(textureToRender, x, y, 0, 0, size, size, size, size);
        }
    }
}
