package com.chickenInavaders.GamePanels;

import com.chickenInavaders.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import com.chickenInavaders.LayoutManager;
import net.miginfocom.swing.MigLayout;


public class SettingsP extends JPanel   {

    private Settings settingsFile;
    private boolean settingschanged = false;
    private ImageDraw BackGrond = null;
    private  String labels[][] = {{"Player 1","cell 2 11"},
            {"Player 2","cell 7 11"},
            {"Up","cell 1 12"},
            {"Up","cell 6 12"},
            {"Down","cell 1 13"},
            {"Down","cell 6 13"},
            {"Left","cell 1 14"},
            {"Left","cell 6 14"},
            {"Right","cell 1 15"},
            {"Right","cell 6 15"},
            {"Fire","cell 1 16"},
            {"Fire","cell 6 16"}};
    private  String JTextFieldsPlayer1[][] = {
            {"Up","cell 2 12"},
            {"Down","cell 2 13"},
            {"Left","cell 2 14"},
            {"Right","cell 2 15"},
            {"Fire","cell 2 16"}};
    private  String JTextFieldsPlayer2[][] = {
            {"Up","cell 7 12"},
            {"Down","cell 7 13"},
            {"Left","cell 7 14"},
            {"Right","cell 7 15"},
            {"Fire","cell 7 16"}};

    private HashMap<String, JTextField> JTextKeyFieldsPlayer1 = new HashMap<String, JTextField>();
    private HashMap<String, JTextField> JTextKeyFieldsPlayer2 = new HashMap<String, JTextField>();
    private GameButton backB;
    private GameButton saveB;
    private JSlider sondSilder;
    private LayoutManager b = null;

    public SettingsP(LayoutManager b) {
        this.b = b;
        settingsFile = new Settings();
        sondSilder = new JSlider();
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
                if ("\u0062ord\u0065r".equals(e.getPropertyName())) throw new RuntimeException
                        ();
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
        //------Controler Label and TextFild -------
        LabelGenerator();
        textFieldGenerator();

        //------ Buttons ---------
        backB.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt) {
                backB.setSelected(!backB.isSelected());
                if(settingschanged)
                    settingsFile.saveSettings();
                b.cardLayout.show(b.cardPane, "MainMenu");
            }
        });

        add(sondSilder, "cell 1 6 7 2");
        add(backB, "cell 0 17 4 2");
        add(saveB, "cell 6 17 4 2");
    }
    private void setUpImages() {
        backB.setRolloverIcon(new ImageIcon(Commons.BACK_HOVER_IMG));
        saveB.setRolloverIcon(new ImageIcon(Commons.SAVE_HOVER_IMG));
        BackGrond = new ImageDraw(new ImageIcon(Commons.SETTINGS_BACKGROUND_IMG).getImage(), 0, 0,Commons.BOARD_WIDTH,Commons.BOARD_HEIGHT);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(BackGrond.image, BackGrond.loc_x, BackGrond.loc_y, BackGrond.width, BackGrond.height, this);
    }

    private void LabelGenerator(){
        for (String[] label :labels) {
            JLabel labelTemp = new JLabel(label[0]);
            labelTemp.setFont(new Font("accidental pregnancy", Font.BOLD, 16));
            labelTemp.setForeground(new Color(145, 191, 237));
            add(labelTemp, label[1]);
        }
    }
    private void textFieldGenerator() {
        // exp  {"Up","cell 2 12"},
        for (String[] TextField : JTextFieldsPlayer1) {
            JTextKeyFieldsPlayer1.put(TextField[0],new JTextField(settingsFile.getKeyboardLayoutPlayer1(TextField[0].toLowerCase())));

            // if key changed set save flag true and update settings
            JTextKeyFieldsPlayer1.get(TextField[0]).getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {
                }
                public void removeUpdate(DocumentEvent e) {
                }
                public void insertUpdate(DocumentEvent e) {
                    settingsFile.setKeyPlayer1(TextField[0].toLowerCase(),JTextKeyFieldsPlayer1.get(TextField[0]).getText());
                    settingschanged = true;
                    if (Commons.IS_DEBUG)
                    System.out.println("Key changed to "+settingsFile.getKeyboardLayoutPlayer1(TextField[0].toLowerCase()));
                }
            });
            add(JTextKeyFieldsPlayer1.get(TextField[0]), TextField[1]);
        }

        for (String[] TextField : JTextFieldsPlayer2) {
            JTextKeyFieldsPlayer2.put(TextField[0],new JTextField(settingsFile.getKeyboardLayoutPlayer2(TextField[0].toLowerCase())));
            // if key changed set save flag true and update settings
            JTextKeyFieldsPlayer2.get(TextField[0]).getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent e) {
                }
                public void removeUpdate(DocumentEvent e) {
                }
                public void insertUpdate(DocumentEvent e) {
                    settingsFile.setKeyPlayer2(TextField[0].toLowerCase(),JTextKeyFieldsPlayer2.get(TextField[0]).getText());
                    settingschanged = true;
                    if (Commons.IS_DEBUG)
                        System.out.println("Key changed to "+settingsFile.getKeyboardLayoutPlayer2(TextField[0].toLowerCase()));
                }
            });
            add(JTextKeyFieldsPlayer2.get(TextField[0]), TextField[1]);
        }
    }
    private void setSoundSlider(){
        sondSilder = new JSlider(JSlider.CENTER, 0, 100, settingsFile.getSoundVolume());
        sondSilder.setMinorTickSpacing(2);
        sondSilder.setMajorTickSpacing(10);
        sondSilder.setPaintTicks(true);
        sondSilder.setOpaque(false);
        sondSilder.setBackground(Color.BLUE);
        sondSilder.setPaintLabels(true);
        sondSilder.setForeground(Color.white);
        sondSilder.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                settingschanged = true;
                int value = sondSilder.getValue();
                settingsFile.setSoundVolume(value);
                if (Commons.IS_DEBUG)
                    System.out.println("Sound Volume Changed to " + value);
                }
            });
    }

}
