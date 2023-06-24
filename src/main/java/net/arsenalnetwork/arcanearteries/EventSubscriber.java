package net.arsenalnetwork.arcanearteries;

import net.arsenalnetwork.arcanearteries.client.config.ConfigFile;
import net.arsenalnetwork.arcanearteries.common.blocks.BlockBloodRuneAA;
import net.arsenalnetwork.arcanearteries.common.entity.EntityGolem;
import net.arsenalnetwork.arcanearteries.common.items.*;
import net.arsenalnetwork.arcanearteries.common.items.baubles.ItemSubCollar;
import net.arsenalnetwork.arcanearteries.common.items.scribes.ItemBloodwell;
import net.arsenalnetwork.arcanearteries.utilities.ModReference;
import net.arsenalnetwork.arcanearteries.utilities.ModUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thaumcraft.common.golems.client.RenderThaumcraftGolem;

@Mod.EventBusSubscriber(modid = ModReference.MOD_ID)
public class EventSubscriber {

    // Logger
    public static final Logger LOGGER = LogManager.getLogger();

    /**
     * Register Items
     **/
    @SubscribeEvent
    public static void onRegisterItemsEvent(final RegistryEvent.Register<Item> event)
    {
        final IForgeRegistry<Item> registry = event.getRegistry();

        ForgeRegistries.BLOCKS.getValuesCollection().stream().filter((block) -> block.getRegistryName().getNamespace().equals(ModReference.MOD_ID)).forEach((ourBlock) -> {
            registry.register(ModUtil.setRegistryNames(new ItemBlock(ourBlock), ourBlock.getRegistryName()));
        });

        if (ConfigFile.botaniasacrifice)
        {
            ItemBotaniaSacrifice item = new ItemBotaniaSacrifice("botaniasacrifice", 4.0F);
            registry.register(item);
        }

        if (ConfigFile.thaumicknife)
        {
            registry.register(new ItemThaumicKnife("thaumicknife", 4.0F));
        }

        if (ConfigFile.thaumicsacrifice)
        {
            registry.register(new ItemThaumcraftSacrifice("thaumcraftsacrifice", 4.0F));
        }

        if (ConfigFile.knife)
        {
            registry.register(new ItemKnife("knife", 4.0F));
        }

        // SLATES
        registry.register(new ItemSlates("thaumicslate"));
        registry.register(new ItemSlates("manaslate"));

        // 原作者把设置物品id的部分注释掉了。
        // 我怎么感觉GitHub上的这份代码不是发布的那版...
        registry.register(new ItemSubCollar("item_sub_collar"));
        registry.register(new ItemBloodwell("item_bloodwell"));

        LOGGER.info("Registered items");

    }

    /**
     * Register Blocks
     **/
    @SubscribeEvent
    public static void onRegisterBlocksEvent(final RegistryEvent.Register<Block> event)
    {
        final IForgeRegistry<Block> registry = event.getRegistry();

        registry.register(new BlockBloodRuneAA(Material.CLAY, "mana_rune"));
        registry.register(new BlockBloodRuneAA(Material.CLAY,"thaumic_rune"));

        registerTileEntities();

        LOGGER.info("Registered blocks");
    }

    private static void registerTileEntities()
    {
        //registerTileEntity(TileEntityBoundJar.class);
    }

    private static void registerTileEntity(final Class<? extends TileEntity> clazz)
    {

        try {
            GameRegistry.registerTileEntity(clazz, new ResourceLocation(ModReference.MOD_ID, ModUtil.getRegistryNameForClass(clazz, "TileEntity")));
        }
        catch (final Exception e) {
            LOGGER.error("Error registering Tile Entity " + clazz.getSimpleName());
            e.printStackTrace();
        }
    }

    // client side event

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRegisterModelsEvent(final ModelRegistryEvent event)
    {
        OBJLoader.INSTANCE.addDomain("arsenalmod");
        LOGGER.info("Registered entity renderers");
        ForgeRegistries.BLOCKS.getValuesCollection().stream().filter(block -> block.getRegistryName().getNamespace().equals("arcanearteries")).forEach(ourBlock -> registerItemBlockModel(ourBlock));
        ForgeRegistries.ITEMS.getValuesCollection().stream().filter(item -> item.getRegistryName().getNamespace().equals("arcanearteries")).forEach(ourItem -> registerItemModel(ourItem));
        LOGGER.info("Registered models");

        registerTileEntitySpecialRenderers();

        LOGGER.info("Registered tile entity special renderers");
    }

    @SideOnly(Side.CLIENT)
    private static void registerTileEntitySpecialRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityGolem.class, new RenderThaumcraftGolem(Minecraft.getMinecraft().getRenderManager()));

    }

    @SideOnly(Side.CLIENT)
    private static void registerItemBlockModel(final Block block) {
        registerItemBlockModel(block, "normal");
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModel(final Item item) {
        registerItemModel(item, "inventory");
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemBlockModel(final Block block, final String variant)
    {
        registerItemModel(Item.getItemFromBlock(block), variant);
    }

    @SideOnly(Side.CLIENT)
    private static void registerItemModel(final Item item, final String variant)
    {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), variant));
    }
}
