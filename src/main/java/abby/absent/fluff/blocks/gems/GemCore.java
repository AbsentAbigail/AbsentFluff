package abby.absent.fluff.blocks.gems;

import abby.absent.fluff.gems.GemType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class GemCore extends Block {
    public static final IntProperty RECOVER;

    private final GemType type;

    public GemCore(GemType type) {
        super(getDefaultSettings());
        this.setDefaultState(
                this.stateManager.getDefaultState()
                        .with(RECOVER, 0)
        );
        this.type = type;
    }
//
//    @Override
//    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
//        if (world.isClient)
//            super.onUse(state, world, pos, player, hit);
//
//        ItemStack heldItem = player.getStackInHand(Hand.MAIN_HAND);
//        if (heldItem.getItem() instanceof KeyItem key && key.getGemType() == type) {
//            heldItem.setDamage(0);
//        }
//        Utility.LOGGER.info("core use");
//
//        return super.onUse(state, world, pos, player, hit);
//    }

    public static Settings getDefaultSettings() {
        Settings settings = Settings.copy(Blocks.IRON_BLOCK);
        settings.strength(3.0F, 3.5F).requiresTool();
        return settings;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        boolean boat = context.isHolding(Items.ACACIA_BOAT);
        if (boat)
            return VoxelShapes.fullCube();

        boolean empty = getGemType().equals(GemType.MAP.get(GemType.ONYX.getName())) && state.get(RECOVER) == 2;
        return empty ? VoxelShapes.empty() : VoxelShapes.fullCube();
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(RECOVER);
    }

    static {
        RECOVER = IntProperty.of("recover", 0, 2);
    }

    public GemType getGemType() {
        return this.type;
    }
}
