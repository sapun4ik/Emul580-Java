package model;

import controller.DigitalPanelButton;
import controller.MainWindowController;
import controller.Processes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import view.NoImageException;

import javax.swing.*;

/**
 * Created by Анатолий on 21.02.2016.
 */
public class InputManager {
    final public static InputManager INSTANCE = new InputManager();
    private MainWindowController mwc = MainWindowController.INSTANCE;
    private Images img = Images.INSTANCE;
    private Data data = Data.INSTANCE;
    private Memory memory = Memory.INSTANCE;
    private ManagerOfChannels moc = ManagerOfChannels.INSTANCE;
    private static Logger logger = LoggerFactory.getLogger(InputManager.class);

    private InputManager() {}

    private boolean visible = true;
    private Timer timer;

    private void setAnimation(boolean value, int element) {
        if (!value) {
            if (timer != null) timer.stop();
            mwc.digitalPanel[element].setVisible(true);
        } else {
            timer = new Timer(500, e -> mwc.digitalPanel[element].setVisible(visible = !visible));
            timer.start();
        }
    }

    public void setGlobalStopAnimation() {
        setAnimation(false, 3);
        setAnimation(false, 5);
    }

    public void setData(DigitalPanelButton dpBTN) throws NoImageException {
        mwc.digitalPanelCounter++;
        switch (mwc.digitalPanelCounter) {
            case 1: {
                mwc.digitalPanel[3].setIcon(img.getImage(ImageResources.getImageResources(dpBTN)));
                data.addressValueArray[3] = dpBTN;
                setAnimation(true, 3);
                break;
            }
            case 2: {
                mwc.digitalPanel[2].setIcon(mwc.digitalPanel[3].getIcon());
                mwc.digitalPanel[3].setIcon(img.getImage(ImageResources.getImageResources(dpBTN)));
                data.addressValueArray[2] = data.addressValueArray[3];
                data.addressValueArray[3] = dpBTN;
                break;
            }
            case 3: {
                for (int i =1; i<3;i++) {
                    mwc.digitalPanel[i].setIcon(mwc.digitalPanel[i + 1].getIcon());
                    data.addressValueArray[i] = data.addressValueArray[i+1];
                }
                mwc.digitalPanel[3].setIcon(img.getImage(ImageResources.getImageResources(dpBTN)));
                data.addressValueArray[3] = dpBTN;
                break;
            }
            case 4: {
                for (int i =0;i<3;i++) {
                    mwc.digitalPanel[i].setIcon(mwc.digitalPanel[i + 1].getIcon());
                    data.addressValueArray[i] = data.addressValueArray[i+1];
                }
                mwc.digitalPanel[3].setIcon(img.getImage(ImageResources.getImageResources(dpBTN)));
                data.addressValueArray[3] = dpBTN;
                String temp = (data.addressValueArray[0].text + data.addressValueArray[1].text + data.addressValueArray[2].text + data.addressValueArray[3].text).toLowerCase();
                logger.info("Temp addresses: {}", temp);
                logger.info("Digital panel number 2: {}", data.addressValueArray[1].text);
                if (data.process == Processes.FINDING_ADDRESSES) {
                    data.PC = Integer.parseInt(temp, 16);
                    logger.info("Temp addresses(Short.parseShort): {}", data.PC);
                    logger.info("Data is memory: {} from: {}", memory.RAM.get(temp), temp);
                    for (int i = 4, k = 0 ; i < 6; i++) {
                        if (memory.RAM.get(temp) == 0) {
                            data.addressValueArray[i] = DigitalPanelButton._0;
                            mwc.digitalPanel[i].setIcon(img.getImage(ImageResources.getImageResources(data.addressValueArray[i])));
                        } else {
                            data.addressValueArray[i] = DigitalPanelButton.getDigitalPanelBtn(String.valueOf(memory.RAM.get(temp)).charAt(k++));
                            mwc.digitalPanel[i].setIcon(img.getImage(ImageResources.getImageResources(data.addressValueArray[i])));
                        }
                    }
                    Short value = Short.parseShort(data.addressValueArray[4].text + data.addressValueArray[5].text, 16);
                    logger.info("Value(addressValueArray[4]+addressValueArray[5]) to Byte: {}", value);
                    moc.changeMagistralData(value);
                }
                moc.changeMagistralAdress(temp);
                setAnimation(false, 3);
                setAnimation(true, 5);
                break;
            }
            case 5: {
                data.addressValueArray[5] = dpBTN;
                mwc.digitalPanel[5].setIcon(img.getImage(ImageResources.getImageResources(dpBTN)));
                Byte value = Byte.parseByte(data.addressValueArray[4].text + data.addressValueArray[5].text, 16);
                logger.info("Value(addressValueArray[4]+addressValueArray[5]) to Byte: {}", value);
                moc.changeMagistralData(value);
                break;
            }
            case 6: {
                data.addressValueArray[4] = data.addressValueArray[5];
                data.addressValueArray[5] = dpBTN;
                mwc.digitalPanel[4].setIcon(mwc.digitalPanel[5].getIcon());
                mwc.digitalPanel[5].setIcon(img.getImage(ImageResources.getImageResources(dpBTN)));
                Short value = Short.parseShort(data.addressValueArray[4].text + data.addressValueArray[5].text, 16);
                logger.info("Value(addressValueArray[4]+addressValueArray[5]) to Byte: {}\n", value);
                moc.changeMagistralData(value);
                mwc.digitalPanelCounter--;
                setAnimation(false, 5);
                break;
            }
            case 7: {
                mwc.digitalPanelCounter--;
                mwc.status.setText("Выберите команду!");
                break;
            }
        }
    }
}
