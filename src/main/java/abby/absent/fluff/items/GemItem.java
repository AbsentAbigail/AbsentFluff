package abby.absent.fluff.items;

import abby.absent.fluff.Constants;
import abby.absent.fluff.gems.GemType;
import abby.absent.fluff.gems.implementation.GemImplementation;
import abby.absent.fluff.miscellaneous.ModDamageSources;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class GemItem extends Item {
    private final GemType type;

    public GemItem(GemType type) {
        super(buildSettings(type));
        this.type = type;
    }

    public GemType getGemType() {
        return type;
    }

    private static Settings buildSettings(GemType gemType) {
        GemImplementation implementation = gemType.getImplementation();
        Settings settings = new Settings();

        settings.component(DataComponentTypes.LORE,
                new LoreComponent(List.of(
                        Text.of("Hardness: " + gemType.getHardness()),
                        Text.translatable(Constants.GEM_LORE_TRANSLATION_KEY + gemType.getName()))
                )
        );

        FoodComponent.Builder foodBuilder =
                new FoodComponent.Builder()
                        .alwaysEdible()
                        .nutrition(implementation.getNutrition())
                        .saturationModifier(implementation.getSaturation());
        if (implementation.isSnack())
            foodBuilder.snack();
        for (GemImplementation.EffectProbability effectProbability : implementation.getStatusEffectsWhenEaten()) {
            foodBuilder.statusEffect(effectProbability.getStatusEffect(), effectProbability.getProbability());
        }
        settings.food(foodBuilder.build());

        return settings;
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        user.damage(ModDamageSources.of(world, ModDamageSources.EAT_ROCK), 1.0F);
        return type.getImplementation().finishUsing(itemStack, world, user);
    }
}
