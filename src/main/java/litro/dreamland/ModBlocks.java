package litro.dreamland;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {

    // 1. Объявляем кастомные альфа-блоки с использованием свойств Mojang (BlockBehaviour)
    public static final Block ALPHA_COBBLESTONE = registerBlock("alpha_cobblestone", 
        new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE))); // копируем свойства обычного булыжника

    public static final Block ALPHA_GRASS_BLOCK = registerBlock("alpha_grass_block", 
        new Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK))); // копируем свойства блока травы


    // Вспомогательный метод для регистрации блока и его предмета
    private static Block registerBlock(String name, Block block) {
        ResourceLocation id = new ResourceLocation("dreamland", name); // Ваш MOD_ID и имя блока
        
        // Регистрируем предмет для блока (чтобы он был в инвентаре)
        Registry.register(BuiltInRegistries.ITEM, id, new BlockItem(block, new Item.Properties()));
        
        // Регистрируем сам блок
        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    // Метод для вызова в главном классе
    public static void registerModBlocks() {
        // Пустой вызов, который просто триггерит инициализацию статических полей выше
    }
}
