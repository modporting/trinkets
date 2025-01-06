package dev.emi.trinkets;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;

import java.util.List;

public interface TrinketEntityRenderState {
    void setTrinketList(List<Pair<SlotReference, ItemStack>> list);
    List<Pair<SlotReference, ItemStack>> getTrinketList();
}
