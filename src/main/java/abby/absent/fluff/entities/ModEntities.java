package abby.absent.fluff.entities;

import abby.absent.fluff.Utility;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEntities {
    public static final EntityType<AlpacaEntity> ALPACA = Registry.register(
            Registries.ENTITY_TYPE,
            Utility.identifier("alpaca"),
            EntityType.Builder.create(AlpacaEntity::new, SpawnGroup.CREATURE).dimensions(1.2f, 1.5f)
                    .build("alpaca")
    );

    public ModEntities() {
        FabricDefaultAttributeRegistry.register(ALPACA, AlpacaEntity.createAlpacaAttributes());
    }
}
