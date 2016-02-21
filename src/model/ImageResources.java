package model;

import controller.DigitalPanelBtn;

/**
 * Created by Анатолий on 20.02.2016.
 */
public enum ImageResources {
    BG("bg.png"),
    ON("on.png"),
    OFF("off.png"),
    SWITCH_OFF("_off.png"),
    _0("_0.png"),
    _1("_1.png"),
    _2("_2.png"),
    _3("_3.png"),
    _4("_4.png"),
    _5("_5.png"),
    _6("_6.png"),
    _7("_7.png"),
    _8("_8.png"),
    _9("_9.png"),
    _A("_A.png"),
    _B("_B.png"),
    _C("_C.png"),
    _D("_D.png"),
    _E("_E.png"),
    _F("_F.png"),
    _H("_H.png"),
    _L("l.png"),
    _NULL("_null.png");

    public final String text;
    public static ImageResources getImageResources(DigitalPanelBtn dpB)
    {
        for (ImageResources val : ImageResources.values()) {
            if (dpB.name() == val.name()) return val;
        }
       return null;
    }
    ImageResources(final String text){
        this.text = text;
    }
}
