package beta.mod.tileentity.press;

import beta.mod.Main;
import beta.mod.init.BlockInit;
import beta.mod.objects.BlockBaseProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkHooks;

@SuppressWarnings("deprecation")
public class BlockPress extends Block {
	public static final DirectionProperty FACING = BlockHorizontal.HORIZONTAL_FACING;
	@SuppressWarnings("unused")
	private final boolean isPressing;
	private static boolean keepInventory;
	public static final VoxelShape PRESS = VoxelShapes.create(new AxisAlignedBB(0D, 0D, 0D, 1D, 1.3125D, 1D));
	
	public BlockPress(String name, BlockBaseProperties props, boolean isPressing) {
		super(props.getProps());
		this.isPressing = isPressing;
		setRegistryName(new ResourceLocation(Main.modid, name));
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, EnumFacing.NORTH));
		
		BlockInit.BLOCKS.add(this);
	}
	
	@Override
	public VoxelShape getCollisionShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return PRESS;
	}
	
	@Override
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			TileEntityPress te = (TileEntityPress)worldIn.getTileEntity(pos);
			
			if(te != null) {
				if(player instanceof EntityPlayerMP && !(player instanceof FakePlayer)) {
					EntityPlayerMP playermp = (EntityPlayerMP)player;
					
					NetworkHooks.openGui(playermp, te, buf -> buf.writeBlockPos(pos));
				}
			}
			
			return true;
		}
		return true;
	}
	
	@Override
	public void onReplaced(IBlockState state, World worldIn, BlockPos pos, IBlockState newState, boolean isMoving) {
		TileEntity te = worldIn.getTileEntity(pos);
		
		if(te != null && te instanceof TileEntityPress && !keepInventory) {
			TileEntityPress tep = (TileEntityPress)te;
			tep.dropInventoryItems(worldIn, tep.getPos());
		}
		
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean hasCustomBreakingProgress(IBlockState state) {
		return true;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
		EnumFacing facing = context.getPlacementHorizontalFacing().getOpposite();
		
		return this.getDefaultState().with(FACING, facing);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		TileEntity te = worldIn.getTileEntity(pos);
		
		if(te != null && te instanceof TileEntityPress) {
			TileEntityPress tep = (TileEntityPress)te;
			if(stack.hasDisplayName()) {
				tep.setCustomName(stack.getDisplayName());
			}
		}
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}
	
	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}
	
	@Override
	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
		return BlockInit.press_off;
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public IBlockState rotate(IBlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}
	
	@Override
	public IBlockState mirror(IBlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, IBlockState> builder) {
		builder.add(FACING);
	}
	
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	public static void setState(boolean active, World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		TileEntity te = world.getTileEntity(pos);
		keepInventory = true;
		
		if(active) {
			world.setBlockState(pos, BlockInit.PRESS_ON.getDefaultState().with(FACING, state.get(FACING)), 3);
		} else {
			world.setBlockState(pos, BlockInit.PRESS_OFF.getDefaultState().with(FACING, state.get(FACING)), 3);
		}
		
		keepInventory = false;
		
		if(te != null) {
			te.validate();
			world.setTileEntity(pos, te);
		}
	}
}
