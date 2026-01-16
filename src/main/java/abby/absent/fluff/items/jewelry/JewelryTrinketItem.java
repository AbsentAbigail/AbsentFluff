package abby.absent.fluff.items.jewelry;

import abby.absent.fluff.gems.GemType;
import abby.absent.fluff.gems.implementation.GemImplementation;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.item.Item;

public class JewelryTrinketItem extends TrinketItem {
    protected final GemType gemType;
    protected final GemImplementation gemImplementation;

    public JewelryTrinketItem(GemType type, Settings settings) {
        super(settings.maxCount(1));
        this.gemType = type;
        this.gemImplementation = gemType.getImplementation();
    }

    public GemType getGemType() {
        return gemType;
    }
}
