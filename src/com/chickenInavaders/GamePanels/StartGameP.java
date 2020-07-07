package src.com.chickenInavaders.GamePanels;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

import src.com.chickenInavaders.*;
import src.com.chickenInavaders.LayoutManager;
import src.com.chickenInavaders.gameui.GameUI;

public class StartGameP extends JPanel {

    private String[][] ButtonConfi = {
            { "RedP1", "205,240,55,60", Commons.RED_SHIP, Commons.RED_SHIP },
            { "RedP2", "205,240,55,60", Commons.RED_SHIP, Commons.RED_SHIP },
            { "BlueP1", "290,240,55,60", Commons.BLUE_SHIP, Commons.BLUE_SHIP },
            { "BlueP2", "290,240,55,60", Commons.BLUE_SHIP, Commons.BLUE_SHIP },
            { "Level.1", "235,525,85,65", Commons.LEVEL_1, Commons.LEVEL_1_HOVER },
            { "Level.2", "350,525,85,65", Commons.LEVEL_2, Commons.LEVEL_2_HOVER },
            { "Level.3", "470,525,85,65", Commons.LEVEL_3, Commons.LEVEL_3_HOVER },
            { "Back", "15,680,117,60", Commons.BACK_IMG, Commons.BACK_HOVER_IMG },
            { "Start", "355,680,223,70", Commons.START_IMG, Commons.START_HOVER_IMG }
    };

    private HashMap<String, GameButton> ButtonList;
    private JLabel BackGroundP;
    private JList LoadList;
    private JTextField p1Name;
    private JScrollPane scrollPane1;
    DefaultListModel gameLoadText;
    private Level levles = Level.getInstance();
    private JComboBox<String> playersCobmBox;
    private LayoutManager panelGraph;
    private String palyes;
    private int selectedLevel;
    public String Playerid;

    public StartGameP(LayoutManager b) {
        panelGraph = b;
        palyes = "1";
        selectedLevel = 1;
        ButtonList = new HashMap<String, GameButton>();
        BackGroundP = new JLabel();
        scrollPane1 = new JScrollPane();
        gameLoadText = new DefaultListModel();
        p1Name = new JTextField("");
        Playerid = null;
        init();

    }

