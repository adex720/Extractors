package com.adex.extractors.block.entity;

import com.adex.extractors.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class ExtractorBlockEntity extends DispenserBlockEntity {

    public ExtractorBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.EXTRACTOR, blockPos, blockState);
    }

    protected Text getContainerName() {
        return Text.translatable("container.extractor");
    }
}
