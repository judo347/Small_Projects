package dk.saveAndLoad;

import dk.model.MainModel;
import dk.model.PlayerInfo;
import dk.model.TraderType;
import dk.model.quest.Quest;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestSaveAndLoad {

    private final int test_saveSlot = 2;

    @Test
    public void saveAndLoad01fresh(){
        MainModel model = new MainModel();
        ModelState modelState_start = new ModelState(model);

        boolean didSave = model.saveSlot(test_saveSlot);
        assertTrue(didSave);
        model.loadSlot(test_saveSlot);

        //Data/model validation
        TestUtils.assertPlayerInfo(modelState_start.playerInfo, model.getPlayerInfo());
        assertEquals(modelState_start.completedQuests.size(), 0);
        assertEquals(model.getQm().getCompleted().size(), 0);
        TestUtils.assertEqualQuestList(modelState_start.completedQuests, model.getQm().getCompleted());
        TestUtils.assertEqualQuestList(modelState_start.activeQuests, model.getQm().getActiveQuests());
        TestUtils.assertEqualQuestList(modelState_start.lockedQuests, model.getQm().getLockedQuests());
    }

    @Test
    public void saveAndLoad02simple(){
        MainModel model = new MainModel();

        for(Quest quest : new ArrayList<>(model.getQm().getActiveQuests()))
            model.getQm().completeQuest(quest);

        assertEquals(0, model.getQm().getActiveQuests().size());

        ModelState modelState_origin = new ModelState(model);
        boolean didSave = model.saveSlot(test_saveSlot);
        assertTrue(didSave);
        model.loadSlot(test_saveSlot);

        assertEquals(model.getQm().getActiveQuests().size(), modelState_origin.activeQuests.size());
        assertEquals(model.getQm().getLockedQuests().size(), modelState_origin.lockedQuests.size());
        assertEquals(model.getQm().getCompleted().size(), modelState_origin.completedQuests.size());
    }

    //TODO Load/save of special cases

    @Test
    public void saveAndLoad03playerInfo(){
        MainModel model = new MainModel();

        for(TraderType tt : TraderType.values())
            model.incrementTraderLoyaltyLevel(tt);

        model.incrementPlayerLevel();
        model.incrementPlayerLevel();
        model.incrementPlayerLevel();

        ModelState modelState_origin = new ModelState(model);
        boolean didSave = model.saveSlot(test_saveSlot);
        assertTrue(didSave);
        model.loadSlot(test_saveSlot);

        assertEquals(model.getPlayerInfo().getPlayerLevel(), modelState_origin.playerInfo.getPlayerLevel());
        for(TraderType tt : TraderType.values())
            assertEquals(model.getPlayerInfo().getLoyaltyLevelFromTrader(tt), modelState_origin.playerInfo.getLoyaltyLevelFromTrader(tt));
    }

    @Test
    public void saveAndLoad04(){
        MainModel model = new MainModel();

        for(Quest quest : new ArrayList<>(model.getQm().getActiveQuests()))
            model.getQm().completeQuest(quest);

        model.incrementPlayerLevel();
        model.incrementPlayerLevel();
        model.incrementPlayerLevel();

        assertEquals(2, model.getQm().getActiveQuests().size());

        for(Quest quest : new ArrayList<>(model.getQm().getActiveQuests()))
            model.getQm().completeQuest(quest);

        assertEquals(1, model.getQm().getActiveQuests().size());

        model.incrementPlayerLevel();
        model.incrementPlayerLevel();
        model.incrementPlayerLevel();

        assertEquals(4, model.getQm().getActiveQuests().size());

        ModelState modelState_origin = new ModelState(model);
        boolean didSave = model.saveSlot(test_saveSlot);
        assertTrue(didSave);
        model.loadSlot(test_saveSlot);

        assertEquals(model.getQm().getActiveQuests().size(), modelState_origin.activeQuests.size());
        assertEquals(model.getQm().getLockedQuests().size(), modelState_origin.lockedQuests.size());
        assertEquals(model.getQm().getCompleted().size(), modelState_origin.completedQuests.size());
    }

    private class ModelState {
        private ArrayList<Quest> completedQuests = new ArrayList<>();
        private ArrayList<Quest> activeQuests = new ArrayList<>();
        private ArrayList<Quest> lockedQuests = new ArrayList<>();

        private PlayerInfo playerInfo;

        public ModelState(MainModel mainModel){
            initialize(mainModel.getQm().getCompleted(), mainModel.getQm().getActiveQuests(), mainModel.getQm().getLockedQuests(), mainModel.getPlayerInfo());
        }

        public ModelState(ArrayList<Quest> completedQuests, ArrayList<Quest> activeQuests, ArrayList<Quest> lockedQuests, PlayerInfo playerInfo) {
            initialize(completedQuests, activeQuests, lockedQuests, playerInfo);
        }

        private void initialize(ArrayList<Quest> completedQuests, ArrayList<Quest> activeQuests, ArrayList<Quest> lockedQuests, PlayerInfo playerInfo){
            this.completedQuests = new ArrayList<>(completedQuests);
            this.activeQuests = new ArrayList<>(activeQuests);
            this.lockedQuests = new ArrayList<>(lockedQuests);
            this.playerInfo = new PlayerInfo();
            this.playerInfo.reload(playerInfo);
        }
    }
}
