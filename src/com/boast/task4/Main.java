/*
 * Main.java 04/08/2017
 *
 * Created by Bondarenko Oleh
 */


package com.boast.task4;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception{
        Class<?>[] paramsType = new Class<?>[] {int.class, int.class, int.class};
        Class<?> clazz = SomeClass.class;
        Constructor<?> constr = clazz.getConstructor(paramsType);
        SomeClass someClass = (SomeClass) constr.newInstance(1, 5, 10);

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            System.out.println("\tName: " + field.getName());
            System.out.println("\tType: " + fieldType.getName());
            System.out.println("\tValue: " + field.get(someClass));
        }

        paramsType = new Class<?>[] {int.class};
        Method method = clazz.getMethod("setAllValues", paramsType);
        method.invoke(someClass, 1000);
    }
}

class SomeClass {
    public int publicValue;
    protected int protectedValue;
    private int privateValue;

    public SomeClass(){
        this(0, 0, 0);
    }
    public SomeClass(int publicValue){
        this(publicValue, 0, 0);
    }
    public SomeClass(int publicValue, int protectedValue){
        this(publicValue, protectedValue, 0);

    }
    public SomeClass(int publicValue, int protectedValue, int privateValue){
        this.publicValue = publicValue;
        this.protectedValue = protectedValue;
        this.privateValue = privateValue;
    }

    public void setProtectedValue(int protectedValue){
        this.protectedValue = protectedValue;
    }
    public void setPrivateValue(int privateValue){
        this.privateValue = privateValue;
    }

    public int getProtectedValue() {
        return protectedValue;
    }
    public int getPrivateValue() {
        return privateValue;
    }

    public void setAllValues(int value){
        publicValue = value;
        protectedValue = value;
        privateValue = value;
    }
}