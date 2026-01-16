package abby.absent.fluff.blocks.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

import static abby.absent.fluff.blocks.GateCompoundBlock.INTANGIBLE;

public class GateCompoundBlockEntity extends BlockEntity {
    int timeout = 0;
    boolean propagate = false;

    static List<BlockPos> cachedRedstoneList = null;
    static boolean cachedRedstoneResult = false;
    static float cacheTime = 0;

    public GateCompoundBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GATE_COMPOUND_BLOCK_ENTITY, pos, state);
    }

    public int getTimeout() {
        return timeout;
    }

    private void setTimeout(int newTimeout) {
        timeout = newTimeout;
        markDirty();
    }

    public boolean shouldPropagate() {
        return propagate;
    }

    public void markForPropagation() {
        this.propagate = true;
        markDirty();
    }

    public void use(BlockState state, World world, BlockPos pos) {
        activate(state, world, pos, 20 * 4);
        this.markForPropagation();
    }

    public boolean checkRedstone(World world, BlockPos pos, ArrayList<BlockPos> checked) {
        if (cachedRedstoneList != null && cachedRedstoneList.contains(pos)) {
            return cachedRedstoneResult;
        }

        if (world.isReceivingRedstonePower(pos)) {
            return true;
        }
        checked.add(pos);
        for (Direction direction : Direction.values()) {
            BlockPos adjacentPos = pos.offset(direction);
            if (checked.contains(adjacentPos)) {
                continue;
            }
            if (!(world.getBlockEntity(adjacentPos) instanceof GateCompoundBlockEntity)) {
                continue;
            }
            if (checkRedstone(world, adjacentPos, checked)) {
                cachedRedstoneList = checked;
                cachedRedstoneResult = true;
                cacheTime = world.getTime();
                return true;
            }
        }
        cachedRedstoneList = checked;
        cachedRedstoneResult = false;
        cacheTime = world.getTime();
        return false;
    }

    public void activate(BlockState state, World world, BlockPos pos, int timeout) {
        setTimeout(timeout);

        world.scheduleBlockTick(pos, state.getBlock(), 1);
    }

    public static void tick(World world, BlockPos pos, BlockState state, GateCompoundBlockEntity gateBlockEntity) {
        if (gateBlockEntity.shouldPropagate()) {
            gateBlockEntity.propagate(world, pos);
        }

        gateBlockEntity.decrementTimeout();

        if (world.getTime() != cacheTime) {
            cachedRedstoneList = null;
        }
        if (gateBlockEntity.checkRedstone(world, pos, new ArrayList<>())) {
            world.setBlockState(pos, state.with(INTANGIBLE, true));
            return;
        }

        boolean isIntangible = state.get(INTANGIBLE);
        boolean shouldBeIntangible = gateBlockEntity.shouldBeIntangible();
        if (isIntangible != shouldBeIntangible)
        {
            world.setBlockState(pos, state.with(INTANGIBLE, shouldBeIntangible));
        }
    }

    public void propagate(World world, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            BlockPos adjacentPos = pos.offset(direction);

            if (!(world.getBlockEntity(adjacentPos) instanceof GateCompoundBlockEntity gateBlockEntity)) {
                continue;
            }
            if (gateBlockEntity.getTimeout() > 0) {
                continue;
            }

            BlockState adjacentState = world.getBlockState(adjacentPos);
            gateBlockEntity.activate(adjacentState, world, adjacentPos, timeout);
            gateBlockEntity.markForPropagation();
        }
        this.propagate = false;
    }

    public void decrementTimeout() {
        setTimeout(Math.max(timeout - 1, 0));
    }

    public boolean shouldBeIntangible() {
        return this.timeout > 0;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putInt("timeout", timeout);
        nbt.putBoolean("propagate", propagate);

        super.writeNbt(nbt, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);

        timeout = nbt.getInt("timeout");
        propagate = nbt.getBoolean("propagate");
    }
}
