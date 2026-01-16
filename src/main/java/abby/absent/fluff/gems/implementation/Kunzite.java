package abby.absent.fluff.gems.implementation;

import abby.absent.fluff.Utility;
import abby.absent.fluff.miscellaneous.ModComponents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Set;

public class Kunzite implements GemImplementation {
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world.isClient) {
            return stack;
        }

        for(int i = 0; i < 16; ++i) {
            double d = user.getX() + (user.getRandom().nextDouble() - 0.5) * 8.0;
            double e = user.getY();
            double f = user.getZ() + (user.getRandom().nextDouble() - 0.5) * 8.0;
            if (user.hasVehicle()) {
                user.stopRiding();
            }

            Vec3d vec3d = user.getPos();
            if (user.teleport(d, e, f, true)) {
                world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(user));
                SoundCategory soundCategory = SoundCategory.PLAYERS;
                SoundEvent soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;

                world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent, soundCategory);
                user.onLanding();
                break;
            }
        }
        return stack;
    }

    @Override
    public boolean skipKeyDamage() {
        return true;
    }

    @Override
    public String keyTooltip() {
        return "Shift click to bind a block. Right click to teleport";
    }

    @Override
    public TypedActionResult<ItemStack> keyUse(World world, PlayerEntity user, Hand hand, ItemStack itemStack, BlockHitResult blockHitResult, TypedActionResult<ItemStack> actionResult) {
        if (user.isSneaking()) {
            itemStack.set(
                    ModComponents.BOUND_TO_POSITION_COMPONENT,
                    GlobalPos.create(world.getRegistryKey(), blockHitResult.getBlockPos().offset(blockHitResult.getSide())));
            return TypedActionResult.success(itemStack);
        }

        if (!itemStack.contains(ModComponents.BOUND_TO_POSITION_COMPONENT))
        {
            return TypedActionResult.pass(itemStack);
        }

        GlobalPos globalPos = itemStack.get(ModComponents.BOUND_TO_POSITION_COMPONENT);
        BlockPos blockPos = globalPos.pos();
        Vec3d teleportPosition = blockPos.toCenterPos();

        if (user.teleport(
                world.getServer().getWorld(globalPos.dimension()),
                teleportPosition.getX(), teleportPosition.getY(), teleportPosition.getZ(),
                Set.of(), user.headYaw, user.getPitch())) {
            Vec3d vec3d = user.getPos();
            world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(user));
            SoundEvent soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;

            Utility.playSound(world, user, soundEvent, 0.7f, 1f);
            user.onLanding();

            EquipmentSlot slot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
            itemStack.damage(1, user, slot);
            user.incrementStat(Stats.USED.getOrCreateStat(itemStack.getItem()));
        }

        return TypedActionResult.success(itemStack);
    }
}
