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
    public void changeMagistralData(short tempDec) {
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
    public void changeMagistralAdress(String tempHex)
    {
        int tempDec = Integer.parseInt(tempHex, 16);
        for (int i = 0; i < 16; i++)
        {
            if (converters.GetUShort(tempDec, i))
            {
                try {
                    mwc.magistralAddreses[15-i].setIcon(img.getImage(ImageResources.ON));
                } catch (NoImageException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                try {
                    mwc.magistralAddreses[15-i].setIcon(img.getImage(ImageResources.OFF));
                } catch (NoImageException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
