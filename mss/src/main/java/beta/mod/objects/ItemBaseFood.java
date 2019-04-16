package beta.mod.objects;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBaseFood extends ItemFood {

	@Nullable
	private PotionEffect effect1, effect2;
	
	public ItemBaseFood(int healAmountIn, float saturation, boolean meat, Properties builder) {
		super(healAmountIn, saturation, meat, builder);
	}
	
	public ItemBaseFood(int heal, float saturation, boolean meat, Properties builder, PotionEffect effect) {
		this(heal, saturation, meat, builder);
		this.effect1 = effect;
	}
	
	public ItemBaseFood(int heal, float saturation, boolean meat, Properties builder, PotionEffect effect, PotionEffect effect2) {
		this(heal, saturation, meat, builder, effect);
		this.effect2 = effect2;
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if(effect1 != null) {
			player.addPotionEffect(new PotionEffect(effect1.getPotion(), effect1.getDuration(), effect1.getAmplifier(), effect1.isAmbient(), effect1.doesShowParticles()));
			if(effect2 != null) {
				player.addPotionEffect(new PotionEffect(effect2.getPotion(), effect2.getDuration(), effect2.getAmplifier(), effect2.isAmbient(), effect2.doesShowParticles()));
			}
		}
	}
}
