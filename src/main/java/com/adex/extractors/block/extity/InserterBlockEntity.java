package com.adex.extractors.block.extity;

import com.adex.extractors.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class InserterBlockEntity extends DispenserBlockEntity {

    public InserterBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.INSERTER, blockPos, blockState);
    }

    protected Text getContainerName() {
        return Text.translatable("container.inserter");
    }
}
