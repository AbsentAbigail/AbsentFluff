package abby.absent.fluff.blocks.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class GemCutterEntity extends BlockEntity {
    public GemCutterEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GEM_CUTTER_ENTITY, pos, state);
    }
}
