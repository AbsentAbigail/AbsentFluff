package abby.absent.fluff.gems;

import abby.absent.fluff.gems.implementation.*;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class GemType implements Comparable<GemType> {
    public static final Map<String, GemType> MAP = new LinkedHashMap<>();

    public static final GemType AMBER = new GemType("amber", "Amber",
            "Remnants of ancient times",
            2.2F,
            5,
            new Amber()).register();
    public static final GemType AZURITE = new GemType("azurite", "Azurite",
            "You can hear the water inside",
            3.5F,
            13,
            new Azurite()).register();
    public static final GemType BULLSEYE = new GemType("bullseye", "Bull's Eye",
            "",
            7.5F,
            8,
            new BullsEye()).register();
    public static final GemType FLUORITE = new GemType("fluorite", "Fluorite",
            "Space warps around it",
            4F,
            13,
            new Fluorite()).register();
    public static final GemType KUNZITE = new GemType("kunzite", "Kunzite",
            "Feels like the end",
            7F,
            18,
            new Kunzite()).register();
    public static final GemType MOISSANITE = new GemType("moissanite", "Moissanite",
            "Don't chew on it",
            9.5F,
            20,
            new Moissanite()).register();
    public static final GemType MOONSTONE = new GemType("moonstone", "Moonstone",
            "The moon giveth, and the moon taketh",
            6.5F,
            30,
            new Moonstone()).register();
    public static final GemType ONYX = new GemType("onyx", "Onyx",
            "Radiates darkness",
            7F,
            10,
            new Onyx()).register();
    public static final GemType PERIDOT = new GemType("peridot", "Peridot",
            "Growth",
            6.5F,
            13,
            new Peridot()).register();
    public static final GemType PETALITE = new GemType("petalite", "Petalite",
            "It's so light, it might float away",
            6.0F,
            22,
            new Petalite()).register();
    public static final GemType PHOSPHOPHYLLITE = new GemType("phosphophyllite", "Phosphophyllite",
            "Careful, or it might break",
            3.5F,
            30,
            new Phosphophyllite()).register();
    public static final GemType ROSEQUARTZ = new GemType("rosequartz", "Rose Quartz",
            "Soothing",
            7F,
            10,
            new RoseQuartz()).register();
    public static final GemType RUBY = new GemType("ruby", "Ruby",
            "Warm to the touch",
            9F,
            17,
            new Ruby()).register();
    public static final GemType SAPPHIRE = new GemType("sapphire", "Sapphire",
            "Cold to the touch",
            9F,
            17,
            new Sapphire()).register();
    public static final GemType TANZANITE = new GemType("tanzanite", "Tanzanite",
            "It's heavy",
            6.5F,
            15,
            new Tanzanite()).register();
    public static final GemType TOPAZ = new GemType("topaz", "Topaz",
            "",
            8.0F,
            7,
            new Topaz()).register();

    private final String name;
    private final String displayName;
    private final String lore;
    private final float hardness;
    private final MaterialGem material;

    private final int capacity;
    private final float rarity;
    private final GemImplementation implementation;

    public GemType(String name,
                   String  displayName,
                   String lore,
                   float hardness,
                   int magicCapacity,
                   GemImplementation implementation) {
        this.name = name;
        this.displayName = displayName;
        this.lore = lore;
        this.hardness = hardness;
        this.capacity = magicCapacity;
        this.rarity = ((Math.round((20F - hardness - capacity) * 100F) / 100F) + 1F) / 10F;
        this.implementation = implementation;
        material = new MaterialGem(this);
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getLore() {
        return lore;
    }

    public float getHardness() {
        return hardness;
    }

    public int getCapacity() {
        return capacity;
    }

    public float getRarity() {
        return rarity;
    }

    public MaterialGem getMaterial() {
        return material;
    }

    public GemImplementation getImplementation() {
        return implementation;
    }

    public GemType register() {
        GemType.MAP.put(this.name, this);
        return this;
    }

    @Override
    public int compareTo(@NotNull GemType o) {
        return this.name.compareTo(o.name);
    }

    public static void forEach(BiConsumer<String, GemType> function) {
        MAP.forEach(function);
    }
}
