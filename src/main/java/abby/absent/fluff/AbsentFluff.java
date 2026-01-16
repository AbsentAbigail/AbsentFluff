package abby.absent.fluff;

import abby.absent.fluff.blocks.ModBlocks;
import abby.absent.fluff.blocks.blockentities.ModBlockEntities;
import abby.absent.fluff.entities.ModEntities;
import abby.absent.fluff.items.ModItems;
import abby.absent.fluff.miscellaneous.ModComponents;
import abby.absent.fluff.miscellaneous.ModItemGroups;
import abby.absent.fluff.miscellaneous.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

public class AbsentFluff implements ModInitializer {
	@Override
	public void onInitialize() {
		ModWorldGeneration.init();
		ModComponents.init();
		ModBlocks.init();
		ModEntities.init();
		ModItems.init();
		ModItemGroups.init();
		ModBlockEntities.init();
	}
}