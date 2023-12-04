package de.dknuth.adventofcode23.day04;

import java.util.Arrays;
import java.util.List;

public class Card {

    private List<String> winningNumbers;
    private List<String> numbersYouHave;

    Card(String input) {
        this.winningNumbers = winningNumbersOutOf(input);
        this.numbersYouHave = numbersYouHave(input);
    }

    public List<String> getWinningNumbers() {
        return winningNumbers;
    }

    public List<String> getNumbersYouHave() {
        return numbersYouHave;
    }

    private List<String> winningNumbersOutOf(String input) {
        return numbersOutOf(input, 0);
    }

    private List<String> numbersYouHave(String input) {
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

    Integer calcPoints() {
        int winnningNumbersCount = (int) numbersYouHave.stream().filter(n -> winningNumbers.contains(n)).count();
        return (int) Math.signum(winnningNumbersCount) * (int) Math.pow(2, winnningNumbersCount - 1.0);
    }

}
