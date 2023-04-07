package com.adex.extractors;

import com.adex.extractors.registry.ModBlockEntities;
import com.adex.extractors.registry.ModBlocks;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Extractors implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger(Extractors.class);

    public static final String MOD_ID = "extracs";

    @Override
    public void onInitialize() {
        LOGGER.info("Starting to initialize {}", MOD_ID);

        ModBlocks.registerBlocks();

        ModBlockEntities.registerBlockEntities();


        LOGGER.info("Finished initializing {}", MOD_ID);

    }
}
