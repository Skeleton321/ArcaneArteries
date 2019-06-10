package net.arsenalnetwork.arcanearteries;

import net.arsenalnetwork.arcanearteries.client.model.RegisterItemModels;
import net.arsenalnetwork.arcanearteries.common.init.ModItems;
import net.arsenalnetwork.arcanearteries.common.items.ItemBotaniaSacrifice;
import net.arsenalnetwork.arcanearteries.utilities.ModReference;
import net.arsenalnetwork.arcanearteries.utilities.ModUtil;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = ModReference.MOD_ID)
public class EventSubscriber
{
    // Logger
    public static final Logger LOGGER = LogManager.getLogger();

    /**
     * Register Items
     **/
    @SubscribeEvent
    public static void onRegisterItemsEvent(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        ForgeRegistries.BLOCKS.getValuesCollection().stream().filter((block) -> block.getRegistryName().getNamespace().equals(ModReference.MOD_ID)).forEach((ourBlock) -> {
            registry.register(ModUtil.setRegistryNames(new ItemBlock(ourBlock), ourBlock.getRegistryName()));
        });

        registry.register(new ItemBotaniaSacrifice("botaniasacrifice"));

        LOGGER.info("Registered items");

    }

    /**
     * Register Blocks
     **/
    @SubscribeEvent
    public static void onRegisterBlocksEvent(final RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();

        LOGGER.info("Registered blocks");
    }

    @SubscribeEvent
    public static void onRegisterModelsEvent(final ModelRegistryEvent event) {
        OBJLoader.INSTANCE.addDomain("arsenalmod");
        LOGGER.info("Registered entity renderers");
        ForgeRegistries.BLOCKS.getValuesCollection().stream().filter(block -> block.getRegistryName().getNamespace().equals("arcanearteries")).forEach(ourBlock -> registerItemBlockModel(ourBlock));
        ForgeRegistries.ITEMS.getValuesCollection().stream().filter(item -> item.getRegistryName().getNamespace().equals("arcanearteries")).forEach(ourItem -> registerItemModel(ourItem));
        LOGGER.info("Registered models");

        LOGGER.info("Registered tile entity special renderers");
    }

    private static void registerItemBlockModel(final Block block) {
        registerItemBlockModel(block, "normal");
    }

    private static void registerItemModel(final Item item) {
        registerItemModel(item, "inventory");
    }

    private static void registerItemBlockModel(final Block block, final String variant) {
        registerItemModel(Item.getItemFromBlock(block), variant);
    }

    private static void registerItemModel(final Item item, final String variant) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), variant));
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        RegisterItemModels.registerItemModels(event);
    }
}
