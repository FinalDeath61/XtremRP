package fr.gitancraft.xtremrp;

import fr.gitancraft.xtremrp.capabilities.IKnowledges;
import fr.gitancraft.xtremrp.capabilities.IProcess;
import fr.gitancraft.xtremrp.capabilities.Knowledges;
import fr.gitancraft.xtremrp.capabilities.Process;
import fr.gitancraft.xtremrp.knowledges.AKnowledge;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

public class ModCapabilities {

    @CapabilityInject(IProcess.class)
    public static final Capability<IProcess> CAPABILITY_PROCESS = null;

    @CapabilityInject(IKnowledges.class)
    public static final Capability<IKnowledges> CAPABILITY_KNOWLEDGES = null;

    public static void registerCapabilities() {
//        CapabilityManager.INSTANCE.register(IProcess.class, new CapabilityProcess(), Process.class::newInstance);
        Knowledges.register();
    }

    public static class CapabilityProcess implements Capability.IStorage<IProcess> {
        @Nullable
        @Override
        public NBTBase writeNBT(Capability<IProcess> capability, IProcess instance, EnumFacing side) {
            return null;
        }

        @Override
        public void readNBT(Capability<IProcess> capability, IProcess instance, EnumFacing side, NBTBase nbt) {
        }
    }

}
