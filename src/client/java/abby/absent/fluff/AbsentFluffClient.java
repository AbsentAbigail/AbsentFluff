package abby.absent.fluff;

import abby.absent.fluff.blocks.ModBlocks;
import abby.absent.fluff.entities.AlpacaModel;
import abby.absent.fluff.entities.AlpacaRenderer;
import abby.absent.fluff.entities.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.EntityType;

public class AbsentFluffClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModBlocks.cutoutBlocks.forEach(block -> {
			BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), block);
		});
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GATE_COMPOUND_BLOCK, RenderLayer.getCutout());

		EntityModelLayerRegistry.registerModelLayer(AlpacaModel.ALPACA, AlpacaModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.ALPACA, AlpacaRenderer::new);
		EntityRendererRegistry.register(ModEntities.BOMB, FlyingItemEntityRenderer::new);
	}
}