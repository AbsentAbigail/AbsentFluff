package abby.absent.fluff.blocks.gems;

import abby.absent.fluff.blocks.blockentities.GemCutterEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class GemCutter extends Block implements BlockEntityProvider {
    private static final VoxelShape BASE_SHAPE = Block.createCuboidShape(2, 0, 2, 14, 4, 14);
    private static final VoxelShape STAND_SHAPE = Block.createCuboidShape(3, 4, 3, 13, 10, 13);
    private static final VoxelShape TOP_SHAPE = Block.createCuboidShape(0, 10, 0, 16, 12, 16);
    private static final VoxelShape CUTTER_SHAPE = Block.createCuboidShape(7, 12, 3, 9, 16, 13);

    public static final String NAME = "gem_cutter";
    public static final VoxelShape SHAPE = VoxelShapes.union(BASE_SHAPE, STAND_SHAPE, TOP_SHAPE, CUTTER_SHAPE);

    public GemCutter() {
        super(Settings.copy(Blocks.STONECUTTER).nonOpaque());
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GemCutterEntity(pos, state);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
