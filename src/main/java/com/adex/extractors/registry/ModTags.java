package com.adex.extractors.registry;

import com.adex.extractors.util.Util;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class ModTags {

    public static final TagKey<Block> INEXTRICABLE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, Util.identifier("inextricable_blocks"));

    public static void registerTags(){
    }

}
