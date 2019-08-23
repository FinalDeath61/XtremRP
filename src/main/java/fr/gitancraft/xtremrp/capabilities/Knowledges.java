package fr.gitancraft.xtremrp.capabilities;

import fr.gitancraft.xtremrp.ModCapabilities;
import fr.gitancraft.xtremrp.ModKnowledge;
import fr.gitancraft.xtremrp.knowledges.AKnowledge;
import fr.gitancraft.xtremrp.network.KnowledgeSyncMessage;
import fr.gitancraft.xtremrp.network.ModPacketHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Knowledges implements IKnowledges {

    public static void register() {
        CapabilityManager.INSTANCE.register(IKnowledges.class, new IKnowledges.Storage(), Knowledges.class::newInstance);
    }

    private Set<AKnowledge> knowledges = new HashSet<>();
    private EntityPlayer player;

    public Knowledges(EntityPlayer player) {
        this.player = player;
    }

    @Override
    public Set<AKnowledge> getKnowledges() {
        return knowledges;
    }

    @Override
    public void addKnowledge(AKnowledge knowledge) {
        boolean needSync = !knowledges.contains(knowledge);
        knowledges.add(knowledge);
        if (needSync) {
            sync();
        }
    }

    @Override
    public void setKnowledges(Set<AKnowledge> knowledges) {
        boolean needSync = !this.knowledges.equals(knowledges);
        this.knowledges = knowledges;
        if (needSync) {
            sync();
        }
    }

    @Override
    public void sync() {
        System.err.println("sync with packet");
        KnowledgeSyncMessage packet = new KnowledgeSyncMessage(knowledges);

        if (!player.world.isRemote) {
            EntityPlayerMP playerMP = (EntityPlayerMP) player;
            ModPacketHandler.INSTANCE.sendTo(packet, playerMP);
        } else {
            ModPacketHandler.INSTANCE.sendToServer(packet);
        }
    }
}
