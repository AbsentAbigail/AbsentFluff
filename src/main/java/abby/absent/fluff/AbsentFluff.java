package abby.absent.fluff;

import abby.absent.fluff.blocks.ModBlocks;
import abby.absent.fluff.blocks.blockentities.ModBlockEntities;
import abby.absent.fluff.entities.ModEntities;
import abby.absent.fluff.items.ModItems;
import abby.absent.fluff.miscellaneous.ModComponents;
import abby.absent.fluff.miscellaneous.ModItemGroups;
import abby.absent.fluff.miscellaneous.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbsentFluff implements ModInitializer {
	@Override
	public void onInitialize() {
		new ModItems();
		new ModBlocks();
		new ModItemGroups();
		new ModBlockEntities();
		new ModWorldGeneration();
		new ModEntities();
		new ModComponents();
	}
}