package de.dknuth.adventofcode23.day;

public class DayFactory {

    private DayFactory() {
    };

    public static <T extends Day> Day create(Class<T> dayClass) {
        try {
            Day instance = dayClass.newInstance();
            instance.init();
            return instance;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
