package com.adex.extractors.registry;

import com.adex.extractors.block.ExtractorBlock;
import com.adex.extractors.block.InserterBlock;
import com.adex.extractors.util.Util;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final ExtractorBlock EXTRACTOR = new ExtractorBlock(FabricBlockSettings.copyOf(Blocks.DROPPER));
    public static final InserterBlock INSERTER = new InserterBlock(FabricBlockSettings.copyOf(Blocks.DROPPER));

    public static final Item EXTRACTOR_BLOCK_ITEM = new BlockItem(EXTRACTOR, new FabricItemSettings());
    public static final Item INSERTER_BLOCK_ITEM = new BlockItem(INSERTER, new FabricItemSettings());

    public static void registerBlocks() {
        register(EXTRACTOR, EXTRACTOR_BLOCK_ITEM, Util.identifier("extractor"), ItemGroups.REDSTONE);
        register(INSERTER, INSERTER_BLOCK_ITEM, Util.identifier("inserter"), ItemGroups.REDSTONE);

    }

    private static void register(Block block, Item blockItem, String identifierString, ItemGroup itemGroup) {
        Identifier identifier = Util.identifier(identifierString);
        registerBlock(block, identifier);
        registerBlockItem(blockItem, identifier, itemGroup);
    }

    private static void register(Block block, Item blockItem, Identifier identifier, ItemGroup itemGroup) {
        registerBlock(block, identifier);
        registerBlockItem(blockItem, identifier, itemGroup);
    }

    private static void registerBlock(Block block, Identifier identifier) {
        Registry.register(Registries.BLOCK, identifier, block);
    }

    private static void registerBlockItem(Item blockItem, Identifier identifier, ItemGroup itemGroup) {
        Registry.register(Registries.ITEM, identifier, blockItem);
        addToItemGroup(blockItem, itemGroup);
    }

    private static void addToItemGroup(Item item, ItemGroup group) {
        ItemGroupEvents.modifyEntriesEvent(group).register(content -> content.add(item));
    }

}