package abby.absent.fluff.gems.implementation;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class Petalite implements GemImplementation {
    @Override
    public List<EffectProbability> getStatusEffectsWhenEaten() {
        List<EffectProbability> list = new ArrayList<>();
        list.add(new EffectProbability(new StatusEffectInstance(StatusEffects.LEVITATION, 30, 2)));
        return list;
    }

    @Override
    public void braceletInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        LivingEntity livingEntity = (LivingEntity) entity;
        livingEntity.fallDistance = 0;
    }
}
