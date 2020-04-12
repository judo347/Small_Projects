package dk.view;

import dk.data.ImageHandler;
import dk.model.MapType;
import dk.model.TraderType;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class QuestCategoryManager {

    private HashMap<TraderType, HBox> categoryBoxes_trader = new HashMap<>();
    private HashMap<MapType, HBox> categoryBoxes_map = new HashMap<>();

    private VBox root;

    private PrimarySceneController psc;

    private ImageHandler imageHandler = new ImageHandler();

    public QuestCategoryManager(PrimarySceneController psc, VBox root) {
        this.psc = psc;
        this.root = root;
    }

    public void reloadSorting(SortingMode sortingMode){
        root.getChildren().clear();
        setupCategoryBoxes(sortingMode);
        psc.reloadQuestVisuals();
    }

    /** Adds the given quest card to the correct hbox based on given mapType. */
    public void addQuestCard(PaneAndController questCardAndController, SortingMode sortingMode, MapType mapType, TraderType traderType){
        HBox desiredHBox;

        if(sortingMode == SortingMode.MAP){
            desiredHBox = categoryBoxes_map.get(mapType);
        }else if(sortingMode == SortingMode.TRADER){
            desiredHBox = categoryBoxes_trader.get(traderType);
        }else{
            throw new IllegalArgumentException("A new sorting map has been implemented!");
        }

        desiredHBox.getChildren().add(questCardAndController.pane);
        questCardAndController.qcc.addBoxParent(desiredHBox, questCardAndController.pane);
    }

    private void setupCategoryBoxes(SortingMode sortingMode){
        if(sortingMode == SortingMode.TRADER){
            categoryBoxes_map.clear();
            categoryBoxes_trader.clear();

            for(TraderType type : TraderType.values()){
                HBox box = getQuestCatHBox(imageHandler.getImageFromTraderType(type));
                root.getChildren().add(box);
                categoryBoxes_trader.put(type, box);
            }
        }else if(sortingMode == SortingMode.MAP){
            categoryBoxes_map.clear();
            categoryBoxes_trader.clear();

            for(MapType type : MapType.values()){
                HBox box = getQuestCatHBox(imageHandler.getImageFromMapType(type));
                root.getChildren().add(box);
                categoryBoxes_map.put(type, box);
            }
        }else{
            throw new IllegalArgumentException("A new sorting map has been implemented!");
        }
    }

    private HBox getQuestCatHBox(Image image){
        HBox box = new HBox();
        box.prefWidth(200);
        box.setMaxHeight(230);

        ImageView categoryLogo = new ImageView(image);
        box.getChildren().add(categoryLogo);

        return box;
    }

    public void clearHboxs(SortingMode sortingMode){
        if (sortingMode == SortingMode.MAP){
            for(MapType type : MapType.values()){
                Node categoryImage = categoryBoxes_map.get(type).getChildren().get(0);
                categoryBoxes_map.get(type).getChildren().clear();
                categoryBoxes_map.get(type).getChildren().add(categoryImage);
            }
        }else if (sortingMode == SortingMode.TRADER){
            for(TraderType type : TraderType.values()){
                Node categoryImage = categoryBoxes_trader.get(type).getChildren().get(0);
                categoryBoxes_trader.get(type).getChildren().clear();
                categoryBoxes_trader.get(type).getChildren().add(categoryImage);
            }
        }
    }

    public ImageHandler getImageHandler() {
        return imageHandler;
    }
}
