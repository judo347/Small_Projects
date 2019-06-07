package files;

import model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class XMLLoader {

    /** https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
     * */
    public static Model loadXml(File file){

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            ArrayList<Week> weeks = getAllWeeks(doc);
            ArrayList<Template> templates = getAllTemplates(doc);

            return new Model(weeks, templates);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException();
    }

    private static ArrayList<Template> getAllTemplates(Document doc){

        ArrayList<Template> collectedTemplates = new ArrayList<>();

        NodeList templates = doc.getElementsByTagName("templates");
        System.out.println(templates.getLength());
        for(int i = 0; i < templates.getLength(); i++){
            Node templateNode = templates.item(i);
            if(templateNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) templateNode;

                String templateName = element.getElementsByTagName("name").item(0).getTextContent();
                ArrayList<Goal> collectedGoals = getGoals(element);

                collectedTemplates.add(new Template(collectedGoals, templateName));
            }
        }

        return collectedTemplates;
    }

    private static ArrayList<Week> getAllWeeks(Document doc){

        ArrayList<Week> collectedWeeks = new ArrayList<>();

        NodeList weeks = doc.getElementsByTagName("weeks");
        for(int i=0; i<weeks.getLength(); i++)
        {
            Node weeksNode = weeks.item(i);
            if(weeksNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element weeksElement = (Element) weeksNode;

                NodeList weekNodes = weeksElement.getElementsByTagName("week");
                for(int x = 0; x < weekNodes.getLength(); x++){
                    Node weekNode = weekNodes.item(x);
                    if(weekNode.getNodeType() == Node.ELEMENT_NODE){

                        Element weekElement = (Element) weekNode;

                        String mondayDateString = weekElement.getElementsByTagName("mondayDate").item(0).getTextContent();
                        Calendar date = getDateFromString(mondayDateString);

                        ArrayList<Goal> goals = getGoals(weekElement);

                        collectedWeeks.add(new Week(date, goals));
                    }
                }
            }
        }

        return collectedWeeks;
    }

    private static ArrayList<Goal> getGoals(Element parentElement){
        ArrayList<Goal> collectedGoals = new ArrayList<>();

        NodeList goals = parentElement.getElementsByTagName("goal");
        int i = 0;
        for(; i < goals.getLength(); i++){
            Node goalNode = goals.item(i);
            if(goalNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) goalNode;

                String description = element.getElementsByTagName("description").item(0).getTextContent();
                String plannedDistance = element.getElementsByTagName("plannedDistance").item(0).getTextContent();
                String plannedDuration = element.getElementsByTagName("plannedDuration").item(0).getTextContent();
                String plannedDay = element.getElementsByTagName("plannedDay").item(0).getTextContent();
                String completedDistance = element.getElementsByTagName("completedDistance").item(0).getTextContent();
                String completedDuration = element.getElementsByTagName("completedDuration").item(0).getTextContent();
                String completedDay = element.getElementsByTagName("completedDay").item(0).getTextContent();

                Goal goal = new Goal();

                if(description.compareTo("") != 0)
                    goal.setDescription(description);
                if(plannedDistance.compareTo("") != 0)
                    goal.setPlannedDistance(plannedDistance);
                if(plannedDuration.compareTo("") != 0)
                    goal.setPlannedMinutes(plannedDuration);
                if(plannedDay.compareTo("") != 0)
                    goal.setPlannedWeekday(Weekday.getWeekdayFromString(plannedDay));
                if(completedDistance.compareTo("") != 0)
                    goal.setCompletedDistance(completedDistance);
                if(completedDuration.compareTo("") != 0)
                    goal.setCompletedMinutes(completedDuration);
                if(completedDay.compareTo("") != 0)
                    goal.setCompletedWeekday(Weekday.getWeekdayFromString(completedDay));

                collectedGoals.add(goal);
            }
        }

        return collectedGoals;
    }

    /** Takes a string in the format day-month-year and returns a date containing the given information. */
    private static Calendar getDateFromString(String dateString){

        StringBuilder sbDay = new StringBuilder();
        StringBuilder sbMonth = new StringBuilder();
        StringBuilder sbYear = new StringBuilder();

        int dashCounter = 0;

        for(char c : dateString.toCharArray()){

            if(c == '-')
                dashCounter++;
            else if(dashCounter == 0)
                sbDay.append(c);
            else if(dashCounter == 1)
                sbMonth.append(c);
            else if(dashCounter == 2)
                sbYear.append(c);
            else
                throw new IllegalArgumentException();

        }

        int day = Integer.parseInt(sbDay.toString());
        int month = Integer.parseInt(sbMonth.toString());
        int year = Integer.parseInt(sbYear.toString());

        return new Calendar.Builder().setDate(year, month-1, day).build();
    }
}
