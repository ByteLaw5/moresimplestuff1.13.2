package beta.mod.objects;

import beta.mod.Main;
import beta.mod.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

public class BlockBase {
	public BlockBaseProperties props;
	private String locname;
	private IBlockState state;
	
	public BlockBase(String name, BlockBaseProperties props) {
		this.props = props;
		locname = name;
	}
	
	public BlockBase(String name, IBlockState stairsModelState, BlockBaseProperties props) {
		this(name, props);
		state = stairsModelState;
	}
	
	public Block init() {
		BlockBaseType type = props.getType();
		Block block = type == BlockBaseType.block ?
				new Block(props.getProps()).setRegistryName(new ResourceLocation(Main.modid, locname)) : type == BlockBaseType.slab ?
				new BlockSlab(props.getProps()).setRegistryName(new ResourceLocation(Main.modid, locname)) :
				new BlockBaseStairs(state, props).setRegistryName(new ResourceLocation(Main.modid, locname));
		BlockInit.BLOCKS.add(block);
		return block;
	}
}
