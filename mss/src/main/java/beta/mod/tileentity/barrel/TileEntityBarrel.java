package beta.mod.tileentity.barrel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import beta.mod.tileentity.ModTET;
import beta.mod.util.GuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityBarrel extends TileEntity implements IInteractionObject {
    public ItemStackHandler handler = new ItemStackHandler(9);
    private ITextComponent customName;

    private TileEntityBarrel(TileEntityType<?> type) {
        super(type);
    }

    public TileEntityBarrel() {
        this(ModTET.BARREL);
    }
    
    @Override
    public void read(NBTTagCompound compound) {
    	super.read(compound);
    	//this.handler = new ItemStackHandler(this.getSizeInventory());
    	this.handler.deserializeNBT(compound.getCompound("inventory"));
    	if(compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
    		this.customName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"));
    	}
    }
    
    @Override
    public NBTTagCompound write(NBTTagCompound compound) {
    	super.write(compound);
    	compound.setTag("inventory", this.handler.serializeNBT());
    	if(this.hasCustomName()) {
    		compound.setString("CustomName", ITextComponent.Serializer.toJson(customName));
    	}
    	return compound;
    }

    @Override
    public TileEntity getTileEntity() {
        return super.getTileEntity();
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerBarrel(playerInventory, this, playerIn);
    }

    @Override //Useless since we got the GuiHandler
    public String getGuiID() {
        return GuiHandler.GUI.BARREL.getGuiID();
    }

    @Override
    public ITextComponent getName() {
        return this.hasCustomName() ? this.customName : new TextComponentTranslation("container.barrel");
    }

    @Override
    public boolean hasCustomName() {
        return this.customName != null;
    }

    @Nullable
    @Override
    public ITextComponent getCustomName() {
        return this.hasCustomName() ? this.customName : new TextComponentTranslation("container.barrel");
    }
    
    public void dropInventoryItems(World worldIn, BlockPos pos) {
    	this.dropInventoryItems(worldIn, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
    }
    
    public void dropInventoryItems(World worldIn, double x, double y, double z) {
		for(int i = 0; i < this.handler.getSlots(); i++) {
			ItemStack stack = this.handler.getStackInSlot(i);
			if(!stack.isEmpty()) {
				InventoryHelper.spawnItemStack(worldIn, x, y, z, stack);
			}
		}
	}

    @SuppressWarnings("unchecked")
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if(!this.removed && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return LazyOptional.of(() -> (T)handler);
        } else {
            return LazyOptional.empty();
        }
    }

    public ItemStackHandler getInventory() {
        return this.handler;
    }
}