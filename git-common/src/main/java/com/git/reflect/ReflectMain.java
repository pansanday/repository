package com.git.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectMain {

    public static void main(String[] args) {
        getProperties();
        getMethods();
    }
    
    /**
     * Method used to: 获取属性及属性值
     */
    public static void getProperties(){
        String clazzName = null;
        for (Field item : ReflectBean.class.getDeclaredFields()) {
            System.out.println("完整的属性定义:" + item.toString());
            System.out.println("修饰类:" + item.getType()); // class com.git.reflect.ReflectUser
            System.out.println("属性名:" + item.getName()); // reflectUser
            try {
                if (item.getName().equals("PI")) {
                    System.out.println("属性值:" + item.get(item.getName())); // 获取属性值
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            if (item.getType().toString().indexOf(" ") > 0) {
                // 完整的类名
                clazzName = item.getType().toString().substring(6,item.getType().toString().length());
                try {
                    Class<?> clazz = Class.forName(clazzName);
                    Object myObj = clazz.newInstance(); // 创建一个类对象
                    // 通过invoke方法调用方法
                    System.out.println("方法执行结果为:" + item.getType().getDeclaredMethod("toString").invoke(myObj).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("==============================");
        }
    }
    /**
     * Method used to: 获取方法及执行方法
     */
    public static void getMethods(){
        for (Method method : ReflectBean.class.getDeclaredMethods()) {
            System.out.println("方法名:" + method.getName()); // getFruit,setFruit..
            System.out.println("方法返回值类型:" + method.getReturnType()); // int,void...
            System.out.println("缺省值:" + method.getDefaultValue());
            try {
                if (method.getName().equals("sayHello")) {
                    System.out.println("方法执行结果为:" + method.invoke(ReflectBean.class.newInstance(),"Panda"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("==============================");
        }
    }
    
}
