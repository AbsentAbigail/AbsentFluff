package abby.absent.fluff.items.jewelry;

import abby.absent.fluff.gems.GemType;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class NecklaceItem extends JewelryTrinketItem {
    public NecklaceItem(GemType type) {
        super(type, new Item.Settings());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.absentfluff.necklace_tooltip." + gemType.getName()).formatted(Formatting.GOLD));
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        super.tick(stack, slot, entity);
        if (entity.isLiving()) {
            gemImplementation.necklaceInventoryTick(stack, slot, entity);
        }
    }
}
