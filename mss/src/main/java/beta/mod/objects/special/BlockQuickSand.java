package beta.mod.objects.special;

import java.util.List;

import beta.mod.Main;
import beta.mod.objects.BlockBaseProperties;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockQuickSand extends BlockFalling {
	
	public static final DamageSource QUICK_SAND = new CustomDamageSource("quickSand");

	public BlockQuickSand(BlockBaseProperties props) {
		super(props.getProps());
		setRegistryName(new ResourceLocation(Main.modid, "quick_sand"));
	}
	
	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation("\u00A7e" + "Be careful not"));
		tooltip.add(new TextComponentTranslation("\u00A7e" + "to step in it!"));
	}
	
	@Override
	public void onEntityCollision(IBlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		entityIn.setInWeb();
		
		if(entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityIn;
			if(!player.isCreative()) {
				entityIn.attackEntityFrom(QUICK_SAND, (int)2.0f);
			}
		}
	}
	
	@Override
	public VoxelShape getCollisionShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return VoxelShapes.empty();
	}
}
