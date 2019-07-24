package net.arsenalnetwork.arcanearteries.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModReference
{
    // Logger
    public static final Logger LOGGER = LogManager.getLogger();

    // Dev Related
    public static boolean DEV_ENVIRONMENT;

    // Mod Info
    public static final String MOD_ID = "arcanearteries";
    public static final String MOD_NAME = "Arcane Arteries";
    public static final String MOD_VERSION = "0.0.1 - Beta";
    public static final String MOD_DEP  = "required-after:baubles@[1.12-1.5.2,);required-after:bloodmagic@[1.12.2-2.4.1-103,);required-after:guideapi@[1.12-2.1.8-63,);required-after:thaumcraft@[1.12.2-6.1.BETA26,);required-after:forge@[14.23.5.2838,);";
}
