package src.com.chickenInavaders.GamePanels;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileReader;
import java.util.HashMap;
import src.com.chickenInavaders.GameButton;
import src.com.chickenInavaders.Commons;
import src.com.chickenInavaders.LayoutManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import src.com.chickenInavaders.TestObserver;
import src.com.chickenInavaders.gameui.GameUI;

public class StartGameP extends JPanel {

    private String[][] ButtonConfi ={
            {"RedP1","205,240,55,60",Commons.RED_SHIP,Commons.RED_SHIP},
            {"RedP2","205,240,55,60",Commons.RED_SHIP,Commons.RED_SHIP},
            {"BlueP1","290,240,55,60",Commons.BLUE_SHIP,Commons.BLUE_SHIP},
            {"BlueP2","290,240,55,60",Commons.BLUE_SHIP,Commons.BLUE_SHIP},
            {"Level1","235,525,85,65",Commons.LEVEL_1,Commons.LEVEL_1_HOVER},
            {"Level2","350,525,85,65",Commons.LEVEL_2,Commons.LEVEL_2_HOVER},
            {"Level3","470,525,85,65",Commons.LEVEL_3,Commons.LEVEL_3_HOVER},
            {"Back","15,680,117,60",Commons.BACK_IMG,Commons.BACK_HOVER_IMG},
            {"Start","355,680,223,70",Commons.START_IMG,Commons.START_HOVER_IMG}
    };

    private HashMap<String,GameButton> ButtonList;
    private HashMap<String,Loads> LoadsList;
    private int AmountOfPlayers;
    private JLabel BackGrondP;
    private JList LoadList;
    private JTextField p1Name;

    private JScrollPane scrollPane1;
    DefaultListModel gameLoadText;

    private JComboBox<String> playersCobmBox;
    private LayoutManager panelGraph ;
    private  String palyes;


