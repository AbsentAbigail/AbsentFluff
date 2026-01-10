package abby.absent.fluff.items.jewelry;

import abby.absent.fluff.gems.GemType;
import abby.absent.fluff.gems.implementation.GemImplementation;
import net.minecraft.item.Item;

public class JewelryItem extends Item {
    protected final GemType gemType;
    protected final GemImplementation gemImplementation;

    public JewelryItem(GemType type, Settings settings) {
        super(settings.maxCount(1));
        this.gemType = type;
        this.gemImplementation = gemType.getImplementation();
    }

    public GemType getGemType() {
        return gemType;
    }
}
