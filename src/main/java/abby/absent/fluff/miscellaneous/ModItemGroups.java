package abby.absent.fluff.miscellaneous;

import abby.absent.fluff.Constants;
import abby.absent.fluff.Utility;
import abby.absent.fluff.blocks.ModBlocks;
import abby.absent.fluff.gems.GemType;
import abby.absent.fluff.items.jewelry.MetalBase;
import abby.absent.fluff.items.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import java.util.Map;

public class ModItemGroups {
    private static final Map<String, Item> TOOL_MAP = ModItems.gemToolMap;

    private static final ItemGroup GENERAL_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.gemItemMap.get(GemType.SAPPHIRE.getName())))
            .displayName(Text.translatable("itemgroup.crystallised.general"))
            .entries(((displayContext, entries) -> {
                ModItems.gemItemMap.forEach((s, item) -> entries.add(item));
                ModBlocks.GEM_BLOCKS.forEach((s, block) -> entries.add(block));
                ModItems.basicItemMap.forEach((s, item) -> entries.add(item));
                ModBlocks.WOOL_BLOCKS.forEach((s, item) -> entries.add(item));
                ModBlocks.CARPET_BLOCKS.forEach((s, item) -> entries.add(item));
                ModBlocks.FLOWERED_WOOL_BLOCKS.forEach((s, item) -> entries.add(item));
                ModBlocks.FLOWERED_CARPET_BLOCKS.forEach((s, item) -> entries.add(item));
                ModBlocks.PRIDE_BLOCKS.forEach((s, item) -> entries.add(item));
                ModBlocks.PRIDE_CARPET_BLOCKS.forEach((s, item) -> entries.add(item));
            }))
            .build();

    private static final ItemGroup TOOL_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(TOOL_MAP.get(GemType.SAPPHIRE.getName() + Constants.PICKAXE_SUFFIX)))
            .displayName(Text.translatable("itemgroup.crystallised.tools"))
            .entries((context, entries) -> {
                String name;
                for(GemType type: GemType.MAP.values()) {
                    name = type.getName();
                    entries.add(new ItemStack(TOOL_MAP.get(name + Constants.PICKAXE_SUFFIX)));
                    entries.add(new ItemStack(TOOL_MAP.get(name + Constants.AXE_SUFFIX)));
                    entries.add(new ItemStack(TOOL_MAP.get(name + Constants.SHOVEL_SUFFIX)));
                    entries.add(new ItemStack(TOOL_MAP.get(name + Constants.SWORD_SUFFIX)));
                    entries.add(new ItemStack(TOOL_MAP.get(name + Constants.HOE_SUFFIX)));
                }
            })
            .build();

    private static final ItemGroup ACCESSORY_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(
                    ModItems.accessoryItemMap.get(
                            GemType.SAPPHIRE.getName() + MetalBase.IRON.getItemSuffix() + Constants.BRACELET_SUFFIX)
            ))
            .displayName(Text.translatable("itemgroup.crystallised.accessories"))
            .entries((context, entries) -> ModItems.accessoryItemMap.forEach(
                    (s, item) -> entries.add(item)
            ))
            .build();

    static {
        Utility.LOGGER.info("START REGISTER ITEM GROUPS");

        Registry.register(Registries.ITEM_GROUP, Utility.identifier(Constants.GENERAL_GROUP_ID), GENERAL_GROUP);
        Registry.register(Registries.ITEM_GROUP, Utility.identifier(Constants.TOOL_GROUP_ID), TOOL_GROUP);
        Registry.register(Registries.ITEM_GROUP, Utility.identifier(Constants.ACCESSORY_GROUP_ID), ACCESSORY_GROUP);
    }

    public static void init() {
        Utility.LOGGER.info("Finished registering item groups");
    }
}
