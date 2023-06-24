package net.arsenalnetwork.arcanearteries.common.items.baubles;

import baubles.api.BaublesApi;
import net.arsenalnetwork.arcanearteries.common.creativetabs.ModCreativeTabs;
import net.arsenalnetwork.arcanearteries.common.init.ModItems;
import net.arsenalnetwork.arcanearteries.utilities.ModUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.items.baubles.ItemAmuletVis;
import vazkii.botania.api.item.ICosmeticAttachable;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

import static net.arsenalnetwork.arcanearteries.utilities.ModUtil.i18nFormat;

public class ItemSubCollar extends ItemAmuletVis implements ICosmeticAttachable
{
    DecimalFormat myFormatter = new DecimalFormat("#######.##");

    public ItemSubCollar(final String name)
    {
        this.setMaxStackSize(1);
        this.setCreativeTab(ModCreativeTabs.MOD_TAB);
        //setTranslationKey(name);
        //ModUtil.setRegistryNames(this, name);
    }

    @Nullable
    @Override
    public CreativeTabs getCreativeTab() {
        return ModCreativeTabs.MOD_TAB;
    }

    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public EnumRarity getRarity(ItemStack itemstack) {
        return EnumRarity.RARE;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(i18nFormat("tooltip.arcanearteries.item_sub_collar.0", this.getMaxVis(stack) / 100));

        NBTTagCompound compound = stack.getTagCompound();
        if(compound == null) return;

        for(Aspect aspect : Aspect.getPrimalAspects()) {
            if(!compound.hasKey(aspect.getTag())) continue;
            String amount = this.myFormatter.format((float)stack.getTagCompound().getInteger(aspect.getTag()) / 100.0F);
            tooltip.add("\u00a7" + aspect.getChatcolor() + aspect.getName() + TextFormatting.RESET + " x " + amount);
        }
        if(compound.hasKey("owner"))
            tooltip.add(i18nFormat("tooltip.arcanearteries.item_sub_collar.addon.0", compound.getString("owner")));
        if(GuiScreen.isShiftKeyDown() && getCosmeticItem(stack) != null)
            tooltip.add(i18nFormat("tooltip.arcanearteries.item_sub_collar.addon.1", getCosmeticItem(stack).getDisplayName()));

    }

    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand)
    {
        if (target.world.isRemote)
        {
            return false;
        }

        if (target instanceof EntityPlayer)
        {
            EntityPlayer entityPlayer = (EntityPlayer) target;
            IInventory baubles = BaublesApi.getBaubles(entityPlayer);

            if (baubles.getStackInSlot(0) == null)
            {
                if (!stack.hasTagCompound())
                {
                    NBTTagCompound tagCompound = new NBTTagCompound();
                    stack.setTagCompound(tagCompound);
                }

                stack.getTagCompound().setString("owner", String.valueOf(playerIn.getDisplayName()));
                baubles.setInventorySlotContents(0, stack.copy());
                stack.getMaxStackSize();
                entityPlayer.sendMessage(new TextComponentString("%s places a collar around your neck.".replace("%s", playerIn.getDisplayNameString())));
                playerIn.sendMessage(new TextComponentString("You place the collar around %s's neck.".replace("%s", entityPlayer.getDisplayNameString())));
                return true;
            } else
                playerIn.sendMessage(new TextComponentString("%s is already wearing something around their neck!".replace("%s", entityPlayer.getDisplayNameString())));
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(ModItems.ITEM_SUB_COLLAR, 1, 0));
        super.getSubItems(tab, items);
    }

    @Override
    public ItemStack getCosmeticItem(ItemStack stack)
    {
        if (stack == null || stack.getTagCompound() == null)
        {
            return null;
        }

        if (!stack.getTagCompound().hasKey("cosmeticItem"))
        {
            return null;
        }

        NBTTagCompound tagCompound = stack.getTagCompound().getCompoundTag("cosmeticItem");
        return ItemStack.EMPTY;
    }

    @Override
    public void setCosmeticItem(ItemStack stack, ItemStack cosmetic)
    {
        if (stack == null)
        {
            return;
        }

        NBTTagCompound cmp = stack.getTagCompound();

        if (cosmetic != null)
        {
            cosmetic.writeToNBT(cmp);
        }

        NBTTagCompound tag = stack.getTagCompound();

        if (tag == null)
        {
            tag = new NBTTagCompound();
            stack.setTagCompound(tag);
        }

        tag.setTag("cosmeticItem", cmp);
    }

    public int getMaxVis(ItemStack stack)
    {
        return 25000;
    }
}
