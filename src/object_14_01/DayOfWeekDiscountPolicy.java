package object_14_01;
import time.DateTimeInterval;

import java.util.ArrayList;
import java.util.List;

public class DayOfWeekDiscountPolicy extends BasicRatePolicy {
    private List<DayOfWeekDiscountRule> rules = new ArrayList<>();

    private DayOfWeekDiscountPolicy(List<DayOfWeekDiscountRule> rules) {
        this.rules = rules;
    }

    @Override
    protected Money calculateCallFee(Call call) {
        Money result = Money.ZERO;
        //통화 기간을 날짜 경계로 분리
        for (DateTimeInterval interval : call.getInterval().splitByDay()) {
            //각 통화 기간을 요일별로 설정된 요금 정책에 따라 계산
            for (DayOfWeekDiscountRule rule: rules) {
                result.plus(rule.calculate(interval));
            }
        }
        return result;
    }
}
