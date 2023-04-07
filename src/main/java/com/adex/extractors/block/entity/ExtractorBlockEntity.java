package com.adex.extractors.block.entity;

import com.adex.extractors.mixin.DispenserBlockEntityInvoker;
import com.adex.extractors.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class ExtractorBlockEntity extends DispenserBlockEntity {

    public ExtractorBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.EXTRACTOR, blockPos, blockState);
    }

    protected Text getContainerName() {
        return Text.translatable("container.extractor");
    }

    public static boolean hasEmptySlot(DispenserBlockEntity entity) {
        DefaultedList<ItemStack> inventory = ((DispenserBlockEntityInvoker) entity).invokeGetInvStackList();
        for (ItemStack itemStack : inventory) {
            if (itemStack.isEmpty()) return true;
        }
        return false;
    }

    public static int chooseEmptySlot(DispenserBlockEntity entity, Random random) {
        DefaultedList<ItemStack> inventory = ((DispenserBlockEntityInvoker) entity).invokeGetInvStackList();
        int slot = -1;
        int found = 1;

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).isEmpty() && random.nextInt(found++) == 0) {
                slot = i;
            }
        }

        return slot;
    }
}
