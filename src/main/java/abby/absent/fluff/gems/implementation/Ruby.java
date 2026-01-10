package abby.absent.fluff.gems.implementation;

import abby.absent.fluff.Utility;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class Ruby implements GemImplementation {
    @Override
    public List<EffectProbability> getStatusEffectsWhenEaten() {
        List<EffectProbability> list = new ArrayList<>();
        list.add(new EffectProbability(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 20 * 5)));
        return list;
    }

    @Override
    public int getKeyDurability() {
        return 8;
    }

    @Override
    public String keyTooltip() {
        return "Reusable lava bucket";
    }

    Fluid fluid = Fluids.LAVA;

    @Override
    public TypedActionResult<ItemStack> keyUse(World world, PlayerEntity user, Hand hand, ItemStack itemStack, BlockHitResult blockHitResult, TypedActionResult<ItemStack> actionResult) {
        BlockPos targetBlockPos = blockHitResult.getBlockPos();
        Direction direction = blockHitResult.getSide();

        BlockPos blockPos = targetBlockPos.offset(direction);

        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.canBucketPlace(fluid))
        {
            world.setBlockState(blockPos, this.fluid.getDefaultState().getBlockState(), 11);
            Utility.playSound(world, user, SoundEvents.ITEM_BUCKET_EMPTY_LAVA, 0.7f, 1f);
        }

        return TypedActionResult.success(itemStack);
    }
}
