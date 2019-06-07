package files;

import model.Model;

import java.io.File;

public class FileManager {

    private static String path = "src/files/data.xml"; //TODO

    public static Model loadData(){
        return XMLLoader.loadXml(new File(path));
    }

    public static void saveData(Model model){
        XMLSaver.saveXml(new File(path), model);
    }
}
