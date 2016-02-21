package view;

import model.Memory;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

/**
 * Created by Анатолий on 18.02.2016.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, AWTException {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });

        new MainWindow().init();


    }
}
