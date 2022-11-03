package net.foulest.apollo.check.impl.clientbrand;

import net.foulest.apollo.alert.AlertManager;
import net.foulest.apollo.check.CheckData;
import net.foulest.apollo.check.impl.clientbrand.type.DataType;
import net.foulest.apollo.check.impl.clientbrand.type.PayloadType;
import net.foulest.apollo.check.type.PacketCheck;
import net.foulest.apollo.data.PlayerData;
import net.foulest.apollo.wrapper.impl.client.WrappedPlayInCustomPayload;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@CheckData(name = "ClientBrand")
public class ClientBrand extends PacketCheck {

    public static final List<PayloadType> BRANDS = Arrays.asList(
            new PayloadType("vanilla", "Vanilla", DataType.BRAND, false),
            new PayloadType("\u0007vanilla", "Vanilla", DataType.BRAND, false),
            new PayloadType("fabric", "Fabric", DataType.BRAND, false),
            new PayloadType("\u0006fabric", "Fabric", DataType.BRAND, false),
            new PayloadType("\u0007fabric", "Fabric", DataType.BRAND, false),
            new PayloadType("\u000EFeather Fabric", "Feather Client", DataType.BRAND, false),
            new PayloadType("\rFeather Forge", "Feather Client", DataType.BRAND, false),
            new PayloadType("fml,forge", "Forge", DataType.BRAND, false),
            new PayloadType("\tfml,forge", "Forge", DataType.BRAND, false),
            new PayloadType("\u0007fml,forge", "Forge", DataType.BRAND, false),
            new PayloadType("LiteLoader", "LiteLoader", DataType.BRAND, false),
            new PayloadType("\u0007LiteLoader", "LiteLoader", DataType.BRAND, false),
            new PayloadType("\u0005PLC18", "PvPLounge Client", DataType.BRAND, false),

            new PayloadType("\nLunar-Client", "Lunar Client Spoof", DataType.BRAND, true),
            new PayloadType("Vanilla", "Vanilla Spoof", DataType.BRAND, true),
            new PayloadType("\u0007Vanilla", "Vanilla Spoof", DataType.BRAND, true),
            new PayloadType("Synergy", "Synergy Client", DataType.BRAND, true),
            new PayloadType("\u0007Synergy", "Synergy Client", DataType.BRAND, true),
            new PayloadType("Created By", "Cracked Vape", DataType.BRAND, true),
            new PayloadType("\u0007Created By ", "Cracked Vape", DataType.BRAND, true),
            new PayloadType("\u0003FML", "Forge Spoof", DataType.BRAND, true),
            new PayloadType("\u0003LMC", "LabyMod Spoof", DataType.BRAND, true),
            new PayloadType("PLC18", "PvPLounge Client Spoof", DataType.BRAND, true),
            new PayloadType("\u0002CB", "CheatBreaker Spoof", DataType.BRAND, true),
            new PayloadType("Geyser", "Geyser Spoof", DataType.BRAND, true)
    );