    public StartGameP(LayoutManager b){
        panelGraph = b;
        palyes ="1" ;
        AmountOfPlayers=0;
        ButtonList =new HashMap<String,GameButton>();
        LoadsList = new HashMap<String,Loads>();
        BackGrondP = new JLabel();
        scrollPane1 = new JScrollPane();
        gameLoadText = new DefaultListModel();
        p1Name = new JTextField("");

        init();

    }
    private void init() {

        BackGrondP.setBounds(0, 0, Commons.BOARD_WIDTH,Commons.BOARD_HEIGHT);
        BackGrondP.setIcon(new ImageIcon(Commons.BACKGROUND_ONE_PLAYER_IMG));
        p1Name.setBounds(225, 200, 105, 35);
        setLayout(null);
        // Load
        readLoadGames();
        playersAmount();
        buttonGenerator();
        loadSaves();
        ButtonList.get("Back").addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt) {
                ButtonList.get("Back").setSelected(!ButtonList.get("Back").isSelected());
                panelGraph.cardLayout.show(panelGraph.cardPane, "MainMenu");
            }
        });

        ButtonList.get("Start").addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt) {
                ButtonList.get("Start").setSelected(!ButtonList.get("Start").isSelected());
                panelGraph.cardLayout.show(panelGraph.cardPane, "Game");
                panelGraph.cardPane.transferFocus();
            }
        });

        add(p1Name);
        add(p1Name);
        add(scrollPane1);
        add(playersCobmBox);
        add(BackGrondP);

    }
    @Override
    public void paintComponent(Graphics g) {}
    private void buttonGenerator(){
        for (String[] item :ButtonConfi){
            GameButton newButton = new GameButton(item[2]);
            newButton.setRolloverIcon(new ImageIcon(item[3]));
            newButton.setBound(item[1]);
            ButtonList.put(item[0],newButton);
            add(newButton);
        }
    }
    private  void loadSaves() {
        // need to get the saves from json

        // .........................

        LoadList = new JList(gameLoadText);
        LoadList.setOpaque(false);
        LoadList.addListSelectionListener(new ListSelectionListener() {
              public void valueChanged (ListSelectionEvent evt) {
                  String temp2 =(String) LoadList.getSelectedValue();
                  buttonLevelChecker(temp2.charAt(0)-48);
                  setP1Name(temp2.charAt(0)-48);
              }
          }
        );
        scrollPane1.setViewportView(LoadList);
        scrollPane1.setOpaque(false);
        scrollPane1.getViewport().setOpaque(false);
        scrollPane1.setBounds(25, 400, 250, 125);
    }
    private void playersAmount(){
        String[] PlayersNum ={"1","2"};
        playersCobmBox = new JComboBox<String>(PlayersNum);
        playersCobmBox.setBounds(new Rectangle(new Point(105, 110), playersCobmBox.getPreferredSize()));
        playersCobmBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                changePlayerPanel(Integer.parseInt((String)playersCobmBox.getSelectedItem()));
            }
        });
    }
    private void readLoadGames(){

        // Temporary , Need to be removed To "saves" Class
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(Commons.LOAD_FILE));
            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;
            // A JSON array. JSONObject supports java.util.List interface.
            JSONArray companyList = (JSONArray) jsonObject.get("Saves");
            companyList.forEach(item  ->{
                JSONObject obj2 = (JSONObject) item;
                Loads NewLoad = new Loads(Integer.parseInt(obj2.get("Id").toString()),obj2.get("Name").toString(),Integer.parseInt(obj2.get("Score").toString()),Integer.parseInt(obj2.get("Level").toString()),obj2.get("Data").toString());
                LoadsList.put(String.valueOf(obj2.get("Id")),NewLoad);
                gameLoadText.addElement(NewLoad.Id+"."+NewLoad.playerName+", level:"+NewLoad.level+", "+NewLoad.date);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void buttonLevelChecker(int id) {
        Loads selectedLoad = LoadsList.get(Integer.toString(id));
        ButtonList.get("Level2").ChangeImage(Commons.LEVEL_2);
        ButtonList.get("Level3").ChangeImage(Commons.LEVEL_3);
        ButtonList.get("Level2").setRolloverIcon(new ImageIcon(Commons.LEVEL_2_HOVER));
        ButtonList.get("Level3").setRolloverIcon(new ImageIcon(Commons.LEVEL_3_HOVER));
        ButtonList.get("Level2").setEnabled(true);
        ButtonList.get("Level3").setEnabled(true);
        switch (selectedLoad.level){
            case 1:
                ButtonList.get("Level2").setEnabled(false);
                ButtonList.get("Level2").ChangeImage(Commons.LEVEL_2_LOCK);
                ButtonList.get("Level2").setRolloverIcon(null);
            case 2 :
                ButtonList.get("Level3").setEnabled(false);
                ButtonList.get("Level3").ChangeImage(Commons.LEVEL_3_LOCK);
                ButtonList.get("Level3").setRolloverIcon(null);
                break;
        }

    }
    private  void setP1Name(int id)
    {
        String pName = LoadsList.get(Integer.toString(id)).playerName;
        p1Name.setText(pName);
    }
    private void changePlayerPanel(int p_Amount)
    {
        ButtonList.get("RedP2").setIcon(null);
        ButtonList.get("BlueP2").setIcon(null);

        switch (p_Amount)
        {
            case 1:
                BackGrondP.setIcon(new ImageIcon(Commons.BACKGROUND_ONE_PLAYER_IMG));
                ButtonList.get("RedP1").setBound("205,240,55,60");
                ButtonList.get("BlueP1").setBound("290,240,55,60");
                p1Name.setBounds(225, 200, 105, 35);

                ButtonList.get("RedP2").setIcon(null);
                ButtonList.get("BlueP2").setIcon(null);
                AmountOfPlayers=1;
                break;
            case 2:
                BackGrondP.setIcon(new ImageIcon(Commons.BACKGROUND_TWO_PLAYER_IMG));
                p1Name.setBounds(90, 200, 105, 35);
                ButtonList.get("RedP1").setBound("80,240,55,60");
                ButtonList.get("BlueP1").setBound("150,240,55,60");
                ButtonList.get("RedP2").setBound("350,200,55,60");
                ButtonList.get("BlueP2").setBound("420,200,55,60");

                ButtonList.get("RedP2").setIcon(new ImageIcon(Commons.RED_SHIP));
                ButtonList.get("BlueP2").setIcon(new ImageIcon(Commons.BLUE_SHIP));
                AmountOfPlayers=2;
                break;
        }
    }
    // Temporary , need to move it to class
    public class Loads{
        public int Id;
        public String playerName;
        public int score;
        public int level;
        public String date;
        public Loads(int Id,String name,int score,int level,String date)
        {
            this.Id = Id;
            this.playerName = name;
            this.score = score;
            this.level = level;
            this.date = date;
        }
        public Loads(){
            this(0,"",0,0,"");
        }


    }
}
