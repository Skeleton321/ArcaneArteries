package net.arsenalnetwork.arcanearteries.utilities;

import net.arsenalnetwork.arcanearteries.common.creativetabs.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;

public class ModUtil
{
    /**
     * Sets the {@link IForgeRegistryEntry.Impl#setRegistryName(ResourceLocation) Registry Name} and the
     * {@link Item#setTranslationKey(String) Translation Key} (if applicable) for the entry
     *
     * @param entry the {@link IForgeRegistryEntry.Impl IForgeRegistryEntry.Impl<?>} to set the names for
     * @param name  the name for the entry that the registry name is derived from
     *
     * @return the entry
     */
    public static <T extends IForgeRegistryEntry.Impl<?>> T setRegistryNames(final T entry, final String name) {

        return setRegistryNames(entry, new ResourceLocation(ModReference.MOD_ID, name));
    }

    /**
     * Sets the {@link IForgeRegistryEntry.Impl#setRegistryName(ResourceLocation) Registry Name} and the
     * {@link Item#setTranslationKey(String) Translation Key} (if applicable) for the entry
     *
     * @param entry        the {@link IForgeRegistryEntry.Impl IForgeRegistryEntry.Impl<?>} to set the names for
     * @param registryName the registry name for the entry that the unlocalised name is also gotten from
     *
     * @return the entry
     */
    public static <T extends IForgeRegistryEntry.Impl<?>> T setRegistryNames(final T entry, final ResourceLocation registryName) {

        return setRegistryNames(entry, registryName, registryName.getPath());
    }

    /**
     * Sets the {@link IForgeRegistryEntry.Impl#setRegistryName(ResourceLocation) Registry Name} and the
     * {@link Item#setTranslationKey(String) Translation Key} (if applicable) for the entry
     *
     * @param entry          the {@link IForgeRegistryEntry.Impl IForgeRegistryEntry.Impl<?>} to set the names for
     * @param registryName   the registry name for the entry
     * @param translationKey the translationKey for the entry
     *
     * @return the entry
     */
    public static <T extends IForgeRegistryEntry.Impl<?>> T setRegistryNames(final T entry, final ResourceLocation registryName, final String translationKey) {

        entry.setRegistryName(registryName);
        if (entry instanceof Block) {
            ((Block) entry).getBlockFromName(translationKey);
            setCreativeTab((Block) entry);
        }
        if (entry instanceof Item) {
            ((Item) entry).getByNameOrId(translationKey);
            setCreativeTab((Item) entry);
        }
        return entry;
    }

    /**
     * Gets the game name from a slot<br>
     * For example {@link EntityEquipmentSlot#CHEST EntityEquipmentSlot.CHEST} -> "CHESTPLATE"
     *
     * @param slot the {@link EntityEquipmentSlot EntityEquipmentSlot} to get the name for
     *
     * @return the game name for the slot
     */
    public static String getSlotGameNameUppercase(final EntityEquipmentSlot slot) {

        switch (slot) {
            case CHEST:
                return "CHESTPLATE";
            case FEET:
                return "BOOTS";
            case HEAD:
                return "HELMET";
            case LEGS:
                return "LEGGINGS";
            default:
                return slot.name().toUpperCase();
        }
    }

    /**
     * Converts the game name to lowercase as per {@link String#toLowerCase() String.toLowerCase}.
     *
     * @param slot the {@link EntityEquipmentSlot} to get the name from
     *
     * @return the game name in lowercase as per {@link String#toLowerCase() String.toLowerCase}.
     */
    public static String getSlotGameNameLowercase(final EntityEquipmentSlot slot) {

        return getSlotGameNameUppercase(slot).toLowerCase();
    }

    /**
     * Capitalizes the game name formatted as per {@link StringUtils#capitalize(String) StringUtils.capitalize}.
     *
     * @param slot the {@link EntityEquipmentSlot} to get the name from
     *
     * @return the game name formatted as per {@link StringUtils#capitalize(String) StringUtils.capitalize}.
     */
    public static String getSlotGameNameFormatted(final EntityEquipmentSlot slot) {

        return StringUtils.capitalize(getSlotGameNameLowercase(slot));
    }

    /**
     * Utility method to make sure that all our items appear on our creative tab, the search tab and any other tab they specify
     *
     * @param item the {@link Item Item}
     *
     * @return an array of all tabs that this item is on.
     */
    public static CreativeTabs[] getCreativeTabs(final Item item) {

        return new CreativeTabs[] { item.getCreativeTab(), ModCreativeTabs.MOD_TAB, CreativeTabs.SEARCH };
    }

