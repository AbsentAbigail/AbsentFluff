package abby.absent.fluff.gems.implementation;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.ArrayList;
import java.util.List;

public class Tanzanite implements GemImplementation {
    @Override
    public boolean isSnack() {
        return true;
    }

    @Override
    public List<EffectProbability> getStatusEffectsWhenEaten() {
        List<EffectProbability> list = new ArrayList<>();
        list.add(new EffectProbability(new StatusEffectInstance(StatusEffects.LEVITATION, 40, -5)));
        return list;
    }
}
