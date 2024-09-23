package gui;

import internationalization.*;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import toasteritem.*;
import logging.AssignmentLogger;

// Main GUI
public class ToasterGUI extends JFrame implements ActionListener {
    private Internationalization locale;
    private JLabel label, imageLabel;
    private JButton buttonEn, buttonDe, startToast;
    private JTextField itemInput;
    private JPanel mainPanel;

    public ToasterGUI() {
        AssignmentLogger.logConstructor(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Toaster");
        setSize(650, 300);

        mainPanel = new JPanel(new FlowLayout());
        locale = new Internationalization("en", "US"); 
        label = new JLabel(locale.getString("first"));
        mainPanel.add(label);

        itemInput = new JTextField(10);
        startToast = new JButton(locale.getString("start"));
        startToast.addActionListener(e -> DoTheToast(e));
        mainPanel.add(itemInput);
        mainPanel.add(startToast);

        add(mainPanel, BorderLayout.NORTH);

        imageLabel = new JLabel();
        add(imageLabel, BorderLayout.CENTER);

        buttonEn = new JButton("English");
        buttonEn.setActionCommand("en_US");
        buttonEn.addActionListener(this);
        mainPanel.add(buttonEn);

        buttonDe = new JButton("German");
        buttonDe.setActionCommand("de_DE");
        buttonDe.addActionListener(this);
        mainPanel.add(buttonDe);

        setVisible(true);
    }

    // action performed for the lang buttons
    public void actionPerformed(ActionEvent e) {
        AssignmentLogger.logMethodEntry(this);
        switch (e.getActionCommand()) {
            case "en_US":
                locale.setLocale("en", "US");
                //playSound("sounds/in.wav");
                break;
            case "de_DE":
                locale.setLocale("de", "DE");
                //playSound("sounds/out.wav");
                break;
        }
        updateText();
        AssignmentLogger.logMethodExit(this);
    }

    //method to update the labels dependant on locale used
    private void updateText() {
        AssignmentLogger.logMethodEntry(this);
        label.setText(locale.getString("first"));
        startToast.setText(locale.getString("start")); 
        AssignmentLogger.logMethodExit(this);
    }

    //method for the process of toasting
    private void DoTheToast(ActionEvent e) {
        AssignmentLogger.logMethodEntry(this);
        String itemName = itemInput.getText();
        try {
            ToasterItem item = ToasterItemCreator.createItem(itemName);
            playSound("sounds/in.wav");
            Timer timer = new Timer(item.getCookTime() * 1000, event -> {
                playSound("sounds/out.wav");
                imageLabel.setIcon(new ImageIcon(item.getImagePath()));
                label.setText(locale.getString("ready").replace("{item}", item.getName()));
            });
            timer.setRepeats(false); //stop timer from repeating
            timer.start();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        AssignmentLogger.logMethodExit(this);
    }

    //method to play sound
    private void playSound(String soundFile) {
        AssignmentLogger.logMethodEntry(this);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(soundFile));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        AssignmentLogger.logMethodExit(this);
    }

    public static void main(String[] args) {
        AssignmentLogger.logMain();
        new ToasterGUI();
    }
}
