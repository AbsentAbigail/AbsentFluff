package abby.absent.fluff.gems.implementation;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.ArrayList;
import java.util.List;

public class RoseQuartz implements GemImplementation {
    /**
     * Gem item effects
     */
    // Status effects and percentage chances when eaten
    public List<EffectProbability> getStatusEffectsWhenEaten() {
        List<EffectProbability> list = new ArrayList<>();
        list.add(new EffectProbability(new StatusEffectInstance(StatusEffects.REGENERATION, 96, 2)));
        return list;
    }
}
