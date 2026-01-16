package abby.absent.fluff.entities;

import abby.absent.fluff.Utility;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.World;

public class ModEntities {
    public static final EntityType<AlpacaEntity> ALPACA = register(
            "alpaca",
            EntityType.Builder.create(AlpacaEntity::new, SpawnGroup.CREATURE).dimensions(1.2f, 1.5f)
    );

    public static final EntityType<BombEntity> BOMB = register(
            "bomb",
            EntityType.Builder.create(
                        (EntityType<BombEntity> entityType, World world) -> new BombEntity(entityType, world),
                        SpawnGroup.MISC
                    ).dimensions(0.25f, 0.25f).maxTrackingRange(4).trackingTickInterval(10)
    );

    static {
        Utility.LOGGER.info("START REGISTER ENTITIES");

        FabricDefaultAttributeRegistry.register(ALPACA, AlpacaEntity.createAlpacaAttributes());
    }

    public static void init() {
        Utility.LOGGER.info("Finished registering entities");
    }

    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, Utility.identifier(id), type.build(id));
    }
}
