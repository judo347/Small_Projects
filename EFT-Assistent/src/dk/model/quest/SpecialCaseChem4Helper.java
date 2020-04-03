package dk.model.quest;

import java.util.ArrayList;

public class SpecialCaseChem4Helper {

    QuestManager qm;

    //These three quests are mentioned as primary
    private Quest quest_chem4 = null;
    private Quest quest_outOfCuriosity = null;
    private Quest quest_bigCustomer = null;

    //These three quests are mentioned as secondary
    private Quest quest_trustRegain = null;
    private Quest quest_loyaltyBuyout = null;
    private Quest quest_noOffence = null;

    //Quest ids
    private final int quest_id_chemical_part_4 = 54;
    private final int quest_id_out_of_curiosity = 39;
    private final int quest_id_big_customer = 16;
    private final int quest_id_trust_regain = 40;
    private final int quest_id_loyalty_buyout = 55;
    private final int quest_id_no_offence = 17;

    public SpecialCaseChem4Helper(QuestManager qm) {
        this.qm = qm;
    }

    public boolean addQuestCheck(Quest quest) {
        int questId = quest.getId();
        if (questId == quest_id_chemical_part_4) {
            quest_chem4 = quest;
            return false;
        }else if (questId == quest_id_out_of_curiosity) {
            quest_outOfCuriosity = quest;
            return true;
        } else if (questId == quest_id_big_customer){
            quest_bigCustomer = quest;
            return true;
        }else if(questId == quest_id_trust_regain) {
            quest_trustRegain = quest;
            return true;
        }else if(questId == quest_id_loyalty_buyout) {
            quest_loyaltyBuyout = quest;
            return true;
        }else if(questId == quest_id_no_offence) {
            quest_noOffence = quest;
            return true;
        }else
            return false;
    }

    public void chem4FromLockedToActiveCheck(Quest quest){
        if(quest.getId() == quest_id_chemical_part_4){
            qm.getActiveQuests().add(quest_outOfCuriosity);
            qm.getActiveQuests().add(quest_bigCustomer);
        }
    }

    public void primaryCompleteCheck(Quest quest){
        if (quest == quest_chem4 || quest == quest_outOfCuriosity ||
                quest == quest_bigCustomer){

            primaryComplete(quest);
        }
    }

    private void primaryComplete(Quest completedQuest){
        //completing a primary quest removes the two others
        if (completedQuest == quest_chem4){
            completeQuest(quest_outOfCuriosity); //TODO failed
            completeQuest(quest_bigCustomer); //TODO failed
            quest_outOfCuriosity = null;
            quest_bigCustomer = null;
        }else if(completedQuest == quest_outOfCuriosity){
            completeQuest(quest_chem4); //TODO failed
            completeQuest(quest_bigCustomer); //TODO failed
            quest_chem4 = null;
            quest_bigCustomer = null;
        }else if(completedQuest == quest_bigCustomer){
            completeQuest(quest_chem4); //TODO failed
            completeQuest(quest_outOfCuriosity); //TODO failed
            quest_chem4 = null;
            quest_outOfCuriosity = null;
        }else
            throw new IllegalArgumentException("Should never happen!!");

        //Completing a primary quest adds two specific secondary quests
        if (completedQuest == quest_chem4){
            qm.getActiveQuests().add(quest_trustRegain);
            qm.getActiveQuests().add(quest_noOffence);
            qm.getCompleted().add(quest_loyaltyBuyout); //TODO not available instead of completed
        }else if(completedQuest == quest_outOfCuriosity){
            qm.getActiveQuests().add(quest_loyaltyBuyout);
            qm.getActiveQuests().add(quest_noOffence);
            qm.getCompleted().add(quest_trustRegain); //TODO not available instead of completed
        }else if(completedQuest == quest_bigCustomer){
            qm.getActiveQuests().add(quest_trustRegain);
            qm.getActiveQuests().add(quest_loyaltyBuyout);
            qm.getCompleted().add(quest_noOffence); //TODO not available instead of completed
        }else
            throw new IllegalArgumentException("Should never happen!!");
    }

    private void completeQuest(Quest quest){
        quest.complete();
        qm.getActiveQuests().remove(quest);
        qm.getCompleted().add(quest);
    }
}
