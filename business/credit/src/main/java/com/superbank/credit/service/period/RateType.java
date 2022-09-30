package com.superbank.credit.service.period;

import com.superbank.rates.model.CreditRates;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public enum RateType {
    FIXED {
        @Override
        public List<Double> calculateAmount(final Long units, final Double sum, final CreditRates creditRates) {
            final Double finalSum = sum * (1 + creditRates.rateFixed);
            return LongStream.range(0, units)
                    .mapToDouble(month -> finalSum / units)
                    .boxed()
                    .collect(Collectors.toList());
        }
    },
    FLOATING {
        @Override
        @SuppressFBWarnings("SA_LOCAL_SELF_ASSIGNMENT")
        public List<Double> calculateAmount(final Long units, final Double sum, final CreditRates creditRates) {
            double floatingRate = creditRates.rateFloating;
            final double sumPerMonth = sum / units;
            final List<Double> amounts = new ArrayList<>();
            for (int i = 0; i < units; i++) {
                final Double floatingAmount = sumPerMonth * (1 + floatingRate);
                floatingRate = +floatingRate;
                amounts.add(floatingAmount);
            }
            return amounts;
        }
    };


    public abstract List<Double> calculateAmount(final Long units, final Double sum, final CreditRates creditRates);
}
