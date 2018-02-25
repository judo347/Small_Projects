package baseSwing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class JPanaWithPictureBackgorund extends JPanel {
    @Override
    protected void paintComponent(Graphics g){

        ImageIcon background = null;
        background = new ImageIcon("E:\\SourceTree\\Small_Projects\\PoE- ChaosRecipeWindow\\src\\baseSwing\\background5.jpg"); //TODO: FIx path

        super.paintComponent(g);
        g.drawImage(background.getImage(), 0, 0, null);
    }
}
