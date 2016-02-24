package model;

import view.NoImageException;

import javax.swing.*;
import java.util.HashMap;

/**
 * Created by Анатолий on 18.02.2016.
 */
public class Images {

    public static final Images INSTANCE = new Images();
    private HashMap<String, ImageIcon> arrImg;

    private Images() {
        arrImg = new HashMap<>();
        setArrImg();
    }

    public ImageIcon getImage(ImageResources img) {
        if (arrImg.get(img.text) == null) try {
            throw new NoImageException("No imege Exeption. Key: " + img + " - null");
        } catch (NoImageException e) {
            e.printStackTrace();
        }
        return arrImg.get(img.text);
    }

    private void setArrImg() {
        for (ImageResources name : ImageResources.values()) {
            try {
                arrImg.put(name.text, new ImageIcon(getClass().getResource("../resources/" + name.text)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public JLabel getImage(ImageResources res, int marginTop, int marginLeft, int marginBottom, int marginRight) {
        JLabel label;
        label = new JLabel(Images.INSTANCE.getImage(res));
        label.setBorder(BorderFactory.createEmptyBorder(marginTop, marginLeft, marginBottom, marginRight));
        return label;
    }


}
