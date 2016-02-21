package controller;

import javax.swing.*;

/**
 * Created by Анатолий on 21.02.2016.
 */
public class MainWindowController {
    final static public MainWindowController INSTANCE = new MainWindowController();

    public JLabel status;
    public JLabel[] magistralData;
    public JLabel[] magistralAddreses;
    public JLabel[] digitalPanel;
    public int digitalPanelCounter;
    private MainWindowController(){
        magistralData = new JLabel[8];
        digitalPanel = new JLabel[6];
        magistralAddreses = new JLabel[16];
        digitalPanelCounter = 6;
    }
}
