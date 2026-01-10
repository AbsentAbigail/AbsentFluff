package abby.absent.fluff.gems.implementation;

import abby.absent.fluff.Utility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class Topaz implements GemImplementation {

    @Override
    public String keyTooltip() {
        return "Change weather to thunderstorm";
    }

    @Override
    public TypedActionResult<ItemStack> keyUse(World world, PlayerEntity user, Hand hand, ItemStack itemStack, BlockHitResult blockHitResult, TypedActionResult<ItemStack> actionResult) {
        ServerWorld serverWorld = (ServerWorld) world;

        if (!(serverWorld.getDimension().hasSkyLight() && !serverWorld.getDimension().hasCeiling()))
            return TypedActionResult.pass(itemStack);

        serverWorld.setWeather(0, 5 * 60 * 20, true, true);
        Utility.playSound(world, user, SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, 0.3f, 0.5f);

        return TypedActionResult.success(itemStack);
    }
}
