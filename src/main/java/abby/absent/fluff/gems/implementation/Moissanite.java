package abby.absent.fluff.gems.implementation;

import abby.absent.fluff.miscellaneous.ModDamageSources;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class Moissanite implements GemImplementation {
    @Override
    public List<EffectProbability> getStatusEffectsWhenEaten() {
        List<EffectProbability> list = new ArrayList<>();
        list.add(new EffectProbability(new StatusEffectInstance(StatusEffects.RESISTANCE, 20 * 60 * 5, 2)));
        return list;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world.isClient)
            return stack;

        user.damage(ModDamageSources.of(world, ModDamageSources.EAT_ROCK), 40);
        return stack;
    }

    @Override
    public void braceletInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        LivingEntity livingEntity = (LivingEntity) entity;
        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 30, 3));
        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 30));
    }
}
