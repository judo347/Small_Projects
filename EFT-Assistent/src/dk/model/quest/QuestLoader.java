package dk.model.quest;

import dk.data.JSONParserHelper;
import dk.model.MapType;
import dk.model.SaveData;
import dk.model.TraderType;
import dk.model.tools.TypeParser;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class QuestLoader {

    private static String json_topNodeId = "quests";


    public ArrayList<Quest> allQuest;

    public QuestLoader() {
        loadAllQuests();
    }

    private void loadAllQuests(){
        allQuest = getAllQuestsFromFile();
    }

    private ArrayList<Quest> getAllQuestsFromFile(){
        JSONParserHelper JPH = new JSONParserHelper();
        JSONObject topObjectJSON = JPH.getAllQuests();

        JSONArray questsArrayJSON = topObjectJSON.getJSONArray(json_topNodeId);

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
        //JSONObject objectivesJSONObject = questObjectJSON.getJSONObject("objectives");
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