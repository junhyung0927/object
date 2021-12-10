package object_14_01;

import time.DateTimeInterval;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimeOfDayDiscountPolicy extends BasicRatePolicy {
    //시작 시간
    private List<LocalTime> starts = new ArrayList<>();
    //종료 시간
    private List<LocalTime> ends = new ArrayList<>();
    //단위 시간
    private List<Duration> durations = new ArrayList<>();
    //단위 요금
    private List<Money> amounts = new ArrayList<>();

    @Override
    protected Money calculateCallFee(final Call call) {
        Money result = Money.ZERO;
        //통화 기간을 일자별로 분리
        for (DateTimeInterval interval : call.splitByDay()) {
            //일자별로 분리된 기간을 다시 시간대별 규칙에 따라 분리한 후 각 기간에 대해 요금 계산
            for (int loop = 0; loop < starts.size(); loop++) {
                result.plus(amounts.get(loop).times(
                    Duration.between(
                        from(interval, starts.get(loop)),
                        to(interval, ends.get(loop))).getSeconds()
                             / durations.get(loop).getSeconds()));
            }
        }
        return result;
    }

    private LocalTime from(DateTimeInterval interval, LocalTime from) {
        return interval.getFrom().toLocalTime().isBefore(from) ?
            from :
            interval.getFrom().toLocalTime();
    }

    private LocalTime to(DateTimeInterval  interval, LocalTime to) {
        return interval.getTo().toLocalTime().isAfter(to) ?
            to :
            interval.getTo().toLocalTime();
    }
}
