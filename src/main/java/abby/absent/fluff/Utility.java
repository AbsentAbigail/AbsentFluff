package abby.absent.fluff;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utility {

    public static final Logger LOGGER = LoggerFactory.getLogger(Constants.MODID);

    public static Identifier identifier(String path) {
        return Identifier.of(Constants.MODID, path);
    }

    public static void playSound(World world, PlayerEntity player, SoundEvent soundEvent) {
        playSound(world, player, soundEvent, 1f, 1f);
    }

    public static void playSound(World world, PlayerEntity player, SoundEvent soundEvent, float volume, float pitch) {
        world.playSound(null, player.getX(), player.getY(), player.getZ(), soundEvent, player.getSoundCategory(), volume, pitch);
    }
}
