package litro.dreamland.world.gen.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import java.util.Optional;

public class StoneblockStructure extends Structure {
    // Правильный кодек для 1.20.1 Mojang Mappings
    public static final Codec<StoneblockStructure> CODEC = RecordCodecBuilder.create(instance -> 
        instance.group(settingsCodec(instance)).apply(instance, StoneblockStructure::new)
    );

    public StoneblockStructure(Structure.StructureSettings settings) {
        super(settings);
    }

    @Override
    public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext context) {
        return Optional.empty();
    }

    @Override
    public StructureType<?> type() {
        return DreamlandStructures.STONEBLOCK_TYPE;
    }
}
