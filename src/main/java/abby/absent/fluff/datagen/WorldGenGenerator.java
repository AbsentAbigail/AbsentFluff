package abby.absent.fluff.datagen;

import abby.absent.fluff.Constants;
import abby.absent.fluff.Utility;
import abby.absent.fluff.datagen.worldgen.WorldGenExporter;
import abby.absent.fluff.gems.GemType;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WorldGenGenerator implements DataProvider {
    final DataOutput.PathResolver configuredPathResolver;
    final DataOutput.PathResolver placedPathResolver;

    public WorldGenGenerator(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
        this.configuredPathResolver = output.getResolver(DataOutput.OutputType.DATA_PACK, "worldgen/configured_feature");
        this.placedPathResolver = output.getResolver(DataOutput.OutputType.DATA_PACK, "worldgen/placed_feature");
    }

    public void generate(WorldGenExporter exporter) {
        GemType.forEach((name, type) -> {
            Identifier id = Utility.identifier(name + Constants.ORE_SUFFIX);
            Identifier deepslateId = Utility.identifier(name + Constants.ORE_DEEPSLATE_SUFFIX);

            int hardness = (int)type.getHardness();
            int amount = 10 - (int)(hardness * 0.9);
            int maxHeight = 160;
            int minHeight = -64;
            int plateau = (5 - hardness) * 15;
            int veinSize = (int)((20 - hardness) * 0.5);

            exporter.accept(
                    id,
                    generateConfiguredFeatureJson(id, deepslateId, veinSize),
                    generatePlacedFeatureJson(id, amount, maxHeight, minHeight, plateau)
            );
        });
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        List<CompletableFuture<?>> list = new ArrayList<>();
        generate(
            (oreId, configuredFeature, placedFeature) -> {
                list.add(DataProvider.writeToPath(writer, configuredFeature, configuredPathResolver.resolveJson(oreId)));
                list.add(DataProvider.writeToPath(writer, placedFeature, placedPathResolver.resolveJson(oreId)));
            }
        );

        return CompletableFuture.allOf(list.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "World Generation";
    }

    private JsonObject generateConfiguredFeatureJson(Identifier oreId, Identifier deepslateOreId, int veinSize) {
        JsonObject json = new JsonObject();
        json.addProperty("type", "minecraft:ore");

        JsonObject config = new JsonObject();
        config.addProperty("discard_chance_on_air_exposure", 0.0F);
        config.addProperty("size", veinSize);

        JsonArray targets = new JsonArray();

        JsonObject stoneOre = new JsonObject();
        JsonObject stoneState = new JsonObject();
        stoneState.addProperty("Name", oreId.toString());
        stoneOre.add("state", stoneState);

        JsonObject stoneTarget = new JsonObject();
        stoneTarget.addProperty("predicate_type", "minecraft:tag_match");
        stoneTarget.addProperty("tag", "minecraft:stone_ore_replaceables");
        stoneOre.add("target", stoneTarget);
        targets.add(stoneOre);

        JsonObject deepslateOre = new JsonObject();
        JsonObject deepslateState = new JsonObject();
        deepslateState.addProperty("Name", deepslateOreId.toString());
        deepslateOre.add("state", deepslateState);

        JsonObject deepslateTarget = new JsonObject();
        deepslateTarget.addProperty("predicate_type", "minecraft:tag_match");
        deepslateTarget.addProperty("tag", "minecraft:deepslate_ore_replaceables");
        deepslateOre.add("target", deepslateTarget);
        targets.add(deepslateOre);

        config.add("targets", targets);
        json.add("config", config);

        return json;
    }

    private JsonObject generatePlacedFeatureJson(Identifier identifier, int amount, int maxHeight, int minHeight, int plateau) {
        JsonObject json = new JsonObject();
        json.addProperty("feature", identifier.toString());

        JsonArray placement = new JsonArray();

        JsonObject count = new JsonObject();
        count.addProperty("type", "minecraft:count");
        count.addProperty("count", amount);
        placement.add(count);

        JsonObject square = new JsonObject();
        square.addProperty("type", "minecraft:in_square");
        placement.add(square);

        JsonObject heightRange = getHeightRange(maxHeight, minHeight, plateau);
        placement.add(heightRange);

        JsonObject biome = new JsonObject();
        biome.addProperty("type", "minecraft:biome");
        placement.add(biome);

        json.add("placement", placement);

        return json;
    }

    @NotNull
    private static JsonObject getHeightRange(int maxHeight, int minHeight, int plateau) {
        JsonObject heightRange = new JsonObject();
        heightRange.addProperty("type", "minecraft:height_range");
        JsonObject height = new JsonObject();
        height.addProperty("type", "minecraft:trapezoid");

        JsonObject max = new JsonObject();
        max.addProperty("absolute", maxHeight);
        height.add("max_inclusive", max);

        JsonObject min = new JsonObject();
        min.addProperty("absolute", minHeight);
        height.add("min_inclusive", min);

        height.addProperty("plateau", plateau);

        heightRange.add("height", height);
        return heightRange;
    }
}
