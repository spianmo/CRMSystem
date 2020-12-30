package utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * @ClassName FormatUtil
 * @Description TODO
 * @Author Finger
 * @Date 11/26/2020
 **/
public class FormatUtil {
    public static String formatGender(int gender) {
        if (gender == 0) {
            return "保密";
        } else if (gender == 1) {
            return "男";
        } else {
            return "女";
        }
    }

    public static String formatDate(Date data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(data);
    }

    public static Date formatAgaDate(String stringCellValue) {
        return Date.valueOf(stringCellValue);
    }
}
