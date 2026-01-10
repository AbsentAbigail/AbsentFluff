package abby.absent.fluff;

import abby.absent.fluff.blocks.ModBlocks;
import abby.absent.fluff.entities.AlpacaModel;
import abby.absent.fluff.entities.AlpacaRenderer;
import abby.absent.fluff.entities.ModEntities;
import abby.absent.fluff.items.ModItems;
import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;

public class AbsentFluffClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModBlocks.cutoutBlocks.forEach(block -> {
			BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), block);
		});

		EntityModelLayerRegistry.registerModelLayer(AlpacaModel.ALPACA, AlpacaModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.ALPACA, AlpacaRenderer::new);

		Item bracelet = ModItems.accessoryItemMap.get("azurite_netherite_bracelet");
		TrinketRendererRegistry.registerRenderer(bracelet, (TrinketRenderer) bracelet);
	}
}