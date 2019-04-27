package beta.mod.objects;

import javax.annotation.Nullable;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemDrink extends Item {
	@Nullable
	private PotionEffect effect_1, effect_2;
	
	public ItemDrink(ItemBaseProperties props, @Nullable PotionEffect effect1) {
		super(props.getProps());
		effect_1 = effect1;
	}
	
	public ItemDrink(ItemBaseProperties props, @Nullable PotionEffect effect1, @Nullable PotionEffect effect2) {
		this(props, effect1);
		effect_2 = effect2;
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityLiving;
			worldIn.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 0.5f, worldIn.rand.nextFloat() * 0.1f + 0.9f);
			this.onDrinkDrank(worldIn, player);
			player.addStat(StatList.ITEM_USED.get(this));
			if(player instanceof EntityPlayerMP) {
				CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)player, stack);
			}
		}
		
		stack.shrink(1);
		return stack;
	}
	
	protected void onDrinkDrank(World worldIn, EntityPlayer player) {
		if(!worldIn.isRemote && this.effect_1 != null) {
			player.addPotionEffect(new PotionEffect(this.effect_1.getPotion(), this.effect_1.getDuration(), this.effect_1.getAmplifier(), this.effect_1.isAmbient(), this.effect_1.doesShowParticles()));
			if(this.effect_2 != null) {
				player.addPotionEffect(new PotionEffect(this.effect_2.getPotion(), this.effect_2.getDuration(), this.effect_2.getAmplifier(), this.effect_2.isAmbient(), this.effect_2.doesShowParticles()));
			}
		}
	}
	
	@Override
	public int getUseDuration(ItemStack stack) {
		return 32;
	}
	
	@Override
	public EnumAction getUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		playerIn.setActiveHand(handIn);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}
}
