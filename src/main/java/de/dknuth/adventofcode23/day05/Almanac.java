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

    private Map<Long, Long> seedToSoil = new HashMap<>();
    private Map<Long, Long> soilToFertilizer = new HashMap<>();
    private Map<Long, Long> fertilizerToWater = new HashMap<>();
    private Map<Long, Long> waterToLight = new HashMap<>();
    private Map<Long, Long> lightToTemperature = new HashMap<>();
    private Map<Long, Long> temperatureToHumidity = new HashMap<>();
    private Map<Long, Long> humidityToLocation = new HashMap<>();
    private final List<Map<Long, Long>> MAPS = Arrays.asList(seedToSoil, soilToFertilizer, fertilizerToWater,
            waterToLight, lightToTemperature, temperatureToHumidity, humidityToLocation);

    public Almanac(List<String> inputs) {
        for (int i = 0; i < MAP_NAMES.size(); i++) {
            MAPS.get(i).putAll(generateMap(MAP_NAMES.get(i), inputs));
        }
    }

    private Map<Long, Long> generateMap(String name, List<String> input) {
        Map<Long, Long> map = new HashMap<>();
        for (int i = indexOfMapName(name, input) + 1; i < input.size() && !input.get(i).isBlank(); i++) {
            List<Long> destinationSourceRange = destinationSourceRange(input.get(i));
            for (long j = 0; j < destinationSourceRange.get(2); j++) {
                map.put(destinationSourceRange.get(1) + j, destinationSourceRange.get(0) + j);
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

    public long seedToLocation(long source) {
        return Stream.of(source)
                .map(this::seedToSoil)
                .map(this::soilToFertilizer)
                .map(this::fertilizerToWater)
                .map(this::waterToLight)
                .map(this::lightToTemperature)
                .map(this::temperatureToHumidity)
                .map(this::humidityToLocation)
                .findFirst().orElse(-1l);
    }

    private long seedToSoil(long source) {
        return getDestination(seedToSoil, source);
    }

    private long soilToFertilizer(long source) {
        return getDestination(soilToFertilizer, source);
    }

    private long fertilizerToWater(long source) {
        return getDestination(fertilizerToWater, source);
    }

    private long waterToLight(long source) {
        return getDestination(waterToLight, source);
    }

    private long lightToTemperature(long source) {
        return getDestination(lightToTemperature, source);
    }

    private long temperatureToHumidity(long source) {
        return getDestination(temperatureToHumidity, source);
    }

    private long humidityToLocation(long source) {
        return getDestination(humidityToLocation, source);
    }

    private long getDestination(Map<Long, Long> map, long source) {
        return map.containsKey(source) ? map.get(source) : source;
    }
}
