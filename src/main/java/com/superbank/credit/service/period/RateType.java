package com.superbank.credit.service.period;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public enum RateType {
    FIXED{
        private final double fixedRate = 0.05;
        @Override
        public List<Double> calculateAmount(Long units, Double sum) {
            final Double finalSum = sum * (1 + fixedRate);
            return LongStream.range(0, units)
                    .mapToDouble(month -> finalSum / units)
                    .boxed()
                    .collect(Collectors.toList());
        }
    },
    FLOATING{
        private double floatingRate = 0.02;
        @Override
        public List<Double> calculateAmount(Long units, Double sum) {
            final double sumPerMonth = sum / units;
            final List<Double> amounts = new ArrayList<>();
            for (int i = 0; i < units; i++) {
                final Double floatingAmount = sumPerMonth * (1 + floatingRate);
                floatingRate =+ floatingRate;
                amounts.add(floatingAmount);
            }
            return amounts;
        }
    };



    public abstract List<Double> calculateAmount(final Long units, final Double sum);
}
