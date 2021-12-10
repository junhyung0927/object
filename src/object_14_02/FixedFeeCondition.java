package object_14_02;

import time.DateTimeInterval;

import java.util.Arrays;
import java.util.List;

//고정요금 정책
public class FixedFeeCondition implements FeeCondition {
    @Override
    public List<DateTimeInterval> findTimeIntervals(Call call) {
        return Arrays.asList(call.getInterval());
    }
}
