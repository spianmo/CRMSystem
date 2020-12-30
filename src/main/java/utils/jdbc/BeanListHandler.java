package utils.jdbc;

/**
 * @ClassName BeanListHandler
 * @Description TODO
 * @Author Finger
 * @Date 12/30/2020
 **/

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.ReflectUtil;

/**
 * 返回一个JavaBean的集合
 * @Author: fangju
 * @Date: 2019/6/15 17:31
 */
/**
 * 返回一个JavaBean的集合
 * @Author: fangju
 * @Date: 2019/6/15 17:31
 */
public class BeanListHandler<T> implements IResultSetHandler<List<T>> {
    private Class<T> clazz;

    public BeanListHandler(Class<T> clazz){
        this.clazz = clazz;
    }

    @Override
    public List<T> handle(ResultSet rs) {
        List<T> list = new ArrayList<>();
        try {
            //获取指定字节码信息
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz,Object.class);
            //获取所有属性描述器
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            while (rs.next()){
                T obj = clazz.newInstance();
                for (PropertyDescriptor pd:pds){
                    //获取结果集中对应字段名的值
                    Object o = rs.getObject(HumpUtil.HumpToUnderline(pd.getName()));
                    //执行当前方法并传入参数
                    Class<?> clazz = ReflectUtil.getField(obj.getClass(),pd.getName()).getType();
                    if (!clazz.isEnum()){
                        pd.getWriteMethod().invoke(obj,o);
                    }else{
                        Method method = ReflectUtil.getMethodByName(clazz, "getEnum");
                        System.out.println(method);
                        Object enumObj = method.invoke(null,o);
                        pd.getWriteMethod().invoke(obj,enumObj);
                    }
                }
                list.add(obj);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

}




