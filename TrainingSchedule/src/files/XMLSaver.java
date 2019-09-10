package files;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.Goal;
import model.Model;
import model.Template;
import model.Week;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLSaver {
    public XMLSaver() {
    }

    public static void saveXml(File file, Model model) {
        Document doc = getXmlFromModel(model);

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("method", "html");
            StringWriter stringWriter = new StringWriter();
            StreamResult result = new StreamResult(file);
            transformer.transform(new DOMSource(doc), result);
            System.out.println(stringWriter.toString());
        } catch (TransformerConfigurationException var6) {
            var6.printStackTrace();
        } catch (TransformerException var7) {
            var7.printStackTrace();
        }

    }

    private static String htmlFormatXmlString(String input) {
        char[] inputChars = input.toCharArray();
        StringBuilder output = new StringBuilder();

        for(int i = 0; i < inputChars.length; ++i) {
            char currentChar = inputChars[i];
            if (currentChar == '/' && inputChars[i - 1] == ' ') {
                String tag = getTag(inputChars, i - 2);
                output.append("></").append(tag).append('>');
                ++i;
            } else {
                output.append(currentChar);
            }
        }

        return output.toString();
    }

    private static String getTag(char[] input, int lastCharOfTag) {
        StringBuilder output = new StringBuilder();

        for(int i = lastCharOfTag; input[i] != '<'; --i) {
            output.append(input[i]);
        }

        return output.toString();
    }

    private static Document getXmlFromModel(Model model) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("data");
            doc.appendChild(rootElement);
            Element weeksElement = doc.createElement("weeks");
            rootElement.appendChild(weeksElement);
            fillElementWithWeeks(weeksElement, doc, model.getWeeks());
            Element templatesElement = doc.createElement("templates");
            rootElement.appendChild(templatesElement);
            fillElementWithTemplates(templatesElement, doc, model.getTemplates());
            return doc;
        } catch (ParserConfigurationException var7) {
            var7.printStackTrace();
            return null;
        }
    }

    private static void fillElementWithTemplates(Element parentElement, Document doc, ArrayList<Template> templates) {
        Iterator var3 = templates.iterator();

        while(var3.hasNext()) {
            Template template = (Template)var3.next();
            Element templateElement = doc.createElement("template");
            Element templateNameElement = doc.createElement("name");
            templateNameElement.appendChild(doc.createTextNode(template.getTemplateName()));
            templateElement.appendChild(templateNameElement);
            templateElement.appendChild(fillElementWithGoals(doc, template.getGoals()));
            parentElement.appendChild(templateElement);
        }

    }

    private static void fillElementWithWeeks(Element parentElement, Document doc, ArrayList<Week> weeks) {
        Iterator var3 = weeks.iterator();

        while(var3.hasNext()) {
            Week week = (Week)var3.next();
            Element weekElement = doc.createElement("week");
            Element mondayDate = doc.createElement("mondayDate");
            mondayDate.appendChild(doc.createTextNode(week.getMondayDateString()));
            weekElement.appendChild(mondayDate);
            weekElement.appendChild(fillElementWithGoals(doc, week.getGoals()));
            parentElement.appendChild(weekElement);
        }

    }

    private static Element fillElementWithGoals(Document doc, ArrayList<Goal> goals) {
        Element goalsElement = doc.createElement("goals");
        Iterator var3 = goals.iterator();

        while(var3.hasNext()) {
            Goal goal = (Goal)var3.next();
            Element goalElement = doc.createElement("goal");
            fillGoalElementWithInformation(goalElement, doc, goal);
            goalsElement.appendChild(goalElement);
        }

        return goalsElement;
    }

    private static void fillGoalElementWithInformation(Element goalElement, Document doc, Goal goal) {
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
