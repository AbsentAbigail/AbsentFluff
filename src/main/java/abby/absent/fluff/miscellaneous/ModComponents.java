package abby.absent.fluff.miscellaneous;

import abby.absent.fluff.Utility;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.GlobalPos;

public class ModComponents {
    public static final ComponentType<GlobalPos> BOUND_TO_POSITION_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Utility.identifier("bound_to_position"),
            ComponentType.<GlobalPos>builder().codec(GlobalPos.CODEC).build()
    );

    protected static void initialize() {
    }
}
