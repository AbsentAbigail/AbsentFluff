package abby.absent.fluff.blocks.gems;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;

import java.util.function.ToIntFunction;

public class GemLamp extends RedstoneLampBlock {
    public GemLamp(boolean inverted) {
        super(Settings.copy(Blocks.REDSTONE_LAMP).luminance(createLightLevelFromLitBlockState(inverted)).strength(0.3F).sounds(BlockSoundGroup.GLASS));
    }

    private static ToIntFunction<BlockState> createLightLevelFromLitBlockState(boolean inverted) {
        return (state) -> inverted ? state.get(Properties.LIT) ? 0 : 15 : state.get(Properties.LIT) ? 15 : 0;
    }
}