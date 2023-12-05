package de.dknuth.adventofcode23.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Almanac {

    private final List<String> MAP_NAMES = Arrays.asList(
            "seed-to-soil", "soil-to-fertilizer", "fertilizer-to-water", "water-to-light", "light-to-temperature",
            "temperature-to-humidity", "humidity-to-location");

    private AlmanacMap seedToSoil = new AlmanacMap();
    private AlmanacMap soilToFertilizer = new AlmanacMap();
    private AlmanacMap fertilizerToWater = new AlmanacMap();
    private AlmanacMap waterToLight = new AlmanacMap();
    private AlmanacMap lightToTemperature = new AlmanacMap();
    private AlmanacMap temperatureToHumidity = new AlmanacMap();
    private AlmanacMap humidityToLocation = new AlmanacMap();
    private final List<AlmanacMap> MAPS = Arrays.asList(seedToSoil, soilToFertilizer, fertilizerToWater,
            waterToLight, lightToTemperature, temperatureToHumidity, humidityToLocation);

    public Almanac(List<String> inputs) {
        for (int i = 0; i < MAP_NAMES.size(); i++) {
            List<String> input = generateAlmanacMapInput(MAP_NAMES.get(i), inputs);
            MAPS.get(i).init(input);
        }
    }

    private List<String> generateAlmanacMapInput(String name, List<String> input) {
        List<String> result = new ArrayList<>();
        for (int i = indexOfMapName(name, input) + 1; i < input.size() && !input.get(i).isBlank(); i++) {
            result.add(input.get(i));
        }
        return result;
    }

    private int indexOfMapName(String name, List<String> input) {
        return input.indexOf(name + " map:");
    }

    public long seedToLocation(long source) {
        return Stream.of(source)
                .map(this.seedToSoil::get)
                .map(this.soilToFertilizer::get)
                .map(this.fertilizerToWater::get)
                .map(this.waterToLight::get)
                .map(this.lightToTemperature::get)
                .map(this.temperatureToHumidity::get)
                .map(this.humidityToLocation::get)
                .findFirst().orElse(-1l);
    }
}
