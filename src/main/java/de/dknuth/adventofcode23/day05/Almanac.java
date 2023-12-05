package de.dknuth.adventofcode23.day05;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Almanac {

    private final List<String> MAP_NAMES = Arrays.asList(
            "seed-to-soil", "soil-to-fertilizer", "fertilizer-to-water", "water-to-light", "light-to-temperature",
            "temperature-to-humidity", "humidity-to-location");

    private Map<String, String> seedToSoil = new HashMap<>();
    private Map<String, String> soilToFertilizer = new HashMap<>();
    private Map<String, String> fertilizerToWater = new HashMap<>();
    private Map<String, String> waterToLight = new HashMap<>();
    private Map<String, String> lightToTemperature = new HashMap<>();
    private Map<String, String> temperatureToHumidity = new HashMap<>();
    private Map<String, String> humidityToLocation = new HashMap<>();
    private final List<Map<String, String>> MAPS = Arrays.asList(seedToSoil, soilToFertilizer, fertilizerToWater,
            waterToLight, lightToTemperature, temperatureToHumidity, humidityToLocation);

    public Almanac(List<String> inputs) {
        for (int i = 0; i < MAP_NAMES.size(); i++) {
            MAPS.get(i).putAll(generateMap(MAP_NAMES.get(i), inputs));
        }
    }

    private Map<String, String> generateMap(String name, List<String> input) {
        Map<String, String> map = new HashMap<>();
        for (int i = indexOfMapName(name, input) + 1; i < input.size() && !input.get(i).isBlank(); i++) {
            List<Long> destinationSourceRange = destinationSourceRange(input.get(i));
            for (long j = 0; j < destinationSourceRange.get(2); j++) {
                map.put(String.valueOf(destinationSourceRange.get(1) + j).intern(),
                        String.valueOf(destinationSourceRange.get(0) + j).intern());
            }
        }
        return map;
    }

    private int indexOfMapName(String name, List<String> input) {
        return input.indexOf(name + " map:");
    }

    private List<Long> destinationSourceRange(String input) {
        return Arrays.asList(input.replace("  ", " ")
                .split(" ")).stream()
                .map(Long::parseLong).toList();
    }

    public String seedToLocation(String source) {
        return Stream.of(source)
                .map(this::seedToSoil)
                .map(this::soilToFertilizer)
                .map(this::fertilizerToWater)
                .map(this::waterToLight)
                .map(this::lightToTemperature)
                .map(this::temperatureToHumidity)
                .map(this::humidityToLocation)
                .findFirst().orElse("");
    }

    private String seedToSoil(String source) {
        return getDestination(seedToSoil, source);
    }

    private String soilToFertilizer(String source) {
        return getDestination(soilToFertilizer, source);
    }

    private String fertilizerToWater(String source) {
        return getDestination(fertilizerToWater, source);
    }

    private String waterToLight(String source) {
        return getDestination(waterToLight, source);
    }

    private String lightToTemperature(String source) {
        return getDestination(lightToTemperature, source);
    }

    private String temperatureToHumidity(String source) {
        return getDestination(temperatureToHumidity, source);
    }

    private String humidityToLocation(String source) {
        return getDestination(humidityToLocation, source);
    }

    private String getDestination(Map<String, String> map, String source) {
        return map.containsKey(source) ? map.get(source) : source;
    }
}
