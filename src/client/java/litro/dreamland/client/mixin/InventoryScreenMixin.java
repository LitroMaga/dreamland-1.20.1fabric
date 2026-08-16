package litro.dreamland.client.mixin;

import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.gui.GuiGraphics;
import org.joml.Quaternionf; // Новый импорт

@Mixin(InventoryScreen.class)
public class InventoryScreenMixin {

    @Inject(
        method = "renderEntityInInventory(Lnet/minecraft/client/gui/GuiGraphics;IIILorg/joml/Quaternionf;Lorg/joml/Quaternionf;Lnet/minecraft/world/entity/LivingEntity;)V",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void onRenderEntity(GuiGraphics guiGraphics, int x, int y, int scale, Quaternionf q1, Quaternionf q2, LivingEntity entity, CallbackInfo ci) {
        // Отменяем рендер куклы
        ci.cancel();
    }
}
