package baseSwing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/** TODO NEEDED:
 *  Save on exit!!
 *  Create file if not found
 *
 *  TODO EXTRA
 *  Better Logo?
 *  PoE colors?
 *  Start at last leauge before close?
 *  Make background maybe poe picture?
 *
 *  TODO BUGS
 *  Something is wrong with the sets with 2xOneHand and 1xOneHand + shield (not refreshing correctly?)
 *  Paths in JPaneWithir... and OWnFFIle..
 */

public class ChaosRecipeWindow extends JFrame{

    private JButton butStandard;
    private JButton butHardcore;
    private JButton butLeagueStandard;
    private JButton butLeagueHardcore;

    private JButton butHelmetPlus;
    private JButton butHelmetMinus;
    private JButton butGlovesPlus;
    private JButton butGlovesMinus;
    private JButton butBootsPlus;
    private JButton butBootsMinus;
    private JButton butChestPlus;
    private JButton butChestMinus;
    private JButton butBeltPlus;
    private JButton butBeltMinus;
    private JButton butRingPlus;
    private JButton butRingMinus;
    private JButton butAmuletPlus;
    private JButton butAmuletMinus;
    private JButton butOneHandWepPlus;
    private JButton butOneHandWepMinus;
    private JButton butShieldPlus;
    private JButton butShieldMinus;
    private JButton butTwoHandWepPlus;
    private JButton butTwoHandWepMinus;

    private JLabel labTotalSets;
    private JButton butTurnInSet;

    private String background = "#201911";
    private String contentPanesColor = "#110E09";
    private ArrayList<Item> itemList = new ArrayList<>();
    private int currentLeague = 0;


    public ChaosRecipeWindow(){
        //Create the window
        ImageIcon windowLogo = new ImageIcon("E:\\SourceTree\\Small_Projects\\PoE- ChaosRecipeWindow\\src\\baseSwing\\logo2.png");
        JFrame frame = new JFrame("PoE: Chaos Recipe Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //TODO: Save on close
        frame.setAlwaysOnTop(true); //TODO: Maybe not
        frame.setResizable(false);
        frame.setIconImage(windowLogo.getImage());

        //Initializing counter array
        fillArrayList(currentLeague);

        //Content pane
        addComponentsToPane(frame.getContentPane());

        //Update completed sets
        calculateSets();

        //Display window
        frame.pack();
        frame.setSize(420, 400);
        frame.setVisible(true);
    }

    /** Added content to the window. Takes the frame as input. */
    private void addComponentsToPane(Container pane){

        //Creating buttons
        createButtons();

        //Split
        JPanel line11 = new JPanel();
        line11.setBackground(new Color(0,0,0,0)); //TODO WORKING HERE
        line11.setAlignmentX(Component.RIGHT_ALIGNMENT);
        line11.add(butHelmetMinus); line11.add(butHelmetPlus);
        JPanel line12 = new JPanel();
        line12.setBackground(new Color(0,0,0,0));
        line12.setAlignmentX(Component.LEFT_ALIGNMENT);
        line12.add(butGlovesPlus); line12.add(butGlovesMinus);

        JPanel line21 = new JPanel();
        line21.setBackground(new Color(0,0,0,0));
        line21.setAlignmentX(Component.RIGHT_ALIGNMENT);
        line21.add(butBootsMinus); line21.add(butBootsPlus);
        JPanel line22 = new JPanel();
        line22.setBackground(new Color(0,0,0,0));
        line22.setAlignmentX(Component.LEFT_ALIGNMENT);
        line22.add(butChestPlus); line22.add(butChestMinus);

        JPanel line31 = new JPanel();
        line31.setBackground(new Color(0,0,0,0));
        line31.setAlignmentX(Component.RIGHT_ALIGNMENT);
        line31.add(butBeltMinus); line31.add(butBeltPlus);
        JPanel line32 = new JPanel();
        line32.setBackground(new Color(0,0,0,0));
        line32.setAlignmentX(Component.LEFT_ALIGNMENT);
        line32.add(butRingPlus); line32.add(butRingMinus);

        JPanel line41 = new JPanel();
        line41.setBackground(new Color(0,0,0,0));
        line41.setAlignmentX(Component.RIGHT_ALIGNMENT);
        line41.add(butAmuletMinus); line41.add(butAmuletPlus);
        JPanel line42 = new JPanel();
        line42.setBackground(new Color(0,0,0,0));
        line42.setAlignmentX(Component.LEFT_ALIGNMENT);
        line42.add(butTwoHandWepPlus); line42.add(butTwoHandWepMinus);

        JPanel line51 = new JPanel();
        line51.setBackground(new Color(0,0,0,0));
        line51.setAlignmentX(Component.RIGHT_ALIGNMENT);
        line51.add(butOneHandWepMinus); line51.add(butOneHandWepPlus);
        JPanel line52 = new JPanel();
        line52.setBackground(new Color(0,0,0,0));
        line52.setAlignmentX(Component.LEFT_ALIGNMENT);
        line52.add(butShieldPlus); line52.add(butShieldMinus);

        //Lines
        JPanel line1 = new JPanel();
        line1.setBackground(new Color(0,0,0,0));
        line1.add(line11); line1.add(line12);
        JPanel line2 = new JPanel();
        line2.setBackground(new Color(0,0,0,0));
        line2.add(line21); line2.add(line22);
        JPanel line3 = new JPanel();
        line3.setBackground(new Color(0,0,0,0));
        line3.add(line31); line3.add(line32);
        JPanel line4 = new JPanel();
        line4.setBackground(new Color(0,0,0,0));
        line4.add(line41); line4.add(line42);
        JPanel line5 = new JPanel();
        line5.setBackground(new Color(0,0,0,0));
        line5.add(line51); line5.add(line52);

        //Topbar
        JPanel topBarPanel = new JPanel();
        topBarPanel.setBackground(Color.decode(background));
        topBarPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topBarPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,15));
        topBarPanel.add(butStandard);
        topBarPanel.add(butHardcore);
        topBarPanel.add(butLeagueStandard);
        topBarPanel.add(butLeagueHardcore);

