package object_14_01;

import java.time.Duration;

public class FixedFeePolicy extends BasicRatePolicy {
    public Money amount;
    public Duration seconds;

    public FixedFeePolicy(Money amount, Duration seconds) {
        this.amount = amount;
        this.seconds = seconds;
    }

    @Override
    protected Money calculateCallFee(final Call call) {
        return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
    }
}

