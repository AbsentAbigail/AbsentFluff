package abby.absent.fluff.entities.goals;

import abby.absent.fluff.entities.AlpacaEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.EnumSet;

public class AlpacaEatGoal extends Goal {
    private final AlpacaEntity alpaca;
    private final World world;
    private final TagKey<Block> edibleTag;
    public int eatTicks = 0;

    public AlpacaEatGoal(AlpacaEntity mob, TagKey<Block> edibleTag) {
        this.alpaca = mob;
        this.world = mob.getWorld();
        this.edibleTag = edibleTag;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK, Control.JUMP));
    }

    public boolean canStart() {
        if (alpaca.getRandom().nextInt(alpaca.isBaby() ? 50 : 100) != 0) {
            return false;
        }
        BlockPos blockPos = alpaca.getBlockPos().down();

        return matchesTag(blockPos);
    }


    public void start() {
        eatTicks = getTickCount(40);
        world.sendEntityStatus(this.alpaca, (byte)10);
        alpaca.getNavigation().stop();
        alpaca.eating = true;
    }

    public void stop() {
        eatTicks = 0;
    }

    public boolean shouldContinue() {
        return eatTicks > 0;
    }

    public void tick() {
        eatTicks = Math.max(eatTicks - 1, 0);
        if (eatTicks != getTickCount(4))
            return;

        BlockPos targetBlockPos = alpaca.getBlockPos().down();

        if (!matchesTag(targetBlockPos))
            return;

        alpaca.onEatingGrass();
    }

    private boolean matchesTag(BlockPos blockPos) {
        return world.getBlockState(blockPos).streamTags().anyMatch(t -> t.equals(edibleTag));
    }
}
