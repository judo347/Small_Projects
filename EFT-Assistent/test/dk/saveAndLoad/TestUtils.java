package dk.saveAndLoad;

import dk.model.PlayerInfo;
import dk.model.TraderType;
import dk.model.quest.Quest;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class TestUtils {

    public static void assertPlayerInfo(PlayerInfo pi_before, PlayerInfo pi_after){
        assertEquals(pi_before.getPlayerLevel(), pi_after.getPlayerLevel());
        assertEquals(pi_before.getLoyaltyLevelFromTrader(TraderType.PRAPOR), pi_after.getLoyaltyLevelFromTrader(TraderType.PRAPOR));
        assertEquals(pi_before.getLoyaltyLevelFromTrader(TraderType.THERAPIST), pi_after.getLoyaltyLevelFromTrader(TraderType.THERAPIST));
        assertEquals(pi_before.getLoyaltyLevelFromTrader(TraderType.SKIER), pi_after.getLoyaltyLevelFromTrader(TraderType.SKIER));
        assertEquals(pi_before.getLoyaltyLevelFromTrader(TraderType.PEACEKEEPER), pi_after.getLoyaltyLevelFromTrader(TraderType.PEACEKEEPER));
        assertEquals(pi_before.getLoyaltyLevelFromTrader(TraderType.MECHANIC), pi_after.getLoyaltyLevelFromTrader(TraderType.MECHANIC));
        assertEquals(pi_before.getLoyaltyLevelFromTrader(TraderType.RAGMAN), pi_after.getLoyaltyLevelFromTrader(TraderType.RAGMAN));
        assertEquals(pi_before.getLoyaltyLevelFromTrader(TraderType.JAEGER), pi_after.getLoyaltyLevelFromTrader(TraderType.JAEGER));
    }

    public static void assertEqualQuestList(ArrayList<Quest> ql_before, ArrayList<Quest> ql_after){
        ArrayList<Integer> il_before_ids = new ArrayList<>();
        ArrayList<Integer> il_after_ids = new ArrayList<>();

        for(Quest q : ql_before)
            il_before_ids.add(q.getId());

        for(Quest q : ql_after)
            il_after_ids.add(q.getId());

        assertEquals(il_before_ids.size(), il_after_ids.size());

        Collections.sort(il_before_ids);
        Collections.sort(il_after_ids);

        for(int i = 0; i < il_before_ids.size(); i++){
            assertEquals(il_before_ids.get(i), il_after_ids.get(i));
        }
    }
}
