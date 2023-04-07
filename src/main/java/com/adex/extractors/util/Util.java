package com.adex.extractors.util;

import com.adex.extractors.Extractors;
import net.minecraft.util.Identifier;

public class Util {

    public static Identifier identifier(String name) {
        return new Identifier(Extractors.MOD_ID, name);
    }

    public static String identifierString(String name) {
        return identifier(name).toString();
    }

}
