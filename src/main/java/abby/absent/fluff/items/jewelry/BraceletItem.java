package abby.absent.fluff.items.jewelry;

import abby.absent.fluff.gems.GemType;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BraceletItem extends JewelryItem {
    private BipedEntityModel<LivingEntity> model;

    public BraceletItem(GemType type) {
        super(type, new Item.Settings());
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (world.isClient)
            return;
        if (entity.isLiving()) {
            gemImplementation.braceletInventoryTick(stack, world, entity, slot, selected);
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