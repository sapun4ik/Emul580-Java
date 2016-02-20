package model;

/**
 * Created by Анатолий on 20.02.2016.
 */
public enum ImageResources {
    BG("bg.png"),
    ON("on.png"),
    OFF("off.png"),
    SWITCH_OFF("_off.png"),
    _0("_0.png"),
    _4("_4.png"),
    _H("_H.png"),
    _l("l.png"),
    _A("_A.png");
    public final String text;
    ImageResources(final String text){
        this.text = text;
    }
}
