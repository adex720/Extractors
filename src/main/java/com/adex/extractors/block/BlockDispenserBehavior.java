package com.adex.extractors.block;

import com.adex.extractors.registry.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class BlockDispenserBehavior implements DispenserBehavior {

    public static boolean isValidBlock(World world, BlockState blockState, BlockPos blockPos) {
        if (blockState.hasBlockEntity()) return false;
        if (blockState.isIn(ModTags.INEXTRICABLE_BLOCKS)) return false;
        if (blockState.getBlock().getHardness() == -1.0F) return false;

        if (blockPos.getY() < world.getBottomY()) return false;
        if (blockPos.getY() > world.getTopY() - 1) return false;
        if (!world.getWorldBorder().contains(blockPos)) return false;

        return blockState.getPistonBehavior() == PistonBehavior.NORMAL;
    }

    public static boolean isValidPosForBlock(World world, BlockState blockState, BlockPos blockPos) {
        return blockState.canPlaceAt(world, blockPos);
    }

    public boolean canDispenseAt(BlockPointer blockPointer) {
        World world = blockPointer.getWorld();
        Position position = DispenserBlock.getOutputLocation(blockPointer);
        BlockPos blockPos = BlockPos.ofFloored(position);

        return world.getBlockState(blockPos).isAir();
    }

    public final ItemStack dispense(BlockPointer blockPointer, ItemStack itemStack) {
        if (!canDispenseAt(blockPointer)) return itemStack;

        ItemStack itemStack2 = this.dispenseSilently(blockPointer, itemStack);
        this.playSound(blockPointer);
        this.spawnParticles(blockPointer, blockPointer.getBlockState().get(DispenserBlock.FACING));
        return itemStack2;
    }

    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
        Direction direction = pointer.getBlockState().get(DispenserBlock.FACING);
        Position position = DispenserBlock.getOutputLocation(pointer);
        ItemStack itemStack = stack.split(1);

        World world = pointer.getWorld();
        Block block = Block.getBlockFromItem(itemStack.getItem());
        if (block != Blocks.AIR) {
            BlockPos blockPos = BlockPos.ofFloored(position);
            BlockState blockState = block.getDefaultState();

            if (isValidBlock(world, blockState, blockPos) && isValidPosForBlock(world, blockState, blockPos)) {
                spawnBlock(world, block, blockState, blockPos, stack);
                return stack;
            }
        }
        spawnItem(world, itemStack, 3, direction, position);
        return stack;
    }

    public static void spawnItem(World world, ItemStack stack, int speed, Direction side, Position pos) {
        double d = pos.getX();
        double e = pos.getY();
        double f = pos.getZ();
        if (side.getAxis() == Direction.Axis.Y) {
            e -= 0.125D;
        } else {
            e -= 0.15625D;
        }

        ItemEntity itemEntity = new ItemEntity(world, d, e, f, stack);
        double g = world.random.nextDouble() * 0.1D + 0.2D;
        itemEntity.setVelocity(world.random.nextTriangular((double) side.getOffsetX() * g, 0.0172275D * (double) speed), world.random.nextTriangular(0.2D, 0.0172275D * (double) speed), world.random.nextTriangular((double) side.getOffsetZ() * g, 0.0172275D * (double) speed));
        world.spawnEntity(itemEntity);
    }

    public static void spawnBlock(World world, Block block, BlockState blockState, BlockPos pos, ItemStack itemStack) {
        world.setBlockState(pos, blockState);
        block.onPlaced(world, pos, blockState, null, itemStack);
    }

    protected void playSound(BlockPointer pointer) {
        pointer.getWorld().syncWorldEvent(1000, pointer.getPos(), 0);
    }

    protected void spawnParticles(BlockPointer pointer, Direction side) {
        pointer.getWorld().syncWorldEvent(2000, pointer.getPos(), side.getId());
    }
}
