package controller;

import model.Commands;
import model.InputManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import view.NoImageException;

/**
 * Created by Анатолий on 20.02.2016.
 */
public class ActionButtonController {
    private static Logger logger = LoggerFactory.getLogger(ActionButtonController.class);
    public static final ActionButtonController INSTANCE = new ActionButtonController();
    private Commands commands = Commands.INSTANCE;
    private InputManager inputManager = InputManager.INSTANCE;
    private MainWindowController mwc = MainWindowController.INSTANCE;
    private ActionButtonController(){

    }

    public void setActionCommandBTN(Processes CBtn) {
        switch (CBtn) {
            case START: {
                logger.info("Start");
                break;
            }
            case STOP: {
                logger.info("Stop");
                break;
            }
            case FINDING_ADDRESSES: {
                logger.info("Finding addresses");
                commands.findingAddresses();
                break;
            }
            case FINDING_REGISTER: {
                logger.info("Finding register");
                break;
            }
            case PROGRAM_COUNTER: {
                logger.info("Program counter");
                break;
            }
            case REDUCE: {
                logger.info("Reduce");
                commands.reduce();
                break;
            }
            case STEP_TEAM: {
                logger.info("Step team");
                break;
            }
            case RELOADING: {
                logger.info("Reloading");
                break;
            }
            case STEP_CYCLE:{
                logger.info("Step cycle");
                break;
            }
            case RECORD: {
                logger.info("Record");
                try {
                    commands.record();
                } catch (NoImageException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public void setActionDigitalPanelBTN(DigitalPanelButton dBtn) {
        if (mwc.digitalPanelCounter != 6)
        {
            try {
                inputManager.setData(dBtn);
            } catch (NoImageException e) {
                e.printStackTrace();
            }
        }
    }



}
