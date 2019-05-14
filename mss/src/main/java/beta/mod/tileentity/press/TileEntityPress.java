package beta.mod.tileentity.press;

import beta.mod.init.ItemInit;
import beta.mod.tileentity.ModTET;
import beta.mod.util.GuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

@SuppressWarnings("unused")
public class TileEntityPress extends TileEntity implements ITickable, IInteractionObject {
	public ItemStackHandler handler = new ItemStackHandler(3);
	private int burnTime, currentBurnTime, cookTime, totalCookTime;
	private ITextComponent customName;
	
	private TileEntityPress(TileEntityType<?> type) {
		super(type);
	}
	
	public TileEntityPress() {
		this(ModTET.PRESS);
	}
	
	@Override
	public ITextComponent getName() {
		return this.hasCustomName() ? this.customName : new TextComponentTranslation("container.press");
	}
	
	@Override
	public boolean hasCustomName() {
		return this.customName != null;
	}
	
	public void setCustomName(ITextComponent customName) {
		this.customName = customName;
	}
	
	@Override
	public void read(NBTTagCompound compound) {
		super.read(compound);
		this.handler.deserializeNBT(compound.getCompound("inventory"));
		this.burnTime = compound.getInt("BurnTime");
		this.cookTime = compound.getInt("CookTime");
		this.totalCookTime = compound.getInt("TotalCookTime");
		this.currentBurnTime = compound.getInt("CurrentBurnTime");
		
		if(compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
			this.customName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"));
		}
	}
	
	@Override
	public NBTTagCompound write(NBTTagCompound compound) {
		super.write(compound);
		compound.setTag("inventory", this.handler.serializeNBT());
		compound.setInt("BurnTime", this.burnTime);
		compound.setInt("CookTime", this.cookTime);
		compound.setInt("TotalCookTime", this.totalCookTime);
		compound.setInt("CurrentBurnTime", this.currentBurnTime);
		if(this.hasCustomName()) {
			compound.setString("CustomName", ITextComponent.Serializer.toJson(customName));
		}
		
		return compound;
	}
	
	public boolean isPressing() {
		return this.burnTime > 0;
	}
	
	@Override
	public void tick() {
		boolean flag = this.isPressing(), flag1 = false;
		
		if(this.isPressing()) {
			this.burnTime--;
		}
		
		if(!this.world.isRemote) {
			ItemStack stack = this.handler.getStackInSlot(1);
			
			if(this.isPressing() || !stack.isEmpty() && !this.handler.getStackInSlot(0).isEmpty()) {
				if(!this.isPressing() && this.canSmelt()) {
					this.burnTime = getBurnTime(stack);
					this.currentBurnTime = this.burnTime;
					
					if(this.isPressing()) {
						flag1 = true;
						
						if(!stack.isEmpty()) {
							Item item = stack.getItem();
							stack.shrink(1);
							
							if(stack.isEmpty()) {
								ItemStack item1 = item.getContainerItem(stack);
								this.handler.setStackInSlot(1, item1);
							}
						}
					}
				}
				
				if(this.isPressing() && this.canSmelt()) {
					this.cookTime++;
					
					if(this.cookTime == this.totalCookTime) {
						this.cookTime = 0;
						this.totalCookTime = this.getCookTime(this.handler.getStackInSlot(0));
						this.smeltItem();
						flag1 = true;
					}
				} else {
					this.cookTime = 0;
				}
			} else if(!this.isPressing() && this.cookTime > 0) {
				this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
			}
			
			if(flag != this.isPressing()) {
				flag1 = true;
				BlockPress.setState(this.isPressing(), this.world, this.pos);
			}
		}
	}

