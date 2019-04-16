package beta.mod.world.biome;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeBuilder;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.Biome.TempCategory;
import net.minecraft.world.gen.surfacebuilders.CompositeSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class BiomeBaseProperties {
	private String baseBiomeID;
	private final boolean hasBaseBiome;
	
	private float averageTemperature = 0.5f;
	private float biomeRainfall;
	
	private TempCategory biomeType = TempCategory.WARM;
	private float biomeBaseHeight = 0.1f;
	private float heightVariation = 0.2f;
	private final String name;
	
	private int grassColour = 0;
	private int foliageColour = 0;
	private int skyColour = 0;
	
	private IBlockState lavaReplacer = Blocks.LAVA.getDefaultState();
	private IBlockState waterReplacer = Blocks.WATER.getDefaultState();
	
	private int waterColor = 16777215;
	private RainType rainType = RainType.RAIN;
	private Category[] typeCategories = new Category[Category.values().length];
	private IBlockState[] surfaceReplacers = new IBlockState[3];
	private float spawnChance = 0.1f;
	private int[] rgbFog = {0, 0, 0};
	public boolean fogColour = false;
	
	public String getName() {
		return name;
	}
	
	public String getBaseBiome() {
		return baseBiomeID;
	}
	
	public float getAverageTemperature() {
		return averageTemperature;
	}
	
	public float getBiomeRainfall() {
		return biomeRainfall;
	}
	
	public float getBiomeBaseHeight() {
		return biomeBaseHeight;
	}
	
	public float getHeightVariation() {
		return heightVariation;
	}
	
	public int getWaterColor() {
		return waterColor;
	}
	
	public RainType getRainType() {
		return rainType;
	}
	
	public Category[] getTypeCategories() {
		return typeCategories;
	}
	
	public TempCategory getBiomeType() {
		return biomeType;
	}
	
	public int getGrassColour() {
		return grassColour;
	}
	
	public int getFoliageColour() {
		return foliageColour;
	}
	
	public IBlockState[] getSurfaceReplacers() {
		return surfaceReplacers;
	}
	
	public IBlockState getLavaReplacer() {
		return lavaReplacer;
	}
	
	public IBlockState getWaterReplacer() {
		return waterReplacer;
	}
	
	public int getSkyColour() {
		return skyColour;
	}
	
	public float getSpawnChance() {
		return spawnChance;
	}
	
	public int[] getFogColour() {
		return rgbFog;
	}
	
	public BiomeBuilder getBiomeProperties(boolean withDefaultReplacers) {
		BiomeBuilder builder = withDefaultReplacers ?
				new BiomeBuilder().surfaceBuilder(new CompositeSurfaceBuilder<>(Biome.DEFAULT_SURFACE_BUILDER, new SurfaceBuilderConfig(
						Blocks.GRASS_BLOCK.getDefaultState(),
						Blocks.DIRT.getDefaultState(),
						Blocks.STONE.getDefaultState()
				))).depth(biomeBaseHeight).scale(heightVariation).category(typeCategories[0]).downfall(biomeRainfall).temperature(averageTemperature).waterColor(waterColor).waterFogColor(waterColor).precipitation(rainType).parent(hasBaseBiome ? baseBiomeID : (String)null) :
				new BiomeBuilder().surfaceBuilder(new CompositeSurfaceBuilder<>(Biome.DEFAULT_SURFACE_BUILDER, new SurfaceBuilderConfig(
						surfaceReplacers[1],
						surfaceReplacers[2],
						surfaceReplacers[3]
				))).depth(biomeBaseHeight).scale(heightVariation).category(typeCategories[0]).downfall(biomeRainfall).temperature(averageTemperature).waterColor(waterColor).waterFogColor(waterColor).precipitation(rainType).parent(hasBaseBiome ? baseBiomeID : (String)null);
		return builder;
	}
	
	public BiomeBaseProperties(String name) {
		this.name = name;
		hasBaseBiome = false;
	}
	
	public BiomeBaseProperties(String name, String baseBiomeID) {
		this.name = name;
		hasBaseBiome = true;
		this.baseBiomeID = baseBiomeID;
	}
	
	public BiomeBaseProperties setHeightContours(float baseHeight, float variation) {
		biomeBaseHeight = baseHeight;
		heightVariation = variation;
		return this;
	}
	
	public BiomeBaseProperties setTemperatureRainfall(float averageTemperature, float rainfall) {
		this.averageTemperature = averageTemperature;
		biomeRainfall = rainfall;
		return this;
	}
	
	public BiomeBaseProperties setTintWaterColor(int color) {
		waterColor = color;
		return this;
	}
	
	public BiomeBaseProperties setBiomeType(TempCategory biomeType) {
		this.biomeType = biomeType;
		return this;
	}
	
	public BiomeBaseProperties setRainType(RainType rain) {
		rainType = rain;
		return this;
	}
	
	public BiomeBaseProperties addTypes(Category...types) {
		typeCategories = types;
		return this;
	}
	
	public BiomeBaseProperties setGrassColor(int color) {
		grassColour = color;
		return this;
	}
	
	public BiomeBaseProperties setFoliageColor(int color) {
		foliageColour = color;
		return this;
	}
	
	public BiomeBaseProperties setSurfaceReplacers(IBlockState state1, IBlockState state2, IBlockState state3) {
		surfaceReplacers = new IBlockState[] {state1, state2, state3};
		return this;
	}
	
	public BiomeBaseProperties setLavaReplacer(IBlockState lavaReplacer) {
		this.lavaReplacer = lavaReplacer;
		return this;
	}
	
	public BiomeBaseProperties setWaterReplacer(IBlockState waterReplacer) {
		this.waterReplacer = waterReplacer;
		return this;
	}
	
	public BiomeBaseProperties setSkyColour(int skyColour) {
		this.skyColour = skyColour;
		return this;
	}
	
	public BiomeBaseProperties setSpawnChance(float spawnChance) {
		this.spawnChance = spawnChance;
		return this;
	}
	
	public BiomeBaseProperties setFogColour(int r, int g, int b) {
		rgbFog = new int[] {r, g, b};
		fogColour = true;
		return this;
	}
}
