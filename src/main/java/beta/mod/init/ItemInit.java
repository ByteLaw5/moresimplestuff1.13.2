package beta.mod.init;

import java.util.ArrayList;
import java.util.List;

import beta.mod.Main;
import beta.mod.lists.ArmorMaterialList;
import beta.mod.objects.ItemBase;
import beta.mod.objects.ItemBaseProperties;
import beta.mod.objects.ItemBaseType;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.PotionEffect;

public class ItemInit {
	/**
	 * A {@link List} to store the {@link Item}s in
	 */
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	/**
	 * {@link Item}s
	 */
	public static final Item DIAMOND_NUGGET = new ItemBase("diamond_nugget", new ItemBaseProperties().tab(Main.mssitems)).init();
	              
	/**            
	 * {@link ItemArmor}s
	 */          
	public static final Item DIVING_HELMET = new ItemBase("diving_helmet", new ItemBaseProperties(ItemBaseType.helmet).tab(Main.mssitems).setArmorMaterial(ArmorMaterialList.DIVING)).init();
	public static final Item DIVING_BOOTS = new ItemBase("diving_boots", new ItemBaseProperties(ItemBaseType.boots).tab(Main.mssitems).setArmorMaterial(ArmorMaterialList.DIVING)).init();
	              
	/**           
	 * {@link ItemFood}s
	 */           
	public static final Item SPINACH = new ItemBase("spinach", new ItemBaseProperties(ItemBaseType.food).tab(Main.mssitems), 2.0f, 4, false).init();
	public static final Item TOMATO = new ItemBase("tomato", new ItemBaseProperties(ItemBaseType.food).tab(Main.mssitems), 2.0f, 4, false).init();
	public static final Item BROCCOLIE = new ItemBase("broccolie", new ItemBaseProperties(ItemBaseType.food).tab(Main.mssitems), 2.0f, 4, false).init();
	public static final Item CUCUMBER = new ItemBase("cucumber", new ItemBaseProperties(ItemBaseType.food).tab(Main.mssitems), 2.0f, 4, false).init();
	public static final Item LETTUCE = new ItemBase("lettuce", new ItemBaseProperties(ItemBaseType.food).tab(Main.mssitems), 1.0f, 2, false).init();
	public static final Item BASIL = new ItemBase("basil", new ItemBaseProperties(ItemBaseType.food).tab(Main.mssitems), 2.0f, 2, false).init();
	public static final Item CORN = new ItemBase("corn", new ItemBaseProperties(ItemBaseType.food).tab(Main.mssitems), 2.0f, 3, false).init();
	public static final Item BAKED_CORN = new ItemBase("baked_corn", new ItemBaseProperties(ItemBaseType.food).tab(Main.mssitems), 5.0f, 8, false).init();
	public static final Item GRAPE = new ItemBase("grape", new ItemBaseProperties(ItemBaseType.food).tab(Main.mssitems), 1.0f, 4, false).init();
	public static final Item WINE = new ItemBase("wine", new ItemBaseProperties(ItemBaseType.food).tab(Main.mssitems), 1.0f, 2, false, new PotionEffect(MobEffects.NAUSEA, 180 * 2, 0, false, false)).init();
}