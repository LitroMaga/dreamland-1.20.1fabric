package litro.dreamland;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SleepingPillsItem extends Item {
    public SleepingPillsItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        // Перемещаем игрока только на стороне сервера
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            ModDimensions.teleportToAlphaMemory(serverPlayer);
            
            // Забираем 1 таблетку, если игрок не в креативе
            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}
