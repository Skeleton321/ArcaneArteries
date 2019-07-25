package net.arsenalnetwork.arcanearteries.common.items.thaumcraft;

import net.arsenalnetwork.arcanearteries.common.creativetabs.ModCreativeTabs;
import thaumcraft.common.golems.ItemGolemPlacer;

public class ItemGolem extends ItemGolemPlacer
{
    public ItemGolem()
    {
        this.setCreativeTab(ModCreativeTabs.MOD_TAB);
        this.maxStackSize = 1;
    }
}
