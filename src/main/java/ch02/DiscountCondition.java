package ch02;

import java.time.DayOfWeek;
import java.time.LocalTime;

interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);

    class SequenceCondition implements DiscountCondition {
        private int sequence;

        public SequenceCondition(int sequence) {
            this.sequence = sequence;
        }

        @Override
        public boolean isSatisfiedBy(Screening screening) {
            return screening.isSequence(sequence);
        }
    }

    class PeriodCondition implements DiscountCondition {
        private DayOfWeek dayOfWeek;
        private LocalTime startTime;
        private LocalTime endTime;

        public PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
            this.dayOfWeek = dayOfWeek;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public boolean isSatisfiedBy(Screening screening) {
            return screening.getStartTime().getDayOfWeek().equals(dayOfWeek)
                    && startTime.compareTo(screening.getStartTime().toLocalTime()) <= 0
                    && endTime.compareTo(screening.getStartTime().toLocalTime()) >= 0;
        }
    }

}
