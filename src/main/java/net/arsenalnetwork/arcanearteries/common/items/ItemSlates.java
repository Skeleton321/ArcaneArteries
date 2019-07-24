package net.arsenalnetwork.arcanearteries.common.items;

import net.arsenalnetwork.arcanearteries.common.creativetabs.ModCreativeTabs;
import net.arsenalnetwork.arcanearteries.utilities.ModUtil;
import net.minecraft.item.Item;

public class ItemSlates extends Item
{
    public ItemSlates(String name)
    {
        this.setCreativeTab(ModCreativeTabs.MOD_TAB);
        this.maxStackSize = 64;
        setTranslationKey(name);
        ModUtil.setRegistryNames(this, name);
    }
}
