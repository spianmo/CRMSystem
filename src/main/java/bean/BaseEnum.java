package bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseEnum
 * @Description TODO
 * @Author Finger
 * @Date 12/30/2020
 **/
public interface BaseEnum {

    /**
     * 获取枚举标识
     *
     * @return 枚举ID
     */
    Integer getCode();

    /**
     * 获取枚举描述
     *
     * @return
     */
    String getDesc();


    /**
     * 通过枚举类型和code值获取对应的枚举类型
     *
     * @param enumType 枚举实体类型
     * @param code 枚举ID
     * @param <T> 枚举实体
     * @return
     */
    static <T extends BaseEnum> T valueOf(Class<? extends BaseEnum> enumType, Integer code) {
        if (enumType == null || code == null) {
            return null;
        }
        T[] enumConstants = (T[]) enumType.getEnumConstants();
        if (enumConstants == null) {
            return null;
        }
        for (T enumConstant : enumConstants) {
            int enumCode = enumConstant.getCode();
            if (code.equals(enumCode)) {
                return enumConstant;
            }
        }
        return null;
    }

    /**
     * 将enum转换为list
     *
     * @param enumType
     * @param <T>
     * @return
     */
    static <T extends BaseEnum> List<Map<String, Object>> enum2List(Class<? extends BaseEnum> enumType) {
        if (enumType == null) {
            return null;
        }
        T[] enumConstants = (T[]) enumType.getEnumConstants();
        if (enumConstants == null) {
            return null;
        }
        ArrayList<Map<String, Object>> results = new ArrayList<>();
        for (T bean : enumConstants) {
            String desc = bean.getDesc();
            Integer code = bean.getCode();
            HashMap<String, Object> map = new HashMap<>();
            map.put("code", code);
            map.put("desc", desc);
            results.add(map);
        }
        return results;
    }

}