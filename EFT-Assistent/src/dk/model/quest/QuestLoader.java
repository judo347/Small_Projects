package dk.model.quest;

import dk.data.JSONParserHelper;
import dk.model.MapType;
import dk.model.PlayerInfo;
import dk.model.SaveData;
import dk.model.TraderType;
import dk.model.tools.TypeParser;
import javafx.beans.property.IntegerProperty;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class QuestLoader {

    public ArrayList<Quest> allQuest;

    public QuestLoader() {
        loadAllQuests();
    }

    private void loadAllQuests(){
        JSONParserHelper jph = new JSONParserHelper();
        allQuest = jph.getAllQuestsFromFile();
    }
}