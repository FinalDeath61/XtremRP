package fr.gitancraft.xtremrp.capabilities;

import fr.gitancraft.xtremrp.ModCapabilities;
import fr.gitancraft.xtremrp.ModKnowledge;
import fr.gitancraft.xtremrp.knowledges.AKnowledge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Set;

public interface IKnowledges {

    Set<AKnowledge> getKnowledges();

    void addKnowledge(AKnowledge knowledge);

    void setKnowledges(Set<AKnowledge> knowledges);

    void sync();

    class Storage implements Capability.IStorage<IKnowledges> {
        @Nullable
        @Override
        public NBTBase writeNBT(Capability<IKnowledges> capability, IKnowledges instance, EnumFacing side) {
            NBTTagCompound tag = new NBTTagCompound();
            NBTTagList list = new NBTTagList();
            for (AKnowledge knowledge : instance.getKnowledges()) {
                NBTTagString k = new NBTTagString(knowledge.getName());
                list.appendTag(k);
            }
            tag.setTag("knowledges", list);
            System.err.println("write knowledge");
            return tag;
        }

        @Override
        public void readNBT(Capability<IKnowledges> capability, IKnowledges instance, EnumFacing side, NBTBase nbt) {
            Set<AKnowledge> knowledges = instance.getKnowledges();
            knowledges.clear();
            NBTTagList list = (NBTTagList) ((NBTTagCompound)nbt).getTag("knowledges");
            for (NBTBase nbtBase : list) {
                String knowledgeName = ((NBTTagString) nbtBase).getString();
                AKnowledge kn = ModKnowledge.getKnowledgeFromName(knowledgeName);
                if (Objects.nonNull(kn)) {
                    knowledges.add(kn);
                }
            }
            System.err.println("read knowledge");
        }
    }

    class Provider implements ICapabilitySerializable {

        private final IKnowledges instance;
        public Provider(EntityPlayer player) {
            instance = new Knowledges(player);
        }

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            if(ModCapabilities.CAPABILITY_KNOWLEDGES.equals(capability)) {
                return true;
            }
            return false;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            return (T) instance;
        }

        @Override
        public NBTBase serializeNBT() {
            return ModCapabilities.CAPABILITY_KNOWLEDGES.getStorage().writeNBT(ModCapabilities.CAPABILITY_KNOWLEDGES, instance, null);
        }

        @Override
        public void deserializeNBT(NBTBase nbt) {
            ModCapabilities.CAPABILITY_KNOWLEDGES.getStorage().readNBT(ModCapabilities.CAPABILITY_KNOWLEDGES, instance, null, nbt);
        }
    }
}
