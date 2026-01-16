package abby.absent.fluff.items.jewelry;

import abby.absent.fluff.entities.BombEntity;
import abby.absent.fluff.gems.GemType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class BombItem extends JewelryItem implements ProjectileItem {
    private static final int COOLDOWN = 10;

    public BombItem(GemType type) {
        super(type, new Item.Settings());
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            BombEntity bombEntity = new BombEntity(world, user);
            bombEntity.setItem(itemStack);
            bombEntity.setVelocity(user, user.getPitch(), user.getYaw(), -20.0F, 1F, 1.0F);
            world.spawnEntity(bombEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        user.getItemCooldownManager().set(this, COOLDOWN);
        itemStack.decrementUnlessCreative(1, user);
        return TypedActionResult.success(itemStack, world.isClient());
    }

    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        PotionEntity potionEntity = new PotionEntity(world, pos.getX(), pos.getY(), pos.getZ());
        potionEntity.setItem(stack);
        return potionEntity;
    }

    public ProjectileItem.Settings getProjectileSettings() {
        return ProjectileItem.Settings.builder().uncertainty(ProjectileItem.Settings.DEFAULT.uncertainty() * 0.5F).power(ProjectileItem.Settings.DEFAULT.power() * 1.25F).build();
    }
}
