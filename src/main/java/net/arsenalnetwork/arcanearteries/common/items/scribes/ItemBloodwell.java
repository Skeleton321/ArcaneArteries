package net.arsenalnetwork.arcanearteries.common.items.scribes;

import WayofTime.bloodmagic.iface.IBindable;
import net.arsenalnetwork.arcanearteries.common.creativetabs.ModCreativeTabs;
import net.arsenalnetwork.arcanearteries.utilities.ModUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumcraft.api.items.IScribeTools;

import javax.annotation.Nullable;
import java.util.List;

import static net.arsenalnetwork.arcanearteries.utilities.ModUtil.i18nFormat;

public class ItemBloodwell extends Item implements IScribeTools, IBindable
{
    public ItemBloodwell(final String name)
    {
        this.setCreativeTab(ModCreativeTabs.MOD_TAB);
        this.maxStackSize = 1;
        this.setMaxDamage(5);
        this.hasSubtypes = false;
        this.canRepair = false;
        setTranslationKey(name);
        ModUtil.setRegistryNames(this, name);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (stack.getItemDamage() > 0 && stack.hasTagCompound())
        {
            EntityPlayer entityPlayer = null;

            if (entityIn instanceof EntityPlayer)
            {
                entityPlayer = (EntityPlayer) entityIn;
            } else
            {
                return;
            }

            if (entityPlayer.capabilities.isCreativeMode)
            {
                stack.setItemDamage(0);
            }

            else if (entityPlayer.getHealth() > 6)
            {
                entityPlayer.setHealth(entityPlayer.getHealth() - 2);
                stack.setItemDamage(stack.getItemDamage() - 1);
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        NBTTagCompound compound = stack.getTagCompound();
        if(compound == null) return;

        tooltip.add("");
        tooltip.add(i18nFormat("tooltip.arcanearteries.blood_well.1", stack.getTagCompound().getString("ownerName")));
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        if (damage > 0)
        {
            super.setDamage(stack, 0);
        } else
        {
            super.setDamage(stack, damage);
        }
    }
}
