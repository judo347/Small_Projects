package dk.data;

import org.json.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class JSONParserHelper {

    private File file = new File("./quests.json");

    public JSONParserHelper() {


    }

    public JSONObject getAllQuests(){

        try {
            String fileContent = FileUtils.readFileToString(file, "uft-8");

            //Convert string to JSON object
            JSONObject allQuestsObject = new JSONObject(fileContent);

            return allQuestsObject;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
