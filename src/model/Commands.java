package model;

import controller.MainWindowController;
import controller.Processes;
import view.NoImageException;

/**
 * Created by Анатолий on 21.02.2016.
 */
public class Commands {
    public static final Commands INSTANCE = new Commands();
    MainWindowController mwc = MainWindowController.INSTANCE;
    ManagerOfChannels moc = ManagerOfChannels.INSTANCE;
    Images img = Images.INSTANCE;
    Data data = Data.INSTANCE;
    private Commands(){}
    public void findingAddresses(){
        moc.changeMagistralData((byte)0);
        data.process = Processes.FINDING_ADDRESSES;
        mwc.status.setText(data.process.text);
        for (int i = 0; i < 6; i++ )
        {
            if (i < 4)
                try {
                    mwc.digitalPanel[i].setIcon(img.getImage(ImageResources._NULL));
                } catch (NoImageException e) {
                    e.printStackTrace();
                }
            else
                try {
                    mwc.digitalPanel[i].setIcon(img.getImage(ImageResources._NULL));
                } catch (NoImageException e) {
                    e.printStackTrace();
                }
        }
        mwc.digitalPanelCounter = 0;
    }
}
