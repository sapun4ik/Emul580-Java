package model;

/**
 * Created by Анатолий on 21.02.2016.
 */
public class Converters {
    public static final Converters INSTANCE = new Converters();
    private Converters(){}
    public String UInt16ToHex(int val)
    {
        String temp = Integer.toHexString(val);
        if (temp.length() == 1)
        {
            return ("000" + temp);
        }
        if (temp.length() == 2)
        {
            return ("00" + temp);
        }
        if (temp.length() == 3)
        {
            return ("0" + temp);
        }
        return temp;
    }
    public static boolean GetByte(byte val, int num)
    {
        return (((val >> num) & 1) > 0);
    }
    public static boolean GetUShort(int val, int num)
    {
        return (((val >> num) & 1) > 0);
    }
}
