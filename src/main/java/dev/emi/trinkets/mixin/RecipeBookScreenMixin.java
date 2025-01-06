package dev.emi.trinkets.mixin;

import dev.emi.trinkets.TrinketScreen;
import dev.emi.trinkets.TrinketScreenManager;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.RecipeBookScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookProvider;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeBookScreen.class)
public abstract class RecipeBookScreenMixin <T extends AbstractRecipeScreenHandler> extends HandledScreen<T> implements RecipeBookProvider, TrinketScreen {
    @Shadow @Final private RecipeBookWidget<?> recipeBook;

    public RecipeBookScreenMixin(T handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }
    @Inject(at = @At("HEAD"), method = "isClickOutsideBounds", cancellable = true)
    private void isClickOutsideBounds(double mouseX, double mouseY, int left, int top, int button, CallbackInfoReturnable<Boolean> info) {
        if (TrinketScreenManager.isClickInsideTrinketBounds(mouseX, mouseY)) {
            info.setReturnValue(false);
        }
    }
    @Override
    public boolean trinkets$isRecipeBookOpen() {
        return this.recipeBook.isOpen();
    }

}
