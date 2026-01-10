package abby.absent.fluff.items.jewelry;

import abby.absent.fluff.Utility;
import abby.absent.fluff.blocks.gems.GemCore;
import abby.absent.fluff.gems.GemType;
import abby.absent.fluff.miscellaneous.ModComponents;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.List;

public class KeyItem extends JewelryItem {
    public KeyItem(GemType type) {
        super(type, new Item.Settings().maxDamage(type.getImplementation().getKeyDurability() + 1));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.absentfluff.key_tooltip." + gemType.getName()).formatted(Formatting.GOLD));
        if (stack.contains(ModComponents.BOUND_TO_POSITION_COMPONENT)) {
            GlobalPos globalPos = stack.get(ModComponents.BOUND_TO_POSITION_COMPONENT);
            String readablePos = globalPos.dimension().getValue().toString() + " " + globalPos.pos().getX() + ", " + globalPos.pos().getY() + ", " + globalPos.pos().getZ();
            tooltip.add(Text.translatable("item.absentfluff.bound_to_position", readablePos).formatted(Formatting.DARK_PURPLE));
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        TypedActionResult<ItemStack> result = super.use(world, user, hand);
        if (world.isClient())
            return result;

        ItemStack itemStack = user.getStackInHand(hand);

        BlockHitResult blockHitResult = raycast(world, user, RaycastContext.FluidHandling.NONE);
        BlockPos blockPos = blockHitResult.getBlockPos();
        Block block = world.getBlockState(blockPos).getBlock();

        EquipmentSlot slot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;

        if (block instanceof GemCore coreBlock && coreBlock.getGemType() == this.getGemType()) {
            itemStack.setDamage(0);
            Utility.playSound(world, user, SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE);
            return result;
        }

        if (itemStack.getDamage() >= gemImplementation.getKeyDurability()) {
            Utility.playSound(world, user, SoundEvents.BLOCK_CANDLE_EXTINGUISH);
            return TypedActionResult.pass(itemStack);
        }

        TypedActionResult<ItemStack> implementationResult = gemImplementation.keyUse(world, user, hand, itemStack, blockHitResult, result);
        if (implementationResult.getResult() == ActionResult.SUCCESS && !gemImplementation.skipKeyDamage())
            itemStack.damage(1, user, slot);
        return implementationResult;
    }
}
