package litro.dreamland;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

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
}