    /**
     * Utility method to make sure that all our items appear on our creative tab
     *
     * @param item the {@link Item Item}
     */
    public static void setCreativeTab(final Item item) {

        if (item.getCreativeTab() == null) {
            item.setCreativeTab(ModCreativeTabs.MOD_TAB);
        }
    }

    /**
     * Utility method to make sure that all our blocks appear on our creative tab
     *
     * @param block the {@link Block Block}
     */
    public static void setCreativeTab(final Block block) {

        if (block.getCreativeTab() == null) {
            block.setCreativeTab(ModCreativeTabs.MOD_TAB);
        }
    }

    /**
     * Maps a value from one range to another range. Taken from https://stackoverflow.com/a/5732117
     *
     * @param input_start  the start of the input's range
     * @param input_end    the end of the input's range
     * @param output_start the start of the output's range
     * @param output_end   the end of the output's range
     * @param input        the input
     *
     * @return the newly mapped value
     */
    public static double map(final double input_start, final double input_end, final double output_start, final double output_end, final double input) {

        final double input_range = input_end - input_start;
        final double output_range = output_end - output_start;

        return (((input - input_start) * output_range) / input_range) + output_start;
    }

    /**
     * Turns a class's name into a registry name<br>
     * It expects the Class's Name to be in CamelCase format<br>
     * It returns the registry name in snake_case format<br>
     * <br>
     * Examples:<br>
     * (TileEntitySuperAdvancedFurnace, "TileEntity") -> super_advanced_furnace<br>
     * (EntityPortableGenerator, "Entity") -> portable_generator<br>
     * (TileEntityPortableGenerator, "Entity") -> tile_portable_generator<br>
     * (EntityPortableEntityGeneratorEntity, "Entity") -> portable_generator<br>
     *
     * @param clazz      the class
     * @param removeType the string to be removed from the class's name
     *
     * @return the recommended registry name for the class
     */
    public static String getRegistryNameForClass(final Class<?> clazz, final String removeType) {

        return StringUtils.uncapitalize(clazz.getSimpleName().replace(removeType, "")).replaceAll("([A-Z])", "_$1").toLowerCase();
    }

    /**
     * Turns a registry name into a more human readable name<br>
     * It expects the registry name to be in snake_case format<br>
     * It returns the registry name with "_" replaced with space and every word {@link StringUtils#capitalize(String) capitalized}<br>
     * <br>
     * Examples:<br>
     * super_advanced_furnace -> Super Advanced Furnace<br>
     * portable_generator -> Portable Generator<br>
     * tile_portable_generator -> Tile Portable Generator <br>
     *
     * @param registryNamePath the path of the registryName name in
     *
     * @return the recommended translated name for the class
     */
    public static String registryNameToTranslatedName(final String registryNamePath) {

        final String[] strs = registryNamePath.split("_");
        for (int i = 0; i < strs.length; i++) {
            strs[i] = StringUtils.capitalize(strs[i]);
        }
        final String translatedName = String.join(" ", strs);
        return translatedName;
    }

    /**
     * Generic & dynamic version of {@link Container#transferStackInSlot(EntityPlayer, int)}<br>
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player inventory and the other inventory(s).
     *
     * @param player    the player passed in
     * @param index     the index passed in
     * @param container the container to apply the transfer to
     *
     * @return the {@link ItemStack}
     */
    public static ItemStack transferStackInSlot(final EntityPlayer player, final int index, final Container container) {

        ItemStack itemstack = ItemStack.EMPTY;
        final Slot slot = container.inventorySlots.get(index);
        if ((slot != null) && slot.getHasStack()) {
            final ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            final int containerSlots = container.inventorySlots.size() - player.inventory.mainInventory.size();
            if (index < containerSlots) {
                if (! mergeItemStack(itemstack1, containerSlots, container.inventorySlots.size(), true, container)) {
                    return ItemStack.EMPTY;
                }
            } else if (! mergeItemStack(itemstack1, 0, containerSlots, false, container)) {
                return ItemStack.EMPTY;
            }
            if (itemstack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, itemstack1);
        }
        return itemstack;
    }

