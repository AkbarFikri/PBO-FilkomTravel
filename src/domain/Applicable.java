package domain;

import entity.Order;

public interface Applicable {
    boolean isCustomerEligible(Object x);

    boolean isMinimumPriceEligible(Object x);

    boolean isShippingFeeEligible(Object x);

    double getTotalPrice(Object x);

    double getTotalCashback(Order order);

    double getTotalShippingFee();
}
