package model;

import controller.DigitalPanelBtn;
import controller.MainWindowController;
import controller.Processes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import view.NoImageException;

/**
 * Created by Анатолий on 21.02.2016.
 */
public class InputManager {
    final public static  InputManager INSTANCE = new InputManager();
    MainWindowController mwc = MainWindowController.INSTANCE;
    Images img = Images.INSTANCE;
    Data data = Data.INSTANCE;
    Memory memory = Memory.INSTANCE;
    ManagerOfChannels moc = ManagerOfChannels.INSTANCE;
    private static Logger logger = LoggerFactory.getLogger(InputManager.class);
    public static DigitalPanelBtn[] AddressValueArray;
    private InputManager(){
        AddressValueArray = new DigitalPanelBtn[6];
    }
    public void setData(DigitalPanelBtn dpBTN) throws NoImageException {
        mwc.digitalPanelCounter++;
        switch(mwc.digitalPanelCounter)
        {
            case 1:
            {
                mwc.digitalPanel[3].setIcon(img.getImage(ImageResources.getImageResources(dpBTN)));
                AddressValueArray[3] = dpBTN;
                break;
            }
            case 2:
            {
                mwc.digitalPanel[2].setIcon(mwc.digitalPanel[3].getIcon());
                mwc.digitalPanel[3].setIcon(img.getImage(ImageResources.getImageResources(dpBTN)));
                AddressValueArray[2] = AddressValueArray[3];
                AddressValueArray[3] = dpBTN;
                break;
            }
            case 3:
            {
                mwc.digitalPanel[1].setIcon(mwc.digitalPanel[2].getIcon());
                mwc.digitalPanel[2].setIcon(mwc.digitalPanel[3].getIcon());
                mwc.digitalPanel[3].setIcon(img.getImage(ImageResources.getImageResources(dpBTN)));
                AddressValueArray[1] = AddressValueArray[2];
                AddressValueArray[2] = AddressValueArray[3];
                AddressValueArray[3] = dpBTN;
                break;
            }
            case 4:
            {
                mwc.digitalPanel[0].setIcon(mwc.digitalPanel[1].getIcon());
                mwc.digitalPanel[1].setIcon(mwc.digitalPanel[2].getIcon());
                mwc.digitalPanel[2].setIcon(mwc.digitalPanel[3].getIcon());
                mwc.digitalPanel[3].setIcon(img.getImage(ImageResources.getImageResources(dpBTN)));
                AddressValueArray[0] = AddressValueArray[1];
                AddressValueArray[1] = AddressValueArray[2];
                AddressValueArray[2] = AddressValueArray[3];
                AddressValueArray[3] = dpBTN;
                String temp = AddressValueArray[0].text + AddressValueArray[1].text + AddressValueArray[2].text + AddressValueArray[3].text;
                logger.info("Temp addresses: {}", temp);
                if (data.process == Processes.FINDING_ADDRESSES)
                {
                    data.PC = Integer.parseInt(temp, 16);
                    logger.info("Temp addresses(Short.parseShort): {}",data.PC);
                    logger.info("Data is memory: {} from: {}",memory.RAM.get(temp), temp);
                    if(memory.RAM.get(temp) == 0){
                        AddressValueArray[4] = DigitalPanelBtn._0;
                        AddressValueArray[5] = DigitalPanelBtn._0;
                        mwc.digitalPanel[4].setIcon(img.getImage(ImageResources.getImageResources(AddressValueArray[4])));
                        mwc.digitalPanel[5].setIcon(img.getImage(ImageResources.getImageResources(AddressValueArray[5])));
                    }else {
                        AddressValueArray[4] = DigitalPanelBtn.getDigitalPanelBtn(String.valueOf(memory.RAM.get(temp)).charAt(0));
                        AddressValueArray[5] = DigitalPanelBtn.getDigitalPanelBtn(String.valueOf(memory.RAM.get(temp)).charAt(1));
                        mwc.digitalPanel[4].setIcon(img.getImage(ImageResources.getImageResources(AddressValueArray[4])));
                        mwc.digitalPanel[5].setIcon(img.getImage(ImageResources.getImageResources(AddressValueArray[5])));
                    }
                    Byte value = Byte.parseByte(AddressValueArray[4].text+AddressValueArray[5].text,16);
                    logger.info("Value(AddressValueArray[4]+AddressValueArray[5]) to Byte: {}",value);
                    moc.changeMagistralData(value);
                }
                moc.changeMagistralAdress(temp);
                break;
            }
            case 5:
            {
                AddressValueArray[5] = dpBTN;
                mwc.digitalPanel[5].setIcon(img.getImage(ImageResources.getImageResources(dpBTN)));
                Byte value = Byte.parseByte(AddressValueArray[4].text+AddressValueArray[5].text,16);
                logger.info("Value(AddressValueArray[4]+AddressValueArray[5]) to Byte: {}",value);
                moc.changeMagistralData(value);
                break;
            }
            case 6:
            {
                AddressValueArray[4] = AddressValueArray[5] ;
                AddressValueArray[5] = dpBTN;
                mwc.digitalPanel[4].setIcon(mwc.digitalPanel[5].getIcon());
                mwc.digitalPanel[5].setIcon(img.getImage(ImageResources.getImageResources(dpBTN)));
                Byte value = Byte.parseByte(AddressValueArray[4].text+AddressValueArray[5].text,16);
                logger.info("Value(AddressValueArray[4]+AddressValueArray[5]) to Byte: {}\n",value);
                moc.changeMagistralData(value);
                mwc.digitalPanelCounter--;
                break;
            }
            case 7:
            {
                mwc.digitalPanelCounter--;
                mwc.status.setText("Выберите команду!");
                break;
            }
        }
    }
}
