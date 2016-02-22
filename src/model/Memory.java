package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Анатолий on 21.02.2016.
 */
public class Memory {
    public Map<String,Integer> RAM = new HashMap<>();
    Converters converters = Converters.INSTANCE;
    public static final Memory INSTANCE = new Memory();
    private Memory(){
        for (int i = 0; i <= 0xFFFF; i++)
        {
            String temp = converters.UInt16ToHex(i);
            RAM.put(temp, 0);
        }

    }

}
