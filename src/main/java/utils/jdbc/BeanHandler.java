package utils.jdbc;

/**
 * @ClassName BeanHandler
 * @Description TODO
 * @Author Finger
 * @Date 12/30/2020
 **/

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;

import cn.hutool.core.util.ReflectUtil;

/**
 * 返回一个JavaBean
 * @Author: fangju
 * @Date: 2019/6/15
 */
/**
 * 返回一个JavaBean
 * @Author: fangju
 * @Date: 2019/6/15
 */
public class BeanHandler<T> implements IResultSetHandler<T> {
    private Class<T> clazz;

    public BeanHandler(Class<T> clazz){
        this.clazz = clazz;
    }

    @Override
    public T handle(ResultSet rs) {
        try{
            if (rs.next()){
                //根据传入的字节码创建传入的指定对象
                T obj = clazz.newInstance();
                //获取指定字节码信息
                BeanInfo beanInfo = Introspector.getBeanInfo(clazz,Object.class);
                //获取所有属性描述器
                PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor pd:pds){
                    //获取结果集中对应字段名的值
                    Object o = rs.getObject(HumpUtil.HumpToUnderline(pd.getName()));
                    //执行当前方法并传入参数
/*                    System.out.println("=====User属性名称==>"+pd.getName());
                    System.out.println("=====User属性类型==>"+pd.getPropertyType().getName());
                    System.out.println("=====User类=====>"+obj.getClass().getName());
                    System.out.println("=====User实体=====>"+obj.toString());
                    System.out.println("=====数据库字段=====>"+HumpUtil.HumpToUnderline(pd.getName()));
                    System.out.println("=====数据库值=====>"+o.toString());
                    System.out.println("==========>>"+pd.getReadMethod().getName());*/
                    Class<?> clazz = ReflectUtil.getField(obj.getClass(),pd.getName()).getType();
                    if (!clazz.isEnum()){
                        pd.getWriteMethod().invoke(obj,o);
                    }else{
                        System.out.println("====>>>处理枚举"+clazz);
                        Method method = ReflectUtil.getMethodByName(clazz, "getEnum");
                        System.out.println(method);
                        Object enumObj = method.invoke(null,o);
                        pd.getWriteMethod().invoke(obj,enumObj);
                    }
                }
                return obj;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