    public static final List<PayloadType> REGISTER_DATA = Arrays.asList(
            new PayloadType("sampler", "Sampler", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("the5zigmod:5zig_set", "5zig Mod", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("fabric:container/ope", "Fabric", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("fabric:registry/sync", "Fabric", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("feather:client", "Feather Client", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("OCMC", "OpticCraft Client", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("Schematica", "Schematica", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("OpenComputers", "OpenComputers", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("skinport", "SkinPort", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("WDL|INIT", "WorldDownloader", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("MorePlayerModels", "MorePlayerModels", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("advancedcapes", "AdvancedCapes", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("PERMISSIONSREPL", "WorldDownloader", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("world_info", "Unknown (world_info)", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("WECUI", "WorldEdit CUI", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BungeeCord", "BungeeCord", DataType.REGISTER_DATA_OTHER, false),
            new PayloadType("legacy:redisbungee", "BungeeCord", DataType.REGISTER_DATA_OTHER, false),
            new PayloadType("FML|HS", "Forge", DataType.REGISTER_DATA_OTHER, false),
            new PayloadType("FML", "Forge", DataType.REGISTER_DATA_OTHER, false),
            new PayloadType("FML|MP", "Forge", DataType.REGISTER_DATA_OTHER, false),
            new PayloadType("FORGE", "Forge", DataType.REGISTER_DATA_OTHER, false),
            new PayloadType("recipemod:key", "NoMoreRecipeConflict", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("Animania", "Animania", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioAStand", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioAtlas", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioAtlasSWP", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioAtlasTGUI", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioAtlasWPT", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioClipboard", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioClock", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioDeskOpenGUI", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioDrillText", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioMCBEdit", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioMCBPage", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioMapPin", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioMeasure", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioOpenBook", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioPaintPress", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioPainting", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioPaintingC", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioPaneler", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioRBook", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioRBookLoad", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioRecipeCraft", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioRecipeText", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioRenderUpdate", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioSign", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioStockCompass", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioStockLog", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioStockTitle", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioType", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioTypeDelete", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioTypeFlag", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioTypeUpdate", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BiblioUpdateInv", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("OpenTerrainGenerator", "OpenTerrainGenerator", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("appleskin", "AppleSkin", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("autoreglib", "AutoRegLib", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("backpack", "Backpacks", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("betteranimalsplus", "BetterAnimalsPlus", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("biomecolorizer:a", "BiomeColorizer", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("cfm", "FurnitureMod", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("chisel", "Chisel", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("craftstudioapi", "CraftStudioAPI", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("eplus", "EnchantingPlus", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("fastbench", "FastWorkbench", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("futuremc", "FutureMC", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("harvestcraft", "Pam's HarvestCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("ichunutil", "iChunUtil", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("jei", "JEI", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("jeid", "JEI", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("jm_dim_permission", "JourneyMap", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("jm_init_login", "JourneyMap", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("journeymap_channel", "JourneyMap", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("openterraingenerator", "OpenTerrainGenerator", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("patchouli", "Patchouli", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("pixelmon", "Pixelmon", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("storagedrawers", "StorageDrawers", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("waila", "WAILA", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("waystones", "Waystones", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("world_info", "WorldInfo", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("AE2", "AE2", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BIN", "BinnieMods", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("BOT", "Botania", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("CCL_INTERNAL", "CharsetCrafting", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("ChickenChunks", "ChickenChunks", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("ChiselsAndBits", "ChiselsAndBits", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("CoFH", "CoFHCore", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("EB", "Unknown (EB)", DataType.REGISTER_DATA_OTHER, false),
            new PayloadType("ES", "Unknown (ES)", DataType.REGISTER_DATA_OTHER, false),
            new PayloadType("ET", "Unknown (ET)", DataType.REGISTER_DATA_OTHER, false),
            new PayloadType("EnderCore", "EnderCore", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("FLoco", "FunkyLocomotion", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("FOR", "Forge", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("ForgeMicroblock", "Forge", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("ForgeMultipart", "Forge", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("GEN", "Unknown (GEN)", DataType.REGISTER_DATA_OTHER, false),
            new PayloadType("GrCCellar", "GrowthCraft", DataType.REGISTER_DATA_OTHER, false),
            new PayloadType("GuideAPI", "GuideAPI", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("HatStand", "HatStand", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("Hats", "Hats", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("InventoryTweaks", "InventoryTweaks", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("LogisticsPipes", "LogisticsPipes", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("MEK", "Mekanism", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("Morph", "Morph", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("Mystcraft", "Mystcraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("OpenComputers", "OpenComputers", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("OpenMods|E", "OpenMods", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("OpenMods|M", "OpenMods", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("OpenMods|RPC", "OpenMods", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("PR|Integr", "ProjectRed", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("PR|Reloc", "ProjectRed", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("PR|Transp", "ProjectRed", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("PneumaticCraft", "PneumaticCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("PortalGun", "PortalGun", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("RC", "RebornCore", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("TestDummy", "TestDummy", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("WRCBE", "WRCBE", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("WorldControl", "WorldControl", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("XU2", "Unknown (XU2)", DataType.REGISTER_DATA_OTHER, false),
            new PayloadType("aroma1997core", "Aroma1997", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("aroma1997sdimension", "Aroma1997", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("autoconfig", "Unknown (autoconfig)", DataType.REGISTER_DATA_OTHER, false),
            new PayloadType("baubles", "Baubles", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("bdew.ae2stuff", "Bdew", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("bdew.compacter", "Bdew", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("bdew.multiblock", "Bdew", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("bibliocraft", "BiblioCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("bigreactors", "BigReactors", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("biomesoplenty", "Biomes O' Plenty", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("bloodmagic", "Blood Magic", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("botania", "Botania", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("buildcraftbuilders", "BuildCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("buildcraftcore", "BuildCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("buildcraftlib", "BuildCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("buildcraftrobotics", "BuildCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("buildcrafttransport", "BuildCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("chrs:lib", "Chrs", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("chrs:pocket", "Chrs", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("computercraft", "ComputerCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("computronics", "Computronics", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("cookingforblockheads", "CookingForBlockheads", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("crafttweaker", "CraftTweaker", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("creativemd", "CreativeMD", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("cucumber", "Cucumber", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("culinaryconstruct", "CulinaryConstruct", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("damagetilt", "DamageTilt", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("eiococ", "EnderIO", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("enderio", "EnderIO", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("enderioconduits", "EnderIO", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("enderioinvpanel", "EnderIO", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("enderiomachines", "EnderIO", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("enderiopowertools", "EnderIO", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("exchangers", "Exchangers", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("extracells", "ExtraCells", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("extraplanets", "ExtraPlanets", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("factorymanager", "FactoryManager", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("fairylights", "FairyLights", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("forgemultipartcbe", "Forge", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("galacticraft", "GalactiCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("galacticraftcore", "GalactiCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("gasconduits", "GasConduits", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("iChun_WorldPortals", "iChun", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("ic2", "IC2", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("icbmclassic", "ICBMClassic", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("industrialforegoing", "IndustrialForegoing", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("ironchest", "IronChest", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("jee", "JustEnoughEnergistics", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("mantle:books", "Mantle", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("mcmultipart", "Forge", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("mcmultipart_cbe", "Forge", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("modularrouters", "ModularRouters", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("mrtjpcore", "MrTJPCore", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("mysticallib", "MysticalLib", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("naturescompass", "NaturesCompass", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("numina", "Numina", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("planetprogression", "PlanetProgression", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("plethora", "Plethora", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("plustic", "Plustic", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("projectred-core", "ProjectRed", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("projectred-expansion", "ProjectRed", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("reccomplex", "RecComplex", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("schematica", "Schematica", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("simplyjetpacks", "SimplyJetpacks", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("statues", "Statues", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("superfactorymanager", "SuperFactoryManager", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("supersoundmuffler", "SuperSoundMuffler", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("tconstruct", "TinkersConstruct", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("teslacorelib", "TeslaCoreLib", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("thaumcraft", "ThaumCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("thaumicaugmentation", "ThaumCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("thaumicenergistics", "ThaumCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("thaumictinkerer", "ThaumCraft", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("torchmaster", "TorchMaster", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("translocators", "Translocators", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("treechop-channel", "TreeChop", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("twilightforest", "TwilightForest", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("wanionlib", "WanionLib", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("world_id", "Unknown (world_id)", DataType.REGISTER_DATA_OTHER, false),
            new PayloadType("xreliquary", "Reliquary", DataType.REGISTER_DATA_MOD, false),
            new PayloadType("zettaindustries", "ZettaIndustries", DataType.REGISTER_DATA_MOD, false),

            new PayloadType("Lunar-Client", "Lunar Client Spoof", DataType.REGISTER_DATA_OTHER, true)
    );

    public static final List<PayloadType> PAYLOADS = Arrays.asList(
            new PayloadType("LOLIMAHCKER", "Cracked Vape", DataType.CHANNEL, true),
            new PayloadType("CPS_BAN_THIS_NIGGER", "Cracked Vape", DataType.CHANNEL, true),
            new PayloadType("#unbanearwax", "Cracked Vape", DataType.CHANNEL, true),
            new PayloadType("mergeclient", "Cracked Merge", DataType.CHANNEL, true),
            new PayloadType("wigger", "Cracked Merge", DataType.CHANNEL, true),
            new PayloadType("1946203560", "Cracked Vape", DataType.CHANNEL, true),
            new PayloadType("cock", "Cracked Merge", DataType.CHANNEL, true),
            new PayloadType("lmaohax", "Cracked Incognito", DataType.CHANNEL, true),
            new PayloadType("reach", "Reach Mod", DataType.CHANNEL, true),
            new PayloadType("gg", "Reach Mod", DataType.CHANNEL, true),
            new PayloadType("ethylene", "Ethylene", DataType.CHANNEL, true),
            new PayloadType("Aimbot", "Merge Aimbot", DataType.CHANNEL, true),
            new PayloadType("gc", "Generic Client", DataType.CHANNEL, true),
            new PayloadType("customGuiOpenBspkrs", "BspkrsCore Client", DataType.CHANNEL, true),
            new PayloadType("0SO1Lk2KASxzsd", "BspkrsCore Client", DataType.CHANNEL, true),
            new PayloadType("mincraftpvphcker", "BspkrsCore Client", DataType.CHANNEL, true),
            new PayloadType("MCnetHandler", "TimeChanger Misplace", DataType.CHANNEL, true),

            new PayloadType("n", "Unknown Cheat (n)", DataType.CHANNEL, true),
            new PayloadType("BLC|M", "Unknown Cheat (BLC|M)", DataType.CHANNEL, true),
            new PayloadType("XDSMKDKFDKSDAKDFkEJF", "Unknown Cheat (XDSMKDKFDKSDAKDFkEJF)", DataType.CHANNEL, true),
            new PayloadType("0SSxzsd", "Unknown Cheat (0SSxzsd)", DataType.CHANNEL, true)
    );

    public ClientBrand(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void process(Object object) {
        if (object instanceof WrappedPlayInCustomPayload) {
            WrappedPlayInCustomPayload packet = (WrappedPlayInCustomPayload) object;

            if (packet.getPayload().equals("minecraft:brand")
                    || packet.getPayload().equals("MC|Brand")) {
                // Kicks players that register empty data.
                if (packet.getData().equals("")) {
                    playerData.kick("Empty Brand", "");
                    return;
                }

                checkBrand(packet.getData());

            } else if (packet.getPayload().equals("minecraft:register")
                    || packet.getPayload().equals("REGISTER")) {
                // Kicks players that register empty data.
                if (packet.getData().equals("")) {
                    playerData.kick("Empty Data", "");
                    return;
                }

                // Splits the null spaces from the data and checks lines individually.
                if (packet.getData().contains("\0")) {
                    String[] splitData = packet.getData().split("\0");

                    for (String line : splitData) {
                        checkData(line);
                    }
                } else {
                    checkData(packet.getData());
                }

            } else {
                // Player sends an unknown channel, check it.
                checkPayload(packet.getPayload());
            }
        }
    }

    public void checkBrand(String data) {
        // Remove Velocity's brand suffix.
        data = data.replace(" (Velocity)", "");

        // Checks for Lunar Client.
        if (data.matches("\u0013lunarclient:.......$")) {
            playerData.getPayloads().add(new PayloadType(data, "Lunar Client", DataType.BRAND, false));
            return;
        }

        // Checks for CM Client.
        if (data.matches("\u0010cmclient:.......$")) {
            playerData.getPayloads().add(new PayloadType(data, "CM Client", DataType.BRAND, false));
            return;
        }

        // Kicks players with blacklisted brands.
        for (PayloadType payloadType : BRANDS) {
            if (payloadType.data.equals(data)) {
                if (payloadType.blacklisted) {
                    playerData.kick("Blacklisted Brand", payloadType.name + " (" + payloadType.data + ")");
                } else {
                    playerData.getPayloads().add(payloadType);
                    return;
                }
            }
        }

        // Prints warnings for players with unknown brands.
        AlertManager.sendAlert("&8[Apollo] &f" + playerData.getBukkitPlayer().getName()
                + " &7sent an unknown brand: &f" + data);

        // Prints unknown brands to a text file.
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("unknown-brand.txt"));
            writer.write(data);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void checkData(String data) {
        // Kicks players with blacklisted registered data.
        for (PayloadType payloadType : REGISTER_DATA) {
            if (payloadType.data.equals(data)) {
                if (payloadType.blacklisted) {
                    playerData.kick("Blacklisted Data", payloadType.name + " (" + payloadType.data + ")");
                } else {
                    playerData.getPayloads().add(payloadType);
                    return;
                }
            }
        }

        // Prints warnings for players with unknown registered data.
        AlertManager.sendAlert("&8[Apollo] &f" + playerData.getBukkitPlayer().getName()
                + " &7registered unknown data: &f" + data);

        // Prints unknown data to a text file.
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("unknown-data.txt"));
            writer.write(data);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void checkPayload(String channelName) {
        // Kicks players on Crystalware.
        if (channelName.startsWith("CRYSTAL|")) {
            playerData.kick("Blacklisted Payload", "CHANNEL=" + channelName);
            return;
        }

        // Kicks players with blacklisted payloads.
        for (PayloadType payloadType : PAYLOADS) {
            if (payloadType.data.equals(channelName)) {
                if (payloadType.blacklisted) {
                    playerData.kick("Blacklisted Payload", payloadType.name + " (" + payloadType.data + ")");
                } else {
                    playerData.getPayloads().add(payloadType);
                    return;
                }
            }
        }
    }
}
