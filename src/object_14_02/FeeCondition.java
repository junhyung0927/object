package object_14_02;

import time.DateTimeInterval;

import java.util.List;

//적용 조건을 표현하는 인터페이스
public interface FeeCondition {
    List<DateTimeInterval> findTimeIntervals(Call call);
}
