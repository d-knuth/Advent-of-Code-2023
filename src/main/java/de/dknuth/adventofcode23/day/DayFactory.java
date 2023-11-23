package de.dknuth.adventofcode23.day;

import java.lang.reflect.InvocationTargetException;

public class DayFactory {

    private DayFactory() {
    };

    public static <T extends Day> Day create(Class<T> dayClass) {
        try {
            Day instance = dayClass.getDeclaredConstructor().newInstance();
            instance.init();
            return instance;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }
}
