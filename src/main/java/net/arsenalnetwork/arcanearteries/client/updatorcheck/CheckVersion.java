package net.arsenalnetwork.arcanearteries.client.updatorcheck;

public class CheckVersion
  //implements Runnable
{
  /**
  private static boolean isLatestVersion = false;
  private static String latestVersion = "";
  
  public CheckVersion() {}
  
  public void run() {
    InputStream in = null;
    try
    {
      in = new URL("https://raw.githubusercontent.com/jordsta95/ArcaneArteries/master/aaversion.txt").openStream();

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
      latestVersion = (String)IOUtils.readLines(in).get(0);

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
  }**/
}
