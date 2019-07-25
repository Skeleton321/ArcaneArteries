package net.arsenalnetwork.arcanearteries.common.items;

import WayofTime.bloodmagic.item.ItemSacrificialDagger;
import WayofTime.bloodmagic.util.helper.PlayerSacrificeHelper;
import net.arsenalnetwork.arcanearteries.common.creativetabs.ModCreativeTabs;
import net.arsenalnetwork.arcanearteries.utilities.ModUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemThaumicKnife extends ItemSacrificialDagger
{
    private final float weaponDamage;

    public ItemThaumicKnife(final String name, final float damage)
    {
        this.setCreativeTab(ModCreativeTabs.MOD_TAB);
        this.maxStackSize = 1;
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
        list.add(TextFormatting.RED + "Some would say I am crazy for using this!");
        list.add(TextFormatting.BLUE + "Damage: " + TextFormatting.YELLOW + this.weaponDamage);
        list.add(TextFormatting.GRAY + "Right click an altar while sneaking to fill it!");

        super.addInformation(stack, world, list, flag);
    }
}
