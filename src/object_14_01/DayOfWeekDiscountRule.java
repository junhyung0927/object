package object_14_01;

import time.DateTimeInterval;

import java.time.DayOfWeek;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class DayOfWeekDiscountRule {
    //요일의 목록
    private List<DayOfWeek> dayOfWeeks = new ArrayList<>();
    //단위 시간
    private Duration duration = Duration.ZERO;
    //단위 요금
    private Money amount = Money.ZERO;

    private DayOfWeekDiscountRule(List<DayOfWeek> dayOfWeeks, Duration duration, Money amount) {
        this.dayOfWeeks = dayOfWeeks;
        this.duration = duration;
        this.amount = amount;
    }

    //요일 조건을 만족할 경우 단위 시간과 단위 요금을 통해 통화 요금 계산
    public Money calculate(DateTimeInterval interval) {
        if (dayOfWeeks.contains(interval.getFrom().getDayOfWeek())) {
            return amount.times(interval.duration().getSeconds() / duration.getSeconds());
        }

        return Money.ZERO;
    }
}
