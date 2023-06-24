package net.arsenalnetwork.arcanearteries.common.items;

import WayofTime.bloodmagic.item.ItemSacrificialDagger;
import WayofTime.bloodmagic.util.helper.PlayerSacrificeHelper;
import net.arsenalnetwork.arcanearteries.client.config.ConfigFile;
import net.arsenalnetwork.arcanearteries.common.creativetabs.ModCreativeTabs;
import net.arsenalnetwork.arcanearteries.utilities.ModUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaUsingItem;

import java.util.List;

import static net.arsenalnetwork.arcanearteries.utilities.ModUtil.i18nFormat;

public class ItemKnife extends ItemSacrificialDagger implements IManaUsingItem
{
    private static final int COST;
    private final float weaponDamage;

    public ItemKnife(final String name, final float damage)
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
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
        if(entityLiving instanceof EntityPlayer)
            PlayerSacrificeHelper.sacrificePlayerHealth((EntityPlayer) entityLiving);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
    {
        if (!world.isRemote && entity instanceof EntityPlayer)
        {
            this.setUseForSacrifice(stack, this.isPlayerPreparedForSacrifice(world, (EntityPlayer)entity));
        }
    }

    @Override
    public boolean canUseForSacrifice(ItemStack stack)
    {
        NBTTagCompound tag = stack.getTagCompound();
        return tag == null ? false : tag.getBoolean("sacrifice");
    }

    @Override
    public void setUseForSacrifice(ItemStack stack, boolean sacrifice)
    {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) {
            tag = new NBTTagCompound();
            stack.setTagCompound(tag);
        }

        tag.setBoolean("sacrifice", sacrifice);
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
    {
        tooltip.add(i18nFormat("tooltip.arcanearteries.knife.0"));
        tooltip.add(i18nFormat("tooltip.arcanearteries.knife.1", this.weaponDamage));
        super.addInformation(stack, world, tooltip, flag);
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }

    static {
        COST = ConfigFile.manaused;
    }
}
