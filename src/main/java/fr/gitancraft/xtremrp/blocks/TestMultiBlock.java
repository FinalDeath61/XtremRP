package fr.gitancraft.xtremrp.blocks;

import com.google.common.collect.ImmutableMap;
import fr.gitancraft.xtremrp.XtremModForge;
import fr.gitancraft.xtremrp.blocks.gui.GuiForge;
import fr.gitancraft.xtremrp.tileentities.TestMultiBlockTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.vecmath.Point3i;
import java.util.Map;

public class TestMultiBlock extends AMultiBlockMaster {

    private static final String[][] multiblockPattern = new String[][]{
            {
                    "AMA",
            },
            {
                    " A ",
            },
            {
                    " A ",
            },
            {
                    " A ",
            },
            {
                    " A ",
            },
            {
                    " A ",
            }
    };

    private static final Map<Character, Class> patternMap = ImmutableMap.<Character, Class>builder()
            .put('A', Blocks.STONE.getClass())
            .put('M', TestMultiBlock.class)
            .build();

    public TestMultiBlock() {
        super(Material.ROCK, multiblockPattern, TestMultiBlock.class, patternMap);
        setUnlocalizedName(XtremModForge.MOD_ID + ".testblock");
        setRegistryName("testblock");

        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH)
                .withProperty(RENDERED, false));
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TestMultiBlockTileEntity();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        // playerIn.sendMessage(new TextComponentString("MultiBlock formé ? " + isMultiBlockFormed(worldIn, pos)));
        playerIn.openGui(XtremModForge.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
