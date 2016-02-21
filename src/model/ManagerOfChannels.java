package model;

import controller.MainWindowController;
import view.NoImageException;

/**
 * Created by Анатолий on 21.02.2016.
 */
public class ManagerOfChannels {
    final public static ManagerOfChannels INSTANCE = new ManagerOfChannels();
    Converters converters = Converters.INSTANCE;
    Images img = Images.INSTANCE;
    MainWindowController mwc = MainWindowController.INSTANCE;

    private ManagerOfChannels(){}
    public void ChangeMagistralData(byte tempDec) {
        for (int i = 0; i < 8; i++) {
            if (converters.GetByte(tempDec, i)) {
                try {
                    mwc.magistralData[7-i].setIcon(img.getImage(ImageResources.ON));
                } catch (NoImageException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    mwc.magistralData[7-i].setIcon(img.getImage(ImageResources.OFF));
                } catch (NoImageException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
