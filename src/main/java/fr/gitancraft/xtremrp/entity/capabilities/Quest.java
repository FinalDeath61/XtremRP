package fr.gitancraft.xtremrp.entity.capabilities;

import java.util.UUID;

import fr.gitancraft.xtremrp.elements.quest.QuestStatus;

public class Quest {

	private UUID idQuest;
	private QuestStatus status;
	public Quest(UUID idquest, QuestStatus status) {
		idQuest= idquest;
		this.status= status;
	}

	public UUID getIdQuest() {
		return idQuest;
	}

	public QuestStatus getStatus() {
		return status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idQuest == null) ? 0 : idQuest.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quest other = (Quest) obj;
		if (idQuest == null) {
			if (other.idQuest != null)
				return false;
		} else if (!idQuest.equals(other.idQuest))
			return false;
		return true;
	}
	
	

	
	
}
