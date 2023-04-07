package com.adex.extractors.block;

import com.adex.extractors.registry.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class BlockExtractorDispenserBehaviour implements DispenserBehavior {

    public boolean isValidBlock(BlockState blockState) {
        if (blockState.isAir()) return false;

        if (blockState.hasBlockEntity()) return false;
        if (blockState.isIn(ModTags.INEXTRICABLE_BLOCKS)) return false;
        if (blockState.getBlock().getHardness() == -1.0F) return false;

        return blockState.getPistonBehavior() == PistonBehavior.NORMAL;
    }

    public boolean isValidPos(World world, BlockPos blockPos) {
        if (blockPos.getY() < world.getBottomY()) return false;
        if (blockPos.getY() > world.getTopY() - 1) return false;
        return world.getWorldBorder().contains(blockPos);
    }

    public final ItemStack dispense(BlockPointer blockPointer, ItemStack itemStack) {
        World world = blockPointer.getWorld();
        BlockPos blockPos = blockPointer.getPos();
        if (!isValidPos(world, blockPos)) return itemStack;

        ItemStack itemStack2 = this.dispenseSilently(blockPointer);
        if (!itemStack2.isEmpty()) {
            this.playSound(blockPointer);
            this.spawnParticles(blockPointer, blockPointer.getBlockState().get(DispenserBlock.FACING));
        }
        return itemStack2;
    }

    protected ItemStack dispenseSilently(BlockPointer pointer) {
        Position position = DispenserBlock.getOutputLocation(pointer);
        BlockPos blockPos = BlockPos.ofFloored(position);

        World world = pointer.getWorld();
        BlockState blockState = world.getBlockState(blockPos);
        Block block = blockState.getBlock();

        if (!isValidBlock(blockState)) return ItemStack.EMPTY;

        world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
        return new ItemStack(block);
    }

    protected void playSound(BlockPointer pointer) {
        pointer.getWorld().syncWorldEvent(1000, pointer.getPos(), 0);
    }

    protected void spawnParticles(BlockPointer pointer, Direction side) {
        pointer.getWorld().syncWorldEvent(2000, pointer.getPos(), side.getId());
    }
}
