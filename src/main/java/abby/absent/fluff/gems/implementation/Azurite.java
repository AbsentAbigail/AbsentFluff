package abby.absent.fluff.gems.implementation;

import abby.absent.fluff.Utility;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidFillable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

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
    public void braceletInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!entity.isTouchingWaterOrRain())
            return;
        if (!entity.isLiving())
            return;
        LivingEntity livingEntity = (LivingEntity) entity;
        if (livingEntity.hasStatusEffect(StatusEffects.SPEED)) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1, 1));
        }
        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1));

        if (livingEntity.hasStatusEffect(StatusEffects.STRENGTH)) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1, 1));
        }
        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1));
    }

    @Override
    public void necklaceInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!entity.isLiving())
            return;
        if (!entity.isTouchingWater())
            return;

        LivingEntity livingEntity = (LivingEntity) entity;

        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 1));
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
        if (blockState.canBucketPlace(fluid))
        {
            world.setBlockState(blockPos, this.fluid.getDefaultState().getBlockState(), 11);
            Utility.playSound(world, user, SoundEvents.ITEM_BUCKET_EMPTY, 0.7f, 1f);
        }

        return TypedActionResult.success(itemStack);
    }

//    public boolean placeFluid(@Nullable PlayerEntity player, World world, BlockPos pos, @Nullable BlockHitResult hitResult) {
//        Block block;
//        boolean bl;
//        FluidFillable fluidFillable;
//        BlockState blockState;
//        boolean var10000;
//        label82: {
//            blockState = world.getBlockState(pos);
//            block = blockState.getBlock();
//            bl = blockState.canBucketPlace(this.fluid);
//            if (!blockState.isAir() && !bl) {
//                label80: {
//                    if (block instanceof FluidFillable) {
//                        fluidFillable = (FluidFillable)block;
//                        if (fluidFillable.canFillWithFluid(player, world, pos, blockState, this.fluid)) {
//                            break label80;
//                        }
//                    }
//
//                    var10000 = false;
//                    break label82;
//                }
//            }
//
//            var10000 = true;
//        }
//
//        boolean bl2 = var10000;
//        if (!bl2) {
//            return hitResult != null && this.placeFluid(player, world, hitResult.getBlockPos().offset(hitResult.getSide()), (BlockHitResult)null);
//        } else if (world.getDimension().ultrawarm() && this.fluid.isIn(FluidTags.WATER)) {
//            int i = pos.getX();
//            int j = pos.getY();
//            int k = pos.getZ();
//            world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
//
//            for(int l = 0; l < 8; ++l) {
//                world.addParticle(ParticleTypes.LARGE_SMOKE, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0, 0.0, 0.0);
//            }
//
//            return true;
//        } else {
//            if (block instanceof FluidFillable) {
//                fluidFillable = (FluidFillable)block;
//                if (this.fluid == Fluids.WATER) {
//                    fluidFillable.tryFillWithFluid(world, pos, blockState, flowableFluid.getStill(false));
//                    this.playEmptyingSound(player, world, pos);
//                    return true;
//                }
//            }
//
//            if (!world.isClient && bl && !blockState.isLiquid()) {
//                world.breakBlock(pos, true);
//            }
//
//            if (!world.setBlockState(pos, this.fluid.getDefaultState().getBlockState(), 11) && !blockState.getFluidState().isStill()) {
//                return false;
//            } else {
//                this.playEmptyingSound(player, world, pos);
//                return true;
//            }
//        }
//    }
}
