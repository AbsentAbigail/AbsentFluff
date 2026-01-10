package abby.absent.fluff.gems;

import abby.absent.fluff.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

public class MaterialGem implements ToolMaterial {
    private final GemType type;

    public MaterialGem(GemType type) {
        this.type = type;
    }

    public int getDurability() {
        return (int)(type.getHardness() * 156F);
    }

    @Override
    public float getMiningSpeedMultiplier() {
        float h = type.getHardness();

        return (float)(Math.pow(-(0.4F * h - 3), 2) + 8);
    }

    @Override
    public float getAttackDamage() {
        if (type == GemType.ROSEQUARTZ)
            return -8F;

        float h = type.getHardness();
        if (h < 3.0F) return 1.0F;

        if (h < 6.0F) return (int)(-1.5F * h) + 11;

        return (int)(0.7F * h) - 2;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        double h = type.getHardness();

        if (h < 4) return BlockTags.INCORRECT_FOR_WOODEN_TOOL;
        if (h < 6) return BlockTags.INCORRECT_FOR_STONE_TOOL;
        if (h < 8) return BlockTags.INCORRECT_FOR_IRON_TOOL;
        return BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
    }

    @Override
    public int getEnchantability() {
        return type.getCapacity();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.gemItemMap.get(type.getName()));
    }

    public GemType getGemType() {
        return type;
    }
}
