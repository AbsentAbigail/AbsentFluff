package abby.absent.fluff.miscellaneous;

import abby.absent.fluff.Utility;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;

public class ModDamageSources {
    public static final RegistryKey<DamageType> EAT_ROCK = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Utility.identifier("eat_rock"));
    public static final RegistryKey<DamageType> MOONFALL = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Utility.identifier("moonfall"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}
