package abby.absent.fluff.datagen;

import abby.absent.fluff.Constants;
import abby.absent.fluff.Utility;
import abby.absent.fluff.WoolColour;
import abby.absent.fluff.blocks.GateCompoundBlock;
import abby.absent.fluff.blocks.ModBlocks;
import abby.absent.fluff.blocks.Pride;
import abby.absent.fluff.blocks.gems.GemCore;
import abby.absent.fluff.gems.GemType;
import abby.absent.fluff.items.ModItems;
import abby.absent.fluff.items.jewelry.MetalBase;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

import java.util.Optional;

import static abby.absent.fluff.blocks.ModBlocks.*;
import static net.minecraft.data.client.BlockStateModelGenerator.createSingletonBlockState;
import static net.minecraft.data.client.TexturedModel.makeFactory;

public class ModelGenerator extends FabricModelProvider {
    public static final Model OVERLAYED_BLOCK = new Model(
            Optional.of(Utility.identifier("block/gem_core_template")),
            Optional.empty(),
            TextureKey.TEXTURE,
            TextureKey.LAYER1
    );
    public static final Model GEM_CUTTER = new Model(
            Optional.of(Utility.identifier("block/gem_cutter")),
            Optional.empty(),
            TextureKey.TEXTURE
    );

    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        GemType.MAP.forEach((name, gemType) -> {
            blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GEM_BLOCKS.get(name + Constants.BLOCK_SUFFIX));
            blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GEM_BLOCKS.get(name + Constants.ORE_SUFFIX));
            blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GEM_BLOCKS.get(name + Constants.ORE_DEEPSLATE_SUFFIX));
            registerLamps(blockStateModelGenerator, gemType);
            registerCore(blockStateModelGenerator, gemType);
        });

        Block gemCutterBlock = ModBlocks.GEM_BLOCKS.get("gem_cutter");
        blockStateModelGenerator.blockStateCollector.accept(createSingletonBlockState(gemCutterBlock, Utility.identifier("block/gem_cutter")));

        Block gateCompoundBlock = GATE_COMPOUND_BLOCK;

        Identifier gateCompoundId = TexturedModel.CUBE_ALL.upload(
                gateCompoundBlock,
                blockStateModelGenerator.modelCollector
        );
        Identifier gateCompoundIntangibleId = blockStateModelGenerator.createSubModel(
                gateCompoundBlock,
                "_intangible",
                Models.CUBE_ALL,
                TextureMap::all
        );
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(gateCompoundBlock)
                        .coordinate(BlockStateModelGenerator.createBooleanModelMap(
                                GateCompoundBlock.INTANGIBLE,
                                gateCompoundIntangibleId,
                                gateCompoundId
                        )));

        for (WoolColour colour : WoolColour.values()) {
            registerAlpacaWoolAndCarpets(
                    blockStateModelGenerator,
                    WOOL_BLOCKS.get(colour),
                    CARPET_BLOCKS.get(colour)
            );
            registerAlpacaWoolAndCarpets(
                    blockStateModelGenerator,
                    FLOWERED_WOOL_BLOCKS.get(colour),
                    FLOWERED_CARPET_BLOCKS.get(colour)
            );
        }

        for (Pride pride : Pride.values()) {
            registerPrideAndCarpets(
                    blockStateModelGenerator,
                    PRIDE_BLOCKS.get(pride),
                    pride.modelTemplate.modelTemplate,
                    PRIDE_CARPET_BLOCKS.get(pride),
                    pride.modelTemplate.carpetModelTemplate
            );
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        GemType.MAP.forEach((s, gemType) -> {
            String name = gemType.getName();

            itemModelGenerator.register(ModItems.gemItemMap.get(name), Models.GENERATED);

            simpleHandheldTexture(
                    name + Constants.PICKAXE_SUFFIX,
                    Constants.PICKAXE_PATH_PREFIX + name + Constants.PICKAXE_SUFFIX,
                    itemModelGenerator
            );
            simpleHandheldTexture(
                    name + Constants.SHOVEL_SUFFIX,
                    Constants.SHOVEL_PATH_PREFIX + name + Constants.SHOVEL_SUFFIX,
                    itemModelGenerator
            );
            simpleHandheldTexture(
                    name + Constants.AXE_SUFFIX,
                    Constants.AXE_PATH_PREFIX + name + Constants.AXE_SUFFIX,
                    itemModelGenerator
            );
            simpleHandheldTexture(
                    name + Constants.SWORD_SUFFIX,
                    Constants.SWORD_PATH_PREFIX + name + Constants.SWORD_SUFFIX,
                    itemModelGenerator
            );
            simpleHandheldTexture(
                    name + Constants.HOE_SUFFIX,
                    Constants.HOE_PATH_PREFIX + name + Constants.HOE_SUFFIX,
                    itemModelGenerator
            );

            for (MetalBase base : MetalBase.values()) {
                layeredTexture(
                        name + base.getItemSuffix() + Constants.BRACELET_SUFFIX,
                        Constants.BRACELET_PATH_PREFIX + base.getLowercaseName() + Constants.BRACELET_SUFFIX,
                        Constants.BRACELET_PATH_PREFIX + name,
                        itemModelGenerator);

                layeredTexture(
                        name + base.getItemSuffix() + Constants.NECKLACE_SUFFIX,
                        Constants.NECKLACE_PATH_PREFIX + base.getLowercaseName() + Constants.NECKLACE_SUFFIX,
                        Constants.NECKLACE_PATH_PREFIX + name,
                        itemModelGenerator);

                layeredTexture(
                        name + base.getItemSuffix() + Constants.KEY_SUFFIX,
                        Constants.KEY_PATH_PREFIX + base.getLowercaseName() + Constants.KEY_SUFFIX,
                        Constants.KEY_PATH_PREFIX + name,
                        itemModelGenerator);
            }
        });

        for (MetalBase base : MetalBase.values()) {
            simpleItemTexture(
                    base.getLowercaseName() + Constants.BRACELET_SUFFIX,
                    Constants.BRACELET_PATH_PREFIX + base.getLowercaseName() + Constants.BRACELET_SUFFIX,
                    itemModelGenerator);

            simpleItemTexture(
                    base.getLowercaseName() + Constants.NECKLACE_SUFFIX,
                    Constants.NECKLACE_PATH_PREFIX + base.getLowercaseName() + Constants.NECKLACE_SUFFIX,
                    itemModelGenerator);

            simpleItemTexture(
                    base.getLowercaseName() + Constants.KEY_SUFFIX,
                    Constants.KEY_PATH_PREFIX + base.getLowercaseName() + Constants.KEY_SUFFIX,
                    itemModelGenerator);
        }

        itemModelGenerator.register(ModItems.basicItemMap.get("obsidian_shard"), Models.GENERATED);
        itemModelGenerator.register(ModItems.basicItemMap.get("tool_rod"), Models.GENERATED);

        ModItems.spawnEggItems.forEach((entity, item) -> itemModelGenerator.register(
                item,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty())
        ));
    }

    private void simpleHandheldTexture(String id, String texture, ItemModelGenerator itemModelGenerator) {
        Models.HANDHELD.upload(
                itemIdentifier(id),
                TextureMap.layer0(itemIdentifier(texture)),
                itemModelGenerator.writer
            );
    }

    private void simpleItemTexture(String id, String texture, ItemModelGenerator itemModelGenerator) {
        Models.GENERATED.upload(
                itemIdentifier(id),
                TextureMap.layer0(itemIdentifier(texture)),
                itemModelGenerator.writer
        );
    }

    private void layeredTexture(String id, String layer0, String layer1, ItemModelGenerator itemModelGenerator) {
        Models.GENERATED_TWO_LAYERS.upload(
                itemIdentifier(id),
                TextureMap.layered(
                        itemIdentifier(layer0),
                        itemIdentifier(layer1)
                ),
                itemModelGenerator.writer);
    }

    private void registerCore(BlockStateModelGenerator blockStateModelGenerator, GemType type) {
        String name = type.getName();
        Block core = ModBlocks.GEM_BLOCKS.get(name + Constants.CORE_SUFFIX);

        Identifier blockTexture = blockIdentifier(name + Constants.BLOCK_SUFFIX);
        Identifier overlayTexture = blockIdentifier("gem_core_overlay");
        Identifier overlayTexture1 = blockIdentifier("gem_core_overlay_1");
        Identifier overlayTexture2 = blockIdentifier("gem_core_overlay_2");

        Identifier charged = OVERLAYED_BLOCK.upload(
                core,
                TextureMap
                        .texture(blockTexture)
                        .put(TextureKey.LAYER1, overlayTexture),
                blockStateModelGenerator.modelCollector
        );

        Identifier recharging_1 = blockStateModelGenerator.createSubModel(
                core,
                "_recharging_1",
                OVERLAYED_BLOCK,
                identifier -> TextureMap
                            .texture(blockTexture)
                            .put(TextureKey.LAYER1, overlayTexture1)
        );

        Identifier recharging_2 = blockStateModelGenerator.createSubModel(
                core,
                "_recharging_2",
                OVERLAYED_BLOCK,
                identifier -> TextureMap
                        .texture(blockTexture)
                        .put(TextureKey.LAYER1, overlayTexture2)
        );
        BlockStateVariantMap blockStateVariantMap = BlockStateVariantMap.create(GemCore.RECOVER)
            .register((recover) ->
                switch (recover) {
                    case 0 -> BlockStateVariant.create().put(VariantSettings.MODEL, charged);
                    case 1 -> BlockStateVariant.create().put(VariantSettings.MODEL, recharging_1);
                    case 2 -> BlockStateVariant.create().put(VariantSettings.MODEL, recharging_2);
                    default -> throw new UnsupportedOperationException("Fix you generator!");
            });
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier
                        .create(core)
                        .coordinate(blockStateVariantMap)
        );
    }

    private void registerLamps(BlockStateModelGenerator blockStateModelGenerator, GemType type) {
        Block normal = ModBlocks.GEM_BLOCKS.get(type.getName() + Constants.LAMP_SUFFIX);
        Block inverted = ModBlocks.GEM_BLOCKS.get(type.getName() + Constants.INVERTED_LAMP_SUFFIX);

        Identifier id_notlit = TexturedModel.CUBE_ALL.upload(
                normal,
                blockStateModelGenerator.modelCollector
        );
        Identifier id_lit = blockStateModelGenerator.createSubModel(
                normal,
                Constants.INVERTED_SUFFIX,
                Models.CUBE_ALL,
                TextureMap::all
        );
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier
                        .create(normal)
                        .coordinate(BlockStateModelGenerator.createBooleanModelMap(Properties.LIT, id_lit, id_notlit))
        );

        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier
                        .create(inverted)
                        .coordinate(BlockStateModelGenerator.createBooleanModelMap(Properties.LIT, id_notlit, id_lit))
        );
    }

    private void registerAlpacaWoolAndCarpets(BlockStateModelGenerator blockStateModelGenerator, Block wool, Block carpet) {
        blockStateModelGenerator.registerSouthDefaultHorizontalFacing(TexturedModel.TEMPLATE_GLAZED_TERRACOTTA, wool);
        Identifier identifier = TexturedModel.CARPET.get(wool).upload(carpet, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(
                        carpet,
                        BlockStateVariant.create().put(VariantSettings.MODEL, identifier)
                ).coordinate(BlockStateModelGenerator.createSouthDefaultHorizontalRotationStates())
        );
    }

    private void registerPrideAndCarpets(BlockStateModelGenerator blockStateModelGenerator,
                                         Block block, Model blockModel,
                                         Block carpet, Model carpetModel) {
        blockStateModelGenerator.registerSouthDefaultHorizontalFacing(
                makeFactory(TextureMap::pattern, blockModel),
                block);
        Identifier identifier = makeFactory(TextureMap::wool, carpetModel).get(block).upload(carpet, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(
                        carpet,
                        BlockStateVariant.create().put(VariantSettings.MODEL, identifier)
                ).coordinate(BlockStateModelGenerator.createSouthDefaultHorizontalRotationStates())
        );
    }

    private Identifier blockIdentifier(String id) {
        return Utility.identifier("block/" + id);
    }
    private Identifier itemIdentifier(String id) {
        return Utility.identifier("item/" + id);
    }
}
