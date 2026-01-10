package abby.absent.fluff.datagen;

import abby.absent.fluff.Constants;
import abby.absent.fluff.blocks.ModBlocks;
import abby.absent.fluff.gems.GemType;
import abby.absent.fluff.items.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BlockLootTableGenerator extends FabricBlockLootTableProvider {

    public BlockLootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        GemType.forEach((name, gemType) -> {
            addDrop(ModBlocks.GEM_BLOCKS.get(name + Constants.ORE_SUFFIX),
                    oreDrops(
                            ModBlocks.GEM_BLOCKS.get(name + Constants.ORE_SUFFIX),
                            ModItems.gemItemMap.get(name)
                    ));
            addDrop(ModBlocks.GEM_BLOCKS.get(name + Constants.ORE_DEEPSLATE_SUFFIX),
                    oreDrops(
                            ModBlocks.GEM_BLOCKS.get(name + Constants.ORE_DEEPSLATE_SUFFIX),
                            ModItems.gemItemMap.get(name)
                    ));
            addDrop(ModBlocks.GEM_BLOCKS.get(name + Constants.BLOCK_SUFFIX));
            addDrop(ModBlocks.GEM_BLOCKS.get(name + Constants.LAMP_SUFFIX));
            addDrop(ModBlocks.GEM_BLOCKS.get(name + Constants.INVERTED_LAMP_SUFFIX));
            addDrop(ModBlocks.GEM_BLOCKS.get(name + Constants.CORE_SUFFIX));
        });

        ModBlocks.WOOL_BLOCKS.forEach((c, block) -> addDrop(block));
        ModBlocks.CARPET_BLOCKS.forEach((c, block) -> addDrop(block));
        ModBlocks.FLOWERED_WOOL_BLOCKS.forEach((c, block) -> addDrop(block));
        ModBlocks.FLOWERED_CARPET_BLOCKS.forEach((c, block) -> addDrop(block));
        ModBlocks.PRIDE_BLOCKS.forEach((c, block) -> addDrop(block));
        ModBlocks.PRIDE_CARPET_BLOCKS.forEach((c, block) -> addDrop(block));
    }
}
