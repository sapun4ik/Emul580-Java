package controller;

import java.util.Objects;

/**
 * Created by Анатолий on 20.02.2016.
 */
public enum DigitalPanelButton {
    _0("0"),
    _1("1"),
    _2("2"),
    _3("3"),
    _4("4"),
    _5("5"),
    _6("6"),
    _7("7"),
    _8("8"),
    _9("9"),
    _A("A"),
    _B("B"),
    _C("C"),
    _D("D"),
    _E("E"),
    _F("F");

    public static DigitalPanelButton getDigitalPanelBtn(Character al)
    {
        for (DigitalPanelButton val : DigitalPanelButton.values()) {
            if (Objects.equals(String.valueOf(al).toUpperCase(), val.text)) return val;
        }
        return null;
    }
    public final String text;
    DigitalPanelButton(final String text){
        this.text = text;
    }
}
