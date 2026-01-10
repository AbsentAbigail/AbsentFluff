package abby.absent.fluff.datagen;

import abby.absent.fluff.Constants;
import abby.absent.fluff.blocks.ModBlocks;
import abby.absent.fluff.gems.GemType;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {
    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        GemType.forEach((name, type) -> {
            Block block = ModBlocks.GEM_BLOCKS.get(name + Constants.BLOCK_SUFFIX);
            Block ore = ModBlocks.GEM_BLOCKS.get(name + Constants.ORE_SUFFIX);
            Block deepslate_ore = ModBlocks.GEM_BLOCKS.get(name + Constants.ORE_DEEPSLATE_SUFFIX);
            Block core = ModBlocks.GEM_BLOCKS.get(name + Constants.CORE_SUFFIX);

            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(
                            block,
                            ore,
                            deepslate_ore,
                            ModBlocks.GEM_BLOCKS.get(name + Constants.LAMP_SUFFIX),
                            ModBlocks.GEM_BLOCKS.get(name + Constants.INVERTED_LAMP_SUFFIX),
                            core
                    );
            getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
                    .add(block);
            getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                    .add(
                            ore,
                            deepslate_ore,
                            core
                    );
        });

        var woolTag = getOrCreateTagBuilder(BlockTags.WOOL);
        ModBlocks.WOOL_BLOCKS.forEach((c, block) -> woolTag.add(block));
        ModBlocks.FLOWERED_WOOL_BLOCKS.forEach((c, block) -> woolTag.add(block));
        ModBlocks.PRIDE_BLOCKS.forEach((c, block) -> woolTag.add(block));
    }
}
