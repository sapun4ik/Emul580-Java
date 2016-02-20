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
            case KeyEvent.VK_SHIFT:
            {
                actionBtnCont.setActionCommandBTN(CommandsBtn.FINDING_ADDRESSES);
                break;
            }
            case KeyEvent.VK_EQUALS:
            case KeyEvent.VK_PLUS:
            case KeyEvent.VK_ADD:
            {
                actionBtnCont.setActionCommandBTN(CommandsBtn.RECORD);
                break;
            }
            case KeyEvent.VK_ESCAPE:
            {
                actionBtnCont.setActionCommandBTN(CommandsBtn.RELOADING);
                break;
            }
            case KeyEvent.VK_QUOTE:
            case KeyEvent.VK_DEAD_TILDE:
            {
                actionBtnCont.setActionCommandBTN(CommandsBtn.PROGRAM_COUNTER);
                break;
            }
            case KeyEvent.VK_SUBTRACT:
            case KeyEvent.VK_MINUS:
            {
                actionBtnCont.setActionCommandBTN(CommandsBtn.REDUCE);
                break;
            }
            case KeyEvent.VK_NUMPAD0:
            case KeyEvent.VK_0:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelBtn._0);
                break;
            }
            case KeyEvent.VK_NUMPAD1:
            case KeyEvent.VK_1:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelBtn._1);
                break;
            }
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_2:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelBtn._2);
                break;
            }
            case KeyEvent.VK_NUMPAD3:
            case KeyEvent.VK_3:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelBtn._3);
                break;
            }
            case KeyEvent.VK_NUMPAD4:
            case KeyEvent.VK_4:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelBtn._4);
                break;
            }
            case KeyEvent.VK_NUMPAD5:
            case KeyEvent.VK_5:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelBtn._5);
                break;
            }
            case KeyEvent.VK_NUMPAD6:
            case KeyEvent.VK_6:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelBtn._6);
                break;
            }
            case KeyEvent.VK_NUMPAD7:
            case KeyEvent.VK_7:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelBtn._7);
                break;
            }
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_8:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelBtn._8);
                break;
            }

            case KeyEvent.VK_NUMPAD9:
            case KeyEvent.VK_9:
            {
                actionBtnCont.setActionDigitalPanelBTN(DigitalPanelBtn._9);
                break;
            }
        }
    }
}
