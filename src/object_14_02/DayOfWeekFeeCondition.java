package object_14_02;

import time.DateTimeInterval;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//요일별 정책
//FeeCondition 인터페이스를 구현하는 서브 타입
public class DayOfWeekFeeCondition implements FeeCondition {
    //여러 요일을 하나의 단위로 관리
    private List<DayOfWeek> dayOfWeeks = new ArrayList<>();

    public DayOfWeekFeeCondition(DayOfWeek ... dayOfWeeks) {
        this.dayOfWeeks = Arrays.asList(dayOfWeeks);
    }

    //Call의 기간 중에서 요일에 해당하는 기간만 추출
    @Override
    public List<DateTimeInterval> findTimeIntervals(Call call) {
        return call.getInterval()
                .splitByDay()
                .stream()
                .filter(each ->
                        dayOfWeeks.contains(each.getFrom().getDayOfWeek()))
                .collect(Collectors.toList());
    }
}
