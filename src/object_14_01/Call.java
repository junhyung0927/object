package object_14_01;

import time.DateTimeInterval;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Call {
    private DateTimeInterval interval;

    public Call(LocalDateTime from, LocalDateTime to) {
        this.interval = DateTimeInterval.of(from, to);
    }

    public List<DateTimeInterval> splitByDay() {
        return interval.splitByDay();
    }

    public Duration getDuration() {
        return interval.duration();
    }

    public LocalDateTime getFrom() {
        return interval.getFrom();
    }

    public LocalDateTime getTo() {
        return interval.getTo();
    }

    public DateTimeInterval getInterval() {
        return interval;
    }
}