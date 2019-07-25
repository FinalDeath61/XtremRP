	package fr.gitancraft.xtremrp.entity.capabilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import fr.gitancraft.xtremrp.elements.quest.QuestStatus;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class PlayerQuestCapabilities implements IStorage<IQuestList> {
	//TODO pour chacune des elements de la map. F
	@Override
	public NBTBase writeNBT(Capability<IQuestList> capability, IQuestList instance,
			EnumFacing side) {
		NBTTagList nbt = new NBTTagList();
		Set<Quest> quetes = instance.getQuestList();
		for (Quest quest : quetes) {
			NBTTagCompound couette = new NBTTagCompound();
			couette.setUniqueId("id", quest.getIdQuest());
			couette.setString("state", quest.getStatus().name());
			nbt.appendTag(nbt);
		}
		return nbt;
	}

	@Override
	public void readNBT(Capability<IQuestList> capability, IQuestList instance, EnumFacing side,
			NBTBase nbt) {
		Set<Quest> listQuest = new HashSet();
		if(nbt instanceof NBTTagList) {
			NBTTagList listNbt = (NBTTagList) nbt;
			for (NBTBase nbtBase : listNbt) {
				if(nbtBase instanceof NBTTagCompound) {
					NBTTagCompound nbtelement  = (NBTTagCompound) nbtBase;
					UUID idQUest = nbtelement.getUniqueId("id");
					QuestStatus state = QuestStatus.valueOf(nbtelement.getString("state"));
					listQuest.add(new Quest(idQUest, state));
				}
			}
		}
		
	}

}
