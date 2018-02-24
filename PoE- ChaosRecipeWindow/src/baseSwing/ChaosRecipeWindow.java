package baseSwing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/** TODO!
 *  Add button: turned in set
 *  Comments
 *  Save on exit!!
 *  PoE logo?
 *  PoE colors?
 */

/** BUGS:
 *  Buttons not connecting corrently
 *  Cannot save/load
 *  Cannot change league/file
 * */

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

    private Color contentPanesColor = Color.LIGHT_GRAY;

    private ArrayList<Item> itemList = new ArrayList<>();

    public ChaosRecipeWindow(){
        //Create the window
        JFrame frame = new JFrame("PoE: Chaos Recipe Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //TODO: Save on close
        frame.setAlwaysOnTop(true); //TODO: Maybe not
        frame.setResizable(false);

        //Initializing counter array
        fillArrayList();

        //Content pane
        addComponentsToPane(frame.getContentPane());

        //TODO: Load from file and set text of buttons

        //Display window
        frame.pack();
        frame.setSize(400, 330);
        frame.setVisible(true);
    }

    private void addComponentsToPane(Container pane){

        //Creating buttons
        createButtons();

        //Split
        JPanel line11 = new JPanel();
        line11.setBackground(contentPanesColor);
        line11.setAlignmentX(Component.RIGHT_ALIGNMENT);
        line11.add(butHelmetMinus); line11.add(butHelmetPlus);
        JPanel line12 = new JPanel();
        line12.setBackground(contentPanesColor);
        line12.setAlignmentX(Component.LEFT_ALIGNMENT);
        line12.add(butGlovesPlus); line12.add(butGlovesMinus);

        JPanel line21 = new JPanel();
        line21.setBackground(contentPanesColor);
        line21.setAlignmentX(Component.RIGHT_ALIGNMENT);
        line21.add(butBootsMinus); line21.add(butBootsPlus);
        JPanel line22 = new JPanel();
        line22.setBackground(contentPanesColor);
        line22.setAlignmentX(Component.LEFT_ALIGNMENT);
        line22.add(butChestPlus); line22.add(butChestMinus);

        JPanel line31 = new JPanel();
        line31.setBackground(contentPanesColor);
        line31.setAlignmentX(Component.RIGHT_ALIGNMENT);
        line31.add(butBeltMinus); line31.add(butBeltPlus);
        JPanel line32 = new JPanel();
        line32.setBackground(contentPanesColor);
        line32.setAlignmentX(Component.LEFT_ALIGNMENT);
        line32.add(butRingPlus); line32.add(butRingMinus);

        JPanel line41 = new JPanel();
        line41.setBackground(contentPanesColor);
        line41.setAlignmentX(Component.RIGHT_ALIGNMENT);
        line41.add(butAmuletMinus); line41.add(butAmuletPlus);
        JPanel line42 = new JPanel();
        line42.setBackground(contentPanesColor);
        line42.setAlignmentX(Component.LEFT_ALIGNMENT);
        line42.add(butTwoHandWepPlus); line42.add(butTwoHandWepMinus);

        JPanel line51 = new JPanel();
        line51.setBackground(contentPanesColor);
        line51.setAlignmentX(Component.RIGHT_ALIGNMENT);
        line51.add(butOneHandWepMinus); line51.add(butOneHandWepPlus);
        JPanel line52 = new JPanel();
        line52.setBackground(contentPanesColor);
        line52.setAlignmentX(Component.LEFT_ALIGNMENT);
        line52.add(butShieldPlus); line52.add(butShieldMinus);

        //Lines
        JPanel line1 = new JPanel();
        line1.setBackground(contentPanesColor);
        line1.add(line11); line1.add(line12);
        JPanel line2 = new JPanel();
        line2.setBackground(contentPanesColor);
        line2.add(line21); line2.add(line22);
        JPanel line3 = new JPanel();
        line3.setBackground(contentPanesColor);
        line3.add(line31); line3.add(line32);
        JPanel line4 = new JPanel();
        line4.setBackground(contentPanesColor);
        line4.add(line41); line4.add(line42);
        JPanel line5 = new JPanel();
        line5.setBackground(contentPanesColor);
        line5.add(line51); line5.add(line52);

        //Topbar
        JPanel topBarPanel = new JPanel();
        topBarPanel.setBackground(Color.DARK_GRAY);
        topBarPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topBarPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE,15));
        topBarPanel.add(butStandard);
        topBarPanel.add(butHardcore);
        topBarPanel.add(butLeagueStandard);
        topBarPanel.add(butLeagueHardcore);

        //Content panel
        JPanel contentPanel = new JPanel();
        //contentPanel.setBackground(Color.LIGHT_GRAY); //TODO: NOTWORKING
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        //contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT); //TODO NEEDED?
        contentPanel.add(line1);
        contentPanel.add(line2);
        contentPanel.add(line3);
        contentPanel.add(line4);
        contentPanel.add(line5);

        //Buttom bar
        JPanel buttomBar = new JPanel();
        buttomBar.setBackground(Color.DARK_GRAY);
        buttomBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 15));
        labTotalSets = new JLabel("Total Sets: XX");
        labTotalSets.setForeground(Color.ORANGE);
        buttomBar.add(labTotalSets);

        //Master Pane
        JPanel masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        //masterPanel.setAlignmentX(Component.LEFT_ALIGNMENT); //TODO DOES NOT MATTER?
        masterPanel.add(topBarPanel);
        masterPanel.add(contentPanel);
        masterPanel.add(buttomBar);

        //Adding masterPanel to frame
        pane.add(masterPanel);
    }

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

        setAndUpdateTextButtons();

        addFunctionButtons();
    }

    private void setAndUpdateTextButtons(){
        butHelmetMinus.setText("-");
        butHelmetPlus.setText(itemList.get(0).getName() + ": " + itemList.get(0).getCount());
        butGlovesPlus.setText(itemList.get(1).getName() + ": " + itemList.get(1).getCount());
        butGlovesMinus.setText("-");
        butBootsPlus.setText(itemList.get(2).getName() + ": " + itemList.get(2).getCount());
        butBootsMinus.setText("-");
        butChestPlus.setText(itemList.get(3).getName() + ": " + itemList.get(3).getCount());
        butChestMinus.setText("-");
        butBeltPlus.setText(itemList.get(4).getName() + ": " + itemList.get(4).getCount());
        butBeltMinus.setText("-");
        butRingPlus.setText(itemList.get(5).getName() + ": " + itemList.get(5).getCount());
        butRingMinus.setText("-");
        butAmuletPlus.setText(itemList.get(6).getName() + ": " + itemList.get(6).getCount());
        butAmuletMinus.setText("-");
        butOneHandWepPlus.setText(itemList.get(7).getName() + ": " + itemList.get(7).getCount());
        butOneHandWepMinus.setText("-");
        butShieldPlus.setText(itemList.get(8).getName() + ": " + itemList.get(8).getCount());
        butShieldMinus.setText("-");
        butTwoHandWepPlus.setText(itemList.get(9).getName() + ": " + itemList.get(9).getCount());
        butTwoHandWepMinus.setText("-");
    }

    private void addFunctionButtons(){
        //butStandard
        //butHardcore
        //butLeagueStandard
        //butLeagueHardcore


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
        butOneHandWepPlus.addActionListener(e -> buttonOneUp(8));
        butOneHandWepMinus.addActionListener(e -> buttonOneDown(8));
        butShieldPlus.addActionListener(e -> buttonOneUp(9));
        butShieldMinus.addActionListener(e -> buttonOneDown(9));
    }

    private void buttonOneUp(int itemNumber){
        itemList.get(itemNumber).setCount(itemList.get(itemNumber).getCount() + 1);
        setAndUpdateTextButtons();
    }

    private void buttonOneDown(int itemNumber){
        if(itemList.get(itemNumber).getCount() != 0)
            itemList.get(itemNumber).setCount(itemList.get(itemNumber).getCount() - 1);
        setAndUpdateTextButtons();
    }


    private void fillArrayList(){
        initArrayItems();


        OwnFileManager ofm = new OwnFileManager();

        ofm.fillArray(itemList);

    }

    private void initArrayItems(){
        itemList.add(new Item("Helmet"));
        itemList.add(new Item("Gloves"));
        itemList.add(new Item("Boots"));
        itemList.add(new Item("Chest"));
        itemList.add(new Item("Belt"));
        itemList.add(new Item("Ring"));
        itemList.add(new Item("Amulet"));
        itemList.add(new Item("TwoHandWep"));
        itemList.add(new Item("OneHandWep"));
        itemList.add(new Item("Shield"));

    }
}
