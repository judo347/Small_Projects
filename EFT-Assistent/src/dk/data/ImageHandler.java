package dk.data;

import dk.model.MapType;
import dk.model.TraderType;
import javafx.scene.image.Image;

import java.io.InputStream;

public class ImageHandler {

    private String map_folder_path = "\\mapBanners\\";
    private String trader_folder_path = "\\traderPortraits\\";

    //TODO optimize by only loading images once!!

    public Image getImageFromMapType(MapType mapType){
        InputStream temp = ImageHandler.class.getResourceAsStream(map_folder_path + mapType.getName().toLowerCase() + ".png");
        return new Image(temp);
    }

    public Image getImageFromTraderType(TraderType traderType){
        return new Image(ImageHandler.class.getResourceAsStream(trader_folder_path + traderType.getName().toLowerCase() + ".png"));
    }
}
