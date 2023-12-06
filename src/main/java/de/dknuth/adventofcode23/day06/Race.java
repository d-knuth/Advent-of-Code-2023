package de.dknuth.adventofcode23.day06;

import java.util.stream.LongStream;

public class Race {

    private long time;
    private long recordDistance;

    public Race(long time, long recordDistance) {
        this.time = time;
        this.recordDistance = recordDistance;
    }

    long countWaysToBeatRecord() {
        return LongStream.range(1, this.time).filter(this::checkIfBetterThanRecord).count();
    }

    private boolean checkIfBetterThanRecord(long buttonPressTime) {
        return calcDistance(buttonPressTime) > recordDistance;
    }

    private long calcDistance(long buttonPressTime) {
        long timeRemainig = this.time - buttonPressTime > 0 ? this.time - buttonPressTime : 0;
        long speed = buttonPressTime;
        return speed * timeRemainig;
    }

}
