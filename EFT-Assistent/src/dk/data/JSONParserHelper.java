package dk.data;

import dk.model.PlayerInfo;
import dk.model.SaveData;
import dk.model.TraderType;
import dk.model.quest.Quest;
import org.json.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JSONParserHelper {

    private static final String dataFolderPath = "src\\dk\\data";
    private static final String questsDataPostfix = "\\quests.json";
    private static final String saveDataPostfix = "\\saveData.json";

    public JSONObject getAllQuests(){
        return getJSONFromFile(questsDataPostfix);
    }

    public SaveData loadSlot(int slotNumber){

        JSONObject rootNodeSavesJSON = getJSONFromFile(saveDataPostfix);

        JSONArray saveSlots = rootNodeSavesJSON.getJSONArray("saves");

        if(slotNumber > saveSlots.length())
            throw new IllegalArgumentException("Trying to load non-existing slot. Given slot number too high");

        JSONObject saveSlotJSONObject = saveSlots.getJSONObject(slotNumber);
        JSONObject playerInfoJSONObject = saveSlotJSONObject.getJSONObject("player_info");

        SaveData loadedData = new SaveData();
        loadedData.completedQuestIds = parseCompletedQuestIds(saveSlotJSONObject);
        loadedData.playerInfo = parsePlayerInfo(playerInfoJSONObject);

        return loadedData;
    }

    private ArrayList<Integer> parseCompletedQuestIds(JSONObject slotObject){

        JSONArray completedQuestIdsJSONArray = slotObject.getJSONArray("quests_completed");
        ArrayList<Integer> completedQuestIds = new ArrayList<>();
        for(int i = 0; i < completedQuestIdsJSONArray.length(); i++)
            completedQuestIds.add(completedQuestIdsJSONArray.getInt(i));

        return completedQuestIds;
    }

    private PlayerInfo parsePlayerInfo(JSONObject playerInfoJSONObject){
        PlayerInfo playerInfo = new PlayerInfo();

        playerInfo.setPlayerLevel(playerInfoJSONObject.getInt("player_level"));

        JSONObject loyaltyLevelJSONObject = playerInfoJSONObject.getJSONObject("loyaltyLevel");
        for(TraderType traderType : TraderType.values()){
            int desiredLevel = loyaltyLevelJSONObject.getInt(traderType.getName().toLowerCase());
            playerInfo.setLoyaltyLevel(traderType, desiredLevel);
        }

        return playerInfo;
    }

    private JSONObject getJSONFromFile(String filePostfix){
        try {
            String canonicalPath = new File(dataFolderPath).getAbsolutePath();
            File file = new File(canonicalPath + filePostfix);
            String fileContent = FileUtils.readFileToString(file, "UTF-8");

            //Convert string to JSON object
            return new JSONObject(fileContent);

        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException();
    }

    private ArrayList<String> parseJSONArray(JSONArray JArray){
        ArrayList<String> stringArray = new ArrayList<>();
        for(int i = 0; i < JArray.length(); i++){
            String parsedString = (String) JArray.get(i);
            stringArray.add(parsedString);
        }

        return stringArray;
    }
}