        //Content panel
        //JPanaWithPictureBackgorund contentPanel = new JPanaWithPictureBackgorund();
        //contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.decode(contentPanesColor));
        //contentPanel.setMaximumSize(new Dimension(600,1000)); //TODO, might not be needed
        //contentPanel.setMinimumSize(new Dimension(0,800));
        contentPanel.add(line1);
        contentPanel.add(line2);
        contentPanel.add(line3);
        contentPanel.add(line4);
        contentPanel.add(line5);

        //Buttom bar
        JPanel buttomBarLabel = new JPanel(); //TODO Limit buttonBar height? ok without?
        labTotalSets = new JLabel("Total Sets: XX");
        labTotalSets.setForeground(Color.decode("#EAAA44"));
        buttomBarLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        buttomBarLabel.setBackground(Color.decode(background));
        buttomBarLabel.add(labTotalSets);

        JPanel buttomBarButton = new JPanel();
        buttomBarButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        buttomBarButton.setBackground(Color.decode(background));
        buttomBarButton.add(butTurnInSet);

        JPanel buttomBar = new JPanel();
        buttomBar.setLayout(new BoxLayout(buttomBar, BoxLayout.Y_AXIS));
        buttomBar.setBackground(Color.decode(background));
        buttomBar.add(buttomBarLabel);
        buttomBar.add(buttomBarButton);

        //Master Pane
        JPanel masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        masterPanel.setBackground(Color.decode(background));
        masterPanel.add(topBarPanel);
        masterPanel.add(contentPanel);
        masterPanel.add(buttomBar);

