package abby.absent.fluff.gems.implementation;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Peridot implements GemImplementation {
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user.getRandom().nextBoolean()) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 8 * 20));
        } else {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 2));
        }

        return stack;
    }
}
