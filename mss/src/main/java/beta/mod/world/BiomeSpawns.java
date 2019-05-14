package beta.mod.world;

import beta.mod.init.ModET;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeSpawns {	
	public static void addSpawns() {
		for(Biome biome : ForgeRegistries.BIOMES) {
			if(biome == Biomes.ICE_SPIKES || biome == Biomes.DEEP_FROZEN_OCEAN) {
				biome.getSpawns(EnumCreatureType.CREATURE).add(new Biome.SpawnListEntry(ModET.ICE_GHAST, 20, 1, 1));
			}
		}
	}
}
