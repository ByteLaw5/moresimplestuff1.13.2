package beta.mod.init;

import java.util.ArrayList;
import java.util.List;

import beta.mod.Main;
import beta.mod.lists.ArmorMaterialList;
import beta.mod.objects.ItemBase;
import beta.mod.objects.ItemBaseProperties;
import beta.mod.objects.ItemBaseType;
import beta.mod.objects.special.shield.ItemBaseShield;
import beta.mod.objects.special.staff.FireStaff;
import beta.mod.objects.special.staff.IceStaff;
import beta.mod.objects.special.staff.LavaStaff;
import beta.mod.objects.special.staff.PoisonStaff;
import beta.mod.objects.special.staff.WitherStaff;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSpawnEgg;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class ItemInit {
	/**
	 * A {@link List} to store the {@link Item}s in
	 */
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	/**
	 * {@link Item}s
	 */
	public static final Item DIAMOND_NUGGET = new ItemBase("diamond_nugget", new ItemBaseProperties().tab(Main.mssitems)).init();
	public static final Item POISON_AMMO = new ItemBase("poison_ammo", new ItemBaseProperties().tab(Main.mssitems)).init();
	public static final Item ICE_AMMO = new ItemBase("ice_ammo", new ItemBaseProperties().tab(Main.mssitems)).init();
	public static final Item FIRE_AMMO = new ItemBase("fire_ammo", new ItemBaseProperties().tab(Main.mssitems)).init();
	public static final Item ICE_GHAST_TEAR = new ItemBase("ice_ghast_tear", new ItemBaseProperties().tab(Main.mssitems)).init();
	public static final Item BURNED_BRICK = new ItemBase("burned_bricks", new ItemBaseProperties().tab(Main.mssitems)).init();
	              
	/**            
	 * {@link ItemArmor}s
	 */          
	public static final Item DIVING_HELMET = new ItemBase("diving_helmet", new ItemBaseProperties(ItemBaseType.helmet).tab(Main.mssitems).setArmorMaterial(ArmorMaterialList.DIVING), new PotionEffect(MobEffects.WATER_BREATHING, 20, 0, false, false)).init();
	public static final Item DIVING_BOOTS = new ItemBase("diving_boots", new ItemBaseProperties(ItemBaseType.boots).tab(Main.mssitems).setArmorMaterial(ArmorMaterialList.DIVING), new PotionEffect(MobEffects.SPEED, 20, 1, false, false)).init();
	              
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
	public static final Item WINE = new ItemBase("wine", new ItemBaseProperties(ItemBaseType.drink).tab(Main.mssitems), new PotionEffect(MobEffects.NAUSEA, 1200, 0, false, false), new PotionEffect(MobEffects.STRENGTH, 1200, 1, false, false)).init();
	
	/**
	 * {@link ItemSpawnEgg}s
	 */
	public static final Item ICE_GHAST_SPAWN = new ItemBase("ice_ghast_spawn", new ItemBaseProperties(ItemBaseType.spawnegg).setEntityType(ModET.ICE_GHAST).setPrimaryColor(0x00ffff).setSecondaryColor(0xf1f1f1).tab(Main.mssitems)).init();
	
	/**
	 * Special Section
	 */
	public static final Item FIRE_STAFF = new FireStaff(new ItemBaseProperties(ItemBaseType.special).tab(Main.mssitems).stackSize(1)).setRegistryName(location("fire_staff"));
	public static final Item WITHER_STAFF = new WitherStaff(new ItemBaseProperties(ItemBaseType.special).tab(Main.mssitems).stackSize(1)).setRegistryName(location("wither_staff"));
	public static final Item POISON_STAFF = new PoisonStaff(new ItemBaseProperties(ItemBaseType.special).tab(Main.mssitems).stackSize(1)).setRegistryName(location("poison_staff"));
	public static final Item ICE_STAFF = new IceStaff(new ItemBaseProperties(ItemBaseType.special).tab(Main.mssitems).stackSize(1)).setRegistryName(location("ice_staff"));
	public static final Item LAVA_STAFF = new LavaStaff(new ItemBaseProperties(ItemBaseType.special).tab(Main.mssitems).stackSize(1)).setRegistryName(location("lava_staff"));
	public static final Item SCUTUM_SHEIELD = new ItemBaseShield("scutum_shield", new ItemBaseProperties().tab(Main.mssitems));
	
	public static void addSpecialItems() {
		ITEMS.add(FIRE_STAFF);
		ITEMS.add(WITHER_STAFF);
		ITEMS.add(POISON_STAFF);
		ITEMS.add(ICE_STAFF);
		ITEMS.add(LAVA_STAFF);
		ITEMS.add(SCUTUM_SHEIELD);
	}
	
	/**
	 * Used in the {@link ResourceLocation} {@code setRegistryName()}. 
	 * @param name Input unlocalized name
	 * @return A {@link ResourceLocation}.
	 */
	private static ResourceLocation location(String name) {
		return new ResourceLocation(Main.modid, name);
	}
}