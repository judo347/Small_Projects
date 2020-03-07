package dk.data;

import dk.model.MapType;
import dk.model.PlayerInfo;
import dk.model.SaveData;
import dk.model.TraderType;
import dk.model.quest.Quest;
import dk.model.quest.QuestObjectives;
import dk.model.tools.TypeParser;
import org.json.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/** Has the responsibility for reading JSON files and parsing them into model object and vice versa.*/
public class JSONParserHelper {

    private static final String dataFolderPath = "src\\dk\\data";
    private static final String questsDataPostfix = "\\quests.json";
    private static final String saveDataPostfix = "\\saveData.json";

    private static final int numberOfSaveSlots = 2;

    public ArrayList<Quest> getAllQuestsFromFile(){
        return parseAllQuests(getJSONFromFile(questsDataPostfix));
    }

    /** Takes in the topNode loaded from the saveData.json file
     * and returns an array containing all loaded quests.*/
    private ArrayList<Quest> parseAllQuests(JSONObject topNode){

        JSONArray questsArrayJSON = topNode.getJSONArray("quests");

        ArrayList<Quest> allParsedQuests = new ArrayList<>();

        for(int i = 0; i < questsArrayJSON.length(); i++){
            Quest parsedQuest = parseQuest(questsArrayJSON.getJSONObject(i));
            allParsedQuests.add(parsedQuest);
        }

        return allParsedQuests;
    }

    private Quest parseQuest(JSONObject questObjectJSON){

        String questName = (String) questObjectJSON.get("name");
        String questGiver = (String) questObjectJSON.get("giver");
        int questReqLevel = questObjectJSON.getInt("req_level");
        ArrayList<String> questMaps = parseJSONArray(questObjectJSON.getJSONArray("maps"));
        JSONArray objectivesJSONArray = questObjectJSON.getJSONArray("objectives");
        ArrayList<String> questRequirements = parseJSONArray(questObjectJSON.getJSONArray("requirements"));

        //Parsing from string to model objects
        ArrayList<MapType> mapTypes = new ArrayList<>();
        for(String questMap : questMaps)
            mapTypes.add(TypeParser.stringToMapType(questMap));

        TraderType trader = TypeParser.stringToTrader(questGiver);

        ArrayList<QuestObjectives> questObjectivesStrings = parseObjectives(objectivesJSONArray);

        boolean hasLoyaltyLevelReq = questObjectJSON.has("req_LL");
        int reqLoyaltyLevel = 0;
        if(hasLoyaltyLevelReq)
            reqLoyaltyLevel = questObjectJSON.getInt("req_LL");

        int id = questObjectJSON.getInt("id");

        //Required quests
        JSONArray reqQuestIdArrayJSON = questObjectJSON.getJSONArray("req_quests");
        ArrayList<Integer> reqQuestId = parseJSONArrayToInt(reqQuestIdArrayJSON);

        return new Quest(questName, mapTypes, trader, questReqLevel, questObjectivesStrings, questRequirements, reqLoyaltyLevel, id, reqQuestId);
    }

    private ArrayList<QuestObjectives> parseObjectives(JSONArray questObjectivesJSON){

        ArrayList<QuestObjectives> objectives = new ArrayList<>();
        for(int i = 0; i < questObjectivesJSON.length(); i++){
            QuestObjectives qo = new QuestObjectives();
            String objective = ((JSONObject)questObjectivesJSON.get(i)).getString("obj");
            qo.setObjective(objective);


            boolean hasSubObj = ((JSONObject)questObjectivesJSON.get(i)).has("subs");
            if(hasSubObj){
                JSONArray subObjectivesJSONArray = ((JSONObject)questObjectivesJSON.get(i)).getJSONArray("subs");
                ArrayList<String> subObjs = parseJSONArray(subObjectivesJSONArray);
                qo.setSubObjectives(subObjs);
            }

            objectives.add(qo);
        }

        return objectives;
    }

    public boolean SaveData(int saveSlotId, ArrayList<Quest> completedQuests, PlayerInfo playerInfo){

        if(saveSlotId > numberOfSaveSlots-1)
            return false;

        JSONObject rootNodeSavesJSON = getJSONFromFile(saveDataPostfix);
        JSONArray saveSlots = rootNodeSavesJSON.getJSONArray("saves");
        JSONObject saveSlotJSONObject = saveSlots.getJSONObject(saveSlotId);
        JSONObject playerInfoJSONObject = saveSlotJSONObject.getJSONObject("player_info");
        JSONArray completedQuestIdsJSONArray = saveSlotJSONObject.getJSONArray("quests_completed");
        JSONObject loyaltyLevelJSONObject = playerInfoJSONObject.getJSONObject("loyaltyLevel");

        while(completedQuestIdsJSONArray.length() > 0)
            completedQuestIdsJSONArray.remove(0);
        for(Quest quest : completedQuests){
            completedQuestIdsJSONArray.put(quest.getId());
        }

        playerInfoJSONObject.put("player_level", playerInfo.getPlayerLevel());

        for(TraderType traderType : TraderType.values()){
            loyaltyLevelJSONObject.put(traderType.getName().toLowerCase(), playerInfo.getLoyaltyLevelFromTrader(traderType));
        }

        return saveJSONToSaveDataFile(rootNodeSavesJSON);
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

    private boolean saveJSONToSaveDataFile(JSONObject rootNode){
        try {
            String canonicalPath = new File(dataFolderPath).getAbsolutePath();
            File file = new File(canonicalPath + saveDataPostfix);
            FileUtils.writeStringToFile(file, rootNode.toString(), false);

            //Convert string to JSON object
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private ArrayList<String> parseJSONArray(JSONArray JArray){
        ArrayList<String> stringArray = new ArrayList<>();
        for(int i = 0; i < JArray.length(); i++){
            String parsedString = (String) JArray.get(i);
            stringArray.add(parsedString);
        }

        return stringArray;
    }

    private ArrayList<Integer> parseJSONArrayToInt(JSONArray JArray){
        ArrayList<Integer> intArray = new ArrayList<>();
        for(int i = 0; i < JArray.length(); i++){
            int parsedInt = JArray.getInt(i);
            intArray.add(parsedInt);
        }

        return intArray;
    }

    private QuestObjectives stringArrayToQObjectives(ArrayList<String> objectivesStringArray){
        return new QuestObjectives(objectivesStringArray.get(0), null);
    }
}
