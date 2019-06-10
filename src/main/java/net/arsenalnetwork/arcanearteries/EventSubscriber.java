package net.arsenalnetwork.arcanearteries;

import net.arsenalnetwork.arcanearteries.common.items.ItemBotaniaSacrifice;
import net.arsenalnetwork.arcanearteries.utilities.ModReference;
import net.arsenalnetwork.arcanearteries.utilities.ModUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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

        registry.register(new ItemBotaniaSacrifice("item_botania_sacrifice"));

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
}
