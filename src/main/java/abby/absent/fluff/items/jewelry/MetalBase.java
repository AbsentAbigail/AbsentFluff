package abby.absent.fluff.items.jewelry;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public enum MetalBase {
    IRON(Items.IRON_INGOT),
    GOLD(Items.GOLD_INGOT),
    COPPER(Items.COPPER_INGOT),
    NETHERITE(Items.NETHERITE_INGOT);

    private final Item material;

    MetalBase(Item material) {
        this.material = material;
    }

    public String getItemSuffix() {
        return "_" + this.name().toLowerCase();
    }

    public String getLowercaseName() {
        return this.name().toLowerCase();
    }

    public String getDisplayName() {
        String s = this.name();
        return s.charAt(0) + s.substring(1).toLowerCase();
    }

    public Item getMaterial() {
        return material;
    }
}
