package net.arsenalnetwork.arcanearteries.common.blocks;

import WayofTime.bloodmagic.block.enums.BloodRuneType;
import WayofTime.bloodmagic.iface.IBloodRune;
import net.arsenalnetwork.arcanearteries.common.creativetabs.ModCreativeTabs;
import net.arsenalnetwork.arcanearteries.utilities.ModUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

public class BlockBloodRuneAA extends Block implements IBloodRune
{
    public BlockBloodRuneAA(Material materialIn, final String name)
    {
        super(materialIn);
        setHardness(2.0F);
        setResistance(5.0F);
        this.setCreativeTab(ModCreativeTabs.MOD_TAB);
        this.setTranslationKey(name);
        ModUtil.setRegistryNames(this, name);
    }

    @Nullable
    @Override
    public BloodRuneType getBloodRune(IBlockAccess iBlockAccess, BlockPos blockPos, IBlockState iBlockState) {
        return BloodRuneType.SPEED;
    }
}
