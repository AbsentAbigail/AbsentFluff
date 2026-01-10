package abby.absent.fluff.datagen;

import abby.absent.fluff.Constants;
import abby.absent.fluff.gems.GemType;
import abby.absent.fluff.items.jewelry.MetalBase;
import abby.absent.fluff.items.ModItems;
import abby.absent.fluff.miscellaneous.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends FabricTagProvider.ItemTagProvider {
    public ItemTagGenerator(
            FabricDataOutput output,
            CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture
    ) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        GemType.forEach((name, type) -> {
            Item gem = ModItems.gemItemMap.get(name);
            getOrCreateTagBuilder(ModItemTags.GEMS).add(gem);
            getOrCreateTagBuilder(ItemTags.BEACON_PAYMENT_ITEMS).add(gem);

            Item pickaxe = ModItems.gemToolMap.get(name + Constants.PICKAXE_SUFFIX);
            getOrCreateTagBuilder(ItemTags.PICKAXES).add(pickaxe);
            getOrCreateTagBuilder(ItemTags.MINING_LOOT_ENCHANTABLE).add(pickaxe);

            Item axe = ModItems.gemToolMap.get(name + Constants.AXE_SUFFIX);
            getOrCreateTagBuilder(ItemTags.AXES).add(axe);
            getOrCreateTagBuilder(ItemTags.MINING_LOOT_ENCHANTABLE).add(axe);

            Item shovel = ModItems.gemToolMap.get(name + Constants.SHOVEL_SUFFIX);
            getOrCreateTagBuilder(ItemTags.SHOVELS).add(shovel);
            getOrCreateTagBuilder(ItemTags.MINING_LOOT_ENCHANTABLE).add(shovel);

            Item sword = ModItems.gemToolMap.get(name + Constants.SWORD_SUFFIX);
            getOrCreateTagBuilder(ItemTags.SWORDS).add(sword);
            getOrCreateTagBuilder(ItemTags.SWORD_ENCHANTABLE).add(sword);

            Item hoe = ModItems.gemToolMap.get(name + Constants.HOE_SUFFIX);
            getOrCreateTagBuilder(ItemTags.HOES).add(hoe);
            getOrCreateTagBuilder(ItemTags.MINING_LOOT_ENCHANTABLE).add(hoe);
        });
        configureAccessories();
    }

    private void configureAccessories() {
        for (MetalBase metalBase : MetalBase.values()) {
            getOrCreateTagBuilder(ModItemTags.NECKLACE)
                    .add(ModItems.accessoryItemMap.get(metalBase.getLowercaseName() + Constants.NECKLACE_SUFFIX));
            getOrCreateTagBuilder(ModItemTags.BRACELET)
                    .add(ModItems.accessoryItemMap.get(metalBase.getLowercaseName() + Constants.BRACELET_SUFFIX));
            getOrCreateTagBuilder(ModItemTags.OFFHAND_BRACELET)
                    .add(ModItems.accessoryItemMap.get(metalBase.getLowercaseName() + Constants.BRACELET_SUFFIX));

            GemType.forEach((name, type) -> {
                getOrCreateTagBuilder(ModItemTags.NECKLACE).add(ModItems.accessoryItemMap.get(
                        name + metalBase.getItemSuffix() + Constants.NECKLACE_SUFFIX)
                );
                getOrCreateTagBuilder(ModItemTags.BRACELET).add(ModItems.accessoryItemMap.get(
                        name + metalBase.getItemSuffix() + Constants.BRACELET_SUFFIX)
                );
                getOrCreateTagBuilder(ModItemTags.OFFHAND_BRACELET).add(ModItems.accessoryItemMap.get(
                        name + metalBase.getItemSuffix() + Constants.BRACELET_SUFFIX)
                );
            });
        }
    }
}
