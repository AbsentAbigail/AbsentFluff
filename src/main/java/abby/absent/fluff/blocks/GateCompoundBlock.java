package abby.absent.fluff.blocks;

import abby.absent.fluff.blocks.blockentities.GateCompoundBlockEntity;
import abby.absent.fluff.blocks.blockentities.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class GateCompoundBlock extends BlockWithEntity {
    public static final BooleanProperty INTANGIBLE;

    public GateCompoundBlock() {
        super(getDefaultSettings());
        this.setDefaultState(
                this.stateManager.getDefaultState()
                        .with(INTANGIBLE, false)
        );
    }

    public static Settings getDefaultSettings() {
        Settings settings = Settings.copy(Blocks.IRON_BLOCK);
        settings.strength(3.0F, 3.5F).requiresTool();
        return settings;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return state.get(INTANGIBLE) ? VoxelShapes.empty() : VoxelShapes.fullCube();
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!(world.getBlockEntity(pos) instanceof GateCompoundBlockEntity gateBlockEntity)) {
            return super.onUse(state, world, pos, player, hit);
        }
        if (!player.getStackInHand(Hand.MAIN_HAND).isEmpty()) {
            return ActionResult.PASS;
        }

        gateBlockEntity.use(state, world, pos);

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.GATE_COMPOUND_BLOCK_ENTITY, GateCompoundBlockEntity::tick);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(INTANGIBLE);
    }

    static {
        INTANGIBLE = BooleanProperty.of("intangible");
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(s -> new GateCompoundBlock());
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GateCompoundBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
