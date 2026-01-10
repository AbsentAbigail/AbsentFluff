package abby.absent.fluff.miscellaneous;

import abby.absent.fluff.Utility;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {
    public static final TagKey<Item> BRACELET = of("trinkets", "hand/bracelet");
    public static final TagKey<Item> OFFHAND_BRACELET = of("trinkets", "offhand/bracelet");
    public static final TagKey<Item> NECKLACE = of("trinkets", "chest/necklace");
    public static final TagKey<Item> GEMS = of("gems");

    private static TagKey<Item> of(String namespace, String path) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(namespace, path));
    }

    private static TagKey<Item> of(String path) {
        return TagKey.of(RegistryKeys.ITEM, Utility.identifier(path));
    }
}
