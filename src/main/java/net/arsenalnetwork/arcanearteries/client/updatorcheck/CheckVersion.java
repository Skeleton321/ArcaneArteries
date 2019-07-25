package net.arsenalnetwork.arcanearteries.client.updatorcheck;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class CheckVersion implements Runnable
{

  private static boolean isLatestVersion = false;
  private static String latestVersion = "";
  
  public CheckVersion() {}
  
  public void run() {
    InputStream in = null;
    try
    {
      in = new URL("https://raw.githubusercontent.com/MaxIsH0t/ArcaneArteries/master/version-checker").openStream();

    }
    catch (MalformedURLException e)
    {

      e.printStackTrace();

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

      try
    {
      latestVersion = (String) IOUtils.readLines(in).get(0);

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      IOUtils.closeQuietly(in);
    }
    System.out.println("Latest version of Arcane Arteries is = " + latestVersion);
    isLatestVersion = "5".equals(latestVersion);
  }
  
  public boolean isLatestVersion()
  {
    return isLatestVersion;
  }
  
  public String getLatestVersion()
  {
    return latestVersion;
  }
}
