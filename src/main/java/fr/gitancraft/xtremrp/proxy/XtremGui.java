package fr.gitancraft.xtremrp.proxy;

import fr.gitancraft.xtremrp.blocks.container.ForgeContainer;
import fr.gitancraft.xtremrp.blocks.gui.GuiForge;
import fr.gitancraft.xtremrp.gui.pnj.GuiBasicDialog;
import fr.gitancraft.xtremrp.tileentities.TestMultiBlockTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class XtremGui implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TestMultiBlockTileEntity) {
            return new ForgeContainer(player.inventory, (TestMultiBlockTileEntity) te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TestMultiBlockTileEntity) {
            TestMultiBlockTileEntity containerTileEntity = (TestMultiBlockTileEntity) te;
            return new GuiForge(containerTileEntity, new ForgeContainer(player.inventory, containerTileEntity));
        }
        if(ID == 2 ) {
        	return new GuiBasicDialog(player);
        }
        return null;
    }
}
