package abby.absent.fluff.gems.implementation;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class Sapphire implements GemImplementation {
    @Override
    public List<EffectProbability> getStatusEffectsWhenEaten() {
        List<EffectProbability> list = new ArrayList<>();
        list.add(new EffectProbability(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 5, 2)));
        return list;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world.isClient) {
            return stack;
        }

        BlockPos userPosition = user.getBlockPos();
        boolean placedIce = false;
        boolean placedSnow = false;
        for(int i = -4; i < 4; i++) {
            for(int j = -4; j < 4; j++) {
                BlockPos position = userPosition.subtract(new Vec3i(i, 0, j));
                BlockPos positionDown = position.down();
                if (world.getBlockState(positionDown).isOf(Blocks.WATER)) {
                    world.setBlockState(positionDown, Blocks.ICE.getDefaultState());
                    placedIce = true;
                }
                if (!world.getBlockState(position).isAir()) {
                    continue;
                }
                if (user.getRandom().nextBoolean()) {
                    world.setBlockState(position, Blocks.SNOW.getDefaultState());
                    placedSnow = true;
                }
            }
        }

        if (placedIce) {
            SoundEvent soundEvent = SoundEvents.BLOCK_GLASS_PLACE;
            SoundCategory soundCategory = SoundCategory.BLOCKS;

            world.playSound(null, userPosition.getX(), userPosition.getY(), userPosition.getZ(), soundEvent, soundCategory);
        }
        if (placedSnow) {
            SoundEvent soundEvent = SoundEvents.BLOCK_SNOW_PLACE;
            SoundCategory soundCategory = SoundCategory.BLOCKS;

            world.playSound(null, userPosition.getX(), userPosition.getY(), userPosition.getZ(), soundEvent, soundCategory);
        }
        return stack;
    }
}
