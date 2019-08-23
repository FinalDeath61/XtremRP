package fr.gitancraft.xtremrp.knowledges;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;

import java.util.List;

public class ForgeKnowledge extends AKnowledge implements ICraftKnowledge {

    private ItemStack result;
    private List<ItemStack> recipe;

    @Override
    public ItemStack getResult() {
        return result;
    }

    @Override
    public List<ItemStack> getRecipe() {
        return recipe;
    }

    public ForgeKnowledge(String name, ItemStack recipeResult, List<ItemStack> recipe) {
        super(name);
        this.result = recipeResult;
        this.recipe = recipe;
    }
}
