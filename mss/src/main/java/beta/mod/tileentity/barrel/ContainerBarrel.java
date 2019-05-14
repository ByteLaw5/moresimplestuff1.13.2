package beta.mod.tileentity.barrel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerBarrel extends Container {
	private final int numRows;
	private TileEntityBarrel barrel;
	
	public ContainerBarrel(InventoryPlayer plrInv, TileEntityBarrel barrel, EntityPlayer plr) {
		this.barrel = barrel;
		this.numRows = 1;
		
		for(int i = 0; i < this.numRows; i++) {
			for(int j = 0; j < 9; j++) {
				this.addSlot(new SlotItemHandler(barrel.getInventory(), j + i * 9, 8 + j * 18, 18 + i * 18));
			}
		}
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++) {
				this.addSlot(new Slot(plrInv, j + i * 9 + 9, 8 + j * 18, 48 + i * 18));
			}
		}
		
		for(int i = 0; i < 9; i++) {
			this.addSlot(new Slot(plrInv, i, 8 + i * 18, 106));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return playerIn.getDistanceSq((double)this.barrel.getPos().getX() + 0.5d, (double)this.barrel.getPos().getY() + 0.5d, (double)this.barrel.getPos().getZ() + 0.5d) <= 64.0d;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if(slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			if(index < this.numRows * 9) {
				if(!this.mergeItemStack(stack1, this.numRows * 9, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if(!this.mergeItemStack(stack1, 0, this.numRows * 9, false)) {
				return ItemStack.EMPTY;
			}
			
			if(stack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}
		
		return stack;
	}
	
	public TileEntityBarrel getBarrelInventory() {
		return this.barrel;
	}
}
