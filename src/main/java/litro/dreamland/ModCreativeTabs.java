package litro.dreamland;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {

    // 1. Объявляем кастомную вкладку
    public static final CreativeModeTab DREAMLAND_TAB = Registry.register(
        BuiltInRegistries.CREATIVE_MODE_TAB,
        Dreamland.id("dreamland_tab"), // Используем ваш метод id()
        CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable("itemGroup.dreamland.dreamland_tab")) // Ключ для перевода названия
            .icon(() -> new ItemStack(ModBlocks.ALPHA_GRASS_BLOCK)) // Иконка вкладки (альфа-трава)
            .displayItems((itemDisplayParameters, output) -> {
                // 2. Перечисляем предметы, которые будут внутри этой вкладки
                output.accept(ModBlocks.ALPHA_GRASS_BLOCK);
                output.accept(ModBlocks.ALPHA_COBBLESTONE);
                output.accept(ModBlocks.LIMB_BLOCK);
            })
            .build()
    );

    // Метод для вызова в главном классе, чтобы затриггерить регистрацию
    public static void registerCreativeTabs() {
        // Достаточно просто вызвать класс, чтобы статические поля инициализировались
    }
}


