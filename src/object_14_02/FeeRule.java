package object_14_02;

//통화 구간에 단위요금을 적용해서 요금을 계산하는 요금 기준 클래스
public class FeeRule {
    //적용 조건
    private FeeCondition feeCondition;
    //단위 요금
    private FeePerDuration feePerDuration;

    public FeeRule(FeeCondition feeCondition, FeePerDuration feePerDuration) {
        this.feeCondition = feeCondition;
        this.feePerDuration = feePerDuration;
    }

    //조건을 만족하는 시간의 목록을 반환받은 후 feePerDuration의 값을 이용하여 요금 계산
    public Money calculateFee(Call call) {
        return feeCondition.findTimeIntervals(call)
                .stream()
                .map(each -> feePerDuration.calculate(each))
                .reduce(Money.ZERO, (first, second) -> first.plus(second));
    }
}
