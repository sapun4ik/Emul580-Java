package controller;

import javax.swing.*;

/**
 * Created by Анатолий on 21.02.2016.
 */
public class MainWindowController {
    final static public MainWindowController INSTANCE = new MainWindowController();
    public Processes process = Processes.STOP;
    public JLabel status;
    public JLabel[] magistralData;
    public JLabel[] digitalPanel;
    private MainWindowController(){
        magistralData = new JLabel[8];
        digitalPanel = new JLabel[6];
    }
}
