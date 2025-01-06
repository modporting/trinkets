package dev.emi.trinkets;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;

import java.util.List;

public class TrinketFeatureRenderer<M extends EntityModel<? super S>, S extends LivingEntityRenderState> extends FeatureRenderer<S, M> {


	public TrinketFeatureRenderer(FeatureRendererContext<S, M> context) {
		super(context);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, S state, float limbAngle, float limbDistance) {
		List<Pair<SlotReference, ItemStack>> list = ((TrinketEntityRenderState) state).getTrinketList();
		list.forEach(slotReferenceItemStackPair -> {
			TrinketRendererRegistry.getRenderer(slotReferenceItemStackPair.getRight().getItem()).ifPresent(renderer -> {
				matrices.push();
				renderer.render(slotReferenceItemStackPair.getRight(), slotReferenceItemStackPair.getLeft(), this.getContextModel(), matrices, vertexConsumers,
						light, state, limbAngle, limbDistance, state.yawDegrees, state.pitch);
				matrices.pop();
			});
		});
	}

}
