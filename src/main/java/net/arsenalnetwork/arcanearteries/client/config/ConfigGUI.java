package net.arsenalnetwork.arcanearteries.client.config;

import net.arsenalnetwork.arcanearteries.ArcaneArteries;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ConfigGUI extends GuiConfig
{
    public ConfigGUI(GuiScreen screen)
    {
        super(screen, new ConfigElement(ArcaneArteries.config.getCategory("general")).getChildElements(), "arcanearteries", false, false,
                GuiConfig.getAbridgedConfigPath(ArcaneArteries.config.toString()));
    }
}
