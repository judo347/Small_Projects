package baseSwing;

import javafx.fxml.Initializable;

import java.io.*;
import java.util.ArrayList;

//1,2,3,4,5,6,7,8,9,10
public class OwnFileManager {
    public void fillArray(ArrayList<Item> itemList){

        String fileName = "E:\\SourceTree\\Small_Projects\\PoE- ChaosRecipeWindow\\src\\baseSwing\\standard.txt"; //TODO: fix this path!
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            line = bufferedReader.readLine();
            if(line == null) //File is empty
                fillArrayWith0(itemList); //Fill items with count = 0
            else //File contains a line
                fillArrayCountFromLine(itemList, line);
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

    private void fillArrayWith0(ArrayList<Item> itemList){
        for(Item item: itemList)
            item.setCount(0);
    }

    private void fillArrayCountFromLine(ArrayList<Item> itemList, String line){
        System.out.println("Filling list with data from file");
        int currentElement = 0;
        String tempString = "";

        for(int i = 0; i < line.length(); i++){

            if(line.charAt(i) != ',' && line.charAt(i) != '.')
                tempString += line.charAt(i);
            else if(line.charAt(i) == ',' || line.charAt(i) == '.'){
                System.out.println(tempString);
                itemList.get(currentElement).setCount(Integer.parseInt(tempString));
                currentElement++;
                tempString = "";
            }
        }
    }
}
