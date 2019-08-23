package fr.gitancraft.xtremrp;

import fr.gitancraft.xtremrp.knowledges.AKnowledge;
import fr.gitancraft.xtremrp.knowledges.ForgeKnowledge;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.Arrays;

public class ModKnowledge {

    public static ForgeKnowledge knowledge1;
    public static ForgeKnowledge knowledge2;
    public static ForgeKnowledge knowledge3;
    public static ForgeKnowledge knowledge4;
    public static ForgeKnowledge knowledge5;
    public static ForgeKnowledge knowledge6;
    public static ForgeKnowledge knowledge7;
    public static ForgeKnowledge knowledge8;
    public static ForgeKnowledge knowledge9;
    public static ForgeKnowledge knowledge10;
    public static ForgeKnowledge knowledge11;
    public static ForgeKnowledge knowledge12;
    public static ForgeKnowledge knowledge13;
    public static ForgeKnowledge knowledge14;
    public static ForgeKnowledge knowledge15;
    public static ForgeKnowledge knowledge16;
    public static ForgeKnowledge knowledge17;
    public static ForgeKnowledge knowledge18;
    public static ForgeKnowledge knowledge19;
    public static ForgeKnowledge knowledge20;

    public static IForgeRegistry<AKnowledge> REGISTRY;

    public static void createRegistry(RegistryEvent.NewRegistry event) {
        REGISTRY = new RegistryBuilder<AKnowledge>() //
                .setName(new ResourceLocation(XtremModForge.MOD_ID, "knowledge")) //
                .setType(AKnowledge.class) //
                .setIDRange(0, 1000).create();
    }

    public static void registerKnowledges(RegistryEvent.Register<AKnowledge> event) {
        registerForgeKnowledges(event);
    }

    private static void registerForgeKnowledges(RegistryEvent.Register<AKnowledge> event) {
        knowledge1 = new ForgeKnowledge("knowledge1",  //
                itemStack(Items.APPLE, 1), //
                Arrays.asList(itemStack(Blocks.DIRT, 1), itemStack(Blocks.SAND, 1)));
        knowledge2 = new ForgeKnowledge("knowledge2", //
                itemStack(Items.DIAMOND_PICKAXE, 1), //
                Arrays.asList(itemStack(Items.DIAMOND, 3), itemStack(Items.STICK, 2)));
        knowledge3 = new ForgeKnowledge("knowledge3", //
                itemStack(Items.BED, 1), //
                Arrays.asList(itemStack(Blocks.WOOL, 3), itemStack(Blocks.PLANKS, 3)));
        knowledge4 = new ForgeKnowledge("knowledge4", //
                itemStack(Items.COMMAND_BLOCK_MINECART, 1),
                Arrays.asList(itemStack(Items.DIAMOND, 5), itemStack(Blocks.GOLD_BLOCK, 4)));
        knowledge5 = new ForgeKnowledge("knowledge5", //
                itemStack(Items.ARROW, 1), //
                Arrays.asList(itemStack(Blocks.DIRT, 1), itemStack(Blocks.SAND, 1)));
        knowledge6 = new ForgeKnowledge("knowledge6", //
                itemStack(Blocks.BEDROCK, 1), //
                Arrays.asList(itemStack(Blocks.STONE, 64), itemStack(Blocks.STONE, 64), itemStack(Blocks.STONE, 64)));
        knowledge7 = new ForgeKnowledge("knowledge7", //
                itemStack(Items.BAKED_POTATO, 1), //
                Arrays.asList(itemStack(Items.POTATO, 1), itemStack(Items.COAL, 1)));
        knowledge8 = new ForgeKnowledge("knowledge8", //
                itemStack(Blocks.BEACON, 1), //
                Arrays.asList(itemStack(Blocks.DIRT, 1), itemStack(Blocks.SAND, 1)));
        knowledge9 = new ForgeKnowledge("knowledge9", //
                itemStack(Blocks.GOLD_BLOCK, 1), //
                Arrays.asList(itemStack(Items.GOLD_INGOT, 9), itemStack(Blocks.COAL_BLOCK, 1)));
        knowledge10 = new ForgeKnowledge("knowledge10", //
                itemStack(Blocks.IRON_BLOCK, 1), //
                Arrays.asList(itemStack(Items.IRON_INGOT, 9), itemStack(Blocks.COAL_BLOCK, 1)));
        knowledge11 = new ForgeKnowledge("knowledge11", //
                itemStack(Blocks.REDSTONE_BLOCK, 1), //
                Arrays.asList(itemStack(Items.REDSTONE, 9), itemStack(Blocks.COAL_BLOCK, 1)));
        knowledge12 = new ForgeKnowledge("knowledge12", //
                itemStack(Blocks.SANDSTONE, 4), //
                Arrays.asList(itemStack(Blocks.SAND, 4), itemStack(Items.COAL, 1)));
        knowledge13 = new ForgeKnowledge("knowledge13", //
                itemStack(Blocks.BRICK_BLOCK, 1), //
                Arrays.asList(itemStack(Items.BRICK, 4), itemStack(Items.COAL, 1)));
        knowledge14 = new ForgeKnowledge("knowledge14", //
                itemStack(Items.IRON_SWORD, 1), //
                Arrays.asList(itemStack(Items.IRON_INGOT, 2), itemStack(Items.STICK, 1)));
        knowledge15 = new ForgeKnowledge("knowledge15", //
                itemStack(Items.STONE_SHOVEL, 1), //
                Arrays.asList(itemStack(Blocks.STONE, 1), itemStack(Items.STICK, 2)));
        knowledge16 = new ForgeKnowledge("knowledge16", //
                itemStack(Blocks.QUARTZ_BLOCK, 1), //
                Arrays.asList(itemStack(Items.QUARTZ, 9), itemStack(Blocks.COAL_BLOCK, 1)));
        knowledge17 = new ForgeKnowledge("knowledge17", //
                itemStack(Blocks.END_PORTAL_FRAME, 1), //
                Arrays.asList(itemStack(Blocks.DIRT, 1)));
        knowledge18 = new ForgeKnowledge("knowledge18", //
                itemStack(Items.SHIELD, 1), //
                Arrays.asList(itemStack(Blocks.PLANKS, 3), itemStack(Items.IRON_INGOT, 6)));
        knowledge19 = new ForgeKnowledge("knowledge19", //
                itemStack(Items.GOLDEN_APPLE, 1), //
                Arrays.asList(itemStack(Items.GOLD_INGOT, 8), itemStack(Items.APPLE, 1)));
        knowledge20 = new ForgeKnowledge("knowledge20", //
                itemStack(Blocks.DIRT, 1), //
                Arrays.asList(itemStack(Items.ROTTEN_FLESH, 8), itemStack(Items.COAL, 1)));

        event.getRegistry().registerAll(knowledge1, knowledge2, knowledge3, knowledge4, knowledge5, knowledge6, //
                knowledge7, knowledge8, knowledge9, knowledge10, knowledge11, knowledge12, knowledge13, knowledge14, //
                knowledge15, knowledge16, knowledge17, knowledge18, knowledge19, knowledge20);
    }

    private static ItemStack itemStack(net.minecraftforge.registries.IForgeRegistryEntry.Impl<?> item, int amount) {
        if (item instanceof Block) {
            return new ItemStack((Block) item, amount);
        } else {
            return new ItemStack((Item) item, amount);
        }
    }

    public static AKnowledge getKnowledgeFromName(String name) {
        for (AKnowledge knowledge : REGISTRY.getValuesCollection()) {
            if(knowledge.getName().equals(name)) {
                return knowledge;
            }
        }
        return null;
    }
}