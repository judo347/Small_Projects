package dk.data;

import org.json.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class JSONParserHelper {

    private static final String questsDataPath = "dk/data/quests.json";

    public JSONParserHelper() {


    }

    public JSONObject getAllQuests(){

        try {
            String canonicalPath = new File("src\\dk\\data").getAbsolutePath();
            File file = new File(canonicalPath + "\\quests.json");
            List lines = FileUtils.readLines(file, "UTF-8");
            String fileContent = FileUtils.readFileToString(file, "UTF-8");

            //Convert string to JSON object
            JSONObject allQuestsObject = new JSONObject(fileContent);

            return allQuestsObject;

        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException();
    }
}
