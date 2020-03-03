package dk.model;

import dk.data.JSONParserHelper;
import dk.model.tools.TypeParser;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class QuestLoader {

    private static String json_topNodeId = "quests";


    ArrayList<Quest> allQuest;

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
        ArrayList<String> questObjectives = parseJSONArray(questObjectJSON.getJSONArray("objectives"));
        ArrayList<String> questRequirements = parseJSONArray(questObjectJSON.getJSONArray("requirements"));

        //Parsing from string to model objects
        ArrayList<MapType> mapTypes = new ArrayList<>();
        for(String questMap : questMaps)
            mapTypes.add(TypeParser.stringToMapType(questMap));

        TraderType trader = TypeParser.stringToTrader(questGiver);

        QuestObjectives questObjectivesStrings = stringArrayToQObjectives(questObjectives);

        return new Quest(questName, mapTypes, trader, questReqLevel, questObjectivesStrings, questRequirements);
    }

    private ArrayList<String> parseJSONArray(JSONArray JArray){
        ArrayList<String> stringArray = new ArrayList<>();
        for(int i = 0; i < JArray.length(); i++){
            String parsedString = (String) JArray.get(i);
            stringArray.add(parsedString);
        }

        return stringArray;
    }

    private QuestObjectives stringArrayToQObjectives(ArrayList<String> objectivesStringArray){
        return new QuestObjectives(objectivesStringArray.get(0), null);
    }
}