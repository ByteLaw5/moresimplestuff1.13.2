package beta.mod.world;

import beta.mod.Main;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeSpawns {	
	public static void addSpawns() {
		for(Biome biome : ForgeRegistries.BIOMES) {
			if(biome == Biomes.ICE_SPIKES || biome == Biomes.DEEP_FROZEN_OCEAN) {
				biome.getSpawns(EnumCreatureType.CREATURE).add(new Biome.SpawnListEntry(Main.RegistryEvents.ICE_GHAST, 20, 1, 1));
			}
			if(biome == Biomes.PLAINS)
				biome.getSpawns(EnumCreatureType.CREATURE).add(new Biome.SpawnListEntry(EntityType.GIANT, 50, 2, 3));
		}
	}
}
