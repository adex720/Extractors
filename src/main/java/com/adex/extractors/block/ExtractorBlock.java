package com.adex.extractors.block;

import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.item.ItemStack;

public class ExtractorBlock extends DispenserBlock {
    private static final DispenserBehavior BEHAVIOR = new BlockDispenserBehavior();

    public ExtractorBlock(Settings settings) {
        super(settings);
    }

    protected DispenserBehavior getBehaviorForItem(ItemStack stack) {
        return BEHAVIOR;
    }
}
