package litro.dreamland.world.gen.structure;

import litro.dreamland.Dreamland;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class DreamlandStructures {

    // Привязываем кодек нашей структуры к типу структуры
    public static final StructureType<StoneblockStructure> STONEBLOCK_TYPE = () -> StoneblockStructure.CODEC;

    public static void registerStructures() {
        Registry.register(
            BuiltInRegistries.STRUCTURE_TYPE,
            Dreamland.id("stoneblock"),
            STONEBLOCK_TYPE
        );
    }
}
