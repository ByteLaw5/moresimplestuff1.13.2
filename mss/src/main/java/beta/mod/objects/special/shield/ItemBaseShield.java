package beta.mod.objects.special.shield;

import beta.mod.Main;
import beta.mod.objects.ItemBaseProperties;
import net.minecraft.item.ItemShield;
import net.minecraft.util.ResourceLocation;

public class ItemBaseShield extends ItemShield {
	public ItemBaseShield(String name, ItemBaseProperties builder) {
		super(builder.getProps().defaultMaxDamage(672));
		setRegistryName(new ResourceLocation(Main.modid, name));
	}
	
	public ItemBaseShield(String name, ItemBaseProperties builder, int maxDamage) {
		super(builder.getProps().defaultMaxDamage(maxDamage));
		setRegistryName(new ResourceLocation(Main.modid, name));
	}
}
