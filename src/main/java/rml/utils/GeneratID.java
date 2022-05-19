package rml.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneratID {
    public static String getDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getRandomNum(int num) {
        String numStr = "";
        for (int i = 0; i < num; i++) {
            numStr += (int) (10 * (Math.random()));
        }
        return numStr;
    }

    public static Long getGeneratID() {
        String sformat = "MMddhhmmssSSS";
        int num = 3;
        String idStr = getDate(sformat) + getRandomNum(num);
        Long id = Long.valueOf(idStr);
        return id;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            System.out.println(getGeneratID());
        }
    }
}
