package beta.mod.tileentity.barrel;

import javax.annotation.Nullable;

import beta.mod.Main;
import beta.mod.init.BlockInit;
import beta.mod.objects.BlockBaseProperties;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockBarrel extends Block {
	public static final VoxelShape BARREL = VoxelShapes.create(new AxisAlignedBB(0.0625d, 0.0d, 0.0625d, 0.9375d, 1.0d, 0.9375d));
	
	public BlockBarrel(String name, BlockBaseProperties props) {
		super(props.getProps());
		setRegistryName(new ResourceLocation(Main.modid, name));
		
		BlockInit.BLOCKS.add(this);
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity(IBlockState state, IBlockReader world) {
		return new TileEntityBarrel();
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		} else {
			TileEntityBarrel te = (TileEntityBarrel)worldIn.getTileEntity(pos);
			
			if(te != null) {
				if(player instanceof EntityPlayerMP && !(player instanceof FakePlayer)) {
					EntityPlayerMP playermp = (EntityPlayerMP)player;
					
					NetworkHooks.openGui(playermp, te, buf -> buf.writeBlockPos(pos));
				}
			}
			
			return true;
		}
	}
	
	@Override @SuppressWarnings("deprecation")
	public void onReplaced(IBlockState state, World worldIn, BlockPos pos, IBlockState newState, boolean isMoving) {
		TileEntity te = worldIn.getTileEntity(pos);
		
		if(te != null && te instanceof TileEntityBarrel) {
			TileEntityBarrel teb = (TileEntityBarrel)te;
			teb.dropInventoryItems(worldIn, teb.getPos());
		}
		
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}

	@Override
	public VoxelShape getCollisionShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return BARREL;
	}
	
	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
}
