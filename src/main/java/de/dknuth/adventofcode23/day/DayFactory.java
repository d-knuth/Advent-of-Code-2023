package de.dknuth.adventofcode23.day;

import static java.util.Map.entry;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Map;
import java.util.NoSuchElementException;

import de.dknuth.adventofcode23.day01.Day01;

public class DayFactory {

    private DayFactory() {
    }

    private static final Map<Integer, Class<? extends Day>> DAY_CLASSES = Map.ofEntries(
            entry(1, Day01.class)
    // ,entry(2, Day02.class)
    // ,entry(3, Day03.class)
    // ,entry(4, Day04.class)
    // ,entry(5, Day05.class)
    // ,entry(6, Day06.class)
    // ,entry(7, Day07.class)
    // ,entry(8, Day08.class)
    // ,entry(9, Day09.class)
    // ,entry(10, Day10.class)
    // ,entry(11, Day11.class)
    // ,entry(12, Day12.class)
    // ,entry(13, Day13.class)
    // ,entry(14, Day14.class)
    // ,entry(15, Day15.class)
    // ,entry(16, Day16.class)
    // ,entry(17, Day17.class)
    // ,entry(18, Day18.class)
    // ,entry(19, Day19.class)
    // ,entry(20, Day20.class)
    // ,entry(21, Day21.class)
    // ,entry(22, Day22.class)
    // ,entry(23, Day23.class)
    // ,entry(24, Day24.class)
    // ,entry(25, Day25.class)
    );

    public static boolean canCreate(int daynumber) {
        return DAY_CLASSES.containsKey(daynumber);
    }

    public static int getMaxDayNumber() {
        return DAY_CLASSES.keySet().stream().max(Comparator.naturalOrder())
                .orElseThrow(() -> new NoSuchElementException("No Solution present."));
    }

    public static Day create(int dayNumber) {
        if (!canCreate(dayNumber)) {
            System.out.println("Day has not been solved yet.");
            return null;
        }
        try {
            Day instance = DAY_CLASSES.get(dayNumber).getDeclaredConstructor().newInstance();
            instance.init();
            return instance;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }
}
