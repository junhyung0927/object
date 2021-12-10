package object_14_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class BasicRatePolicy implements RatePolicy {
    private List<FeeRule> feeRules = new ArrayList<>();

    public BasicRatePolicy(FeeRule ... feeRules) {
        this.feeRules = Arrays.asList(feeRules);
    }

    @Override
    public Money calculateFee(Phone phone) {
        return phone.getCalls()
                .stream()
                .map(call -> calculate(call))
                .reduce(Money.ZERO, (first, second) -> first.plus(second));
    }

    private Money calculate(Call call) {
        return feeRules
                .stream()
                //feeRules 컬렉션을 이용해 전체 통화 요금을 계산
                .map(rule -> rule.calculateFee(call))
                .reduce(Money.ZERO, (first, second) -> first.plus(second));
    }
}
