package net.arsenalnetwork.arcanearteries.common.items;

import WayofTime.bloodmagic.entity.mob.EntityDemonBase;
import WayofTime.bloodmagic.item.ItemDaggerOfSacrifice;
import WayofTime.bloodmagic.soul.IDemonWill;
import net.arsenalnetwork.arcanearteries.common.creativetabs.ModCreativeTabs;
import net.arsenalnetwork.arcanearteries.utilities.ModUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBotaniaSacrifice extends ItemDaggerOfSacrifice
{
    private float weaponDamage;
    private static final int COST = 100;

    public ItemBotaniaSacrifice(final String name)
    {
        this.setCreativeTab(ModCreativeTabs.MOD_TAB);
        this.maxStackSize = 1;
        this.setFull3D();
        this.setMaxDamage(100);
        this.weaponDamage = 1.0F;
        ModUtil.setRegistryNames(this, name);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        tooltip.add(TextFormatting.RED + "A prick of a flower so sweet");
        tooltip.add(TextFormatting.RED + "To draw blood from thy meat");
    }
}