	public void dropInventoryItems(World worldIn, BlockPos pos) {
		for(int i = 0; i < this.handler.getSlots(); i++) {
			ItemStack stack = this.handler.getStackInSlot(i);
			
			if(!stack.isEmpty()) {
				InventoryHelper.spawnItemStack(worldIn, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), stack);
			}
		}
	}
	
	public int getCookTime(ItemStack stack) {
		return 200;
	}
	
	private boolean canSmelt() {
		if(this.handler.getStackInSlot(0).isEmpty()) {
			return false;
		} else {
			ItemStack stack = PressRecipes.instance().getCookingResult(this.handler.getStackInSlot(0));
			
			if(stack.isEmpty()) {
				return false;
			} else {
				ItemStack stack1 = this.handler.getStackInSlot(2);
				
				if(stack1.isEmpty()) {
					return true;
				} else if(!stack1.isItemEqual(stack)) {
					return false;
				} else if(stack1.getCount() + stack.getCount() <= 64 && stack1.getCount() + stack.getCount() <= stack1.getMaxStackSize()) {
					return true;
				} else {
					return stack1.getCount() + stack.getCount() <= stack.getMaxStackSize();
				}
			}
		}
	}
	
	public void smeltItem() {
		if(this.canSmelt()) {
			ItemStack stack = this.handler.getStackInSlot(0), stack1 = PressRecipes.instance().getCookingResult(stack), stack2 = this.handler.getStackInSlot(2);
			
			if(stack2.isEmpty()) {
				this.handler.setStackInSlot(2, stack1.copy());
			} else if(stack2.getItem() == stack1.getItem()) {
				stack2.grow(stack1.getCount());
			}
			
			if(stack.getItem() == Blocks.WET_SPONGE.asItem() && !this.handler.getStackInSlot(1).isEmpty() && this.handler.getStackInSlot(1).getItem() == Items.BUCKET) {
				this.handler.setStackInSlot(1, new ItemStack(Items.WATER_BUCKET));
			}
			
			stack.shrink(1);
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public static int getBurnTime(ItemStack stack) {
		if(stack.isEmpty()) {
			return 0;
		} else {
			int burnTime = net.minecraft.tileentity.TileEntityFurnace.getBurnTimes().get(stack);
			if(burnTime >= 0) return burnTime;
			Item item = stack.getItem();
			
			if(item == ItemInit.GRAPE) {
				return 20;
			}
		}
		
		return 200;
	}
	
	public static boolean isItemFuel(ItemStack stack) {
		return getBurnTime(stack) > 0;
	}
	
	@Override
	public String getGuiID() {
		return GuiHandler.GUI.PRESS.getGuiID();
	}
	
	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerPress(playerInventory, this);
	}
	
	public int getField(int id) {
		switch(id) {
		case 0:
			return this.burnTime;
		case 1:
			return this.currentBurnTime;
		case 2:
			return this.cookTime;
		case 3:
			return this.totalCookTime;
		default:
			return 0;
		}
	}
	
	public void setField(int id, int value) {
		switch(id) {
		case 0:
			this.burnTime = value;
			break;
		case 1:
			this.currentBurnTime = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = value;
			break;
		}
	}
	
	public int getFieldCount() {
		return 4;
	}
	
	public void clear() {
		for(int i = 0; i < this.handler.getSlots(); i++) {
			this.handler.setStackInSlot(i, ItemStack.EMPTY);
		}
	}
	
	public ItemStackHandler getInventory() {
		return this.handler;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap) {
		if(!this.removed && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return LazyOptional.of(() -> (T)handler);
		} else {
			return LazyOptional.empty();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, EnumFacing side) {
		if(!this.removed && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && side != null) {
			switch(side) {
			case NORTH:
			case WEST:
			case EAST:
			case SOUTH:
			case DOWN:
			case UP:
				return LazyOptional.of(() -> (T)handler);
			default:
				return LazyOptional.empty();
			}
		}
		return LazyOptional.of(() -> (T)handler);
	}

	@Override
	public ITextComponent getCustomName() {
		return this.hasCustomName() ? this.customName : new TextComponentTranslation("Press");
	}
}
