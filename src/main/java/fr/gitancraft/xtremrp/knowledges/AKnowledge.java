package fr.gitancraft.xtremrp.knowledges;

import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class AKnowledge extends IForgeRegistryEntry.Impl<AKnowledge> {

    private String name;

    public AKnowledge(String name) {
        this.name = name;
        setRegistryName(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AKnowledge) {
            AKnowledge knowledge = (AKnowledge) obj;
            return knowledge.getRegistryName().equals(this.getRegistryName());
        }
        return false;
    }
}
