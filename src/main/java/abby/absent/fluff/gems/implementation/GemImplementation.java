package abby.absent.fluff.gems.implementation;

import abby.absent.fluff.Utility;
import abby.absent.fluff.entities.BombEntity;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public interface GemImplementation {
    /**
     * Gem item effects
     */

    // Restored hunger when eaten
    default int getNutrition() { return 0; }

    // Restored Saturation when eaten (Saturation = Hunger * saturation * 2
    default float getSaturation() { return 0F; }

    default boolean isSnack() { return false; }

    // Status effects and percentage chances when eaten
    default List<EffectProbability> getStatusEffectsWhenEaten() {
        return new ArrayList<>();
    }

    default ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        return stack;
    }

    /**
     * Jewelry item effects
     */
    default String braceletTooltip() {
        return "";
    }
    default void braceletInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {}
    default void braceletInventoryTick(ItemStack stack, SlotReference slot, LivingEntity entity) {}

    default String necklaceTooltip() {
        return "";
    }
    default void necklaceInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {}

    default void necklaceInventoryTick(ItemStack stack, SlotReference slot, LivingEntity entity) {}

    default String keyTooltip() {
        return "";
    }

    default int getKeyDurability() {
        return 1;
    }

    default boolean skipKeyDamage() {
        return false;
    }

    default TypedActionResult<ItemStack> keyUse(World world, PlayerEntity user, Hand hand, ItemStack itemStack, BlockHitResult blockHitResult, TypedActionResult<ItemStack> actionResult) {
        return actionResult;
    }

    default String bombTooltip() {
        return "";
    }

    default void bombExplosion(BombEntity entity, World world, BlockPos pos) {
        Utility.LOGGER.info("boom");
        DamageSource damageSource = entity.getDamageSources().explosion(entity, entity.getOwner());
        world.createExplosion(
                entity,
                damageSource,
                null,
                pos.getX(), pos.getY(), pos.getZ(),
                (float)(5),
                false,
                World.ExplosionSourceType.TNT
        );
    }

    class EffectProbability {

        private final StatusEffectInstance statusEffect;
        private final float probability;

        public EffectProbability(StatusEffectInstance statusEffect) {
            this(statusEffect, 1f);
        }

        public EffectProbability(StatusEffectInstance statusEffect, float probability) {
            this.statusEffect = statusEffect;
            this.probability = probability;
        }

        public StatusEffectInstance getStatusEffect() {
            return statusEffect;
        }

        public float getProbability() {
            return probability;
        }
    }
}
