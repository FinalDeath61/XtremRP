package fr.gitancraft.xtremrp.entity.capabilities;

import fr.gitancraft.xtremrp.ModCapabilities;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ListQuestProvider implements ICapabilitySerializable<NBTBase>{
 
	IQuestList instantce = ModCapabilities.CAPABILITY_QUESTLIST.getDefaultInstance();
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
	
		  return capability == ModCapabilities.CAPABILITY_QUESTLIST;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return hasCapability(capability, facing) ? ModCapabilities.CAPABILITY_QUESTLIST.<T> cast(instantce):null;
	}

	@Override
	public NBTBase serializeNBT() {
		return ModCapabilities.CAPABILITY_QUESTLIST.getStorage().writeNBT(ModCapabilities.CAPABILITY_QUESTLIST, instantce, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		ModCapabilities.CAPABILITY_QUESTLIST.getStorage().readNBT(ModCapabilities.CAPABILITY_QUESTLIST, instantce, null, nbt);
	}

}
