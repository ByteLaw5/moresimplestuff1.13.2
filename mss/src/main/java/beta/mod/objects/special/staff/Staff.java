package beta.mod.objects.special.staff;

import java.util.List;

import beta.mod.objects.ItemBaseProperties;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

/**
 * The {@code abstract} class that is a {@code super}ior to all staffs.
 */
public abstract class Staff extends Item {
	public Staff(ItemBaseProperties props) {
		super(props.getProps().defaultMaxDamage(400));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		return this.onRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		this.addDescription(stack, worldIn, tooltip, flagIn);
	}
	
	public abstract ActionResult<ItemStack> onRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn);
	public abstract void addDescription(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn);
}
