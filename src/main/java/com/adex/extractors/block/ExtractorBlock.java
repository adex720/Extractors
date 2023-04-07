package com.adex.extractors.block;

import com.adex.extractors.block.entity.ExtractorBlockEntity;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointerImpl;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.event.GameEvent;

public class ExtractorBlock extends DispenserBlock {
    private static final DispenserBehavior BEHAVIOR = new BlockExtractorDispenserBehaviour();

    public ExtractorBlock(Settings settings) {
        super(settings);
    }

    protected DispenserBehavior getBehaviorForItem(ItemStack stack) {
        return BEHAVIOR;
    }

    @Override
    protected void dispense(ServerWorld world, BlockPos pos) {
        BlockPointerImpl blockPointerImpl = new BlockPointerImpl(world, pos);
        DispenserBlockEntity blockEntity = blockPointerImpl.getBlockEntity();
        if (!ExtractorBlockEntity.hasEmptySlot(blockEntity)) {
            world.syncWorldEvent(1001, pos, 0);
            world.emitGameEvent(null, GameEvent.DISPENSE_FAIL, pos);
        } else {
            blockEntity.addToFirstFreeSlot(BEHAVIOR.dispense(blockPointerImpl, ItemStack.EMPTY));
        }
    }
}
