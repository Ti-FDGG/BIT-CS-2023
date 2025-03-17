package model;

import java.util.Comparator;

public class TimeComparator implements Comparator<Time> {
    public int compare(Time time1, Time time2) {
        int hourCompare = time1.getHour() - time2.getHour();

        // test the hour first
        if (hourCompare != 0)
            return hourCompare;

        int minuteCompare =
                time1.getMinute() - time2.getMinute();

        // then test the minute
        if (minuteCompare != 0)
            return minuteCompare;

        int secondCompare =
                time1.getSecond() - time2.getSecond();

        return secondCompare;
    }
}
