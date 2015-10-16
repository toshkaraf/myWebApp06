package ru.javawebinar.webapp;


import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class TestRunner {
    private Class clazz;
    private List<Method> beforeMethods;
    private List<Method> testMethods;

    public TestRunner(String className) throws Exception {
        clazz = Class.forName(className);
        beforeMethods = getMethodsByAnnotation(Before.class);
        testMethods = getMethodsByAnnotation(Test.class);
    }

    public static void main(String[] args) throws Exception {
        new TestRunner("main.ru.javawebinar.webapp.store.ArrayStorageTest").runAll();
        new TestRunner("main.ru.javawebinar.webapp.store.SortedArrayStorageTest").runAll();
    }

    public void runAll() {
        int testsFailed = 0;
        int testsNumber = 0;
        System.out.println("-------------------------------------------");
        System.out.println("RUNNING TESTS FOR " + clazz.toString());
        for (Method testMethod : testMethods) {
            testsNumber++;
            if (!runTest(testMethod)) {
                testsFailed++;
            }
        }
        System.out.println("-------------------------------------------");
        System.out.println(String.format("Failed %d from %d tests", testsFailed, testsNumber));
        System.out.println("-------------------------------------------");
    }

    public boolean runTest(Method testMethod) {
        Object instance;
        try {
            instance = clazz.newInstance();
            for (Method method : beforeMethods) {
                method.invoke(instance);
            }
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new IllegalStateException("Wrong test syntax");
        }
        try {
            testMethod.invoke(instance);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Wrong test syntax");
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            Class<? extends Throwable> expectedClass = testMethod.getAnnotation(Test.class).expected();
//          e instanceof expectedClass
            return targetException.getClass().isAssignableFrom(expectedClass);
        }
        return true;
    }

    private List<Method> getMethodsByAnnotation(Class annotationClass) {
        List<Method> annotatedMethods = new LinkedList<>();
        for (Method method : clazz.getMethods()) {
            if (method.getAnnotation(annotationClass) != null) {
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }
}