    private void init() {

        BackGroundP.setBounds(0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        BackGroundP.setIcon(new ImageIcon(Commons.BACKGROUND_ONE_PLAYER_IMG));
        p1Name.setBounds(225, 200, 105, 35);
        p1Name.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
            }

            public void removeUpdate(DocumentEvent e) {
            }

            public void insertUpdate(DocumentEvent e) {
                if (Playerid != null )
                    if(!p1Name.getText().equals(panelGraph.gameSaves.LoadsList.get(Integer.parseInt(Playerid)).name))
                    resetValue();

            }
        });
        setLayout(null);
        // Load
        playersAmount();
        buttonGenerator();
        loadSaves();
        ButtonList.get("Back").addActionListener(evt -> {
            ButtonList.get("Back").setSelected(!ButtonList.get("Back").isSelected());
            panelGraph.cardLayout.show(panelGraph.cardPane, "MainMenu");
        });

        ButtonList.get("Start").addActionListener(evt -> {
            ButtonList.get("Start").setSelected(!ButtonList.get("Start").isSelected());
            panelGraph.gameUI = new GameUI(panelGraph);
            panelGraph.gameUI.setName("Game");
            panelGraph.gameUI.addGameObserver(new GameObserver(panelGraph));
            panelGraph.gameUI.startLevel(selectedLevel, 3, levles.levelList.get(selectedLevel).tick, levles.levelList.get(selectedLevel).eggInterval);
            panelGraph.cardPane.add("Game", panelGraph.gameUI);
            panelGraph.cardLayout.show(panelGraph.cardPane, "Game");
            panelGraph.cardPane.transferFocus();
            if (Commons.IS_DEBUG)
                System.out.println("Starts game at level :" + selectedLevel + "\n player name : " + p1Name.getText() + "\n Playerid : "+Playerid);

        });
        // lock the level at start

        resetValue();

        add(p1Name);
        add(scrollPane1);
        // add(playersComboBox);
        add(BackGroundP);

    }

    @Override
    public void paintComponent(Graphics g) {
    }

    private void buttonGenerator() {
        for (String[] item : ButtonConfi) {
            GameButton newButton = new GameButton(item[2]);
            newButton.setText(item[0]);
            newButton.setRolloverIcon(new ImageIcon(item[3]));
            if (item[0].contains("Level")) {
                newButton.addActionListener(evt -> {
                    selectedLevel = Integer.parseInt(newButton.getText().split("\\.")[1]);
                    if (Commons.IS_DEBUG)
                        System.out.println("Level selected " + selectedLevel);
                });
            }
            newButton.setBound(item[1]);
            ButtonList.put(item[0], newButton);
            add(newButton);
        }
    }

    public void loadSaves() {
        // need to get the saves from json
        gameLoadText = new DefaultListModel();
        panelGraph.gameSaves.LoadsList.forEach((key, value) -> {
            gameLoadText.addElement(value.id + "." + value.name + ", level:" + value.level + ", " + value.date + ",Score : "+ value.score);
        });

        LoadList = new JList(gameLoadText);
        LoadList.setOpaque(false);
        LoadList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                String temp2 = (String) LoadList.getSelectedValue();
                Playerid = temp2.split("\\.")[0];
                buttonLevelChecker(Integer.parseInt(Playerid));
                setP1Name(Integer.parseInt(Playerid));
            }
        });
        scrollPane1.setViewportView(LoadList);
        scrollPane1.setOpaque(false);
        scrollPane1.getViewport().setOpaque(false);
        scrollPane1.setBounds(25, 400, 280, 125);
    }

    private void playersAmount() {
        String[] PlayersNum = { "1", "2" };
        playersCobmBox = new JComboBox<String>(PlayersNum);
        playersCobmBox.setBounds(new Rectangle(new Point(105, 110), playersCobmBox.getPreferredSize()));
        playersCobmBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                changePlayerPanel(Integer.parseInt((String) playersCobmBox.getSelectedItem()));
            }
        });
    }

    private void buttonLevelChecker(int id) {
        int num = panelGraph.gameSaves.LoadsList.get(id).level;
        ButtonList.get("Level.2").ChangeImage(Commons.LEVEL_2);
        ButtonList.get("Level.3").ChangeImage(Commons.LEVEL_3);
        ButtonList.get("Level.2").setRolloverIcon(new ImageIcon(Commons.LEVEL_2_HOVER));
        ButtonList.get("Level.3").setRolloverIcon(new ImageIcon(Commons.LEVEL_3_HOVER));
        ButtonList.get("Level.2").setEnabled(true);
        ButtonList.get("Level.3").setEnabled(true);
        switch (num) {
            case 1:
                ButtonList.get("Level.2").setEnabled(false);
                ButtonList.get("Level.2").ChangeImage(Commons.LEVEL_2_LOCK);
                ButtonList.get("Level.2").setRolloverIcon(null);
            case 2:
                ButtonList.get("Level.3").setEnabled(false);
                ButtonList.get("Level.3").ChangeImage(Commons.LEVEL_3_LOCK);
                ButtonList.get("Level.3").setRolloverIcon(null);
                break;
        }

    }

    private void setP1Name(int id) {
        String pName = panelGraph.gameSaves.LoadsList.get(id).name;
        p1Name.setText(pName);
    }
    private void changePlayerPanel(int p_Amount) {
        ButtonList.get("RedP2").setIcon(null);
        ButtonList.get("BlueP2").setIcon(null);

        switch (p_Amount) {
            case 1:
                BackGroundP.setIcon(new ImageIcon(Commons.BACKGROUND_ONE_PLAYER_IMG));
                ButtonList.get("RedP1").setBound("205,240,55,60");
                ButtonList.get("BlueP1").setBound("290,240,55,60");
                p1Name.setBounds(225, 200, 105, 35);

                ButtonList.get("RedP2").setIcon(null);
                ButtonList.get("BlueP2").setIcon(null);
                break;
            case 2:
                BackGroundP.setIcon(new ImageIcon(Commons.BACKGROUND_TWO_PLAYER_IMG));
                p1Name.setBounds(90, 200, 105, 35);
                ButtonList.get("RedP1").setBound("80,240,55,60");
                ButtonList.get("BlueP1").setBound("150,240,55,60");
                ButtonList.get("RedP2").setBound("350,200,55,60");
                ButtonList.get("BlueP2").setBound("420,200,55,60");

                ButtonList.get("RedP2").setIcon(new ImageIcon(Commons.RED_SHIP));
                ButtonList.get("BlueP2").setIcon(new ImageIcon(Commons.BLUE_SHIP));
                break;
        }
    }

    public String getPName() {
        return p1Name.getText();
    }
    public void setPlayerid(String num){
        Playerid = num;
    }
    public void  resetValue(){
        ButtonList.get("Level.2").setEnabled(false);
        ButtonList.get("Level.2").ChangeImage(Commons.LEVEL_2_LOCK);
        ButtonList.get("Level.2").setRolloverIcon(null);
        ButtonList.get("Level.3").setEnabled(false);
        ButtonList.get("Level.3").ChangeImage(Commons.LEVEL_3_LOCK);
        selectedLevel = 1 ;
        Playerid = null;
    }
}
