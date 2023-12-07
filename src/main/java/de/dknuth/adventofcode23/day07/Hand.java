package de.dknuth.adventofcode23.day07;

import static java.util.Map.entry;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Hand implements Comparable<Hand> {

    private String cards;
    private long bid;
    private boolean withJokers;
    private long jokerCount;
    private Map<String, Long> cardsCount;
    private Map<Character, Integer> cardStrengths;

    public Hand(String cards, long bid) {
        this(cards, bid, false);
    }

    public Hand(String cards, long bid, boolean withJokers) {
        this.withJokers = withJokers;
        this.cards = cards;
        this.bid = bid;
        this.cardsCount = countCards();
        this.jokerCount = jokerCount();

        if (withJokers) {
            this.cardsCount.remove("J");
            this.cardStrengths = Map.ofEntries(
                    entry('2', 2),
                    entry('3', 3),
                    entry('4', 4),
                    entry('5', 5),
                    entry('6', 6),
                    entry('7', 7),
                    entry('8', 8),
                    entry('9', 9),
                    entry('T', 10),
                    entry('J', 1),
                    entry('Q', 12),
                    entry('K', 13),
                    entry('A', 14));
        } else {
            this.cardStrengths = Map.ofEntries(
                    entry('2', 2),
                    entry('3', 3),
                    entry('4', 4),
                    entry('5', 5),
                    entry('6', 6),
                    entry('7', 7),
                    entry('8', 8),
                    entry('9', 9),
                    entry('T', 10),
                    entry('J', 11),
                    entry('Q', 12),
                    entry('K', 13),
                    entry('A', 14));
        }
    }

    public long getBid() {
        return bid;
    }

    @Override
    public int compareTo(Hand other) {
        if (this.rankOfHand() > other.rankOfHand()) {
            return 1;
        } else if (this.rankOfHand() < other.rankOfHand()) {
            return -1;
        }
        for (int i = 0; i < this.cards.length(); i++) {
            if (cardStrengths.get(cards.charAt(i)) > cardStrengths.get(other.cards.charAt(i))) {
                return 1;
            } else if (cardStrengths.get(cards.charAt(i)) < cardStrengths.get(other.cards.charAt(i))) {
                return -1;
            }
        }
        return 0;
    }

    private Map<String, Long> countCards() {
        List<String> cardList = stringListOf(cards);
        return cardList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private List<String> stringListOf(String s) {
        return s.chars().boxed().map(c -> Character.toString((char) c.intValue())).toList();
    }

    private int rankOfHand() {
        if (isFiveOfAKind()) {
            return 6;
        }
        if (isFourOfAKind()) {
            return 5;
        }
        if (isFullHouse()) {
            return 4;
        }
        if (isThreeOfAKind()) {
            return 3;
        }
        if (isTwoPairs()) {
            return 2;
        }
        if (isPair()) {
            return 1;
        }
        return 0;
    }

    private long jokerCount() {
        return cardsCount.get("J") != null && withJokers ? cardsCount.get("J") : 0;
    }

    private boolean isFiveOfAKind() {
        return maxCountInCards(cardsCount) + jokerCount == 5l;
    }

    private boolean isFourOfAKind() {
        return maxCountInCards(cardsCount) + jokerCount == 4l;
    }

    private boolean isFullHouse() {
        Map<String, Long> cardsCountCopy = new HashMap<>(cardsCount);
        boolean isThreeOfAKind = maxCountInCards(cardsCountCopy) + jokerCount == 3l;
        cardsCountCopy.remove(getCardWithMaxCount(cardsCountCopy));
        boolean isPair = cardsCountCopy.values().contains(2l);
        return isThreeOfAKind && isPair;
    }

    private boolean isThreeOfAKind() {
        return maxCountInCards(cardsCount) + jokerCount == 3l && !isFullHouse();
    }

    private boolean isTwoPairs() {
        Map<String, Long> cardsCountCopy = new HashMap<>(cardsCount);
        boolean isPair = cardsCountCopy.values().contains(2l) || maxCountInCards(cardsCountCopy) + jokerCount == 2l;
        cardsCountCopy.remove(getCardWithMaxCount(cardsCountCopy));
        boolean isSecondPair = cardsCountCopy.values().contains(2l);
        return isPair && isSecondPair;

    }

    private boolean isPair() {
        return maxCountInCards(cardsCount) + jokerCount == 2l && !isTwoPairs();
    }

    private String getCardWithMaxCount(Map<String, Long> map) {
        long max = maxCountInCards(map);
        return map.entrySet().stream().filter(e -> e.getValue() == max).map(Entry::getKey).findFirst().orElse("");
    }

    private long maxCountInCards(Map<String, Long> cardsCount) {
        return cardsCount.values().stream().max(Comparator.naturalOrder()).orElse(0l);
    }

}
