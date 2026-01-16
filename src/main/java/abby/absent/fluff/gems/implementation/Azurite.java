package abby.absent.fluff.gems.implementation;

import abby.absent.fluff.Utility;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class Azurite implements GemImplementation {
    /**
     * Gem item effects
     */
    // Status effects and percentage chances when eaten
    public List<EffectProbability> getStatusEffectsWhenEaten() {
        List<EffectProbability> list = new ArrayList<>();
        list.add(new EffectProbability(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 20 * 15)));
        return list;
    }

    @Override
    public void braceletInventoryTick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (!entity.isTouchingWater())
            return;
        if (!entity.isLiving())
            return;

        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 1));
    }

    @Override
    public void necklaceInventoryTick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (!entity.isLiving())
            return;
        if (!entity.isTouchingWater())
            return;

        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 1));
    }

    @Override
    public String keyTooltip() {
        return "Reusable water bucket";
    }

    @Override
    public int getKeyDurability() {
        return 32;
    }

    Fluid fluid = Fluids.WATER;

    @Override
    public TypedActionResult<ItemStack> keyUse(World world, PlayerEntity user, Hand hand, ItemStack itemStack, BlockHitResult blockHitResult, TypedActionResult<ItemStack> actionResult) {
        BlockPos targetBlockPos = blockHitResult.getBlockPos();
        Direction direction = blockHitResult.getSide();

        BlockPos blockPos = targetBlockPos.offset(direction);

        BlockState blockState = world.getBlockState(blockPos);
        if (!blockState.canBucketPlace(fluid)) {
            return TypedActionResult.success(itemStack);
        }

        if (world.getDimension().ultrawarm()) {
            int x = blockPos.getX();
            int y = blockPos.getY();
            int z = blockPos.getZ();
            Utility.playSound(world, user, SoundEvents.BLOCK_FIRE_EXTINGUISH, 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);

            for(int i = 0; i < 8; ++i) {
                ((ServerWorld)world).spawnParticles(ParticleTypes.LARGE_SMOKE,
                        (double) x + Math.random(), (double) y + Math.random(), (double) z + Math.random(),
                        1, 0, 0, 0, 0);
            }
        } else {
            world.setBlockState(blockPos, this.fluid.getDefaultState().getBlockState(), 11);
            Utility.playSound(world, user, SoundEvents.ITEM_BUCKET_EMPTY, 0.7f, 1f);
        }

        return TypedActionResult.success(itemStack);
    }
}
