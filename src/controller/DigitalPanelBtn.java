package controller;

/**
 * Created by Анатолий on 20.02.2016.
 */
public enum DigitalPanelBtn {
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

    public final String text;
    DigitalPanelBtn(final String text){
        this.text = text;
    }
}
