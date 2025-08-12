package dev.emi.trinkets.mixin.accessor;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.storage.NbtReadView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
//TODO Remove this when data is ported properly
@Mixin(NbtReadView.class)
public interface NbtReadViewAccesor {
    @Accessor("nbt")
    NbtCompound getNbt();
}
