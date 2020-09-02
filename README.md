# Small_Projects

## EFT: Assistant

The inspiration for the project is coming from playing the game. Escape from Tarkov is a survival shooter, which contains a lot of quests. Each quest is given by a trader and consists of a bit of conversation and some objectives. These objectives is more often than not very precise and you often have to go to the wiki to gain the needed information for you to be able to complete the quest. This is infuriating when some quests takes longer to lookup than complete. And this project was then started with the goal of making an application which would make the completion of quests as easy as possible.

### Application Requirements/Specification

*The project was started with the goal of making an application which would make the completion of quests as easy as possible.*

This should be done by **tracking the players progression** through the quests, and by providing **additional resources** which all aim to ease the process of quest completion.

### Application Structure

![](https://github.com/judo347/Small_Projects/blob/master/EFT-Assistent/umlDiagram/edited/edited.png)

The design pattern used for the structure is: model-view-controller. As displayed in the above diagram. The application will additionally have a data section which will contain the raw data describing quests and their relations.

*JavaFx models the view and control part of the pattern as .fxml and .java files respectively, and the two is for convenience sake combined in the diagram.*

### Solution

![](https://github.com/judo347/Small_Projects/blob/master/EFT-Assistent/img/overall.PNG)

#### Quest model

![](https://github.com/judo347/Small_Projects/blob/master/EFT-Assistent/img/questJson.PNG)

The game currently contains just above 200 quest. Each quest has manually been entered into a datafile, as JSON format, where each quest has a name and additional properties such as: required level for acceptance, previous quests, quest giver and more.

The structure of the quests can logically be seen as a tree with several root nodes being the starting quests given at level 1. Each completed quest then unlocks zero to more quests which can be accepted if level and trader reputation requirements are met. When the application launches a tree model of the quests is initialized and instantiated and can be viewed with the quest overview tool as seen below:

![](https://github.com/judo347/Small_Projects/blob/master/EFT-Assistent/img/questOverview.PNG)

Several features is easily accomplished when utilizing a tree structure. For instance when a user launches the application for the first time, he might already have completed some quests and can, in *God-mode*, click a given quest and using the tree, all prior quests will be completed aswel. 



### Future works

**Deployment**

The application is and has been a great help to me throughout my endeavours in the world of Escape from Tarkov - but deploying the application to freely be used by all Escape from Tarkov players is the goal.

**Quest Editor Tool**

A tool for adding and editing quest would be very useful. This would replace the process of manually adding and editing the JSON datafile. The game is still in early access, so chances to quests is inevitable. 