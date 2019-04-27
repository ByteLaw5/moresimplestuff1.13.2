package beta.mod.objects;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public class BlockBaseStairs extends BlockStairs {
	public BlockBaseStairs(IBlockState modelState, BlockBaseProperties props) {
		super(modelState, props.getProps());
	}
}	
