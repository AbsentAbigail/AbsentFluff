package abby.absent.fluff.items.jewelry;

import abby.absent.fluff.gems.GemType;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class BraceletItem extends JewelryTrinketItem {

    public BraceletItem(GemType type) {
        super(type, new Item.Settings());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.absentfluff.bracelet_tooltip." + gemType.getName()).formatted(Formatting.GOLD));
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        super.tick(stack, slot, entity);
        if (entity.isLiving()) {
            gemImplementation.braceletInventoryTick(stack, slot, entity);
        }
    }

    //    @Override
//    @Environment(EnvType.CLIENT)
//    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
//        BipedEntityModel<LivingEntity> model = this.getModel();
//        model.setAngles(entity, limbAngle, limbDistance, animationProgress, animationProgress, headPitch);
//        model.animateModel(entity, limbAngle, limbDistance, tickDelta);
//        TrinketRenderer.followBodyRotations(entity, model);
//        Identifier texture = Utility.identifier("textures/entity/trinket/bracelet.png");
//        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(model.getLayer(texture));
//        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1);
//    }
//
//    @Environment(EnvType.CLIENT)
//    private BipedEntityModel<LivingEntity> getModel() {
//        if (this.model == null) {
//            // Vanilla 1.17 uses EntityModels, EntityModelLoader and EntityModelLayers
//            this.model = new BraceletModel(BraceletModel.getTexturedModelData().createModel());
//        }
//
//        return this.model;
//    }
}