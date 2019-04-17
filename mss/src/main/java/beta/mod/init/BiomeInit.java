package beta.mod.init;

import beta.mod.world.biome.BiomeBase;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Don't delete this, I just made the system so we don't need to make it again :(
 */
@SuppressWarnings("unused")
public class BiomeInit {
	public static void registerAllBiomes(RegistryEvent.Register<Biome> e) {
		IForgeRegistry<Biome> registry = e.getRegistry();
		
		//Start registering biomes here using registerBiome()
	}
	
	private static void registerBiome(IForgeRegistry<Biome> registry, BiomeBase biome, BiomeType biomeType, int weight, Type...types) {
		registry.register(biome);
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, weight));
	}
}
