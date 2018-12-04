package day19homework;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestTreasure {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 类加载器, 作用：加载一个不在classpath下的类
        ClassLoader cl = new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                try {
                    FileInputStream in  = new FileInputStream("Treasure.class");
                    byte[] bytes = new byte[1024*8];
                    int len = in.read(bytes);

                    // 调用父类的方法根据字节数组加载类
                    return defineClass(name, bytes, 0, len);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };

        Class<?> clazz = cl.loadClass("com.westos.Treasure"); // 根据类名加载类, 得到类对象
        Constructor<?>[] constructor = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor1 : constructor) {
            System.out.println(constructor1);
        }

        constructor[0].setAccessible(true);
        Object o = constructor[0].newInstance();
        Method[] methods = clazz.getMethods();
//      System.out.println(methods);
//        for (Method method : methods) {
//            System.out.println(method.getAnnotations());
//        }
        for (int i = 0; i < methods.length; i++) {
            if(!methods[i].getAnnotations().toString().equals(methods[i+1].getAnnotations().toString())){
                methods[i+1].invoke(o);
                break;
            }
        }
    }
}
