package net.arsenalnetwork.arcanearteries.client.updatorcheck;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public final class VersionChecker
{
  private static final int FLAVOUR_MESSAGES = 50;
  
  public VersionChecker() {}
  
  public static boolean doneChecking = false;
  public static String onlineVersion = "";
  public static boolean triedToWarnPlayer = false;
  
  public static boolean startedDownload = false;
  public static boolean downloadedFile = false;
  
  public void init() {
    new ThreadVersionChecker();
    FMLCommonHandler.instance().bus().register(this);
  }
  
  @SubscribeEvent
  public void onTick(TickEvent.ClientTickEvent event) {
    if (doneChecking && event.phase == TickEvent.Phase.END && Minecraft.getMinecraft().thePlayer != null && !triedToWarnPlayer) {
      if (!onlineVersion.isEmpty()) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        int onlineBuild = Integer.parseInt(onlineVersion);
        int clientBuild = "173".contains("GRADLE") ? 2147483647 : Integer.parseInt("173");
        if (onlineBuild > clientBuild) {
          player.addChatComponentMessage((new ChatComponentTranslation("botania.versioning.flavour" + player.worldObj.rand.nextInt(50), new Object[0])).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.RED)));
          player.addChatComponentMessage(new ChatComponentTranslation("botania.versioning.outdated", new Object[]{clientBuild, onlineBuild}));
          IChatComponent component = IChatComponent.Serializer.func_150699_a(StatCollector.translateToLocal("botania.versioning.updateMessage").replaceAll("%version%", onlineVersion));
          player.addChatComponentMessage(component);
        }
      }

      triedToWarnPlayer = true;
    }
  }
}
