package beta.mod.objects.special.staff;

import java.util.List;

import beta.mod.objects.ItemBaseProperties;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class FireStaff extends Staff {

	public FireStaff(ItemBaseProperties props) {
		super(props);
	}

	@Override
	public ActionResult<ItemStack> onRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack item = playerIn.getHeldItem(handIn);
		Vec3d aim = playerIn.getLookVec();
		EntityLargeFireball fireball = new EntityLargeFireball(worldIn, playerIn, 1, 1, 1);
		
		playerIn.getCooldownTracker().setCooldown(this, 45);
		
		fireball.setPosition(playerIn.posX + aim.x, playerIn.posY + aim.y * -3.1d, playerIn.posZ + aim.y);
		fireball.accelerationX = aim.x * 0.1; fireball.accelerationY = aim.y * 0.1; fireball.accelerationZ = aim.z * 0.1;
		fireball.explosionPower = 4;
		worldIn.spawnEntity(fireball);
		
		item.damageItem(1, playerIn);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
	}

	@Override
	public void addDescription(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation("\u00A7e" + "This is currently a creative"));
		tooltip.add(new TextComponentTranslation("\u00A7e" + "only tool, I don't have plans"));
		tooltip.add(new TextComponentTranslation("\u00A7e" + "to implement this into survival"));
		tooltip.add(new TextComponentTranslation("\u00A7e" + "but let me know your thoughts!"));
	}
}
