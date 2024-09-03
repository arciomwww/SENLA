package org.example;

import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationContext {
    private static final Logger logger = Logger.getLogger(ApplicationContext.class.getName());
    private final Map<Class<?>, Object> container = new HashMap<>();
    private final Map<String, String> properties = new HashMap<>();

    public ApplicationContext(String... packageNames) {
        this.properties.putAll(PropertyLoader.loadProperties("C:\\Users\\arcem\\IdeaProjects\\SenlaEx\\main\\src\\main\\resources\\application.properties"));

        ConfigurationBuilder config = new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forJavaClassPath())
                .setScanners(new TypeAnnotationsScanner(), new SubTypesScanner());

        for (String packageName : packageNames) {
            config.addUrls(ClasspathHelper.forPackage(packageName));
        }

        Reflections reflections = new Reflections(config);

        Set<Class<?>> components = reflections.getTypesAnnotatedWith(Component.class);
        logger.info("Found components: " + components);

        for (Class<?> clazz : components) {
            try {
                Object instance = createInstance(clazz);
                container.put(clazz, instance);
                logger.info("Registered instance of: " + clazz.getName());
            } catch (Exception e) {
                logger.log(Level.SEVERE, "An error occurred while creating instance of " + clazz.getName(), e);
            }
        }

        registerInterfaces();

        for (Object instance : container.values()) {
            injectDependencies(instance);
        }
    }

    private Object createInstance(Class<?> clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() > 0) {
                Object[] parameters = new Object[constructor.getParameterCount()];
                for (int i = 0; i < parameters.length; i++) {
                    Class<?> parameterType = constructor.getParameterTypes()[i];
                    parameters[i] = container.get(parameterType);
                    System.out.println("Parameter for constructor: " + parameterType.getName() + " = " + parameters[i]);
                }
                return constructor.newInstance(parameters);
            }
        }
        return clazz.getDeclaredConstructor().newInstance();
    }

    private void injectDependencies(Object instance) {
        Class<?> clazz = instance.getClass();

        logger.info("Injecting dependencies for: " + clazz.getName());

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowire.class)) {
                injectField(instance, field);
            }
            if (field.isAnnotationPresent(Value.class)) {
                injectValue(instance, field);
            }
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Autowire.class)) {
                injectMethod(instance, method);
            }
        }
    }

    private void injectField(Object instance, Field field) {
        Class<?> fieldType = field.getType();
        Object dependency = container.get(fieldType);

        if (dependency != null) {
            field.setAccessible(true);
            try {
                field.set(instance, dependency);
                logger.info("Injected field: " + field.getName() + " in class: " + instance.getClass().getName());
            } catch (IllegalAccessException e) {
                logger.log(Level.SEVERE, "Failed to inject field: " + field.getName(), e);
            }
        } else {
            logger.warning("No dependency found for field: " + field.getName() + " in class: " + instance.getClass().getName());
        }
    }

    private void injectValue(Object instance, Field field) {
        Value valueAnnotation = field.getAnnotation(Value.class);
        String value = properties.get(valueAnnotation.value());

        if (value != null) {
            field.setAccessible(true);
            try {
                field.set(instance, value);
                logger.info("Injected value: " + value + " into field: " + field.getName() + " in class: " + instance.getClass().getName());
            } catch (IllegalAccessException e) {
                logger.log(Level.SEVERE, "Failed to inject value: " + field.getName(), e);
            }
        } else {
            logger.warning("No value found for field: " + field.getName() + " in class: " + instance.getClass().getName());
        }
    }

    private void injectMethod(Object instance, Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] parameters = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            parameters[i] = container.get(parameterTypes[i]);
            System.out.println("Parameter for method: " + parameterTypes[i].getName() + " = " + parameters[i]);
        }

        try {
            method.setAccessible(true);
            method.invoke(instance, parameters);
            logger.info("Injected method: " + method.getName() + " in class: " + instance.getClass().getName());
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.log(Level.SEVERE, "Failed to inject method: " + method.getName(), e);
        }
    }

    private void registerInterfaces() {
        List<Object> instances = new ArrayList<>(container.values());

        for (Object instance : instances) {
            Class<?>[] interfaces = instance.getClass().getInterfaces();
            for (Class<?> iface : interfaces) {
                container.put(iface, instance);
                logger.info("Registered interface: " + iface.getName() + " with instance: " + instance.getClass().getName());
            }
        }
    }

    public <T> T getBean(Class<T> clazz) {
        return clazz.cast(container.get(clazz));
    }

    public void printRegisteredComponents() {
        container.keySet().forEach(key -> System.out.println("Registered component: " + key.getName()));
    }
}