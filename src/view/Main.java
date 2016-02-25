package view;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Анатолий on 18.02.2016.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, AWTException, ParserConfigurationException, SAXException, IOException {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });

        new MainWindow().init();



    }
}
