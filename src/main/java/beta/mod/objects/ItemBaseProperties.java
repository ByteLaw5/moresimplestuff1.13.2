package beta.mod.objects;

import beta.mod.lists.ArmorMaterialList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBaseProperties {
	private Item.Properties props = new Item.Properties();
	private ItemBaseType itype;
	
	private ArmorMaterialList armat;
	
	public ItemBaseProperties(ItemBaseType type) {
		itype = type;
	}
	
	public ItemBaseProperties() {
		this(ItemBaseType.item);
	}
	
	public ItemBaseType getType() {
		return itype;
	}
	
	public Item.Properties getProps() {
		return props;
	}
	
	public ItemBaseProperties tab(ItemGroup ttab) {
		props.group(ttab);
		return this;
	}
	
	public ItemBaseProperties stackSize(int count) {
		props.maxStackSize(count);
		return this;
	}
	
	public ItemBaseProperties setArmorMaterial(ArmorMaterialList material) {
		armat = material;
		return this;
	}
	
	public ArmorMaterialList getArmorMaterial() {
		return armat;
	}
}
