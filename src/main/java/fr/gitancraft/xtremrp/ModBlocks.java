package fr.gitancraft.xtremrp;

import fr.gitancraft.xtremrp.blocks.TestMultiBlock;
import fr.gitancraft.xtremrp.tileentities.TestMultiBlockTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

    @GameRegistry.ObjectHolder(XtremModForge.MOD_ID + ":testblock")
    public static TestMultiBlock testBlock;

    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new TestMultiBlock());

        GameRegistry.registerTileEntity(TestMultiBlockTileEntity.class, new ResourceLocation(XtremModForge.MOD_ID + "_testblock"));
    }

    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(testBlock).setRegistryName(testBlock.getRegistryName()));
    }
}
