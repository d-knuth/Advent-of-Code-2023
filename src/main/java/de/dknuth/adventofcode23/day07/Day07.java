package de.dknuth.adventofcode23.day07;

import java.util.List;
import java.util.stream.LongStream;

import de.dknuth.adventofcode23.day.Day;

public class Day07 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {
        List<Hand> handsSortedByRank = inputs.stream().map(this::createHand).sorted().toList();
        long sumOfRanksTimesBids = LongStream.range(0, handsSortedByRank.size())
                .map(i -> (i + 1) * handsSortedByRank.get((int) i).getBid())
                .sum();
        return String.valueOf(sumOfRanksTimesBids);
    }

    @Override
    public String solutionToPart2(List<String> inputs) {
        List<Hand> handsSortedByRank = inputs.stream().map(this::createHandWithJokers).sorted().toList();
        long sumOfRanksTimesBids = LongStream.range(0, handsSortedByRank.size())
                .map(i -> (i + 1) * handsSortedByRank.get((int) i).getBid())
                .sum();
        return String.valueOf(sumOfRanksTimesBids);
    }

    private Hand createHand(String input) {
        String hand = input.substring(0, 6).trim();
        long bid = Long.parseLong(input.substring(6).trim());
        return new Hand(hand, bid);
    }

    private Hand createHandWithJokers(String input) {
        String hand = input.substring(0, 6).trim();
        long bid = Long.parseLong(input.substring(6).trim());
        return new Hand(hand, bid, true);
    }

}
