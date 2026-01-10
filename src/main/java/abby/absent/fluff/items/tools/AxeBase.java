package abby.absent.fluff.items.tools;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterial;

public class AxeBase extends AxeItem {
    public ToolMaterial material;
    public AxeBase(ToolMaterial material, float attackDamage, float attackSpeed) {
        super(
                material,
                new Settings().attributeModifiers(createAttributeModifiers(material, attackDamage, attackSpeed))
        );
        this.material = material;
    }

    public AxeBase(ToolMaterial material) {
        this(material, 6.0F, -2.8F);
    }
}
