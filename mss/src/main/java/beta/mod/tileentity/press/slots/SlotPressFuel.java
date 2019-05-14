package beta.mod.tileentity.press.slots;

import beta.mod.tileentity.press.TileEntityPress;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotPressFuel extends SlotItemHandler {
	public SlotPressFuel(ItemStackHandler handlerIn, int slotIndex, int xPos, int yPos) {
		super(handlerIn, slotIndex, xPos, yPos);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return TileEntityPress.isItemFuel(stack) || isBucket(stack);
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return isBucket(stack) ? 1 : super.getItemStackLimit(stack);
	}
	
	public static boolean isBucket(ItemStack stack) {
		return stack.getItem() == Items.BUCKET;
	}
}
