package dk.saveAndLoad;

import dk.model.MainModel;
import dk.model.PlayerInfo;
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
        PlayerInfo playerInfo_before = new PlayerInfo();
        playerInfo_before.reload(model.getPlayerInfo());
        ArrayList<Quest> completedQuests_before = new ArrayList<>(model.getQm().getCompleted());
        ArrayList<Quest> activeQuests_before = new ArrayList<>(model.getQm().getActiveQuests());
        ArrayList<Quest> lockedQuests_before = new ArrayList<>(model.getQm().getLockedQuests());

        boolean didSave = model.saveSlot(test_saveSlot);
        assertTrue(didSave);
        model.loadSlot(test_saveSlot);

        PlayerInfo playerInfo_after = model.getPlayerInfo();
        ArrayList<Quest> completedQuests_after = model.getQm().getCompleted();
        ArrayList<Quest> activeQuests_after = model.getQm().getActiveQuests();
        ArrayList<Quest> lockedQuests_after = model.getQm().getLockedQuests();

        //Data/model validation
        TestUtils.assertPlayerInfo(playerInfo_before, playerInfo_after);
        assertEquals(completedQuests_before.size(), 0);
        assertEquals(completedQuests_after.size(), 0);
        TestUtils.assertEqualQuestList(completedQuests_before, completedQuests_after);
        TestUtils.assertEqualQuestList(activeQuests_before, activeQuests_after);
        TestUtils.assertEqualQuestList(lockedQuests_before, lockedQuests_after);
    }

    //TODO Add method for comparing and coping models.
}
