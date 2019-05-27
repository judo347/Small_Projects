package files;

import model.Model;

import java.io.File;

public class FileManager {

    private static String path = "src/files/data.xml"; //TODO

    public static Model loadData(){
        return XMLHandler.loadXml(new File(path));
    }
}
