package com.hamusuke.tooltipscroller.mixin;

import com.hamusuke.tooltipscroller.TooltipScroller;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Screen.class)
public class ScreenMixin {
    @ModifyVariable(method = "renderTooltipInternal", at = @At(value = "STORE", ordinal = 1), ordinal = 5)
    private int renderTooltipInternal(int m) {
        return m + TooltipScroller.AMOUNT.get();
    }

    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    private void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (TooltipScroller.UP.matches(keyCode, scanCode)) {
            this.slide(1);
            cir.setReturnValue(true);
            cir.cancel();
        } else if (TooltipScroller.DOWN.matches(keyCode, scanCode)) {
            this.slide(-1);
            cir.setReturnValue(true);
            cir.cancel();
        } else if (TooltipScroller.RESET.matches(keyCode, scanCode)) {
            TooltipScroller.AMOUNT.set(0);
            cir.setReturnValue(true);
            cir.cancel();
        }
    }

    private void slide(int direction) {
        if (direction > 0 && TooltipScroller.AMOUNT.get() >= Integer.MAX_VALUE - direction * 9) {
            TooltipScroller.AMOUNT.set(Integer.MAX_VALUE);
            return;
        } else if (direction < 0 && TooltipScroller.AMOUNT.get() <= Integer.MIN_VALUE - direction * 9) {
            TooltipScroller.AMOUNT.set(Integer.MIN_VALUE);
            return;
        }

        TooltipScroller.AMOUNT.set(TooltipScroller.AMOUNT.get() + direction * 9);
    }
}
