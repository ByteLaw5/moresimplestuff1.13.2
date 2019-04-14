package beta.mod.objects.special.staff;

import java.util.List;

import beta.mod.objects.ItemBaseProperties;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class WitherStaff extends Staff {
	public WitherStaff(ItemBaseProperties props) {
		super(props);
	}

	@Override
	public ActionResult<ItemStack> onRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack item = playerIn.getHeldItem(handIn);
		Vec3d aim = playerIn.getLookVec();
		EntityWitherSkull skull = new EntityWitherSkull(worldIn, playerIn, 1, 1, 1);
		
		playerIn.getCooldownTracker().setCooldown(this, 60);
		
		skull.setPosition(playerIn.posX + aim.x, playerIn.posY + aim.y + 2d, playerIn.posZ + aim.z);
		skull.accelerationX = aim.x * 0.1; skull.accelerationY = aim.y * 0.1; skull.accelerationZ = aim.z * 0.1;
		
		worldIn.spawnEntity(skull);
		
		item.damageItem(1, playerIn);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
	}

	@Override
	public void addDescription(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation("\u00A7e" + "This tool is dangerous,"));
		tooltip.add(new TextComponentTranslation("\u00A7e" + "Use it wisely!"));
	}
}
