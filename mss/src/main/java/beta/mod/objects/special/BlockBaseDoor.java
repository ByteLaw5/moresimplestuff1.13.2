package beta.mod.objects.special;

import beta.mod.objects.BlockBaseProperties;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockBaseDoor extends BlockDoor {
	public BlockBaseDoor(BlockBaseProperties builder) {
		super(builder.getProps());
	}

	@Override
	public IItemProvider getItemDropped(IBlockState state, World worldIn, BlockPos pos, int fortune) {
		return this.asItem();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, IBlockReader world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(this);
	}
}