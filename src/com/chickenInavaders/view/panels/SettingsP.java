package src.com.chickenInavaders.view.panels;

import src.com.chickenInavaders.*;
import src.com.chickenInavaders.controllers.settings.SettingsController;
import src.com.chickenInavaders.view.LayoutManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import net.miginfocom.swing.MigLayout;
import src.com.chickenInavaders.view.GameButton;
import src.com.chickenInavaders.view.ImageDraw;


public class SettingsP extends JPanel {

    private SettingsController settingsFile;
    private boolean settingsChanged = false;
    private ImageDraw BackGround = null;
    private String labels[][] = {{"Player 1", "cell 2 11"},
            {"Left", "cell 1 12"},
            {"Right", "cell 1 13"},
            {"Fire", "cell 1 14"}};
    private String JTextFieldsPlayer1[][] = {
            {"Left", "cell 2 12"},
            {"Right", "cell 2 13"},
            {"Fire", "cell 2 14"}};



    private HashMap<String, JTextField> JTextKeyFieldsPlayer1 = new HashMap<String, JTextField>();
    private GameButton backB;
    private GameButton saveB;
    private JSlider soundSlider;
    private LayoutManager b = null;

    public SettingsP(LayoutManager b) {
        this.b = b;
        settingsFile = SettingsController.getInstance();
        soundSlider = new JSlider();
        backB = new GameButton(Commons.BACK_IMG);
        saveB = new GameButton(Commons.SAVE_IMG);
        setUpImages();
        init();
    }

    private void init() {
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.
                swing.border.EmptyBorder(0, 0, 0, 0), "", javax.swing.border
                .TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dia\u006cog"
                , java.awt.Font.BOLD, 12), java.awt.Color.red), getBorder
                ()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("\u0062ord\u0065r".equals(e.getPropertyName())) throw new RuntimeException();
            }
        });
        setLayout(new MigLayout("fill,hidemode 3,alignx center",
                // columns
                "[]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]",
                // rows
                "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]" +
                        "[]"));

        //-----Sound Slider ------
        setSoundSlider();
        //------Controller Label and TextField -------
        LabelGenerator();
        textFieldGenerator();

        //------ Buttons ---------
        backB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                backB.setSelected(!backB.isSelected());
                if (settingsChanged)
                    settingsFile.saveSettings();
                b.cardLayout.show(b.cardPane, "MainMenu");
            }
        });

        add(soundSlider, "cell 1 6 7 2");
        add(backB, "cell 0 17 4 2");
        add(saveB, "cell 6 17 4 2");
    }

    private void setUpImages() {
        backB.setRolloverIcon(new ImageIcon(Commons.BACK_HOVER_IMG));
        saveB.setRolloverIcon(new ImageIcon(Commons.SAVE_HOVER_IMG));
        BackGround = new ImageDraw(new ImageIcon(Commons.SETTINGS_BACKGROUND_IMG).getImage(), 0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(BackGround.image, BackGround.loc_x, BackGround.loc_y, BackGround.width, BackGround.height, this);
    }

    private void LabelGenerator() {
        for (String[] label : labels) {
            JLabel labelTemp = new JLabel(label[0]);
            labelTemp.setFont(new Font("accidental pregnancy", Font.BOLD, 16));
            labelTemp.setForeground(new Color(145, 191, 237));
            add(labelTemp, label[1]);
        }
    }

    private void textFieldGenerator() {
        // exp  {"Up","cell 2 12"},
        for (String[] TextField : JTextFieldsPlayer1) {
            String toCodeToText = KeyEvent.getKeyText(Integer.parseInt(settingsFile.getKeyboardLayoutPlayer1(TextField[0].toLowerCase())));
            JTextKeyFieldsPlayer1.put(TextField[0], new JTextField(toCodeToText));
            // if key changed set save flag true and update settings
            JTextKeyFieldsPlayer1.get(TextField[0]).addKeyListener(new KeyListener() {
                public void keyPressed(KeyEvent keyEvent) {

                }

                public void keyReleased(KeyEvent keyEvent) {
                    String selectedKey = KeyEvent.getKeyText(keyEvent.getKeyCode());
                    JTextKeyFieldsPlayer1.get(TextField[0]).setText(selectedKey);
                    settingsFile.setKeyPlayer1(TextField[0].toLowerCase(), Integer.toString(keyEvent.getKeyCode()));
                    settingsChanged = true;

                }

                public void keyTyped(KeyEvent keyEvent) {
                }
            });
            add(JTextKeyFieldsPlayer1.get(TextField[0]), TextField[1]);
        }
    }

    private void setSoundSlider() {
        soundSlider = new JSlider(JSlider.CENTER, 0, 100, settingsFile.getSoundVolume());
        soundSlider.setMinorTickSpacing(2);
        soundSlider.setMajorTickSpacing(10);
        soundSlider.setPaintTicks(true);
        soundSlider.setOpaque(false);
        soundSlider.setBackground(Color.BLUE);
        soundSlider.setPaintLabels(true);
        soundSlider.setForeground(Color.white);
        soundSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                settingsChanged = true;
                int value = soundSlider.getValue();
                settingsFile.setSoundVolume(value);
                if (Commons.IS_DEBUG)
                    System.out.println("Sound Volume Changed to " + value);
            }
        });
    }

}
