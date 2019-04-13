package beta.mod.objects;

import net.minecraft.item.ItemFood;
import net.minecraft.potion.PotionEffect;

public class ItemBaseFood extends ItemFood {

	public ItemBaseFood(int healAmountIn, float saturation, boolean meat, Properties builder) {
		super(healAmountIn, saturation, meat, builder);
	}
	
	public ItemBaseFood(int heal, float saturation, boolean meat, Properties builder, PotionEffect effect) {
		this(heal, saturation, meat, builder);
		this.setPotionEffect(effect, 1.1f);
	}
}