    /**
     * Exact copy of {@link Container#mergeItemStack} with the same JavaDoc (improved for readability)<br>
     * Merges provided ItemStack with the first available one in the container/player inventor between startIndex (included) and endIndex (excluded).<br>
     * <font color="#FDCA42"> ⚠WARNING⚠: The Container implementation does not check if the item is valid for the slot! </font>
     *
     * @param stack            the stack to merge
     * @param startIndex
     * @param endIndex
     * @param reverseDirection
     * @param container        the container to apply the merge to
     *
     * @return
     */
    public static boolean mergeItemStack(final ItemStack stack, final int startIndex, final int endIndex, final boolean reverseDirection, final Container container) {

        boolean flag = false;
        int i = startIndex;

        if (reverseDirection) {
            i = endIndex - 1;
        }

        if (stack.isStackable()) {
            while (! stack.isEmpty()) {
                if (reverseDirection) {
                    if (i < startIndex) {
                        break;
                    }
                } else if (i >= endIndex) {
                    break;
                }

                final Slot slot = container.inventorySlots.get(i);
                final ItemStack itemstack = slot.getStack();

                if (! itemstack.isEmpty() && (itemstack.getItem() == stack.getItem()) && (! stack.getHasSubtypes() || (stack.getMetadata() == itemstack.getMetadata())) && ItemStack.areItemStackTagsEqual(stack, itemstack)) {
                    final int j = itemstack.getCount() + stack.getCount();
                    final int maxSize = Math.min(slot.getSlotStackLimit(), stack.getMaxStackSize());

                    if (j <= maxSize) {
                        stack.setCount(0);
                        itemstack.setCount(j);
                        slot.onSlotChanged();
                        flag = true;
                    } else if (itemstack.getCount() < maxSize) {
                        stack.shrink(maxSize - itemstack.getCount());
                        itemstack.setCount(maxSize);
                        slot.onSlotChanged();
                        flag = true;
                    }
                }

                if (reverseDirection) {
                    -- i;
                } else {
                    ++ i;
                }
            }
        }

        if (! stack.isEmpty()) {
            if (reverseDirection) {
                i = endIndex - 1;
            } else {
                i = startIndex;
            }

            while (true) {
                if (reverseDirection) {
                    if (i < startIndex) {
                        break;
                    }
                } else if (i >= endIndex) {
                    break;
                }

                final Slot slot1 = container.inventorySlots.get(i);
                final ItemStack itemstack1 = slot1.getStack();

                if (itemstack1.isEmpty() && slot1.isItemValid(stack)) {
                    if (stack.getCount() > slot1.getSlotStackLimit()) {
                        slot1.putStack(stack.splitStack(slot1.getSlotStackLimit()));
                    } else {
                        slot1.putStack(stack.splitStack(stack.getCount()));
                    }

                    slot1.onSlotChanged();
                    flag = true;
                    break;
                }

                if (reverseDirection) {
                    -- i;
                } else {
                    ++ i;
                }
            }
        }

        return flag;
    }

    /**
     * @param whole the whole number
     * @param parts how many parts to split the number into
     *
     * @return an array of parts adding up to the whole number
     */
    public static int[] splitIntoParts(final int whole, final int parts) {

        final int[] arr = new int[parts];
        int remain = whole;
        int partsLeft = parts;
        for (int i = 0; partsLeft > 0; i++) {
            final int size = ((remain + partsLeft) - 1) / partsLeft; // rounded up, aka ceiling
            arr[i] = size;
            remain -= size;
            partsLeft--;
        }
        return arr;
    }

    /**
     * @param world the world to get the logical side from
     *
     * @return the logical side of the world
     */
    public static Side getLogicalSide(final World world) {

        if (world.isRemote) {
            return Side.CLIENT;
        } else {
            return Side.SERVER;
        }
    }

    /**
     * @param world the world to get the logical side from
     */
    public static void logLogicalSide(final World world) {

        LogManager.getLogger().info("Logical Side: " + getLogicalSide(world));
    }

    /**
     * "name", "suffix" -> "name_suffix"<br>
     * "name, "" -> "name"<br>
     * "", "suffix" -> "_suffix"
     *
     * @param name   the name
     * @param suffix the suffix
     *
     * @return name + "_" + suffix if the suffix's length is greater than 0 or just the name
     */
    public static String getNameWithSuffix(final String name, final String suffix) {

        if (suffix.length() <= 0) {
            return name;
        }
        return name + "_" + suffix;
    }

    /**
     * translate and process colour code.
     * @param key text key
     * @param parameters parameters
     * @return translated text
     */
    @SideOnly(Side.CLIENT)
    public static String i18nFormat(String key, Object... parameters) {
        return I18n.format(key, parameters).replace('&', '\u00a7');
    }
}
