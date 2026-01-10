package abby.absent.fluff.entities;
// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import abby.absent.fluff.Utility;
import abby.absent.fluff.entities.animations.AlpacaAnimations;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class AlpacaModel<T extends AlpacaEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer ALPACA = new EntityModelLayer(Utility.identifier("alpaca"), "main");
    private final ModelPart alpaca;
    private final ModelPart head;
    private final ModelPart woolHead;
    private final ModelPart woolTorso;

    public AlpacaModel(ModelPart root) {
        alpaca = root.getChild("alpaca");
        ModelPart torso = alpaca.getChild("body").getChild("torso");
        head = torso.getChild("head");
        woolHead = head.getChild("woolHead");
        woolTorso = torso.getChild("wool");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData alpaca = modelPartData.addChild("alpaca", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData body = alpaca.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(9.0F, -15.0F, 0.0F));

        ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(0, 0).cuboid(-17.0F, -3.0F, -5.0F, 16.0F, 8.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData wool = torso.addChild("wool", ModelPartBuilder.create(), ModelTransform.of(-9.0F, 1.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r1 = wool.addChild("cube_r1", ModelPartBuilder.create().uv(0, 44).cuboid(-8.5F, -4.5F, -5.5F, 17.0F, 9.0F, 11.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData head = torso.addChild("head", ModelPartBuilder.create().uv(0, 18).cuboid(-1.25F, -11.625F, -3.0F, 4.0F, 14.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-18.75F, 1.625F, 0.0F));

        ModelPartData woolHead = head.addChild("woolHead", ModelPartBuilder.create().uv(0, 49).cuboid(0.0F, -3.0F, -4.0F, 5.0F, 7.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 56).cuboid(-1.0F, -3.0F, -4.0F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 56).cuboid(-1.0F, -3.0F, 3.0F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 56).cuboid(-1.0F, 2.0F, -3.0F, 1.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 57).cuboid(-1.0F, -3.0F, -3.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.75F, -9.125F, 0.0F));

        ModelPartData snout = head.addChild("snout", ModelPartBuilder.create().uv(20, 32).cuboid(-1.0F, -1.5F, -2.0F, 2.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.25F, -8.125F, 0.0F));

        ModelPartData rEar = head.addChild("rEar", ModelPartBuilder.create().uv(36, 18).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.75F, -11.625F, 3.25F));

        ModelPartData lEar = head.addChild("lEar", ModelPartBuilder.create().uv(36, 22).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.75F, -11.625F, -3.25F));

        ModelPartData tail = torso.addChild("tail", ModelPartBuilder.create().uv(32, 32).cuboid(-1.0F, -2.0F, -1.0F, 1.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData frLeg = body.addChild("frLeg", ModelPartBuilder.create().uv(20, 18).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-15.0F, 5.0F, 2.8F));

        ModelPartData flLeg = body.addChild("flLeg", ModelPartBuilder.create().uv(20, 18).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-15.0F, 5.0F, -2.8F));

        ModelPartData brLeg = body.addChild("brLeg", ModelPartBuilder.create().uv(20, 18).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 5.0F, 2.8F));

        ModelPartData blLeg = body.addChild("blLeg", ModelPartBuilder.create().uv(20, 18).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 5.0F, -2.8F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(AlpacaEntity entity, float limbAngle, float limbDistance, float ageInTicks, float headYaw, float headPitch) {
        getPart().traverse().forEach(ModelPart::resetTransform);
        setHeadAngles(headYaw, headPitch);

        animateMovement(AlpacaAnimations.WALK, limbAngle, limbDistance, 1f, 1f);
        updateAnimation(entity.idleAnimationState, AlpacaAnimations.IDLE, ageInTicks);
        updateAnimation(entity.eatAnimationState, AlpacaAnimations.EAT_GRASS, ageInTicks);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30f, 30f);
        headPitch = MathHelper.clamp(headPitch, -25f, 45f);

        // magic numbers copied from tutorial idk
        this.head.yaw = headYaw * 0.017453292f;
        this.head.pitch = headPitch * 0.017453292f;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        alpaca.render(matrices, vertices, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return alpaca;
    }

    public ModelPart[] getWool() {
        return new ModelPart[] { woolTorso, woolHead };
    }
}