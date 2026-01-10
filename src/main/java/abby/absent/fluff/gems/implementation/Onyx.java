package abby.absent.fluff.gems.implementation;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class Onyx implements GemImplementation {
    /**
     * Gem item effects
     */
    // Status effects and percentage chances when eaten
    public List<EffectProbability> getStatusEffectsWhenEaten() {
        List<EffectProbability> list = new ArrayList<>();
        list.add(new EffectProbability(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 20 * 60)));
        list.add(new EffectProbability(new StatusEffectInstance(StatusEffects.BLINDNESS, 20 * 5), 0.4F));
        return list;
    }

    @Override
    public void braceletInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        LivingEntity livingEntity = (LivingEntity) entity;
        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 20 * 10 + 10));
    }
}
