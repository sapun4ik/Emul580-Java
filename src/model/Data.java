package model;

import controller.DigitalPanelButton;
import controller.Processes;

/**
 * Created by Анатолий on 21.02.2016.
 */
public class Data {
    final public static Data INSTANCE = new Data();
    private Data(){}
    public short input; // байт тумблера ввода
    public short output;
    public Processes process = Processes.STOP;
    //public Processes process;
    public String PCadr = "";
    public Short A; // аккумулятор
    public Short B; // Регистр
    public Short C; // Регистр
    public Short D; // Регистр
    public Short E; // Регистр
    public Short H; // Регистр
    public Short L; // Регистр
    public int PC = 0x800; // Счетчик команд
    public short PSW; //  — регистр флагов
    public short SP = 0xfb0; // указатель стека 4016
    public boolean PC_Flag;
    public int numberReg;
    public DigitalPanelButton[] addressValueArray = {DigitalPanelButton._0,DigitalPanelButton._0,DigitalPanelButton._0,DigitalPanelButton._0,DigitalPanelButton._0,DigitalPanelButton._0};
}
