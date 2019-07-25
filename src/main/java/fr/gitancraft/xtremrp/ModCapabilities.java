package fr.gitancraft.xtremrp;

import fr.gitancraft.xtremrp.entity.capabilities.IQuestList;
import fr.gitancraft.xtremrp.entity.capabilities.PlayerQuestCapabilities;
import fr.gitancraft.xtremrp.entity.capabilities.QuestListImpl;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModCapabilities {

    @CapabilityInject(IQuestList.class)
    public static final Capability<IQuestList> CAPABILITY_QUESTLIST = null;

    public static void registerCapabilities() {
        CapabilityManager.INSTANCE.register(IQuestList.class, new PlayerQuestCapabilities(), QuestListImpl.class::newInstance);
    }
}
