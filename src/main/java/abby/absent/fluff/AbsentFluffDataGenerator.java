package abby.absent.fluff;

import abby.absent.fluff.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AbsentFluffDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(LanguageFileGenerator::new);
		pack.addProvider(ModelGenerator::new);
		pack.addProvider(RecipeGenerator::new);
		pack.addProvider(BlockLootTableGenerator::new);
		pack.addProvider(ItemTagGenerator::new);
		pack.addProvider(BlockTagGenerator::new);
		pack.addProvider(WorldGenGenerator::new);
	}
}
