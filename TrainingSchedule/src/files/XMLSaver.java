package files;

import model.Goal;
import model.Model;
import model.Template;
import model.Week;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

//https://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/

public class XMLSaver {

    public static void saveXml(File file, Model model){

        Document doc = getXmlFromModel(model);


        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "html"); //https://stackoverflow.com/questions/13860041/java-xml-self-closing-tags
            // transformer.

            StringWriter stringWriter = new StringWriter();

            StreamResult result = new StreamResult(file);
            //StreamResult result =  new StreamResult(System.out); //DEBUGGING
            //StreamResult result =  new StreamResult(stringWriter); //DEBUGGING

            transformer.transform(new DOMSource(doc), result);

            //String tempString = htmlFormatXmlString(stringWriter.toString());

            System.out.println(stringWriter.toString());
            //System.out.println(tempString);


        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private static String htmlFormatXmlString(String input){


        char[] inputChars = input.toCharArray();
        StringBuilder output = new StringBuilder();


        for(int i = 0; i < inputChars.length; i++){

            char currentChar = inputChars[i];

            if(currentChar == '/'){
                if(inputChars[i-1] == ' '){

                    //Get tag.
                    String tag = getTag(inputChars, i-2);
                    //append
                    output.append("></").append(tag).append('>');
                    //skip
                    i++;
                    continue;
                }
            }

            output.append(currentChar);
        }



        /*
        throw new IllegalArgumentException(); //TODO

        StringBuilder formattetString = new StringBuilder();

        boolean inTag = false;
        boolean lastCharIsSpace = false;
        boolean shouldAppendTag = false;
        StringBuilder endTag = null;

        for(char c : input.toCharArray()){

            //In a tag - save word
            if(inTag){
                if(endTag == null)
                    endTag = new StringBuilder();

                endTag.append(c);
            }

            if(c == '<')
                inTag = true;
            else if(c == '>'){

                if(shouldAppendTag){
                    formattetString.append('>');
                    formattetString.append(endTag);
                    shouldAppendTag = false;
                    inTag = false;
                    endTag = null;
                    continue;
                }

                inTag = false;
                endTag = null;
            } else if(c == ' '){
                lastCharIsSpace = true;
                continue;
            } else if(lastCharIsSpace && c == '/'){
                formattetString.append('/');
                lastCharIsSpace = false;
                shouldAppendTag = false;
            }

            lastCharIsSpace = false;
            formattetString.append(c);
        }

        /*

         */
        return output.toString();
    }

    private static String getTag(char[] input, int lastCharOfTag){

        StringBuilder output = new StringBuilder();

        for(int i = lastCharOfTag;;i--){

            if(input[i] == '<')
                return output.toString();
            else
                output.append(input[i]);
        }
    }

    /*
    private static String htmlFormatXmlString(String input){

        StringBuilder formattetString = new StringBuilder();

        boolean inTag = false;
        boolean lastCharIsSpace = false;
        boolean shouldAppendTag = false;
        StringBuilder endTag = null;

        for(char c : input.toCharArray()){

            //In a tag - save word
            if(inTag){
                if(endTag == null)
                    endTag = new StringBuilder();

                endTag.append(c);
            }

            if(c == '<')
                inTag = true;
            else if(c == '>'){

                if(shouldAppendTag){
                    formattetString.append('>');
                    formattetString.append(endTag);
                    shouldAppendTag = false;
                    inTag = false;
                    endTag = null;
                    continue;
                }

                inTag = false;
                endTag = null;
            } else if(c == ' '){
                lastCharIsSpace = true;
                continue;
            } else if(lastCharIsSpace && c == '/'){
                formattetString.append('/');
                lastCharIsSpace = false;
                shouldAppendTag = false;
            }

            lastCharIsSpace = false;
            formattetString.append(c);
        }

        return formattetString.toString();
    }*/

    private static Document getXmlFromModel(Model model){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            //Root element
            Element rootElement = doc.createElement("data");
            doc.appendChild(rootElement);

            //Weeks element
            Element weeksElement = doc.createElement("weeks");
            rootElement.appendChild(weeksElement);

            //Week elements
            fillElementWithWeeks(weeksElement, doc, model.getWeeks());

            //Templates element
            Element templatesElement = doc.createElement("templates");
            rootElement.appendChild(templatesElement);

            //fillTemplates
            fillElementWithTemplates(templatesElement, doc, model.getTemplates());

            return doc;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void fillElementWithTemplates(Element parentElement, Document doc, ArrayList<Template> templates){

        for(Template template : templates){

            Element templateElement = doc.createElement("template");

            Element templateNameElement = doc.createElement("name");
            templateNameElement.appendChild(doc.createTextNode(template.getTemplateName()));
            templateElement.appendChild(templateNameElement);

            //Goals
            //Element goalsElement = doc.createElement("goals");
            //fillElementWithGoals(goalsElement, doc, template.getGoals());
            templateElement.appendChild(fillElementWithGoals(doc, template.getGoals()));

            parentElement.appendChild(templateElement);
        }
    }

    private static void fillElementWithWeeks(Element parentElement, Document doc, ArrayList<Week> weeks){

        for(Week week : weeks){

            Element weekElement = doc.createElement("week");

            Element mondayDate = doc.createElement("mondayDate");
            mondayDate.appendChild(doc.createTextNode(week.getMondayDateString()));
            weekElement.appendChild(mondayDate);

            //Goals
            //Element goalsElement = doc.createElement("goals");
            //fillElementWithGoals(goalsElement, doc, week.getGoals());
            weekElement.appendChild(fillElementWithGoals(doc, week.getGoals()));

            parentElement.appendChild(weekElement);
        }
    }

    private static Element fillElementWithGoals(Document doc, ArrayList<Goal> goals){

        Element goalsElement = doc.createElement("goals");

        for(Goal goal : goals){
            Element goalElement = doc.createElement("goal");
            fillGoalElementWithInformation(goalElement, doc, goal);
            goalsElement.appendChild(goalElement);
        }

        return goalsElement;
    }

    private static void fillGoalElementWithInformation(Element goalElement, Document doc, Goal goal){

        //TODO is all elements a string and not null?

        Element description = doc.createElement("description");
        description.appendChild(doc.createTextNode(goal.getDescription()));
        goalElement.appendChild(description);

        Element plannedDistance = doc.createElement("plannedDistance");
        plannedDistance.appendChild(doc.createTextNode(goal.getPlannedDistance()));
        goalElement.appendChild(plannedDistance);

        Element plannedDuration = doc.createElement("plannedDuration");
        plannedDuration.appendChild(doc.createTextNode(goal.getPlannedMinutes()));
        goalElement.appendChild(plannedDuration);

        Element plannedDay = doc.createElement("plannedDay");
        plannedDay.appendChild(doc.createTextNode(goal.getPlannedWeekday()));
        goalElement.appendChild(plannedDay);

        Element completedDistance = doc.createElement("completedDistance");
        completedDistance.appendChild(doc.createTextNode(goal.getCompletedDistance()));
        goalElement.appendChild(completedDistance);

        Element completedDuration = doc.createElement("completedDuration");
        completedDuration.appendChild(doc.createTextNode(goal.getCompletedMinutes()));
        goalElement.appendChild(completedDuration);

        Element completedDay = doc.createElement("completedDay");
        completedDay.appendChild(doc.createTextNode(goal.getCompletedWeekday()));
        goalElement.appendChild(completedDay);
    }
}
