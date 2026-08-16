package litro.dreamland;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.level.Level;

public class ModEvents {

    public static void registerEvents() {
        // Проверка бессонницы каждый тик сервера (48000 тиков = 2 дня)
        ServerTickEvents.START_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                // Проверяем, что игрок находится в обычном мире
                if (player.level().dimension() == Level.OVERWORLD) {
                    
                    // Получаем время без сна для конкретного игрока
                    int ticksSinceLastSleep = player.getStats().getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));

                    if (ticksSinceLastSleep >= 48000) {
                        // Сбрасываем счётчик бессонницы
                        player.getStats().setValue(player, Stats.CUSTOM.get(Stats.TIME_SINCE_REST), 0);
                        
                        // Телепортируем в Альфа-Память
                        ModDimensions.teleportToAlphaMemory(player);
                    }
                }
            }
        });
    }
}
