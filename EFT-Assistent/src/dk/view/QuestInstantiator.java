package dk.view;

import dk.model.*;

import java.util.ArrayList;

public class QuestInstantiator {

    public ArrayList<Quest> allQuests;

    public QuestInstantiator() {
        QuestLoader questLoader = new QuestLoader();
        allQuests = questLoader.allQuest;
    }

    /** TEMP METHOD */
    public Quest getQuestTemp1(){
        ArrayList<MapType> maps = new ArrayList<>();
        maps.add(MapType.SHORELINE);

        QuestObjectives inner1 = new QuestObjectives("West wing roof", null);
        QuestObjectives inner2 = new QuestObjectives("Radar", null);
        QuestObjectives inner3 = new QuestObjectives("Right side of map, tower", null);
        ArrayList<QuestObjectives> outerObjectives = new ArrayList<>();
        outerObjectives.add(inner1);
        outerObjectives.add(inner2);
        outerObjectives.add(inner3);
        //QuestObjectives outer = new QuestObjectives("3x Place jammers at antennas", outerObjectives);

        ArrayList<String> requirements = new ArrayList<>();
        requirements.add("Key 303 west wing");
        requirements.add("Bottle of water");

        //Quest quest1 = new Quest("Signal Part 3", maps, TraderType.MECHANIC, 0, outer, requirements);

        return null;
        //return quest1;
    }
}
