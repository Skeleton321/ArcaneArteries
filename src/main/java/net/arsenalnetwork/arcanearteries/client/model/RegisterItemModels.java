package net.arsenalnetwork.arcanearteries.client.model;

import net.arsenalnetwork.arcanearteries.common.init.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;

public class RegisterItemModels
{
    public static void registerItemModels(ModelRegistryEvent event) {
        registerNormalItemModels(event);
    }

    private static void registerNormalItemModels(ModelRegistryEvent event) {

    }

    private static void registerNormalItemModel(ModelRegistryEvent event, Item item) {
        ModelLoader.setCustomModelResourceLocation(item,0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
