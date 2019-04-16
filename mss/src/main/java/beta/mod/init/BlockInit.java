package beta.mod.init;

import java.util.ArrayList;
import java.util.List;

import beta.mod.Main;
import beta.mod.objects.BlockBase;
import beta.mod.objects.BlockBaseProperties;
import beta.mod.objects.ItemBase;
import beta.mod.objects.ItemBaseProperties;
import beta.mod.objects.ItemBaseType;
import beta.mod.objects.special.BlockBars;
import beta.mod.objects.special.BlockQuickSand;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockInit {
	/**
	 * A {@link List} to store the {@link Block}s in
	 */
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	/**
	 * A {@link List} to store the {@link ItemBlock}s in, unused
	 */
	public static final List<ItemBlock> ITEMBLOCKS = new ArrayList<ItemBlock>();
	
	/**
	 * {@link Block}s
	 */
	public static final Block STONE_TILE = new BlockBase("stone_tile", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(2f, 3f).sound(SoundType.STONE)).init();
	public static final Block MIXED_BRICKS = new BlockBase("mixed_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(3f, 4f).sound(SoundType.STONE)).init();
	public static final Block CHARCOAL_BLOCK = new BlockBase("charcoal_block", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(2f, 1f).sound(SoundType.STONE)).init();
	public static final Block ANDESITE_BRICKS = new BlockBase("andesite_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(3f, 4f).sound(SoundType.STONE)).init();
	public static final Block DIORITE_BRICKS = new BlockBase("diorite_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(3f, 4f).sound(SoundType.STONE)).init();
	public static final Block GRANITE_BRICKS = new BlockBase("granite_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(3f, 4f).sound(SoundType.STONE)).init();
	public static final Block SNOW_BRICKS = new BlockBase("snow_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(2f, 3f).sound(SoundType.STONE)).init();
    public static final Block WHITE_CONCRETE_BRICKS = new BlockBase("white_concrete_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(2f, 3f).sound(SoundType.STONE)).init();
    public static final Block ORANGE_CONCRETE_BRICKS = new BlockBase("orange_concrete_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(2f, 3f).sound(SoundType.STONE)).init();
    public static final Block MAGENTA_CONCRETE_BRICKS = new BlockBase("magenta_concrete_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(2f, 3f).sound(SoundType.STONE)).init();
    public static final Block LIGHT_BLUE_CONCRETE_BRICKS = new BlockBase("light_blue_concrete_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(2f, 3f).sound(SoundType.STONE)).init();
    public static final Block YELLOW_CONCRETE_BRICKS = new BlockBase("yellow_concrete_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(2f, 3f).sound(SoundType.STONE)).init();
    public static final Block LIME_CONCRETE_BRICKS = new BlockBase("lime_concrete_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(2f, 3f).sound(SoundType.STONE)).init();
    public static final Block PINK_CONCRETE_BRICKS = new BlockBase("pink_concrete_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(2f, 3f).sound(SoundType.STONE)).init();
    public static final Block GRAY_CONCRETE_BRICKS = new BlockBase("gray_concrete_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(2f, 3f).sound(SoundType.STONE)).init();
    public static final Block SILVER_CONCRETE_BRICKS = new BlockBase("silver_concrete_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(2f, 3f).sound(SoundType.STONE)).init();
	public static final Block CYAN_CONCRETE_BRICKS = new BlockBase("cyan_concrete_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(2f, 3f).sound(SoundType.STONE)).init();
	public static final Block PURPLE_CONCRETE_BRICKS = new BlockBase("purple_concrete_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(2f, 3f).sound(SoundType.STONE)).init();
	public static final Block BLUE_CONCRETE_BRICKS = new BlockBase("blue_concrete_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(2f, 3f).sound(SoundType.STONE)).init();
	public static final Block BROWN_CONCRETE_BRICKS = new BlockBase("brown_concrete_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(2f, 3f).sound(SoundType.STONE)).init();
	public static final Block GREEN_CONCRETE_BRICKS = new BlockBase("green_concrete_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(2f, 3f).sound(SoundType.STONE)).init();
	public static final Block RED_CONCRETE_BRICKS = new BlockBase("red_concrete_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(2f, 3f).sound(SoundType.STONE)).init();
	public static final Block BLACK_CONCRETE_BRICKS = new BlockBase("black_concrete_bricks", new BlockBaseProperties(Material.ROCK).hardnessAndResistance(2f, 3f).sound(SoundType.STONE)).init();
	
	/**
	 * {@link ItemBlock}s (Initialized as {@link Item}s)
	 */
	public static final Item stone_tile = new ItemBase(STONE_TILE, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item mixed_bricks = new ItemBase(MIXED_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item charcoal_block = new ItemBase(CHARCOAL_BLOCK, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item andesite_bricks = new ItemBase(ANDESITE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item diorite_bricks = new ItemBase(DIORITE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item granite_bricks = new ItemBase(GRANITE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item snow_brick = new ItemBase(SNOW_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item white_concrete_bricks = new ItemBase(WHITE_CONCRETE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item orange_concrete_bricks = new ItemBase(ORANGE_CONCRETE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item magenta_concrete_bricks = new ItemBase(MAGENTA_CONCRETE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item light_blue_concrete_bricks = new ItemBase(LIGHT_BLUE_CONCRETE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item yellow_concrete_bricks = new ItemBase(YELLOW_CONCRETE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item lime_concrete_bricks = new ItemBase(LIME_CONCRETE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item pink_concrete_bricks = new ItemBase(PINK_CONCRETE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item gray_concrete_bricks = new ItemBase(GRAY_CONCRETE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item silver_concrete_bricks = new ItemBase(SILVER_CONCRETE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item cyan_concrete_bricks = new ItemBase(CYAN_CONCRETE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item purple_concrete_bricks = new ItemBase(PURPLE_CONCRETE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item blue_concrete_bricks = new ItemBase(BLUE_CONCRETE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item brown_concrete_bricks = new ItemBase(BROWN_CONCRETE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item green_concrete_bricks = new ItemBase(GREEN_CONCRETE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item red_concrete_bricks = new ItemBase(RED_CONCRETE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item black_concrete_bricks = new ItemBase(BLACK_CONCRETE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	
	/**
	 * Special Section
	 */
	public static final BlockQuickSand QUICK_SAND = new BlockQuickSand(new BlockBaseProperties(Material.SAND).hardnessAndResistance(0.5f, 0.5f).sound(SoundType.SAND));
	public static final Block BARS_WALL = new BlockBars("bars_wall", new BlockBaseProperties(Material.IRON).hardness(1.5f));
	public static final Block BARS_PLUS = new BlockBars("bars_plus", new BlockBaseProperties(Material.IRON).hardness(1.5f));
	public static final Block BARS_CHAIN = new BlockBars("bars_chain", new BlockBaseProperties(Material.IRON).hardness(1.5f));
	public static final Block BARS_STONE = new BlockBars("bars_stone", new BlockBaseProperties(Material.ROCK).hardness(1.5f));
	
	public static final Item quick_sand = new ItemBase(QUICK_SAND, new ItemBaseProperties(ItemBaseType.special).tab(Main.mssblocks)).init();
	public static final Item bars_wall = new ItemBase(BARS_WALL, new ItemBaseProperties(ItemBaseType.special).tab(Main.mssblocks)).init();
	public static final Item bars_plus = new ItemBase(BARS_PLUS, new ItemBaseProperties(ItemBaseType.special).tab(Main.mssblocks)).init();
	public static final Item bars_chain = new ItemBase(BARS_CHAIN, new ItemBaseProperties(ItemBaseType.special).tab(Main.mssblocks)).init();
	public static final Item bars_stone = new ItemBase(BARS_STONE, new ItemBaseProperties(ItemBaseType.special).tab(Main.mssblocks)).init();
	
	public static void addSpecialBlocks() {
		BLOCKS.add(QUICK_SAND);
		BLOCKS.add(BARS_WALL);
		BLOCKS.add(BARS_PLUS);
		BLOCKS.add(BARS_CHAIN);
		BLOCKS.add(BARS_STONE);
	}
}