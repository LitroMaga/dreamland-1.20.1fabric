package litro.dreamland.client;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class DreamlandClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Подключаем наш новый GUI файл к циклу отрисовки игры
        HudRenderCallback.EVENT.register(new InsomniaHudOverlay());
    }
}