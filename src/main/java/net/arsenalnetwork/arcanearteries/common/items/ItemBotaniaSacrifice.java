package net.arsenalnetwork.arcanearteries.common.items;

import WayofTime.bloodmagic.client.IVariantProvider;
import net.arsenalnetwork.arcanearteries.common.creativetabs.ModCreativeTabs;
import net.arsenalnetwork.arcanearteries.utilities.ModUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBotaniaSacrifice extends Item implements IVariantProvider
{
    private final float weaponDamage;

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
        target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) target), this.weaponDamage);
        stack.damageItem(1, target);
        return true;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote)
        {
            worldIn.spawnParticle(EnumParticleTypes.REDSTONE, pos.getX(), pos.getY(), pos.getZ(), hitX, hitY, hitZ, 10);

        }

        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        tooltip.add(TextFormatting.RED + "A prick of a flower so sweet");
        tooltip.add(TextFormatting.RED + "To draw blood from thy meat");

        tooltip.add(TextFormatting.RED + "Damage: " + TextFormatting.YELLOW + this.weaponDamage);
    }

    @Override
    public int getMaxItemUseDuration(final ItemStack itemStack)
    {
        return 100000;
    }
}
