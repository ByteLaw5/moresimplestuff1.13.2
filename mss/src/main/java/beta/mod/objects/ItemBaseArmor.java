package beta.mod.objects;

import beta.mod.lists.ArmorMaterialList;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemBaseArmor extends ItemArmor {
	public ItemBaseArmor(ArmorMaterialList materialIn, EntityEquipmentSlot slot, ItemBaseProperties props) {
		super(materialIn, slot, props.getProps());
	}
}
