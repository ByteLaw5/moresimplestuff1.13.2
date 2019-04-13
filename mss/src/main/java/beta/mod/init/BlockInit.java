package beta.mod.init;

import java.util.ArrayList;
import java.util.List;

import beta.mod.Main;
import beta.mod.objects.BlockBase;
import beta.mod.objects.BlockBaseProperties;
import beta.mod.objects.ItemBase;
import beta.mod.objects.ItemBaseProperties;
import beta.mod.objects.ItemBaseType;
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
	
	/**
	 * {@link ItemBlock}s (Initialized as {@link Item}s)
	 */
	public static final Item stone_tile = new ItemBase(STONE_TILE, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item mixed_bricks = new ItemBase(MIXED_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item charcoal_block = new ItemBase(CHARCOAL_BLOCK, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item andesite_bricks = new ItemBase(ANDESITE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item diorite_bricks = new ItemBase(DIORITE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	public static final Item granite_bricks = new ItemBase(GRANITE_BRICKS, new ItemBaseProperties().tab(Main.mssblocks)).init();
	
	/**
	 * Special Section
	 */
	public static final BlockQuickSand QUICK_SAND = new BlockQuickSand(new BlockBaseProperties(Material.SAND).hardnessAndResistance(0.5f, 0.5f).sound(SoundType.SAND));
	
	public static final Item quick_sand = new ItemBase(QUICK_SAND, new ItemBaseProperties(ItemBaseType.special).tab(Main.mssblocks)).init();
}