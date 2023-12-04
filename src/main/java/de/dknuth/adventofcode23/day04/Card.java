package de.dknuth.adventofcode23.day04;

import java.util.Arrays;
import java.util.List;

public class Card {

    private int id;
    private List<String> winningNumbers;
    private List<String> numbersYouHave;

    Card(String input) {
        this.id = cardId(input);
        this.winningNumbers = winningNumbersOutOf(input);
        this.numbersYouHave = numbersYouHaveOutOf(input);
    }

    public int getId() {
        return id;
    }

    public List<String> getWinningNumbers() {
        return winningNumbers;
    }

    public List<String> getNumbersYouHave() {
        return numbersYouHave;
    }

    private int cardId(String input) {
        return numberOutOf(input.substring(5, input.indexOf(":")));
    }

    private int numberOutOf(String input) {
        return Integer.parseInt(input.replaceAll("\\D", ""));
    }

    private List<String> winningNumbersOutOf(String input) {
        return numbersOutOf(input, 0);
    }

    private List<String> numbersYouHaveOutOf(String input) {
        return numbersOutOf(input, 1);
    }

    private List<String> numbersOutOf(String input, int zeroOrOne) {
        return Arrays.asList(input
                .substring(input.indexOf(":") + 1)
                .split("\\|")[zeroOrOne]
                .trim()
                .replace("  ", " ")
                .split(" "));
    }

    int calcPoints() {
        return (int) Math.signum(winningNumbersCount()) * (int) Math.pow(2, winningNumbersCount() - 1.0);
    }

    int winningNumbersCount() {
        return (int) numbersYouHave.stream().filter(n -> winningNumbers.contains(n)).count();
    }

}
