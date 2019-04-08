package fr.gitancraft.xtremrp.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.vecmath.Point3i;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AMultiBlockMaster extends Block implements ITileEntityProvider {

    public static final PropertyBool RENDERED = PropertyBool.create("rendered");
    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    private String[][] multiblockPattern;
    private Point3i masterPosInPattern;
    private Map<Character, Class> patternMap;

    protected AMultiBlockMaster(Material material, String[][] multiblockPattern, Class<? extends AMultiBlockMaster> masterclass, Map<Character, Class> patternMap) {
        super(material);
        this.multiblockPattern = multiblockPattern;
        this.patternMap = patternMap;
        this.masterPosInPattern = getMasterPosInPattern(masterclass);
        System.out.println(masterPosInPattern);
    }

    private Point3i getMasterPosInPattern(Class<? extends AMultiBlockMaster> masterclass) {
        for (int y = 0; y < multiblockPattern.length; y++) {
            for (int x = 0; x < multiblockPattern[y].length; x++) {
                for (int z = 0; z < multiblockPattern[y][x].length(); z++) {
                    if(patternMap.get(multiblockPattern[y][x].charAt(z)) == masterclass) {
                        return new Point3i(multiblockPattern[y].length - 1 -x, y ,z);
                    }
                }
            }
        }
        return new Point3i();
    }

    protected boolean isMultiBlockFormed(World world, BlockPos pos) {
        int xWorld = 0, yWorld, zWorld = 0;
        IBlockState state = world.getBlockState(pos);
        EnumFacing facing = state.getValue(FACING);
        for (int y = 0; y < multiblockPattern.length; y++) {
            for (int x = 0; x < multiblockPattern[y].length; x++) {
                for (int z = 0; z < multiblockPattern[y][x].length(); z++) {
                    switch (facing) {
                        case WEST:
                            xWorld = masterPosInPattern.x + (multiblockPattern[y].length - 1 - x);
                            zWorld = masterPosInPattern.z - z;
                            break;
                        case EAST:
                            xWorld = masterPosInPattern.x - (multiblockPattern[y].length - 1 - x);
                            zWorld = masterPosInPattern.z - z;
                            break;
                        case NORTH:
                            xWorld = masterPosInPattern.z - (multiblockPattern[y][x].length() - 1 - z);
                            zWorld = masterPosInPattern.x + (multiblockPattern[y].length - 1 - x);
                            break;
                        case SOUTH:
                            xWorld = masterPosInPattern.z - (multiblockPattern[y][x].length() - 1 - z);
                            zWorld = masterPosInPattern.x - (multiblockPattern[y].length - 1 - x);
                            break;
                    }
                    yWorld = y - masterPosInPattern.y;

                    Class expectedBlock = patternMap.get(multiblockPattern[y][x].charAt(z));
                    Class realBlock = world.getBlockState(pos.add(xWorld, yWorld, zWorld)).getBlock().getClass();
                    if (realBlock != expectedBlock && expectedBlock != null) {
                        return false;
                    }
                    System.out.printf("pos : %s %s %s / expected : %s / real : %s  #   ", xWorld, yWorld, zWorld, expectedBlock, realBlock.getSimpleName());
                }
                System.out.printf("%n");
            }
        }
        return true;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 3);
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    private void renderMultiBlock(World world, BlockPos pos, boolean render) {
        int x = 0, y = 0, z = 0;
        IBlockState state = world.getBlockState(pos);
        EnumFacing facing = state.getValue(FACING);

        for (int i = 0; i < multiblockPattern.length; i++) {
            for (int j = 0; i < multiblockPattern[i].length; j++) {
                for (int k = 0; k < multiblockPattern[j].length; k++) {
                    if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH) {
                        x = masterPosInPattern.x - j;
                        y = masterPosInPattern.y - i;
                        z = masterPosInPattern.z - k;
                    }
                    if (facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
                        x = masterPosInPattern.z - k;
                        y = masterPosInPattern.y - i;
                        z = masterPosInPattern.x - j;
                    }
                    Block block = world.getBlockState(pos.add(x, y, z)).getBlock();
                    if ((block.getClass().isAssignableFrom(AMultiBlockSlave.class) || block.getClass().isAssignableFrom(AMultiBlockMaster.class))) {
                        world.setBlockState(pos.add(x, y, z), world.getBlockState(pos.add(x, y, z)).withProperty(RENDERED, render), 3);
                    }

                }
            }
        }
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i |= (state.getValue(FACING)).getHorizontalIndex();
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, RENDERED);
    }
}
