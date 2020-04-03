package dk.saveAndLoad;

import dk.model.MainModel;
import dk.view.PrimarySceneController;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSaveAndLoad {

    private MainModel mainModel;


    private void initializeBasicModel(){
        PrimarySceneController psc = new PrimarySceneController();
        mainModel = new MainModel();
        psc.setMainModel(mainModel);
    }

    @Test
    public void save01(){
        initializeBasicModel();
    }

    @Test
    public void saveAndLoad01(){



        Assert.assertEquals(0,3);
    }

    @Test
    public void saveAndLoad02(){
        Assert.assertEquals(3,3);
    }
}
