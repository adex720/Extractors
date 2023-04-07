package com.adex.extractors.registry;

import com.adex.extractors.block.entity.ExtractorBlockEntity;
import com.adex.extractors.block.entity.InserterBlockEntity;
import com.adex.extractors.util.Util;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntities {

    public static final BlockEntityType<ExtractorBlockEntity> EXTRACTOR = BlockEntityType.Builder.create(ExtractorBlockEntity::new, ModBlocks.EXTRACTOR).build(net.minecraft.util.Util.getChoiceType(TypeReferences.BLOCK_ENTITY, Util.identifierString("extractor")));
    public static final BlockEntityType<InserterBlockEntity> INSERTER = BlockEntityType.Builder.create(InserterBlockEntity::new, ModBlocks.INSERTER).build(net.minecraft.util.Util.getChoiceType(TypeReferences.BLOCK_ENTITY, Util.identifierString("inserter")));

    public static void registerBlockEntities() {
        Registry.register(Registries.BLOCK_ENTITY_TYPE, Util.identifier("extractor"), EXTRACTOR);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, Util.identifier("inserter"), INSERTER);
    }
}
