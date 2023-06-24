package net.arsenalnetwork.arcanearteries.common.items;

import WayofTime.bloodmagic.item.ItemSacrificialDagger;
import net.arsenalnetwork.arcanearteries.common.creativetabs.ModCreativeTabs;
import net.arsenalnetwork.arcanearteries.utilities.ModUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static net.arsenalnetwork.arcanearteries.utilities.ModUtil.i18nFormat;

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
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
    {
        tooltip.add(i18nFormat("tooltip.arcanearteries.thaumic_knife.0"));
        tooltip.add(i18nFormat("tooltip.arcanearteries.thaumic_knife.1", this.weaponDamage));
        super.addInformation(stack, world, tooltip, flag);
    }
}
