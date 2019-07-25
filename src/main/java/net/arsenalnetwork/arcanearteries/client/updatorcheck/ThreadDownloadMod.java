//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package net.arsenalnetwork.arcanearteries.client.updatorcheck;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent.Serializer;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ThreadDownloadMod extends Thread {
  String fileName;
  byte[] buffer = new byte[10240];
  int totalBytesDownloaded;
  int bytesJustDownloaded;
  InputStream webReader;

  public ThreadDownloadMod(String fileName) {
    this.setName("Arcane Arteries Download File Thread");
    this.fileName = fileName;
    this.setDaemon(true);
    this.start();
  }

  public void run() {
    try {
      IChatComponent component = Serializer.func_150699_a(String.format(StatCollector.translateToLocal("botania.versioning.startingDownload"), this.fileName));
      if (Minecraft.getMinecraft().thePlayer != null) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(component);
      }

      VersionChecker.startedDownload = true;
      String base = "http://botaniamod.net/";
      String file = this.fileName.replaceAll(" ", "%20");
      URL url = new URL(base + "dl.php?file=" + file);

      try {
        url.openStream().close();
      } catch (IOException var10) {
      }

      url = new URL(base + "files/" + file);
      this.webReader = url.openStream();
      File dir = new File(".", "mods");
      File f = new File(dir, this.fileName + ".dl");
      f.createNewFile();

      FileOutputStream outputStream;
      for(outputStream = new FileOutputStream(f.getAbsolutePath()); (this.bytesJustDownloaded = this.webReader.read(this.buffer)) > 0; this.totalBytesDownloaded += this.bytesJustDownloaded) {
        outputStream.write(this.buffer, 0, this.bytesJustDownloaded);
        this.buffer = new byte[10240];
      }

      outputStream.close();
      this.webReader.close();
      File f1 = new File(dir, this.fileName);
      if (!f1.exists()) {
        f.renameTo(f1);
      }

      if (Minecraft.getMinecraft().thePlayer != null) {
        Minecraft.getMinecraft().thePlayer.addChatMessage((new ChatComponentTranslation("botania.versioning.doneDownloading", new Object[]{this.fileName})).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.AQUA)));
      }

      Desktop.getDesktop().open(dir);
      VersionChecker.downloadedFile = true;
      this.finalize();
    } catch (Throwable var11) {
      var11.printStackTrace();
      this.sendError();

      try {
        this.finalize();
      } catch (Throwable var9) {
        var9.printStackTrace();
      }
    }

  }

  private void sendError() {
    if (Minecraft.getMinecraft().thePlayer != null) {
      Minecraft.getMinecraft().thePlayer.addChatComponentMessage((new ChatComponentTranslation("botania.versioning.error", new Object[0])).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.RED)));
    }

  }
}
