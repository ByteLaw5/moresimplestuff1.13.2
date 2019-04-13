package beta.mod.objects.special.staff;

import java.util.List;

import beta.mod.objects.ItemBaseProperties;
import beta.mod.objects.entity.EntityPoisonProjectile;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class PoisonStaff extends Staff {
	public PoisonStaff(ItemBaseProperties props) {
		super(props);
	}

	@Override
	public ActionResult<ItemStack> onRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack item = playerIn.getHeldItem(handIn);
		Vec3d aim = playerIn.getLookVec();
		EntityPoisonProjectile poison = new EntityPoisonProjectile(worldIn, playerIn, aim.x * 0.1, aim.y * 0.1, aim.z * 0.1);
		
		playerIn.getCooldownTracker().setCooldown(this, 50);
		
		poison.setPosition(playerIn.posX + aim.x, playerIn.posY + aim.y * -3.1d, playerIn.posZ + aim.z);
		poison.accelerationX = aim.x * 0.1; poison.accelerationY = aim.y * 0.1; poison.accelerationZ = aim.y * 0.1;
		
		worldIn.spawnEntity(poison);
		
		item.damageItem(1, playerIn);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
	}

	@Override
	public void addDescription(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		
	}
}
