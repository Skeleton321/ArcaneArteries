package net.arsenalnetwork.arcanearteries.common.items;

import WayofTime.bloodmagic.event.SacrificeKnifeUsedEvent;
import WayofTime.bloodmagic.util.helper.PlayerSacrificeHelper;
import net.arsenalnetwork.arcanearteries.common.creativetabs.ModCreativeTabs;
import net.arsenalnetwork.arcanearteries.common.init.ModItems;
import net.arsenalnetwork.arcanearteries.utilities.ModUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;

import javax.annotation.Nullable;
import java.util.List;

public class ItemThaumicKnife extends ItemBotaniaSacrifice
{
    public ItemThaumicKnife(String name) {
        super(name);
        this.maxStackSize = 1;
        this.isFull3D();
        this.setCreativeTab(ModCreativeTabs.MOD_TAB);
        setTranslationKey(name);
        setRegistryName(name);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
    {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;
        PlayerSacrificeHelper.sacrificePlayerHealth(player);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemStack = new ItemStack(ModItems.BOTANIASACRIFICE);
        if (this.canUseForSacrifice(itemStack)) {
            playerIn.setHeldItem(handIn, itemStack);
        } else {
            if (!playerIn.capabilities.isCreativeMode) {
                SacrificeKnifeUsedEvent evt = new SacrificeKnifeUsedEvent(playerIn, true, true, 2, 1);
                if (MinecraftForge.EVENT_BUS.post(evt)) {
                }

                if (evt.shouldDrainHealth) {
                    playerIn.setHealth(playerIn.getHealth() - 2.0F);
                }

                if (!evt.shouldFillAltar) {

                }
            }

            if (playerIn instanceof FakePlayer) {

            } else {
                double posX = playerIn.posX;
                double posY = playerIn.posY;
                double posZ = playerIn.posZ;
                // TODO Fix the sound
                //worldIn.playSound((double)((float)posX + 0.5F), (double)((float)posY + 0.5F), (double)((float)posZ + 0.5F), "random.fizz", 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
                //worldIn.playSound(posX + 0.5F, posY + 0.5F, posZ + 0.5F, SoundEvents.UI_TOAST_IN,"", 0.5F, 2.6F, 0);
                float f = 1.0F;
                float f1 = f * 0.6F + 0.4F;
                float f2 = f * f * 0.7F - 0.5F;
                float f3 = f * f * 0.6F - 0.7F;

                for (int l = 0; l < 8; ++l) {
                    // TODO CHANGE THIS PARTICLE
                    worldIn.spawnParticle(EnumParticleTypes.REDSTONE, posX + Math.random() - Math.random(), posY + Math.random() - Math.random(), posZ + Math.random() - Math.random(), (double) f1, (double) f2, (double) f3);
                }

                if (!worldIn.isRemote) {

                }
                /**else {
                 if (ConfigFile.hardDaggers) {
                 if (player.isPotionActive(AlchemicalWizardry.customPotionSoulFray)) {
                 this.findAndFillAltar(world, player, 50);
                 } else {
                 this.findAndFillAltar(world, player, 500);
                 }
                 }

                 if (playerIn.isPotionActive(AlchemicalWizardry.customPotionSoulFray)) {
                 this.findAndFillAltar(world, player, 25);
                 } else {
                 this.findAndFillAltar(world, player, 250);
                 }

                 if (playerIn.getHealth() <= 0.001F) {
                 playerIn.onDeath(DamageSource.cactus);
                 }

                 return null;
                 }**/

            }
        }
        return null;
    }

    public boolean canUseForSacrifice(ItemStack stack) {
        NBTTagCompound tag = stack.getTagCompound();
        return tag == null ? false : tag.getBoolean("sacrifice");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.RED + "Some would say I am crazy for using this!");
    }
}
