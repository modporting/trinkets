package dev.emi.trinkets.mixin;

import dev.emi.trinkets.TrinketEntityRenderState;
import dev.emi.trinkets.TrinketFeatureRenderer;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Adds the trinket feature renderer to the list of living entity features
 *
 * @author C4
 * @author powerboat9
 */
@Environment(EnvType.CLIENT)
@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin <T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>> extends EntityRenderer<T, S> implements FeatureRendererContext<S, M> {
    protected LivingEntityRendererMixin(EntityRendererFactory.Context context) {
        super(context);
    }

    @Invoker("addFeature")
    public abstract boolean invokeAddFeature(FeatureRenderer<?, ?> feature);

	@Inject(at = @At("RETURN"), method = "<init>")
	public void init(EntityRendererFactory.Context ctx, EntityModel<?> model, float shadowRadius, CallbackInfo info) {
        this.invokeAddFeature(new TrinketFeatureRenderer<>((LivingEntityRenderer<T, S, M>) (Object) this));
	}
    @Inject(method = "updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V",
    at = @At("HEAD"))
    public void addTrinketListToRenderState(T livingEntity, S livingEntityRenderState, float f, CallbackInfo ci){
        List<Pair<SlotReference, ItemStack>> trinkets = new ArrayList<>();
        TrinketsApi.getTrinketComponent(livingEntity).ifPresent(component ->
                component.forEach((slotReference, stack) ->
                        trinkets.add(new Pair<>(slotReference, stack))
                )
        );
        ((TrinketEntityRenderState) livingEntityRenderState).setTrinketList(trinkets);
    }
}
