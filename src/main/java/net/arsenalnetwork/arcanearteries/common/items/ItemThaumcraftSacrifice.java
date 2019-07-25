package net.arsenalnetwork.arcanearteries.common.items;

import WayofTime.bloodmagic.item.ItemDaggerOfSacrifice;
import net.arsenalnetwork.arcanearteries.common.creativetabs.ModCreativeTabs;
import net.arsenalnetwork.arcanearteries.utilities.ModUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemThaumcraftSacrifice extends ItemDaggerOfSacrifice
{
    private final float weaponDamage;

    public ItemThaumcraftSacrifice(final String name, final float damage)
    {
        this.setCreativeTab(ModCreativeTabs.MOD_TAB);
        this.maxStackSize = 1;
        this.setMaxDamage(100);
        this.weaponDamage = damage;
        this.setFull3D();
        setTranslationKey(name);
        ModUtil.setRegistryNames(this, name);
    }


    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), this.weaponDamage);
        stack.damageItem(1, attacker);
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<String> list, ITooltipFlag flag)
    {
        list.add(TextFormatting.RED + "The ancient art of killing");
        list.add(TextFormatting.RED + "through the use of magic is thrilling");
        list.add(TextFormatting.BLUE + "Damage: " + TextFormatting.YELLOW + this.weaponDamage);

        super.addInformation(stack, world, list, flag);
    }
}
