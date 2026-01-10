package abby.absent.fluff.items.tools;

import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;

public class HoeBase extends HoeItem {
    public ToolMaterial material;
    public HoeBase(ToolMaterial material, float attackDamage, float attackSpeed) {
        super(
                material,
                new Settings().attributeModifiers(createAttributeModifiers(material, attackDamage, attackSpeed))
        );
        this.material = material;
    }

    public HoeBase(ToolMaterial material) {
        this(material, -3.0F, 0.0F);
    }
}
