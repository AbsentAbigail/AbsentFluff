package abby.absent.fluff.gems.implementation;

import abby.absent.fluff.Constants;
import abby.absent.fluff.Utility;
import abby.absent.fluff.miscellaneous.ModComponents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.ArrayList;
import java.util.List;

public class Fluorite implements GemImplementation {
    @Override
    public List<EffectProbability> getStatusEffectsWhenEaten() {
        List<EffectProbability> list = new ArrayList<>();
        list.add(new EffectProbability(new StatusEffectInstance(StatusEffects.NAUSEA, 20 * 7, 1), 0.1F));
        return list;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world.isClient) {
            return stack;
        }

        // repeat 5 times
        for (int i = 0; i < 5; i++) {
            // get random coordinates within 8 blocks of user
            int d1 = user.getBlockX() + (int)((user.getRandom().nextDouble() - 0.5) * 16);
            int e1 = user.getBlockY() + (int)((user.getRandom().nextDouble() - 0.5) * 16);
            int f1 = user.getBlockZ() + (int)((user.getRandom().nextDouble() - 0.5) * 16);
            BlockPos blockPos1 = new BlockPos(d1, e1, f1);

            // get random coordinates within 8 blocks of user
            int d2 = user.getBlockX() + (int)((user.getRandom().nextDouble() - 0.5) * 16);
            int e2 = user.getBlockY() + (int)((user.getRandom().nextDouble() - 0.5) * 16);
            int f2 = user.getBlockZ() + (int)((user.getRandom().nextDouble() - 0.5) * 16);
            BlockPos blockPos2 = new BlockPos(d2, e2, f2);

            // Save chosen blocks
            BlockState block1 = world.getBlockState(blockPos1);
            BlockState block2 = world.getBlockState(blockPos2);

            // Skip if block has block entity
            if (block1.hasBlockEntity() || block2.hasBlockEntity()) {
                continue;
            }
            // Skip if block is bedrock
            if (block1.getBlock() == Blocks.BEDROCK || block2.getBlock() == Blocks.BEDROCK) {
                continue;
            }

            // Swap blocks
            world.setBlockState(blockPos1, block2);
            world.setBlockState(blockPos2, block1);
        }

        Vec3d vec3d = user.getPos();
        world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(user));
        SoundCategory soundCategory = SoundCategory.PLAYERS;

        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, soundCategory);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_GLASS_BREAK, soundCategory);

        return stack;
    }

    @Override
    public boolean skipKeyDamage() {
        return true;
    }

    @Override
    public int getKeyDurability() {
        return 3;
    }

    @Override
    public String keyTooltip() {
        return "Shift click to bind a block. Right click to swap with targeted block";
    }

    @Override
    public TypedActionResult<ItemStack> keyUse(World world, PlayerEntity user, Hand hand, ItemStack itemStack, BlockHitResult blockHitResult, TypedActionResult<ItemStack> actionResult) {
        if (user.isSneaking()) {
            itemStack.set(ModComponents.BOUND_TO_POSITION_COMPONENT, GlobalPos.create(world.getRegistryKey(), blockHitResult.getBlockPos()));
            return TypedActionResult.success(itemStack);
        }

        if (!itemStack.contains(ModComponents.BOUND_TO_POSITION_COMPONENT)) {
            return TypedActionResult.pass(itemStack);
        }

        GlobalPos globalPos = itemStack.get(ModComponents.BOUND_TO_POSITION_COMPONENT);
        BlockPos pos1 = globalPos.pos();
        BlockPos pos2 = blockHitResult.getBlockPos();
        boolean fail = globalPos.dimension() != world.getRegistryKey() || !pos1.isWithinDistance(pos2, 32);
        if (fail) {
            user.sendMessage(Text.translatable(Constants.KUNZITE_OUT_OF_RANGE_TRANSLATION_KEY));
            return TypedActionResult.success(itemStack);
        }

        BlockState state1 = world.getBlockState(pos1);
        BlockState state2 = world.getBlockState(pos2);
        world.setBlockState(pos1, state2);
        world.setBlockState(pos2, state1);
        itemStack.remove(ModComponents.BOUND_TO_POSITION_COMPONENT);
        Utility.playSound(world, user, SoundEvents.ENTITY_ILLUSIONER_MIRROR_MOVE, 0.7f, 1f);

        EquipmentSlot slot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
        itemStack.damage(1, user, slot);
        user.incrementStat(Stats.USED.getOrCreateStat(itemStack.getItem()));

        return TypedActionResult.success(itemStack);
    }
}
