package abby.absent.fluff.blocks;

import abby.absent.fluff.Constants;
import abby.absent.fluff.Utility;
import abby.absent.fluff.WoolColour;
import abby.absent.fluff.blocks.gems.*;
import abby.absent.fluff.gems.GemType;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.*;

public class ModBlocks {
    public static final Map<String, Block> GEM_BLOCKS = new LinkedHashMap<>();
    public static final HashMap<WoolColour, Block> WOOL_BLOCKS = new HashMap<>();
    public static final HashMap<WoolColour, Block> CARPET_BLOCKS = new HashMap<>();
    public static final HashMap<WoolColour, Block> FLOWERED_WOOL_BLOCKS = new HashMap<>();
    public static final HashMap<WoolColour, Block> FLOWERED_CARPET_BLOCKS = new HashMap<>();
    public static final HashMap<Pride, Block> PRIDE_BLOCKS = new HashMap<>();
    public static final HashMap<Pride, Block> PRIDE_CARPET_BLOCKS = new HashMap<>();

    public static final List<Block> cutoutBlocks = new ArrayList<>();

    public ModBlocks() {
        Utility.LOGGER.debug("START REGISTER BLOCKS");

        putAndRegisterGemBlocks();

        putWoolBlocks();
        putPrideBlocks();
    }

    private void putWoolBlocks() {
        for (WoolColour colour : WoolColour.values()) {
            String colourName = colour.name().toLowerCase();

            AbstractBlock.Settings blockSettings = AbstractBlock.Settings.copy(Blocks.WHITE_WOOL);
            Block wool = new GlazedTerracottaBlock(blockSettings);
            WOOL_BLOCKS.put(colour, wool);
            registerBlock(wool, colourName + Constants.ALPACA_WOOL_SUFFIX);

            Block flowerWool = new GlazedTerracottaBlock(blockSettings);
            FLOWERED_WOOL_BLOCKS.put(colour, flowerWool);
            registerBlock(flowerWool, colourName + Constants.FLOWER_WOOL_SUFFIX + Constants.ALPACA_WOOL_SUFFIX);

            AbstractBlock.Settings carpetSettings = AbstractBlock.Settings.copy(Blocks.WHITE_CARPET);
            Block carpet = new DirectionalCarpetBlock(carpetSettings);
            CARPET_BLOCKS.put(colour, carpet);
            registerBlock(carpet, colourName + Constants.ALPACA_CARPET_SUFFIX);

            Block flowerCarpet = new DirectionalCarpetBlock(carpetSettings);
            FLOWERED_CARPET_BLOCKS.put(colour, flowerCarpet);
            registerBlock(flowerCarpet, colourName + Constants.FLOWER_WOOL_SUFFIX + Constants.ALPACA_CARPET_SUFFIX);
        }
    }

    private void putPrideBlocks() {
        for (Pride pride : Pride.values()) {
            String name = pride.name().toLowerCase();
            AbstractBlock.Settings blockSettings = AbstractBlock.Settings.create()
                    .hardness(0f)
                    .resistance(1200f)
                    .sounds(BlockSoundGroup.WOOL);

            Item.Settings itemSettings = new Item.Settings().fireproof().rarity(Rarity.UNCOMMON);

            Block block = new GlazedTerracottaBlock(blockSettings);
            PRIDE_BLOCKS.put(pride, block);
            registerBlockWithItemSettings(block,
                    Constants.PRIDE_PREFIX + name + Constants.BLOCK_SUFFIX,
                    itemSettings);

            Block carpet = new DirectionalCarpetBlock(blockSettings);
            PRIDE_CARPET_BLOCKS.put(pride, carpet);
            registerBlockWithItemSettings(carpet,
                    Constants.PRIDE_PREFIX + name + Constants.CARPET_SUFFIX + Constants.BLOCK_SUFFIX,
                    itemSettings);
        }
    }

    private void putAndRegisterGemBlocks() {
        GemType.forEach((name, type) -> {
            GEM_BLOCKS.put(name + Constants.BLOCK_SUFFIX, new GemBlock(type));
            GEM_BLOCKS.put(name + Constants.ORE_SUFFIX, new GemOre(type));
            GEM_BLOCKS.put(name + Constants.ORE_DEEPSLATE_SUFFIX, new GemOre(type));
            GEM_BLOCKS.put(name + Constants.LAMP_SUFFIX, new GemLamp(false));
            GEM_BLOCKS.put(name + Constants.INVERTED_LAMP_SUFFIX, new GemLamp(true));
            Block gemCore = new GemCore(type);
            GEM_BLOCKS.put(name + Constants.CORE_SUFFIX, gemCore);
            cutoutBlocks.add(gemCore);
        });

        GEM_BLOCKS.put("gem_cutter", new GemCutter());

        GEM_BLOCKS.forEach((name, block) ->
                registerBlock(block, name)
        );
    }

    private void registerBlock(Block block, String name) {
        registerBlockWithItemSettings(block, name, new Item.Settings());
    }

    private void registerBlockWithItemSettings(Block block, String name, Item.Settings itemSettings) {
        Utility.LOGGER.debug("Registered: {}", name);
        Identifier identifier = Utility.identifier(name);
        Registry.register(Registries.BLOCK, identifier, block);
        Registry.register(Registries.ITEM, identifier, new BlockItem(block, itemSettings));
    }
}
