package files;

import java.io.File;
import model.Model;

public class FileManager {
    private static String path = "src/files/data.xml";

    public FileManager() {
    }

    public static Model loadData() {
        return XMLLoader.loadXml(new File(path));
    }

    public static void saveData(Model model) {
        XMLSaver.saveXml(new File(path), model);
    }
}