        //Adding masterPanel to frame
        pane.add(masterPanel);
    }

    /** Creates the buttons. (Calls setText and addFunc) */
    private void createButtons(){
        //TODO: Combine the JButton("");
        butStandard = new JButton("Standard");
        butHardcore = new JButton("Hardcore");
        butLeagueStandard = new JButton("League SD");
        butLeagueHardcore = new JButton("League HC");


        butHelmetMinus = new JButton();
        butHelmetPlus = new JButton();
        butGlovesMinus = new JButton();
        butGlovesPlus = new JButton();
        butBootsPlus = new JButton();
        butBootsMinus = new JButton();
        butChestPlus = new JButton();
        butChestMinus = new JButton();
        butBeltPlus = new JButton();
        butBeltMinus = new JButton();
        butRingPlus = new JButton();
        butRingMinus = new JButton();
        butAmuletPlus = new JButton();
        butAmuletMinus = new JButton();
        butTwoHandWepPlus = new JButton();
        butTwoHandWepMinus = new JButton();
        butOneHandWepPlus = new JButton();
        butOneHandWepMinus = new JButton();
        butShieldPlus = new JButton();
        butShieldMinus = new JButton();

        butTurnInSet = new JButton();

        setAndUpdateTextButtons();

        addFunctionButtons();
    }

    /** Uses the itemList to put text on buttons. */
    private void setAndUpdateTextButtons(){

        //The list used to determine number of item left after turn in.
        ArrayList<Item> afterTurnIn = itemsLeftAfterTurnIn(this.itemList);

        butHelmetMinus.setText("-");
        butHelmetPlus.setText(itemList.get(0).getName() + ": " + itemList.get(0).getCount() + " (" + afterTurnIn.get(0).getCount() + ")");
        butGlovesPlus.setText(itemList.get(1).getName() + ": " + itemList.get(1).getCount() + " (" + afterTurnIn.get(1).getCount() + ")");
        butGlovesMinus.setText("-");
        butBootsPlus.setText(itemList.get(2).getName() + ": " + itemList.get(2).getCount() + " (" + afterTurnIn.get(2).getCount() + ")");
        butBootsMinus.setText("-");
        butChestPlus.setText(itemList.get(3).getName() + ": " + itemList.get(3).getCount() + " (" + afterTurnIn.get(3).getCount() + ")");
        butChestMinus.setText("-");
        butBeltPlus.setText(itemList.get(4).getName() + ": " + itemList.get(4).getCount() + " (" + afterTurnIn.get(4).getCount() + ")");
        butBeltMinus.setText("-");
        butRingPlus.setText(itemList.get(5).getName() + ": " + itemList.get(5).getCount() + " (" + afterTurnIn.get(5).getCount() + ")");
        butRingMinus.setText("-");
        butAmuletPlus.setText(itemList.get(6).getName() + ": " + itemList.get(6).getCount() + " (" + afterTurnIn.get(6).getCount() + ")");
        butAmuletMinus.setText("-");
        butTwoHandWepPlus.setText(itemList.get(7).getName() + ": " + itemList.get(7).getCount() + " (" + afterTurnIn.get(7).getCount() + ")");
        butTwoHandWepMinus.setText("-");
        butShieldPlus.setText(itemList.get(8).getName() + ": " + itemList.get(8).getCount() + " (" + afterTurnIn.get(8).getCount() + ")");
        butShieldMinus.setText("-");
        butOneHandWepPlus.setText(itemList.get(9).getName() + ": " + itemList.get(9).getCount() + " (" + afterTurnIn.get(9).getCount() + ")");
        butOneHandWepMinus.setText("-");

        butTurnInSet.setText("Turn in set");
    }

    /** Adds actions to the buttons */
    private void addFunctionButtons(){
        butStandard.addActionListener(e -> changeLeague(0));
        butHardcore.addActionListener(e -> changeLeague(1));
        butLeagueStandard.addActionListener(e -> changeLeague(2));
        butLeagueHardcore.addActionListener(e -> changeLeague(3));


        butHelmetMinus.addActionListener(e -> buttonOneDown(0));
        butHelmetPlus.addActionListener(e -> buttonOneUp(0));
        butGlovesMinus.addActionListener(e -> buttonOneDown(1));
        butGlovesPlus.addActionListener(e -> buttonOneUp(1));
        butBootsPlus.addActionListener(e -> buttonOneUp(2));
        butBootsMinus.addActionListener(e -> buttonOneDown(2));
        butChestPlus.addActionListener(e -> buttonOneUp(3));
        butChestMinus.addActionListener(e -> buttonOneDown(3));
        butBeltPlus.addActionListener(e -> buttonOneUp(4));
        butBeltMinus.addActionListener(e -> buttonOneDown(4));
        butRingPlus.addActionListener(e -> buttonOneUp(5));
        butRingMinus.addActionListener(e -> buttonOneDown(5));
        butAmuletPlus.addActionListener(e -> buttonOneUp(6));
        butAmuletMinus.addActionListener(e -> buttonOneDown(6));
        butTwoHandWepPlus.addActionListener(e -> buttonOneUp(7));
        butTwoHandWepMinus.addActionListener(e -> buttonOneDown(7));
        butShieldPlus.addActionListener(e -> buttonOneUp(8));
        butShieldMinus.addActionListener(e -> buttonOneDown(8));
        butOneHandWepPlus.addActionListener(e -> buttonOneUp(9));
        butOneHandWepMinus.addActionListener(e -> buttonOneDown(9));

        butTurnInSet.addActionListener(e -> turnInSet());
    }

    /** Action for button: adding 1 to count of desired item.
     *  @param itemNumber the desired item to change. */
    private void buttonOneUp(int itemNumber){
        itemList.get(itemNumber).setCount(itemList.get(itemNumber).getCount() + 1);
        setAndUpdateTextButtons();
        calculateSets();
    }

    /** Action for button: subtracting 1 from count of desired item.
     *  @param itemNumber the desired item to change. */
    private void buttonOneDown(int itemNumber){
        if(itemList.get(itemNumber).getCount() != 0)
            itemList.get(itemNumber).setCount(itemList.get(itemNumber).getCount() - 1);
        setAndUpdateTextButtons();
        calculateSets();
    }

    /** Fills the array with items and count from file to the current selected league.
     *  Gets called when the window is created.
     *  @param league the current selected league. */
    private void fillArrayList(int league){
        initArrayItems();

        OwnFileManager ofm = new OwnFileManager();

        ofm.fillArray(itemList, league);

    }

    /** Initializes the array of items. Fills it with items with the right names and count 0 */
    private void initArrayItems(){
        itemList.add(new Item("Helmet"));
        itemList.add(new Item("Gloves"));
        itemList.add(new Item("Boots"));
        itemList.add(new Item("Chest"));
        itemList.add(new Item("Belt"));
        itemList.add(new Item("Ring"));
        itemList.add(new Item("Amulet"));
        itemList.add(new Item("TwoHandWep"));
        itemList.add(new Item("Shield"));
        itemList.add(new Item("OneHandWep"));
    }

    /** Changes the league. Checks for valid input.
     *  Saves the current itemlist and load one from a specific file.
     *  Standard = 0, Hardcore = 1, Temp Standard = 2, Temp Hardcore = 3.
     *  @param desiredLeague the league to change to.*/
    private void changeLeague(int desiredLeague){
        if(desiredLeague > -1 && desiredLeague < 4){
            if(this.currentLeague != desiredLeague){
                OwnFileManager ofm = new OwnFileManager();
                ofm.saveArray(itemList, this.currentLeague);
                boolean success = ofm.fillArray(itemList, desiredLeague);//load new one
                if(success)
                    this.currentLeague = desiredLeague;

                setAndUpdateTextButtons();
                calculateSets();
            }
        }
    }

    /** Calculates the number of sets that can be completed. Uses preferred order.
     *  Changes the TotalSets label. */
    private void calculateSets(){
        boolean isAllTrue;
        int completedSets = 0;

        //Make work copy of list
        ArrayList<Item> workingItemlist = new ArrayList<>();
        for(int i = 0; i < itemList.size(); i++)
            workingItemlist.add(new Item(itemList.get(i).getName(), itemList.get(i).getCount()));

        //Counts completed sets in preferred order.
        do{
            if(setCompleteMethod1(workingItemlist)){ //TwoHandWep
                removeSetCompleteMethod1(workingItemlist);
                completedSets++;
            }
            if(setCompleteMethod3(workingItemlist)){ //2xOneHandWep
                removeSetCompleteMethod3(workingItemlist);
                completedSets++;
            }
            if(setCompleteMethod2(workingItemlist)){ //OneHandWep + Shield
                removeSetCompleteMethod2(workingItemlist);
                completedSets++;
            }

            //Is there still a set to be made?
            isAllTrue = setCompleteMethod1(workingItemlist) || setCompleteMethod2(workingItemlist) || setCompleteMethod3(workingItemlist);
        } while(isAllTrue);

        labTotalSets.setText("Total Sets: " + completedSets);
    }

    /** The first method check if it is possible to complete a set with a TWO-HANDED WEAPON.
     *  The second method removes the set.
     * @param itemList work copy of the original itemlist */
    private boolean setCompleteMethod1(ArrayList<Item> itemList){
        if(itemList.get(0).getCount() >= 1 ){ //Helmet
            if(itemList.get(1).getCount() >= 1 ){ //Gloves
                if(itemList.get(2).getCount() >= 1 ){ //Boots
                    if(itemList.get(3).getCount() >= 1 ){ //Chest
                        if(itemList.get(4).getCount() >= 1 ){ //Belt
                            if(itemList.get(5).getCount() >= 2 ){ //Ring
                                if(itemList.get(6).getCount() >= 1 ) { //Amulet
                                    if(itemList.get(7).getCount() >= 1) { //TwoHandWep
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
    private void removeSetCompleteMethod1(ArrayList<Item> itemList){
        itemList.get(0).remove1Count();
        itemList.get(1).remove1Count();
        itemList.get(2).remove1Count();
        itemList.get(3).remove1Count();
        itemList.get(4).remove1Count();
        itemList.get(5).remove2Count();
        itemList.get(6).remove1Count();
        itemList.get(7).remove1Count();
    }

    /** The first method check if it is possible to complete a set with a 2xONE-HANDED WEAPON.
     *  The second method removes the set.
     * @param itemList work copy of the original itemlist */
    private boolean setCompleteMethod2(ArrayList<Item> itemList){
        if(itemList.get(0).getCount() >= 1 ){ //Helmet
            if(itemList.get(1).getCount() >= 1 ){ //Gloves
                if(itemList.get(2).getCount() >= 1 ){ //Boots
                    if(itemList.get(3).getCount() >= 1 ){ //Chest
                        if(itemList.get(4).getCount() >= 1 ){ //Belt
                            if(itemList.get(5).getCount() >= 2 ){ //Ring
                                if(itemList.get(6).getCount() >= 1 ) { //Amulet
                                    if(itemList.get(9).getCount() >= 2) { //OneHandWep
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
    public void removeSetCompleteMethod2(ArrayList<Item> itemList){
        itemList.get(0).remove1Count();
        itemList.get(1).remove1Count();
        itemList.get(2).remove1Count();
        itemList.get(3).remove1Count();
        itemList.get(4).remove1Count();
        itemList.get(5).remove2Count();
        itemList.get(6).remove1Count();
        itemList.get(9).remove2Count();
    }

    /** The first method check if it is possible to complete a set with a ONE-HANDED WEAPON + SHIELD.
     *  The second method removes the set.
     * @param itemList work copy of the original itemlist */
    private boolean setCompleteMethod3(ArrayList<Item> itemList){
        if(itemList.get(0).getCount() >= 1 ){ //Helmet
            if(itemList.get(1).getCount() >= 1 ){ //Gloves
                if(itemList.get(2).getCount() >= 1 ){ //Boots
                    if(itemList.get(3).getCount() >= 1 ){ //Chest
                        if(itemList.get(4).getCount() >= 1 ){ //Belt
                            if(itemList.get(5).getCount() >= 2 ){ //Ring
                                if(itemList.get(6).getCount() >= 1 ) { //Amulet
                                    if(itemList.get(9).getCount() >= 1) { //OneHandWep
                                        if(itemList.get(8).getCount() >= 1) { //Shield
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
    public void removeSetCompleteMethod3(ArrayList<Item> itemList){
        itemList.get(0).remove1Count();
        itemList.get(1).remove1Count();
        itemList.get(2).remove1Count();
        itemList.get(3).remove1Count();
        itemList.get(4).remove1Count();
        itemList.get(5).remove2Count();
        itemList.get(8).remove1Count();
        itemList.get(9).remove1Count();
    }

    /** Removes a set from the itemList. Uses pref. order.
     *  Used by the button turnInSet. */
    public void turnInSet(){
        //1 - 3 - 2
        if(setCompleteMethod1(this.itemList)){
            removeSetCompleteMethod1(this.itemList);
            setAndUpdateTextButtons();
            calculateSets();
            return;
        }
        if(setCompleteMethod3(this.itemList)){
            removeSetCompleteMethod3(this.itemList);
            setAndUpdateTextButtons();
            calculateSets();
            return;
        }
        if(setCompleteMethod2(itemList)){
            removeSetCompleteMethod2(this.itemList);
            setAndUpdateTextButtons();
            calculateSets();
            return;
        }
    }

    /** TODO */
    public ArrayList<Item> itemsLeftAfterTurnIn(ArrayList<Item> itemList){
        //Make work copy of list
        ArrayList<Item> workingItemlist = new ArrayList<>();
        for(int i = 0; i < itemList.size(); i++)
            workingItemlist.add(new Item(itemList.get(i).getName(), itemList.get(i).getCount()));

        boolean isAllTrue;

        do{
            if(setCompleteMethod1(workingItemlist)){ //TwoHandWep
                removeSetCompleteMethod1(workingItemlist);
            }
            if(setCompleteMethod3(workingItemlist)){ //2xOneHandWep
                removeSetCompleteMethod3(workingItemlist);
            }
            if(setCompleteMethod2(workingItemlist)){ //OneHandWep + Shield
                removeSetCompleteMethod2(workingItemlist);
            }

            //Is there still a set to be made?
            isAllTrue = setCompleteMethod1(workingItemlist) || setCompleteMethod2(workingItemlist) || setCompleteMethod3(workingItemlist);
        } while(isAllTrue);

        return workingItemlist;
    }
}
