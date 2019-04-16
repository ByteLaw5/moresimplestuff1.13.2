package beta.mod.world.biome;

import beta.mod.Main;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public class BiomeBase extends Biome {
	public BiomeBaseProperties props;
	
	public BiomeBase(boolean withDefaultReplacers, BiomeBaseProperties prop) {
		super(prop.getBiomeProperties(withDefaultReplacers));
		setRegistryName(Main.modid, prop.getName());
		props = prop;
	}
	
	@Override
	public int getGrassColor(BlockPos pos) {
		return props.getGrassColour() != 0 ? props.getGrassColour() : super.getGrassColor(pos);
	}
	
	@Override
	public int getFoliageColor(BlockPos pos) {
		return props.getFoliageColour() != 0 ? props.getFoliageColour() : super.getFoliageColor(pos);
	}
	
	@Override
	public float getSpawningChance() {
		return props.getSpawnChance();
	}
}
