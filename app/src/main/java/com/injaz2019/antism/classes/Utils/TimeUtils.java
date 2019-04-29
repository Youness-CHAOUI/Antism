package com.injaz2019.antism.classes.Utils;

public class TimeUtils {
    public static boolean periodFromTime(String time) {
        int H = Integer.parseInt(time.substring(0, 2));
        if (H >= 0 && H < 12)
            return false;
        else
            return true;
    }
}
