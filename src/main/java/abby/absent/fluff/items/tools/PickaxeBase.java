package abby.absent.fluff.items.tools;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class PickaxeBase extends PickaxeItem {
    public ToolMaterial material;

    public PickaxeBase(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(
                material,
                new Settings().attributeModifiers(createAttributeModifiers(material, attackDamage, attackSpeed))
        );
        this.material = material;
    }

    public PickaxeBase(ToolMaterial material) {
        this(material, 2, -2.8F);
    }
}
