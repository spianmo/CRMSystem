package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.sun.org.apache.bcel.internal.util.SecuritySupport.getResourceAsStream;

/**
 * @ClassName RandomLineUtil
 * @Description TODO
 * @Author Finger
 * @Date 12/7/2020
 **/
public class RandomLineUtil {
    List<String> list = new ArrayList<>();

    public RandomLineUtil(String fileName) {
        BufferedReader reader = null;
        try {
            InputStreamReader read = new InputStreamReader(getResourceAsStream(fileName));
            reader = new BufferedReader(read);
            String str;
            while (null != (str = reader.readLine())) {
                list.add(str);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getRandomNumber(int total) {
        return (int) (Math.random() * total);
    }

    public String getRandomLine() {
        if (null != list) {
            int line = getRandomNumber(list.size());
            return list.get(line);
        }
        return null;
    }
}
