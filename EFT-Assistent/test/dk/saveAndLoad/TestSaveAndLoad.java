package dk.saveAndLoad;

import junit.framework.Assert;
import org.junit.Test;

public class TestSaveAndLoad {

    @Test
    public void saveAndLoad01(){
        Assert.assertEquals(0,3);
    }

    @Test
    public void saveAndLoad02(){
        Assert.assertEquals(3,3);
    }
}
