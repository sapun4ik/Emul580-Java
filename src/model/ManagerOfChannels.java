package model;

import controller.DigitalPanelButton;
import controller.MainWindowController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import view.NoImageException;

/**
 * Created by Анатолий on 21.02.2016.
 */
public class ManagerOfChannels {
    final public static ManagerOfChannels INSTANCE = new ManagerOfChannels();
    final private Logger logger = LoggerFactory.getLogger(ManagerOfChannels.class);
    private Converters converters = Converters.INSTANCE;
    private Images img = Images.INSTANCE;
    private MainWindowController mwc = MainWindowController.INSTANCE;
    private Data data = Data.INSTANCE;
    private Memory memory = Memory.INSTANCE;

    private ManagerOfChannels() {}

    public void changeMagistralData(short tempDec) {
        for (int i = 0; i < 8; i++) {
            if (converters.GetByte(tempDec, i)) {
                mwc.magistralData[7 - i].setIcon(img.getImage(ImageResources.ON));
            } else {
                mwc.magistralData[7 - i].setIcon(img.getImage(ImageResources.OFF));
            }
        }
    }

    public void changeMagistralAdress(String tempHex) {
        int tempDec = Integer.parseInt(tempHex, 16);
        for (int i = 0; i < 16; i++) {
            if (converters.GetUShort(tempDec, i)) {
                mwc.magistralAddreses[15 - i].setIcon(img.getImage(ImageResources.ON));
            } else {
                mwc.magistralAddreses[15 - i].setIcon(img.getImage(ImageResources.OFF));
            }
        }
    }

    public void setDataFromPC() throws NoImageException {
        String PCtoHex = converters.UInt16ToHex(data.PC);
        logger.info("PC: {}, PCtoHex: {}", data.PC, PCtoHex);

        if (mwc.digitalPanelCounter > 3) {
            for (int i = 0, k = 0; i < 6; i++) {
                if (i < 4) {
                    data.addressValueArray[i] = DigitalPanelButton.getDigitalPanelBtn(PCtoHex.charAt(i));
                    mwc.digitalPanel[i].setIcon(img.getImage(ImageResources.getImageResources(data.addressValueArray[i])));
                } else {
                    if (memory.RAM.get(PCtoHex) == 0) {
                        data.addressValueArray[i] = DigitalPanelButton._0;
                        mwc.digitalPanel[i].setIcon(img.getImage(ImageResources.getImageResources(data.addressValueArray[i])));
                    } else {
                        data.addressValueArray[i] = DigitalPanelButton.getDigitalPanelBtn(Integer.toHexString(memory.RAM.get(PCtoHex)).charAt(k++));
                        mwc.digitalPanel[i].setIcon(img.getImage(ImageResources.getImageResources(data.addressValueArray[i])));
                    }
                }
                changeMagistralData(memory.RAM.get(PCtoHex).shortValue());
                changeMagistralAdress(PCtoHex);
            }
        } else {
            logger.warn("Call(setDataFromAddress)-> digitalPanelCounter: {}, addresesHex: {}", mwc.digitalPanelCounter, PCtoHex.length());
        }

    }
}
