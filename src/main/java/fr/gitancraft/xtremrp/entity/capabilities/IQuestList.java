package fr.gitancraft.xtremrp.entity.capabilities;

import java.util.Set;

import fr.gitancraft.xtremrp.elements.quest.QuestStatus;

public interface IQuestList {

	public void ajouterQuete(Quest quete);
	public void changerEtatQuete(Quest quete, QuestStatus statut);
	public Set<Quest> getQuestList();
	public void setQuestList(Set<Quest> questList);
}
