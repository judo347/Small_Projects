package baseSwing;

import javafx.fxml.Initializable;

import java.io.*;
import java.util.ArrayList;

//1,2,3,4,5,6,7,8,9,10
public class OwnFileManager {
    public void saveArray(ArrayList<Item> itemList, int currentLeague){
        String leagueFileName = getFileName(currentLeague);
        String fileName = "E:\\SourceTree\\Small_Projects\\PoE- ChaosRecipeWindow\\src\\baseSwing\\" + leagueFileName; //TODO: fix this path!
        String line = createFormattedString(itemList);
        System.out.println("Formatted line?: " + line);
        //TODO: Write line to file;
        
        try{
            FileWriter fw = new FileWriter(fileName, false); //False = not append
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(createFormattedString(itemList));
            bw.flush();
            bw.close();
        } catch (IOException e){
            System.out.println("Failed to open and write to file");
            e.printStackTrace();
        }

    }

    private String createFormattedString(ArrayList<Item> itemList){
        String line = "";

        for(int i = 0; i < itemList.size(); i++){
            line += itemList.get(i).getCount();
            if(i == itemList.size()-1)
                line += ".";
            else
                line += ",";
        }

        return line;
    }

    public boolean fillArray(ArrayList<Item> itemList, int desiredLeague){

        String leagueFileName = getFileName(desiredLeague);
        //System.out.println(leagueFileName); //TEMP
        String fileName = "E:\\SourceTree\\Small_Projects\\PoE- ChaosRecipeWindow\\src\\baseSwing\\" + leagueFileName; //TODO: fix this path!
        String line = null;
        //System.out.println(leagueFileName); //TEMP

        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            line = bufferedReader.readLine();
            if(line == null) //File is empty
                //TODO: Create file;
                fillArrayWith0(itemList); //Fill items with count = 0
            else //File contains a line
                fillArrayCountFromLine(itemList, line);
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
            return false;
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
            return false;
        }
        return true;
    }

    //HELP FUNCTION FOR fill array
    private String getFileName(int desiredLeague){
        switch (desiredLeague){
            case 0: return "standard.txt";
            case 1: return "hardcore.txt";
            case 2: return "tempStandard.txt";
            case 3: return "tempHardcore.txt";
        }
        return "";
    }

    //HELP FUNCTION FOR fill array
    private void fillArrayWith0(ArrayList<Item> itemList){
        for(Item item: itemList)
            item.setCount(0);
    }

    //HELP FUNCTION FOR fill array
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
