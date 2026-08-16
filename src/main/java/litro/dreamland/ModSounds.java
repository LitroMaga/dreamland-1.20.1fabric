package litro.dreamland;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {
    public static final ResourceLocation BIOME_AMBIENT_ID = new ResourceLocation(Dreamland.MOD_ID, "alpha_memory_ambient");
    
    // Указываем фиксированный радиус затухания в 160 блоков!
    public static final SoundEvent BIOME_AMBIENT = SoundEvent.createFixedRangeEvent(BIOME_AMBIENT_ID, 160.0F);

    public static void registerSounds() {
        Registry.register(BuiltInRegistries.SOUND_EVENT, BIOME_AMBIENT_ID, BIOME_AMBIENT);
    }
}
