package net.arsenalnetwork.arcanearteries.client.research;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;
import thaumcraft.api.research.ResearchEntry;

public class ArcaneResearch
{
    public static ResearchCategory researchCategory;

    public static void addResearch()
    {
        AspectList aspectList = new AspectList();
        researchCategory = ResearchCategories.registerCategory("arcanearteries", null,
                aspectList, new ResourceLocation(""), new ResourceLocation(""));

        ResearchEntry researchEntry = new ResearchEntry();
        researchEntry.setMeta(null);
        ResearchCategories.registerCategory("arcanearteries", null,
                aspectList, new ResourceLocation(""), new ResourceLocation(""));
    }
}
