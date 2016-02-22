package model;

import controller.MainWindowController;
import controller.Processes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import view.NoImageException;

/**
 * Created by Анатолий on 21.02.2016.
 */
public class Commands {
    public static final Commands INSTANCE = new Commands();
    final Logger logger = LoggerFactory.getLogger(Commands.class);
    MainWindowController mwc = MainWindowController.INSTANCE;
    ManagerOfChannels moc = ManagerOfChannels.INSTANCE;
    Images img = Images.INSTANCE;
    Data data = Data.INSTANCE;
    InputManager im = InputManager.INSTANCE;
    Memory memory = Memory.INSTANCE;

    private Commands() {
    }

    public void findingAddresses() {
        moc.changeMagistralData((byte) 0);
        moc.changeMagistralAdress("0000");
        data.process = Processes.FINDING_ADDRESSES;
        mwc.status.setText(data.process.text);
        for (int i = 0; i < 6; i++) {
            try {
                mwc.digitalPanel[i].setIcon(img.getImage(ImageResources._NULL));
            } catch (NoImageException e) {
                e.printStackTrace();
            }
        }
        mwc.digitalPanelCounter = 0;
        im.setGlobalStopAnimation();
    }
    public void record() throws NoImageException {
        moc.changeMagistralData((byte) 0);
        if (data.process == Processes.FINDING_REGISTER){

        }
        if (data.process == Processes.FINDING_ADDRESSES || data.process == Processes.RECORD){
            if (mwc.digitalPanelCounter == 4){
                data.PC = data.PC+1;
                    moc.setDataFromPC();
                    mwc.status.setText("Отыскание адрса");
            }
            if (mwc.digitalPanelCounter > 4)
            {
                String temp = (data.addressValueArray[0].text +data.addressValueArray[1].text +data.addressValueArray[2].text +data.addressValueArray[3].text).toLowerCase();
                logger.info("Address: {}",temp);
                if (Integer.parseInt(temp, 16) >= 0x800)
                {
                    data.process = Processes.RECORD;
                    mwc.status.setText("Запомнить значение и увеличить счетчик");
                    memory.RAM.put(temp,Integer.valueOf(data.addressValueArray[4].text+data.addressValueArray[5].text,16));
                    logger.info("Data to RAM: {}",Integer.valueOf(data.addressValueArray[4].text+data.addressValueArray[5].text,16));
                    data.PC = data.PC+1;
                    moc.setDataFromPC();
                    mwc.digitalPanelCounter = 4;
                }
                else
                {
                    mwc.status.setText("Адреса с 0x0000 по 0x07FF недоступны для записи");

                }
            }
        }
    }
}
