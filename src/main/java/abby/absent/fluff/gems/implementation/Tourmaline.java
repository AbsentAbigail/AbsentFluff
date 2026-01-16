package abby.absent.fluff.gems.implementation;

import abby.absent.fluff.entities.BombEntity;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Tourmaline implements GemImplementation {
    @Override
    public void bombExplosion(BombEntity bombEntity, World world, BlockPos pos) {
        int power = 8;

        for (int offsetX = -power; offsetX < power; offsetX++) {
            for (int offsetY = -power; offsetY < power; offsetY++) {
                for (int offsetZ = -power; offsetZ < power; offsetZ++) {
                    BlockPos checkExplodePos = pos.add(offsetX, offsetY, offsetZ);
                    if (!pos.isWithinDistance(checkExplodePos, power)) {
                        continue;
                    }
                    if (world.getBlockState(checkExplodePos).isOf(Blocks.BEDROCK)) {
                        continue;
                    }
                    world.setBlockState(checkExplodePos, Blocks.AIR.getDefaultState());
                }
            }
        }

        List<Entity> entities = world.getOtherEntities(null,
                Box.enclosing(pos.add(-power,-power,-power), pos.add(pos.add(power,power,power))));
        DamageSource damageSource = bombEntity.getDamageSources().explosion(bombEntity, bombEntity.getOwner());
        for (Entity entity : entities) {
            if (!entity.getBlockPos().isWithinDistance(pos, power)) {
                return;
            }
            entity.damage(damageSource, calculateDamage(power, pos, entity));
        }
    }

    public float calculateDamage(float power, BlockPos pos, Entity entity) {
        float f = power * 2.0F;
        Vec3d vec3d = pos.toCenterPos();
        double d = Math.sqrt(entity.squaredDistanceTo(vec3d)) / (double)f;
        double e = (1.0 - d) * (double)Explosion.getExposure(vec3d, entity);
        return (float)((e * e + e) / 2.0 * 7.0 * (double)f + 1.0);
    }
}
