import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AreaManager {

    private String imageFolderPath = "E:\\Test\\";

    public void saveImage(BufferedImage image){

        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuuMMddHHmmss");

            ImageIO.write(image, "png", new File(imageFolderPath + LocalDateTime.now().format(dtf) + ".png"));
            //ImageIO.write(image, "png", new File("E:\\Test\\" + "test" + ".png"));
        } catch (IOException e) {
            System.out.println("COULD NOT SAVE THE IMAGE");
            e.printStackTrace();
        }
    }

    /** @return an image of an area. */
    public BufferedImage generateImage(int height, int width){

        AreaGenerator areaGenerator = new AreaGenerator(height, width);
        AreaHandler.TileType[][] area = areaGenerator.getArea();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //Convert area to image
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                image.setRGB(x, y, area[x][y].getColer().getRGB());
            }
        }

        return image;
    }
}
