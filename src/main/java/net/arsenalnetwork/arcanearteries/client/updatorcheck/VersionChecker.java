package net.arsenalnetwork.arcanearteries.client.updatorcheck;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public final class VersionChecker
{
  private static final int FLAVOUR_MESSAGES = 50;
  
  public VersionChecker() {}
  
  public static boolean doneChecking = false;
  public static String onlineVersion = "";
  public static boolean triedToWarnPlayer = false;
  
  public static boolean startedDownload = false;
  public static boolean downloadedFile = false;
  
  public void init()
  {
    new ThreadVersionChecker();
    FMLCommonHandler.instance().bus().register(this);
  }
  
  @SubscribeEvent
  public void onTick(TickEvent.ClientTickEvent event)
  {
    if (doneChecking && event.phase == TickEvent.Phase.END && Minecraft.getMinecraft().player != null && !triedToWarnPlayer)
    {
      if (!onlineVersion.isEmpty())
      {
        EntityPlayer player = Minecraft.getMinecraft().player;
        int onlineBuild = Integer.parseInt(onlineVersion);
        int clientBuild = "173".contains("GRADLE") ? 2147483647 : Integer.parseInt("173");
        if (onlineBuild > clientBuild)
        {
          player.sendMessage((new TextComponentTranslation("botania.versioning.flavour" + player.world.rand.nextInt(50), new Object[0])).setStyle((new Style()).setColor(TextFormatting.RED)));
          player.sendMessage(new TextComponentTranslation("botania.versioning.outdated", new Object[]{clientBuild, onlineBuild}));
        }


        triedToWarnPlayer = true;
      }
    }
  }
}
