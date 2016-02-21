package controller;

import java.awt.event.KeyEvent;

/**
 * Created by Анатолий on 20.02.2016.
 */
public class ActionHotKeyButtonController  {
    public static final ActionHotKeyButtonController INSTANCE = new ActionHotKeyButtonController();
    ActionButtonController actionBtnCont = ActionButtonController.INSTANCE;
    private ActionHotKeyButtonController(){}

    public void hotKey(int keyCode){

        switch (keyCode){
            //setActionCommandBTN
            case KeyEvent.VK_SHIFT:
            {
                actionBtnCont.setActionCommandBTN(Processes.FINDING_ADDRESSES);
                break;
            }
            case KeyEvent.VK_ALT:
            {
                actionBtnCont.setActionCommandBTN(Processes.FINDING_REGISTER);
                break;
            }
            case KeyEvent.VK_EQUALS:
            case KeyEvent.VK_PLUS:
            case KeyEvent.VK_ADD:
            {
                actionBtnCont.setActionCommandBTN(Processes.RECORD);
                break;
            }
            case KeyEvent.VK_ESCAPE:
            {
                actionBtnCont.setActionCommandBTN(Processes.RELOADING);
                break;
            }
            case KeyEvent.VK_Q:
            {
                actionBtnCont.setActionCommandBTN(Processes.STEP_TEAM);
                break;
            }
            case KeyEvent.VK_W:
            {
                actionBtnCont.setActionCommandBTN(Processes.STEP_CYCLE);
                break;
            }
            case KeyEvent.VK_F5:
            {
                actionBtnCont.setActionCommandBTN(Processes.START);
                break;
            }
            case 192:
            case KeyEvent.VK_QUOTE:
            case KeyEvent.VK_DEAD_TILDE:
            {
                actionBtnCont.setActionCommandBTN(Processes.PROGRAM_COUNTER);
                break;
            }
            case KeyEvent.VK_SUBTRACT:
            case KeyEvent.VK_MINUS:
            {
                actionBtnCont.setActionCommandBTN(Processes.REDUCE);
                break;
            }
            //setActionDigitalPanelBTN
            case KeyEvent.VK_A:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelButton._A);
                break;
            }
            case KeyEvent.VK_B:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelButton._B);
                break;
            }
            case KeyEvent.VK_C:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelButton._C);
                break;
            }
            case KeyEvent.VK_D:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelButton._D);
                break;
            }
            case KeyEvent.VK_F:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelButton._F);
                break;
            }
            case KeyEvent.VK_NUMPAD0:
            case KeyEvent.VK_0:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelButton._0);
                break;
            }
            case KeyEvent.VK_NUMPAD1:
            case KeyEvent.VK_1:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelButton._1);
                break;
            }
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_2:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelButton._2);
                break;
            }
            case KeyEvent.VK_NUMPAD3:
            case KeyEvent.VK_3:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelButton._3);
                break;
            }
            case KeyEvent.VK_NUMPAD4:
            case KeyEvent.VK_4:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelButton._4);
                break;
            }
            case KeyEvent.VK_NUMPAD5:
            case KeyEvent.VK_5:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelButton._5);
                break;
            }
            case KeyEvent.VK_NUMPAD6:
            case KeyEvent.VK_6:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelButton._6);
                break;
            }
            case KeyEvent.VK_NUMPAD7:
            case KeyEvent.VK_7:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelButton._7);
                break;
            }
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_8:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelButton._8);
                break;
            }

            case KeyEvent.VK_NUMPAD9:
            case KeyEvent.VK_9:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelButton._9);
                break;
            }
        }
    }
}
