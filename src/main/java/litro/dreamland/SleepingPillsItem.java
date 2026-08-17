package litro.dreamland;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.Nullable;
import net.minecraft.ChatFormatting;


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

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        // Берём текст из JSON, красим в тёмно-красный (DARK_RED) и делаем курсивом (ITALIC)
        tooltip.add(Component.translatable("item.dreamland.sleeping_pills.tooltip")
                .withStyle(ChatFormatting.DARK_RED, ChatFormatting.ITALIC));
        
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
