package litro.dreamland;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;

public class ModDimensions {
    // Ключ для самого измерения (мира)
    public static final ResourceKey<Level> ALPHA_MEMORY_KEY = ResourceKey.create(
            Registries.DIMENSION,
            new ResourceLocation(Dreamland.MOD_ID, "alpha_memory")
    );
    
    // Ключ для типа измерения (настроек физики мира)
    public static final ResourceKey<DimensionType> ALPHA_MEMORY_TYPE_KEY = ResourceKey.create(
            Registries.DIMENSION_TYPE,
            new ResourceLocation(Dreamland.MOD_ID, "alpha_memory_type")
    );

    public static void registerDimensions() {
        Dreamland.LOGGER.info("Registering dimensions for " + Dreamland.MOD_ID);
    }

    // Метод для перемещения игрока
        public static void teleportToAlphaMemory(ServerPlayer player) {
        ServerLevel targetDim = player.getServer().getLevel(ALPHA_MEMORY_KEY);
        if (targetDim != null && player.level() != targetDim) {

            double spawnX = targetDim.getSharedSpawnPos().getX() + 0.5; // +0.5 чтобы игрок встал ровно по центру блока
            double spawnZ = targetDim.getSharedSpawnPos().getZ() + 0.5;
            
            double spawnY = 130.0; 

            // Телепортируем игрока на безопасную высоту
            player.teleportTo(targetDim, spawnX, spawnY, spawnZ, player.getYRot(), player.getXRot());
        }
    }

}
