package fr.gitancraft.xtremrp.entity.capabilities;

import java.util.HashSet;
import java.util.Set;

import fr.gitancraft.xtremrp.elements.quest.QuestStatus;

public class QuestListImpl implements IQuestList {
	
	Set<Quest> listOfQuest = new HashSet();
	
	@Override
	public void ajouterQuete(Quest quete) {
		System.out.println("ajouté quest");
	}

	@Override
	public void changerEtatQuete(Quest quete, QuestStatus statut) {
		System.out.println("quete edited");
	}

	@Override
	public Set<Quest> getQuestList() {
		return listOfQuest;
	}

	@Override
	public void setQuestList(Set<Quest> questList) {
		this.listOfQuest = questList;
		
	}


}
