package litro.dreamland;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundAwardStatsPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.world.level.Level;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.world.entity.player.Player;


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
                        player.getStats().setValue((Player) player, Stats.CUSTOM.get(Stats.TIME_SINCE_REST), 0);

                        // Телепортируем в Альфа-Память
                        ModDimensions.teleportToAlphaMemory(player);
                    }
                }
            }
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
                    dispatcher.register(Commands.literal("insomnia")
                    .requires(source -> source.hasPermission(2))
                    .then(Commands.argument("ticks", IntegerArgumentType.integer(0, 48000))
                    .executes(context -> {
                        ServerPlayer player = context.getSource().getPlayerOrException();
                        int ticks = IntegerArgumentType.getInteger(context, "ticks");
                        
                        // Устанавливаем статистику на сервере
                        player.getStats().setValue((Player) player, Stats.CUSTOM.get(Stats.TIME_SINCE_REST), ticks);

                        // Отправляем обновление клиенту через правильную карту FastUtil
                        Object2IntMap<Stat<?>> statsMap = new Object2IntOpenHashMap<>();
                        statsMap.put(Stats.CUSTOM.get(Stats.TIME_SINCE_REST), ticks);
                        player.connection.send(new ClientboundAwardStatsPacket(statsMap));

                        
                        context.getSource().sendSuccess(() -> Component.literal("Бессонница установлена на: " + ticks), false);
                        return 1;
                            })
                        )
                    );
                });

    }
}
