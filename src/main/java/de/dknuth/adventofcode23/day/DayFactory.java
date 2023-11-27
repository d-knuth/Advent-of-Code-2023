package de.dknuth.adventofcode23.day;

import java.lang.reflect.InvocationTargetException;

public class DayFactory {

    private DayFactory() {
    }

    public static boolean canCreate(int daynumber) {
        try {
            Class.forName(numberToClassname(daynumber));
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public static Day create(int dayNumber) {
        try {
            Class<? extends Day> dayClass = (Class<? extends Day>) Class.forName(numberToClassname(dayNumber));
            Day instance = dayClass.getDeclaredConstructor().newInstance();
            instance.init();
            return instance;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassCastException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Day has not been solved yet.");
        }
        return null;
    }

    private static String numberToClassname(int n) {
        String number = n < 10 ? "0" + n : String.valueOf(n);
        return DayFactory.class.getPackageName() + number + ".Day" + number;
    }
}
