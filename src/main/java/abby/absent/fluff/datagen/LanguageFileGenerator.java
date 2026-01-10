package abby.absent.fluff.datagen;

import abby.absent.fluff.Constants;
import abby.absent.fluff.Utility;
import abby.absent.fluff.WoolColour;
import abby.absent.fluff.blocks.ModBlocks;
import abby.absent.fluff.blocks.Pride;
import abby.absent.fluff.gems.GemType;
import abby.absent.fluff.items.jewelry.MetalBase;
import abby.absent.fluff.items.ModItems;
import abby.absent.fluff.miscellaneous.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class LanguageFileGenerator extends FabricLanguageProvider {
    private TranslationBuilder translationBuilder;
    public LanguageFileGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        this.translationBuilder = translationBuilder;

        GemType.forEach((name, gemType) -> {
            String displayName = gemType.getDisplayName();

            addTranslation(ModItems.gemItemMap.get(name), gemType.getDisplayName());

            translationBuilder.add(
                    Constants.GEM_LORE_TRANSLATION_KEY + name,
                    gemType.getLore()
            );

            translationBuilder.add(
                    Constants.KEY_TOOLTIP_TRANSLATION_KEY + name,
                    gemType.getImplementation().keyTooltip()
            );

            addTranslation(ModItems.gemToolMap.get(
                    name + Constants.PICKAXE_SUFFIX),
                    displayName + " Pickaxe"
            );
            addTranslation(ModItems.gemToolMap.get(name + Constants.AXE_SUFFIX), displayName + " Axe");
            addTranslation(ModItems.gemToolMap.get(name + Constants.SHOVEL_SUFFIX), displayName + " Shovel");
            addTranslation(ModItems.gemToolMap.get(name + Constants.SWORD_SUFFIX), displayName + " Sword");
            addTranslation(ModItems.gemToolMap.get(name + Constants.HOE_SUFFIX), displayName + " Hoe");

            for (MetalBase metal : MetalBase.values()) {
                addTranslation(
                        ModItems.accessoryItemMap.get(name + metal.getItemSuffix() + Constants.BRACELET_SUFFIX),
                        displayName + " " + metal.getDisplayName() + " Bracelet"
                );
                addTranslation(
                        ModItems.accessoryItemMap.get(name + metal.getItemSuffix() + Constants.NECKLACE_SUFFIX),
                        displayName + " " + metal.getDisplayName() + " Necklace"
                );
                addTranslation(
                        ModItems.accessoryItemMap.get(name + metal.getItemSuffix() + Constants.KEY_SUFFIX),
                        displayName + " " + metal.getDisplayName() + " Key"
                );
            }

            addTranslation(ModBlocks.GEM_BLOCKS.get(name + Constants.BLOCK_SUFFIX), "Block of " + displayName);
            addTranslation(ModBlocks.GEM_BLOCKS.get(name + Constants.ORE_SUFFIX), displayName + " Ore");
            addTranslation(ModBlocks.GEM_BLOCKS.get(
                    name + Constants.ORE_DEEPSLATE_SUFFIX),
                    displayName + " Deepslate Ore"
            );
            addTranslation(ModBlocks.GEM_BLOCKS.get(name + Constants.CORE_SUFFIX), displayName + " Lock Box");
            addTranslation(ModBlocks.GEM_BLOCKS.get(name + Constants.LAMP_SUFFIX), displayName + " Lamp");
            addTranslation(ModBlocks.GEM_BLOCKS.get(
                    name + Constants.INVERTED_LAMP_SUFFIX),
                    displayName + " Inverted Lamp"
            );
        });

        for (MetalBase metal : MetalBase.values()) {
            addTranslation(
                    ModItems.accessoryItemMap.get(metal.getLowercaseName() + Constants.BRACELET_SUFFIX),
                    metal.getDisplayName() + " Bracelet"
            );
            addTranslation(
                    ModItems.accessoryItemMap.get(metal.getLowercaseName() + Constants.NECKLACE_SUFFIX),
                    metal.getDisplayName() + " Necklace"
            );
            addTranslation(
                    ModItems.accessoryItemMap.get(metal.getLowercaseName() + Constants.KEY_SUFFIX),
                    metal.getDisplayName() + " Key"
            );
        }

        addTranslation(ModBlocks.GEM_BLOCKS.get("gem_cutter"), "Gem Cutter");

        addTranslation(ModItems.basicItemMap.get("obsidian_shard"), "Obsidian Shard");
        addTranslation(ModItems.basicItemMap.get("tool_rod"), "Obsidian Plated Iron Rod");

        for (WoolColour colour : WoolColour.values()) {
            String name = colour.name().toLowerCase();
            addTranslation(ModBlocks.WOOL_BLOCKS.get(colour), name + " Alpaca Wool");
            addTranslation(ModBlocks.CARPET_BLOCKS.get(colour), name + " Alpaca Wool Carpet");
            addTranslation(ModBlocks.FLOWERED_WOOL_BLOCKS.get(colour), name + " Flowered Alpaca Wool");
            addTranslation(ModBlocks.FLOWERED_CARPET_BLOCKS.get(colour), name + " Flowered Alpaca Wool Carpet");
        }
        for (Pride pride : Pride.values()) {
            addTranslation(ModBlocks.PRIDE_BLOCKS.get(pride), pride.displayName + " Flag");
            addTranslation(ModBlocks.PRIDE_CARPET_BLOCKS.get(pride), pride.displayName + " Flag Carpet");
        }

        translationBuilder.add(
                Registries.ITEM_GROUP.getKey(
                        Registries.ITEM_GROUP.get(Utility.identifier(Constants.GENERAL_GROUP_ID))).orElseThrow(),
                "Crystallised General"
        );
        translationBuilder.add(
                Registries.ITEM_GROUP.getKey(
                        Registries.ITEM_GROUP.get(Utility.identifier(Constants.TOOL_GROUP_ID))).orElseThrow(),
                "Crystallised Tools"
        );
        translationBuilder.add(
                Registries.ITEM_GROUP.getKey(
                        Registries.ITEM_GROUP.get(Utility.identifier(Constants.ACCESSORY_GROUP_ID))).orElseThrow(),
                "Crystallised Accessories"
        );

        translationBuilder.add(
                "death.attack.eat_rock",
                "Just because Abigail does it, doesn't mean %1$s can eat rocks"
        );
        translationBuilder.add(
                "death.attack.moonfall",
                "%1$s succumbed to the moon"
        );

        translationBuilder.add(
                "trinkets.slot.hand.bracelet",
                "Bracelet"
        );
        translationBuilder.add(
                "trinkets.slot.offhand.bracelet",
                "Offhand Bracelet"
        );

        translationBuilder.add(
                Constants.KUNZITE_OUT_OF_RANGE_TRANSLATION_KEY,
                "Blocks have to be within 32 blocks of each other"
        );

        translationBuilder.add(
                Constants.BOUND_TO_POSITION_TOOLTIP_TRANSLATION_KEY,
                "Bound to block at %1$s"
        );

        translationBuilder.add(
                ModItemTags.BRACELET,
                "Bracelets"
        );
        translationBuilder.add(
                ModItemTags.OFFHAND_BRACELET,
                "Offhand Bracelets"
        );
        translationBuilder.add(
                ModItemTags.NECKLACE,
                "Necklaces"
        );
        translationBuilder.add(
                ModItemTags.GEMS,
                "Crystallised Gems"
        );
    }

    private void addTranslation(Item item, String displayName) {
        translationBuilder.add(item, displayName);
    }

    private void addTranslation(Block block, String displayName) {
        translationBuilder.add(block, displayName);
    }
}
