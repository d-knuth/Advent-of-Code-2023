package de.dknuth.adventofcode23.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import de.dknuth.adventofcode23.day.Day;

public class Day04 implements Day {

    @Override
    public String solutionToPart1(List<String> inputs) {

        return inputs.stream()
                .map(Card::new)
                .map(Card::calcPoints)
                .reduce(0, Integer::sum)
                .toString();
    }

    @Override
    public String solutionToPart2(List<String> inputs) {
        List<Card> cardDeck = inputs.stream().map(Card::new).collect(Collectors.toCollection(ArrayList::new));
        for (int i = 0; i < cardDeck.size(); i++) {
            Card currentCard = cardDeck.get(i);
            int currentCardId = currentCard.getId();
            for (int j = 1; j <= currentCard.winningNumbersCount(); j++) {
                getCardWithId(cardDeck, currentCardId + j).ifPresent(cardDeck::add);
            }
        }
        return "" + cardDeck.size();
    }

    private Optional<Card> getCardWithId(List<Card> deck, int id) {
        return deck.stream().filter(c -> c.getId() == id).findFirst();
    }

}
