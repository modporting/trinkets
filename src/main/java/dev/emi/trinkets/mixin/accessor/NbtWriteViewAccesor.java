package dev.emi.trinkets.mixin.accessor;

import com.mojang.serialization.DynamicOps;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.storage.NbtReadView;
import net.minecraft.storage.NbtWriteView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

//TODO Remove this when data is ported properly
@Mixin(NbtWriteView.class)
public interface NbtWriteViewAccesor {
    @Accessor("ops")
    DynamicOps<NbtElement> getOps();
}
