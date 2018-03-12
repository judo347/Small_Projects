package baseJSON.cookieWork;

//https://www.youtube.com/watch?v=OaJ43rTJRqw //TODO: WATCH AND CREATE

import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        cookieLogin();
        getCharStashInfoFromURL();
    }

    static void cookieLogin(){
        String PoELogin = "https://www.pathofexile.com/login";
        String poesessid = "32bd224db9dc3bcf472f656eeaa2af10"; //Used as cookie name

        try {
            URL url = new URL(PoELogin);
            URLConnection conn = url.openConnection();

            //set the cookie value to send
            conn.setRequestProperty("Cookie", poesessid);

            //send the request to the server
            conn.connect();

            System.out.println(conn.getRequestProperty(poesessid));

            System.out.println(getStringFromInputStream(conn.getInputStream()));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void getCharStashInfoFromURL(){
        try{
            //https://stackoverflow.com/questions/1485708/how-do-i-do-a-http-get-in-java //TODO: MAYBE

            // create a url object
            URL charStashUrl = new URL("https://www.pathofexile.com/character-window/get-stash-items?league=Standard&accountName=lekkkim&tabs=1&tabindex=1"); //returns 403 if no login

            // create a urlconnection object
            URLConnection urlConnection = charStashUrl.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            String charStashInfoFile = "E:\\SourceTree\\Small_Projects\\PoE- ChaosRecipeWindow\\src\\baseJSON\\cookieWork\\charStashInfo.txt";


            FileWriter fw = new FileWriter(charStashInfoFile, false); //False = not append
            BufferedWriter bw = new BufferedWriter(fw);

            while((line = br.readLine()) != null)
                bw.write(line); //THE STUFF TO WRITE


            bw.flush();
            bw.close();
            br.close();
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
}
