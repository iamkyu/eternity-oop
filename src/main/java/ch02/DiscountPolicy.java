package ch02;

import java.util.Arrays;
import java.util.List;

abstract class DiscountPolicy {
    private List<DiscountCondition> discountConditions;

    public DiscountPolicy(DiscountCondition... discountConditions) {
        this.discountConditions = Arrays.asList(discountConditions);
    }

    public Money calculateDiscountAmount(Screening screening) {
        for (DiscountCondition each : discountConditions) {
            if (each.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening);
            }
        }
        return Money.ZERO;
    }

    abstract protected Money getDiscountAmount(Screening screening);


    class AmountDiscountPolicy extends DiscountPolicy {
        private Money discountAmount;

        public AmountDiscountPolicy(Money discountAmount, DiscountCondition... discountConditions) {
            super(discountConditions);
            this.discountAmount = discountAmount;
        }

        @Override
        protected Money getDiscountAmount(Screening screening) {
            return discountAmount;
        }
    }

    class PercentDiscountPolicy extends DiscountPolicy {
        private double percent;

        public PercentDiscountPolicy(double percent, DiscountCondition... discountConditions) {
            super(discountConditions);
            this.percent = percent;
        }

        @Override
        protected Money getDiscountAmount(Screening screening) {
            return screening.getMovieFee().times(percent);
        }
    }

    class NoneDiscountPolicy extends DiscountPolicy {

        @Override
        protected Money getDiscountAmount(Screening screening) {
            return Money.ZERO;
        }
    }
}

