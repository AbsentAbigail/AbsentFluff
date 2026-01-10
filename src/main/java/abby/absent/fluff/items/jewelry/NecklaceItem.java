package abby.absent.fluff.items.jewelry;

import abby.absent.fluff.gems.GemType;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class NecklaceItem extends JewelryItem {
    public NecklaceItem(GemType type) {
        super(type, new Item.Settings());
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (world.isClient)
            return;
        if (entity.isLiving()) {
            gemImplementation.necklaceInventoryTick(stack, world, entity, slot, selected);
        }
    }
}
