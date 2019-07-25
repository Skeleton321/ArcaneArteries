package net.arsenalnetwork.arcanearteries;

import net.arsenalnetwork.arcanearteries.client.config.ConfigFile;
import net.arsenalnetwork.arcanearteries.client.modfixes.ModFixesMaster;
import net.arsenalnetwork.arcanearteries.client.modfixes.thaumcraft.TestFix;
import net.arsenalnetwork.arcanearteries.client.research.ArcaneResearch;
import net.arsenalnetwork.arcanearteries.common.CommonProxy;
import net.arsenalnetwork.arcanearteries.utilities.ModReference;
import net.minecraft.client.Minecraft;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.io.IOException;

/**
 * Created by MaxIsH0t on June 9, 2019 | Copyright 2019 Arsenal Network
 */
@Mod(modid = ModReference.MOD_ID, name = ModReference.MOD_NAME, version = ModReference.MOD_VERSION, dependencies = ModReference.MOD_DEP)
public class ArcaneArteries
{
    public static Configuration config;

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(ModReference.MOD_ID)
    public static ArcaneArteries INSTANCE;

    @SidedProxy(clientSide = "net.arsenalnetwork.arcanearteries.client.ClientProxy", serverSide = "net.arsenalnetwork.arcanearteries.common.CommonProxy")
    public static CommonProxy PROXY;

    /**
     * Do your mod setup. Build whatever data structures you care about. Register recipes, send FMLInterModComms messages to other mods.
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModReference.LOGGER.info(ModReference.MOD_ID + ":init");
        PROXY.init(event);
        MinecraftForge.EVENT_BUS.register(ArcaneArteries.INSTANCE);
    }

    /**
     * Run before anything else. <s>Read your config, create blocks, items, etc, and register them with the GameRegistry</s>
     *
     * @see {@link net.minecraftforge.common.ForgeModContainer#preInit(FMLPreInitializationEvent) ForgeModContainer.preInit}
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) throws IOException {
        ModReference.LOGGER.info(ModReference.MOD_ID + ":preInit");
        PROXY.preInit(event);

        config = new Configuration(event.getSuggestedConfigurationFile());
        ConfigFile.INSTANCE.syncConfig();

        ModReference.DEV_ENVIRONMENT = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
        if(ModReference.DEV_ENVIRONMENT)
        {
            System.out.println("Started [ArcaneArteries] mod in development environment");
        } else {
            System.out.println("Started [ArcaneArteries] mod outside of development environment");
        }

        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Mod compatibility, or anything which depends on other mods’ init phases being finished.
     *
     * @see {@link net.minecraftforge.common.ForgeModContainer#postInit(FMLPostInitializationEvent) ForgeModContainer.postInit}
     */
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ModReference.LOGGER.info(ModReference.MOD_ID + ":postInit");
        PROXY.postInit(event);

        registerModFixes();
        ModFixesMaster.enableModFixes();
        ArcaneResearch.addResearch();
    }

    /**
     * Register your mod-fixes here
     */
    private void registerModFixes()
    {
        ModFixesMaster.registerModFix(new TestFix());
    }

    /**
     * Registers commands and server sided regions
     * @param event
     */
    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        System.out.println( ModReference.MOD_NAME + " | Server Started");
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals("arcanearteries"))
        {
            ConfigFile.INSTANCE.syncConfig();
        }
    }

}
