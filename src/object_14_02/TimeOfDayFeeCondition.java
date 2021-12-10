package object_14_02;

import time.DateTimeInterval;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

//시간대별 정책
//FeeCondition 인터페이스를 구현하는 서브 타입
public class TimeOfDayFeeCondition implements FeeCondition {
    private LocalTime from;
    private LocalTime to;

    public TimeOfDayFeeCondition(LocalTime from, LocalTime to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public List<DateTimeInterval> findTimeIntervals(Call call) {
        return call.getInterval().splitByDay()
                .stream()
                //DateTimeInterval의 splitByDay 메서드를 호출하여 날짜별로 시간 간격을 분할한 후
                //from과 to 사이의 시간대 도출
                .filter(each -> from(each).isBefore(to(each)))
                .map(each -> DateTimeInterval.of(
                                LocalDateTime.of(each.getFrom().toLocalDate(), from(each)),
                                LocalDateTime.of(each.getTo().toLocalDate(), to(each))))
                .collect(Collectors.toList());
    }

    private LocalTime from(DateTimeInterval interval) {
        return interval.getFrom().toLocalTime().isBefore(from) ?
                from : interval.getFrom().toLocalTime();
    }

    private LocalTime to(DateTimeInterval interval) {
        return interval.getTo().toLocalTime().isAfter(to) ?
                to : interval.getTo().toLocalTime();
    }
}
