package abby.absent.fluff.datagen.worldgen;

import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;

public interface WorldGenExporter {
    void accept(Identifier oreId, JsonObject configuredFeature, JsonObject placedFeature);
}
