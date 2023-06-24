package net.arsenalnetwork.arcanearteries.common.items;

import WayofTime.bloodmagic.client.IVariantProvider;
import net.arsenalnetwork.arcanearteries.common.creativetabs.ModCreativeTabs;
import net.arsenalnetwork.arcanearteries.utilities.ModReference;
import net.arsenalnetwork.arcanearteries.utilities.ModUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static net.arsenalnetwork.arcanearteries.utilities.ModUtil.i18nFormat;

public class ItemBotaniaSacrifice extends Item implements IVariantProvider
{
    private final float weaponDamage;
    int x, y, z;

    public ItemBotaniaSacrifice(final String name, final float damage)
    {
        this.setCreativeTab(ModCreativeTabs.MOD_TAB);
        this.maxStackSize = 1;
        this.setFull3D();
        this.setMaxDamage(100);
        setTranslationKey(name);
        this.weaponDamage = damage;
        ModUtil.setRegistryNames(this, name);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        World world = attacker.world;
        BlockPos pos = new BlockPos(x, y, z);
        if (!world.isRemote)
        {
            world.spawnParticle(EnumParticleTypes.REDSTONE, pos.getX(), pos.getY(), pos.getZ(), 1, 1, 1, 10);
        }
        target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), this.weaponDamage);
        stack.damageItem(1, attacker);
        return true;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(i18nFormat("tooltip.arcanearteries.botania_sacrifice.0"));
        tooltip.add(i18nFormat("tooltip.arcanearteries.botania_sacrifice.1"));
        tooltip.add(i18nFormat("tooltip.arcanearteries.botania_sacrifice.2", this.weaponDamage));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public int getMaxItemUseDuration(final ItemStack itemStack)
    {
        return 100000;
    }
}
