package net.arsenalnetwork.arcanearteries.common.items;

import WayofTime.bloodmagic.api.impl.BloodMagicAPI;
import WayofTime.bloodmagic.client.IVariantProvider;
import WayofTime.bloodmagic.item.ItemDaggerOfSacrifice;
import WayofTime.bloodmagic.util.DamageSourceBloodMagic;
import WayofTime.bloodmagic.util.helper.PlayerSacrificeHelper;
import WayofTime.bloodmagic.util.helper.PurificationHelper;
import net.arsenalnetwork.arcanearteries.common.creativetabs.ModCreativeTabs;
import net.arsenalnetwork.arcanearteries.utilities.ModUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBotaniaSacrifice extends Item implements IVariantProvider
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
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (attacker instanceof FakePlayer) {
            return false;
        } else if (target == null || attacker == null || attacker.getEntityWorld().isRemote || attacker instanceof EntityPlayer && !(attacker instanceof EntityPlayerMP)) {
            return false;
        } else if (!target.isNonBoss()) {
            return false;
        } else if (target instanceof EntityPlayer) {
            return false;
        } else if (target.isChild() && !(target instanceof IMob)) {
            return false;
        } else if (!target.isDead && target.getHealth() >= 0.5F) {
            EntityEntry entityEntry = EntityRegistry.getEntry(target.getClass());
            if (entityEntry == null) {
                return false;
            } else {
                int lifeEssenceRatio = (Integer) BloodMagicAPI.INSTANCE.getValueManager().getSacrificial().getOrDefault(entityEntry.getRegistryName(), 25);
                if (lifeEssenceRatio <= 0) {
                    return false;
                } else {
                    int lifeEssence = (int)((float)lifeEssenceRatio * target.getHealth());
                    if (target instanceof EntityAnimal) {
                        lifeEssence = (int)((double)lifeEssence * (1.0D + PurificationHelper.getCurrentPurity((EntityAnimal)target)));
                    }

                    if (target.isChild()) {
                        lifeEssence = (int)((float)lifeEssence * 0.5F);
                    }

                    if (PlayerSacrificeHelper.findAndFillAltar(attacker.getEntityWorld(), target, lifeEssence, true)) {
                        target.getEntityWorld().playSound((EntityPlayer)null, target.posX, target.posY, target.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (target.getEntityWorld().rand.nextFloat() - target.getEntityWorld().rand.nextFloat()) * 0.8F);
                        target.setHealth(-1.0F);
                        target.onDeath(DamageSourceBloodMagic.INSTANCE);
                    }

                    return false;
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        tooltip.add(TextFormatting.RED + "A prick of a flower so sweet");
        tooltip.add(TextFormatting.RED + "To draw blood from thy meat");
    }
}
