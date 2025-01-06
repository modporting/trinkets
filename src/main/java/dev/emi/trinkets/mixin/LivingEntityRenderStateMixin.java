package dev.emi.trinkets.mixin;

import dev.emi.trinkets.TrinketEntityRenderState;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;

@Mixin(LivingEntityRenderState.class)
public abstract class LivingEntityRenderStateMixin implements TrinketEntityRenderState {
    @Unique
    private List<Pair<SlotReference, ItemStack>> trinkets;
    @Override
    public void setTrinketList(List<Pair<SlotReference, ItemStack>> list) {
        trinkets = list;
    }

    @Override
    public List<Pair<SlotReference, ItemStack>> getTrinketList() {
        return trinkets;
    }
}
