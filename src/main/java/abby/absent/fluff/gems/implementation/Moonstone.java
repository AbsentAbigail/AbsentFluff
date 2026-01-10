package abby.absent.fluff.gems.implementation;

import abby.absent.fluff.miscellaneous.ModDamageSources;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class Moonstone implements GemImplementation {
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world.isClient) {
            return stack;
        }

        int moonPhase = world.getMoonPhase();
        boolean isNight = world.isNight();

        if (!isNight) {
            SoundEvent soundEvent = SoundEvents.BLOCK_REDSTONE_TORCH_BURNOUT;
            SoundCategory soundCategory = SoundCategory.PLAYERS;

            world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent, soundCategory);

            return stack;
        }

        // On full moon
        if (moonPhase == 0) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20 * 2, 5));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 20));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 20 * 60 * 8, 4));
        // Waning and waxing gibbous
        } else if (moonPhase == 1 || moonPhase == 7) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 25 * 6, 1)); // Heal 6 health
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 4)); // Sate 4 hunger and 8 saturation
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 20 * 15));
        // Waning and waxing crescent
        } else if (moonPhase == 3 || moonPhase == 5) {
            int mobCount = dealAOE(world, user, 3, 7);
            if (mobCount != 0) {
                user.heal(mobCount);
                playSound(user, world);
            }
        // On new moon
        } else if (moonPhase == 4) {
            int mobCount = dealAOE(world, user, 7, 20);
            if (mobCount != 0) {
                user.heal(mobCount * 3);
                playSound(user, world);
            }
        }
        return stack;
    }

    private int dealAOE(World world, LivingEntity user, float radius, float damage) {
        Box box = new Box(
                user.getX() - radius, user.getY() - radius, user.getZ() - radius,
                user.getX() + radius, user.getY() + radius, user.getZ() + radius
        );

        List<Entity> entities = world.getOtherEntities(user, box, entity -> {
            entity.setGlowing(false);
            if (entity.isSpectator())
                return false;
            if (entity.squaredDistanceTo(user) > radius*radius)
                return false;
            return entity.isLiving();
        });

        int count = entities.size();
        entities.forEach(entity -> entity.damage(ModDamageSources.of(world, ModDamageSources.MOONFALL), damage));

        return count;
    }

    private void playSound(LivingEntity user, World world) {
        SoundEvent soundEvent = SoundEvents.BLOCK_GLASS_BREAK;
        SoundCategory soundCategory = SoundCategory.PLAYERS;

        world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent, soundCategory);
    }
}
