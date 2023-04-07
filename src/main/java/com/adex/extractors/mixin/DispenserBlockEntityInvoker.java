package com.adex.extractors.mixin;

import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DispenserBlockEntity.class)
public interface DispenserBlockEntityInvoker {
    @Invoker("getInvStackList")
    DefaultedList<ItemStack> invokeGetInvStackList();
}
