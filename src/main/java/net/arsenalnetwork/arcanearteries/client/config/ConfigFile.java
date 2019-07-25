package net.arsenalnetwork.arcanearteries.client.config;

import net.arsenalnetwork.arcanearteries.ArcaneArteries;

public class ConfigFile
{
    public static ConfigFile INSTANCE = new ConfigFile();
    public static boolean botaniasacrifice;
    public static boolean thaumicknife;
    public static boolean thaumicsacrifice;
    public static boolean knife;
    public static boolean configSacrifice;
    public static boolean evilDagger;
    public static boolean slates;
    public static boolean slateRecipe;
    public static int rbDamage;
    public static int rbDamageSF;
    public static int rbDamageH;
    public static String rbDesc;
    public static String rbNameOrb;
    public static String rbNameKnife;
    public static int cDaggerLP;
    public static int cDaggerVillager;
    public static int cDaggerSlime;
    public static int cDaggerEnderman;
    public static int cDaggerAnimal;
    public static String cDesc;
    public static String cName;
    public static boolean hardCrafting;
    public static boolean hardDaggers;
    public static boolean manadagger;
    public static int manaused;
    public static boolean disableSac;
    public static boolean disableSelfSac;
    public static final boolean enable = true;
    public static final boolean disable = false;

    public ConfigFile() {}

    public void syncConfig()
    {
        //String HARDCRAFTING = "general" + "." + "Hard Mode";
        //ArcaneArteries.config.addCustomCategoryComment(HARDCRAFTING, "Turn on hardmode");
        //hardCrafting = ArcaneArteries.config.get(HARDCRAFTING, "Use hardmode crafting recipes?", false).getBoolean(false);

        //String HARDDAGGERS = "general" + "." + "Hard Mode";
        //hardDaggers = ArcaneArteries.config.get(HARDDAGGERS, "Arcane Arteries daggers/knives will give less LP?", false).getBoolean(false);

        //String MANADAGGER = "general" + "." + "Hard Mode";
        //manadagger = ArcaneArteries.config.get(MANADAGGER, "The Botantical knife should require mana?", true).getBoolean(true);

        //String MANADAGGERUSED = "general" + "." + "Hard Mode";
        //manaused = ArcaneArteries.config.get(MANADAGGERUSED, "The Botantical knife should use X mana?", 100).getInt();



        String BS = "general" + "." + "Daggers";
        ArcaneArteries.config.addCustomCategoryComment(BS, "Disable different dagger types here");
        botaniasacrifice = ArcaneArteries.config.get(BS, "Enable BotaniaSacrifice Dagger", true).getBoolean(true);

        String TK = "general" + "." + "Daggers";
        ArcaneArteries.config.addCustomCategoryComment(TK, "Disable different dagger types here");
        thaumicknife = ArcaneArteries.config.get(TK, "Enable ThaumicKnife Dagger", true).getBoolean(true);

        String TS = "general" + "." + "Daggers";
        ArcaneArteries.config.addCustomCategoryComment(TS, "Disable different dagger types here");
        thaumicsacrifice = ArcaneArteries.config.get(TS, "Enable ThaumicSacrifice Dagger", true).getBoolean(true);

        String KNIFE = "general" + "." + "Daggers";
        ArcaneArteries.config.addCustomCategoryComment(KNIFE, "Disable different dagger types here");
        knife = ArcaneArteries.config.get(KNIFE, "Enable Knife Dagger", true).getBoolean(true);

        //String RAZ = "general" + "." + "Daggers";
        //razorBlade = ArcaneArteries.config.get(RAZ, "Do you want the configurable 'dagger' to be disabled?", true).getBoolean(true);


        //String SELFSAC = "general" + "." + "Daggers";
        //disableSelfSac = ArcaneArteries.config.get(SELFSAC, "Enable all Daggers of Self Sacrifice?", true).getBoolean(true);

        //String SAC = "general" + "." + "Daggers";
        //disableSac = ArcaneArteries.config.get(SAC, "Enable all Daggers of Sacrifice?", true).getBoolean(true);



        /**
        String RAZMAIN = "general" + "." + "Custom Dagger";
        ArcaneArteries.config.addCustomCategoryComment(RAZMAIN, "Configure the custom dagger here");
        rbDamage = ArcaneArteries.config.get(RAZMAIN, "How much LP should you get without Soul Fray?", 100).getInt();


        String RAZSF = "general" + "." + "Custom Dagger";
        rbDamageSF = ArcaneArteries.config.get(RAZSF, "How much LP should you get with Soul Fray?", 10).getInt();


        String RAZD = "general" + "." + "Custom Dagger";
        rbDamageH = ArcaneArteries.config.get(RAZD, "How many half hearts should a prick damge the player by?", 2).getInt();


        String RAZI = "general" + "." + "Custom Dagger";
        rbDesc = ArcaneArteries.config.get(RAZI, "What should the blade info be?", "Slice 'n' Dice!").getString();


        String RAZON = "general" + "." + "Custom Dagger";
        rbNameOrb = ArcaneArteries.config.get(RAZON, "What should the blade be called, if Blood Magic is in Wimp-mode?", "Special Orb").getString();



        String RAZDN = "general" + "." + "Custom Dagger";
        rbNameKnife = ArcaneArteries.config.get(RAZDN, "What should the blade be called?", "Razor Blade").getString();



        String SLATES = "general" + "." + "Slates";
        ArcaneArteries.config.addCustomCategoryComment(SLATES, "Configs to do with custom slates");
        slates = ArcaneArteries.config.get(SLATES, "Enable different basic slates, and runes", true).getBoolean(true);

        String SLATERECIPE = "general" + "." + "Slates";
        slateRecipe = ArcaneArteries.config.get(SLATERECIPE, "Enable non-Blood Magic -> Reinforced Slate recipe", true).getBoolean(true);




        String CD = "general" + "." + "Custom Dagger of Sacrifice";
        ArcaneArteries.config.addCustomCategoryComment(CD, "Configure the custom dagger of sacrifice here");
        configSacrifice = ArcaneArteries.config.get(CD, "Do you want the configurable Dagger of Self Sacrifice to be disabled?", true).getBoolean(true);

        String CDLP = "general" + "." + "Custom Dagger of Sacrifice";
        cDaggerLP = ArcaneArteries.config.get(CDLP, "How much LP should any mob not configured below give per hit?", 500).getInt();

        String CDV = "general" + "." + "Custom Dagger of Sacrifice";
        cDaggerVillager = ArcaneArteries.config.get(CDV, "How much should LP should a villager give?", 2000).getInt();

        String CDS = "general" + "." + "Custom Dagger of Sacrifice";
        cDaggerSlime = ArcaneArteries.config.get(CDS, "How much should LP should a slime give?", 150).getInt();

        String CDE = "general" + "." + "Custom Dagger of Sacrifice";
        cDaggerEnderman = ArcaneArteries.config.get(CDE, "How much should LP should an Enderman give?", 200).getInt();

        String CDA = "general" + "." + "Custom Dagger of Sacrifice";
        cDaggerAnimal = ArcaneArteries.config.get(CDA, "How much should LP should Animals give?", 150).getInt();

        String CDD = "general" + "." + "Custom Dagger of Sacrifice";
        cDesc = ArcaneArteries.config.get(CDD, "What should the dagger info be?", "Deafault Message").getString();

        String CDN = "general" + "." + "Custom Dagger of Sacrifice";
        cName = ArcaneArteries.config.get(CDN, "What should the dagger be called?", "Default Name").getString();
**/


        if (ArcaneArteries.config.hasChanged()) {
            ArcaneArteries.config.save();
        }
    }

}
