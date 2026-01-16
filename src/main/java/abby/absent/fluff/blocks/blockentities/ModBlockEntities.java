package abby.absent.fluff.blocks.blockentities;

import abby.absent.fluff.Utility;
import abby.absent.fluff.blocks.ModBlocks;
import abby.absent.fluff.blocks.gems.GemCutter;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntities {
    public static final BlockEntityType<GemCutterEntity> GEM_CUTTER_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Utility.identifier("gem_cutter_entity"),
            BlockEntityType.Builder.create(GemCutterEntity::new, ModBlocks.GEM_BLOCKS.get(GemCutter.NAME)).build()
    );
    public static final BlockEntityType<GateCompoundBlockEntity> GATE_COMPOUND_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Utility.identifier("gate_compound_block_entity"),
            BlockEntityType.Builder.create(GateCompoundBlockEntity::new, ModBlocks.GATE_COMPOUND_BLOCK).build()
    );

    static {
        Utility.LOGGER.info("START REGISTER BLOCK ENTITIES");
    }

    public static void init() {
        Utility.LOGGER.info("Finished registering block entities");
    }
}
