package beta.mod.objects.special.staff;

import java.util.List;

import beta.mod.init.ItemInit;
import beta.mod.objects.ItemBaseProperties;
import beta.mod.objects.entity.EntityIceProjectile;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class IceStaff extends Staff {
	public IceStaff(ItemBaseProperties props) {
		super(props);
	}

	@Override
	public ActionResult<ItemStack> onRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack item = playerIn.getHeldItem(handIn);
		ItemStack ammo = playerIn.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);
		ItemStack neededAmmo = new ItemStack(ItemInit.ICE_AMMO);
		if(ammo == ItemStack.EMPTY) {
			playerIn.sendMessage(new TextComponentTranslation("\u00A76" + "You need to have ammo in your offhand!"));
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, item);
		} else if(ItemStack.areItemsEqual(ammo, neededAmmo)) {
			ammo.shrink(1);
			Vec3d aim = playerIn.getLookVec();
			EntityIceProjectile ice = new EntityIceProjectile(worldIn, playerIn, aim.x * 0.155, aim.y * 0.155, aim.z * 0.155);
			
			playerIn.getCooldownTracker().setCooldown(this, 50);
			
			ice.setPosition(playerIn.posX + aim.x, playerIn.posY + aim.y * -3.1d, playerIn.posZ + aim.y);
			ice.accelerationX = aim.x * 0.155; ice.accelerationY = aim.y * 0.155; ice.accelerationZ = aim.z * 0.155;
			
			worldIn.spawnEntity(ice);
			
			item.damageItem(1, playerIn);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, item);
	}

	@Override
	public void addDescription(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation("\u00A7e" + "Freeze your enemies"));
		tooltip.add(new TextComponentTranslation("\u00A7e" + "with this tool!"));
	}
}
