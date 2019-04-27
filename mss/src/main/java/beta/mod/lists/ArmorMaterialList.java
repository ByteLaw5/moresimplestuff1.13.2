package beta.mod.lists;

import beta.mod.Main;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public enum ArmorMaterialList implements IArmorMaterial {
	DIVING("diving", 13, new int[] {2, 5, 5, 2}, 10, null, "item.armor.equip_gold", 0.0f);
	
	private static final int[] max_damage_array = new int[] {6, 7, 5, 4};
	private String name, sound;
	private int durability, enchantability;
	private int[] damageReduction;
	private Item repairItem;
	private float toughness;
	
	ArmorMaterialList(String name, int durability, int[] reduction, int enchantability, Item repair, String equip, float toughness) {
		this.name = name;
		this.durability = durability;
		damageReduction = reduction;
		this.enchantability = enchantability;
		repairItem = repair;
		this.toughness = toughness;
		sound = equip;
	}

	@Override
	public int getDurability(EntityEquipmentSlot slotIn) {
		return max_damage_array[slotIn.getIndex()] * this.durability;
	}

	@Override
	public int getDamageReductionAmount(EntityEquipmentSlot slotIn) {
		return this.damageReduction[slotIn.getIndex()];
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public SoundEvent getSoundEvent() {
		return new SoundEvent(new ResourceLocation(this.sound));
	}

	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(this.repairItem);
	}

	@Override
	public String getName() {
		return Main.modid + ":" + this.name;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}
}
