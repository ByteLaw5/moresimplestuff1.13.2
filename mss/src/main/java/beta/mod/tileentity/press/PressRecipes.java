package beta.mod.tileentity.press;

import java.util.Map;

import com.google.common.collect.Maps;

import beta.mod.Main;
import beta.mod.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PressRecipes {
	private static final PressRecipes COOKING_BASE = new PressRecipes();
	private final Map<ItemStack, ItemStack> cookingList = Maps.newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.newHashMap();
	
	public static PressRecipes instance() {
		return COOKING_BASE;
	}
	
	private PressRecipes() {
		this.addCookingRecipe(new ItemStack(Items.GLASS_BOTTLE), new ItemStack(ItemInit.WINE), 0.1f);
	}
	
	public void addCookingRecipeForBlock(Block input, ItemStack stack, float experience)
    {
        this.addCooking(input.asItem(), stack, experience);
    }
	
	public void addCooking(Item input, ItemStack stack, float experience)
    {
        this.addCookingRecipe(new ItemStack(input, 1), stack, experience);
    }
	
	public void addCookingRecipe(ItemStack input, ItemStack stack, float experience)
    {
        if (getCookingResult(input) != ItemStack.EMPTY) 
        { 
        	Main.logger.info("Ignored cooking recipe with conflicting input: {} = {}", input, stack); return; 
        }
        this.cookingList.put(input, stack);
        this.experienceList.put(stack, Float.valueOf(experience));
    }
	
	public ItemStack getCookingResult(ItemStack stack)
    {
        for (Map.Entry<ItemStack, ItemStack> entry : this.cookingList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return entry.getValue();
            }
        }

        return ItemStack.EMPTY;
    }
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem();
    }

    public Map<ItemStack, ItemStack> getCookingList()
    {
        return this.cookingList;
    }

    public float getCookingExperience(ItemStack stack)
    {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;
        for (Map.Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return ((Float)entry.getValue()).floatValue();
            }
        }
        return 0.0F;
    }
}
