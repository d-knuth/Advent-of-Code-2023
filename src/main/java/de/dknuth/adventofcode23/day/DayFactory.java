package de.dknuth.adventofcode23.day;

import java.lang.reflect.InvocationTargetException;

public class DayFactory {

    private DayFactory() {
    }

    public static boolean canCreate(int daynumber) {
        try {
            Class.forName(classNameOf(daynumber));
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static Day create(int dayNumber) {
        try {
            Class<?> clazz = Class.forName(classNameOf(dayNumber));
            Object instance = clazz.getDeclaredConstructor().newInstance();
            if (instance instanceof Day) {
                Day day = (Day) instance;
                day.init();
                return day;
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassCastException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Day has not been solved yet.");
        }
        System.out.println("Specified day has not implemented Day interface.");
        return null;
    }

    private static String classNameOf(int dayNumber) {
        String number = dayNumber < 10 ? "0" + dayNumber : String.valueOf(dayNumber);
        return DayFactory.class.getPackageName() + number + ".Day" + number;
    }
}
