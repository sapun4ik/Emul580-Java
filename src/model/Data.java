package model;

import controller.DigitalPanelButton;
import controller.Processes;

/**
 * Created by Анатолий on 21.02.2016.
 */
public class Data {
    final public static Data INSTANCE = new Data();
    private Data(){}
    public static byte input; // байт тумблера ввода
    public static byte output;
    public Processes process = Processes.STOP;
    public static String PCadr = "";
    public static byte A; // аккумулятор
    public static byte B; // Регистр
    public static byte C; // Регистр
    public static byte D; // Регистр
    public static byte E; // Регистр
    public static byte H; // Регистр
    public static byte L; // Регистр
    public int PC = 0x800; // Счетчик команд
    public static short PSW; //  — регистр флагов
    public static short SP = 0xfb0; // указатель стека 4016
    public static boolean PC_Flag;
    public static int numberReg;
    public static boolean executionProgramms = false;
    public DigitalPanelButton[] addressValueArray = new DigitalPanelButton[6];
}
