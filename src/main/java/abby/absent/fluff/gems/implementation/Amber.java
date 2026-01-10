package abby.absent.fluff.gems.implementation;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.ArrayList;
import java.util.List;

public class Amber implements GemImplementation {
    @Override
    public List<EffectProbability> getStatusEffectsWhenEaten() {
        List<EffectProbability> list = new ArrayList<>();
        list.add(new EffectProbability(new StatusEffectInstance(StatusEffects.SLOWNESS, 200), 0.7F));
        list.add(new EffectProbability(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 200, 1), 0.7F));
        list.add(new EffectProbability(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 4), 0.7F));
        return list;
    }
}
