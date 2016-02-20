package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Анатолий on 20.02.2016.
 */
public class ActionButtonController implements IActionButtons {
    private static Logger logger = LoggerFactory.getLogger(ActionButtonController.class);
    public static final ActionButtonController INSTANCE = new ActionButtonController();
    private ActionButtonController(){

    }

    @Override
    public void setActionCommandBTN(CommandsBtn CBtn) {
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
            case STEP_CYCLE:
                logger.info("Step cycle");{
                break;
            }
            case RECORD: {
                logger.info("Record");
                break;
            }
        }
    }

    @Override
    public void setActionDigitalPanelBTN(DigitalPanelBtn dBtn) {
        switch (dBtn) {
            case _0: {
                System.out.println("0");
                break;
            }
            case _1: {
                System.out.println("1");
                break;
            }
            case _2: {
                System.out.println("2");
                break;
            }
            case _3: {
                System.out.println("3");
                break;
            }
            case _4: {
                System.out.println("4");
                break;
            }
            case _5: {
                System.out.println("5");
                break;
            }
            case _6: {
                System.out.println("6");
                break;
            }
            case _7: {
                System.out.println("7");
                break;
            }
            case _8: {
                System.out.println("8");
                break;
            }
            case _9: {
                System.out.println("9");
                break;
            }

        }
    }



}
