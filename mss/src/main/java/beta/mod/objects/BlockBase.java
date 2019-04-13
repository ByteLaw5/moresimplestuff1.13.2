package beta.mod.objects;

import beta.mod.Main;
import beta.mod.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class BlockBase extends Block {
	public BlockBaseProperties props;
	private String locname;
	
	public BlockBase(String name, BlockBaseProperties props) {
		super(props.getProps());
		this.props = props;
		locname = name;
	}
	
	public Block init() {
		Block block = new Block(props.getProps()).setRegistryName(new ResourceLocation(Main.modid, locname));
		BlockInit.BLOCKS.add(block);
		return block;
	}
}
