package fr.gitancraft.xtremrp.knowledges;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface ICraftKnowledge {

    /**
     *
     * @return the result of the craft.
     */
    ItemStack getResult();

    /**
     * @return the {@link List} of item needed to have the result.
     */
    List<ItemStack> getRecipe();
}
