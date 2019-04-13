package beta.mod.objects;

import beta.mod.Main;
import beta.mod.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class ItemBase {
	public ResourceLocation loc;
	public ItemBaseProperties props;
	public Block block;
	
	public float saturation;
	public int heal;
	public boolean isMeat;
	public PotionEffect effect;
	
	public ItemBase(String name, ItemBaseProperties props) {
		this.props = props;
		loc = new ResourceLocation(Main.modid, name);
	}
	
	public ItemBase(ResourceLocation blockLoc, ItemBaseProperties props) {
		this.props = props;
		loc = blockLoc;
	}
	
	public ItemBase(Block bl, ItemBaseProperties props) {
		block = bl;
		this.props = props;
	}
	
	public ItemBase(String name, ItemBaseProperties props, float sat, int heal, boolean meat) {
		this(name, props);
		saturation = sat;
		this.heal = heal;
		isMeat = meat;
	}
	
	public ItemBase(String name, ItemBaseProperties props, float sat, int heal, boolean meat, PotionEffect eff) {
		this(name, props, sat, heal, meat);
		effect = eff;
	}
	
	public Item init() {
		ItemBaseType type = props.getType();
		Item item = block != null ?
				new ItemBlock(block, props.getProps()).setRegistryName(block.getRegistryName()) : type == ItemBaseType.helmet ?
				new ItemBaseArmor(props.getArmorMaterial(), EntityEquipmentSlot.HEAD, props).setRegistryName(loc) : type == ItemBaseType.chestplate ?
				new ItemBaseArmor(props.getArmorMaterial(), EntityEquipmentSlot.CHEST, props).setRegistryName(loc) : type == ItemBaseType.leggings ?
				new ItemBaseArmor(props.getArmorMaterial(), EntityEquipmentSlot.LEGS, props).setRegistryName(loc) : type == ItemBaseType.boots ?
				new ItemBaseArmor(props.getArmorMaterial(), EntityEquipmentSlot.FEET, props).setRegistryName(loc) : type == ItemBaseType.food ?
				new ItemBaseFood(heal, saturation, isMeat, props.getProps(), effect).setRegistryName(loc) : 
				new Item(props.getProps()).setRegistryName(loc);
		ItemInit.ITEMS.add(item);
		return item;
	}
}
