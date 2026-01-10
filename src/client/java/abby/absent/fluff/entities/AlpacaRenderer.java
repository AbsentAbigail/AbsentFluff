package abby.absent.fluff.entities;

import abby.absent.fluff.Utility;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.util.Arrays;

public class AlpacaRenderer extends MobEntityRenderer<AlpacaEntity, AlpacaModel<AlpacaEntity>> {
    public AlpacaRenderer(EntityRendererFactory.Context rendererContext) {
        super(rendererContext, new AlpacaModel<>(rendererContext.getPart(AlpacaModel.ALPACA)), 0.7f);
    }

    @Override
    public void render(AlpacaEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int packedLight) {
        if (entity.isBaby())
            matrixStack.scale(0.5f, 0.5f, 0.5f);

        Arrays.stream(model.getWool()).forEach(p -> p.visible = !entity.isSheared());

        super.render(entity, entityYaw, partialTicks, matrixStack, vertexConsumerProvider, packedLight);
    }

    @Override
    public Identifier getTexture(AlpacaEntity entity) {
        String color = entity.getColor().getName();
        return Utility.identifier("textures/entity/" + color + "_alpaca.png");
    }
}
