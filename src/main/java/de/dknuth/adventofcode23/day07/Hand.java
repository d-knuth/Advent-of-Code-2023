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

    private static final Map<Character, Integer> CARD_STRENGTH = Map.ofEntries(
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

    private static final Map<Character, Integer> CARD_STRENGTH_WITH_JOKERS = Map.ofEntries(
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

    private String cards;
    private long bid;
    private boolean withJokers = false;
    private long jokerCount;

    private Map<String, Long> cardsCount;

    public Hand(String cards, long bid) {
        this.cards = cards;
        this.bid = bid;
        this.cardsCount = countCards();
        this.jokerCount = jokerCount();
    }

    public Hand(String cards, long bid, boolean withJokers) {
        this.cards = cards;
        this.bid = bid;
        this.cardsCount = countCards();
        this.jokerCount = jokerCount();
        this.withJokers = withJokers;
    }

    public long getBid() {
        return bid;
    }

    @Override
    public int compareTo(Hand other) {
        Map<Character, Integer> cardStrength;
        if (withJokers) {
            cardStrength = CARD_STRENGTH_WITH_JOKERS;
        } else {
            cardStrength = CARD_STRENGTH;
        }

        if (this.rankOfHand() > other.rankOfHand()) {
            return 1;
        } else if (this.rankOfHand() < other.rankOfHand()) {
            return -1;
        }
        for (int i = 0; i < this.cards.length(); i++) {
            if (cardStrength.get(cards.charAt(i)) > cardStrength.get(other.cards.charAt(i))) {
                return 1;
            } else if (cardStrength.get(cards.charAt(i)) < cardStrength.get(other.cards.charAt(i))) {
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
        return cardsCount.get("J") != null ? cardsCount.get("J") : 0;
    }

    private boolean isFiveOfAKind() {
        if (withJokers) {
            Map<String, Long> cardsCountCopy = new HashMap<>(cardsCount);
            cardsCountCopy.remove("J");
            return cardsCountCopy.values().contains(5l) || maxCount(cardsCountCopy) + jokerCount >= 5l;
        }
        return cardsCount.values().contains(5l);
    }

    private boolean isFourOfAKind() {
        if (withJokers) {
            Map<String, Long> cardsCountCopy = new HashMap<>(cardsCount);
            cardsCountCopy.remove("J");
            return cardsCountCopy.values().contains(4l) || maxCount(cardsCountCopy) + jokerCount == 4l;
        }
        return cardsCount.values().contains(4l);
    }

    private boolean isFullHouse() {
        if (withJokers) {
            Map<String, Long> cardsCountCopy = new HashMap<>(cardsCount);
            cardsCountCopy.remove("J");
            boolean isThreeOfAKind = cardsCountCopy.values().contains(3l)
                    || maxCount(cardsCountCopy) + jokerCount == 3l;
            cardsCountCopy.remove(getMaxCountKey(cardsCountCopy));
            boolean isPair = cardsCountCopy.values().contains(2l);
            return isThreeOfAKind && isPair;
        }
        return cardsCount.values().contains(3l) && cardsCount.values().contains(2l);
    }

    private boolean isThreeOfAKind() {
        if (withJokers) {
            Map<String, Long> cardsCountCopy = new HashMap<>(cardsCount);
            cardsCountCopy.remove("J");
            return (cardsCountCopy.values().contains(3l) || maxCount(cardsCountCopy) + jokerCount == 3l)
                    && !isFullHouse();
        }
        return cardsCount.values().contains(3l) && !isFullHouse();
    }

    private boolean isTwoPairs() {
        if (withJokers) {
            Map<String, Long> cardsCountCopy = new HashMap<>(cardsCount);
            cardsCountCopy.remove("J");
            boolean isPair = cardsCountCopy.values().contains(2l) || maxCount(cardsCountCopy) + jokerCount == 2l;
            cardsCountCopy.remove(getMaxCountKey(cardsCountCopy));
            boolean isSecondPair = cardsCountCopy.values().contains(2l);
            return isPair && isSecondPair;
        }
        return cardsCount.values().stream().filter(e -> e == 2l).count() == 2;
    }

    private boolean isPair() {
        if (withJokers) {
            Map<String, Long> cardsCountCopy = new HashMap<>(cardsCount);
            cardsCountCopy.remove("J");
            return (cardsCountCopy.values().contains(2l) || maxCount(cardsCountCopy) + jokerCount == 2l)
                    && !isTwoPairs();
        }
        return cardsCount.values().contains(2l) && !isTwoPairs();
    }

    private String getMaxCountKey(Map<String, Long> map) {
        long max = maxCount(map);
        return map.entrySet().stream().filter(e -> e.getValue() == max).map(Entry::getKey).findFirst().orElse("");
    }

    private long maxCount(Map<String, Long> map) {
        return map.values().stream().max(Comparator.naturalOrder()).orElse(0l);
    }

}
