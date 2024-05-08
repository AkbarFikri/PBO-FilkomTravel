package domain;

public interface Applicable {
    boolean isCustomerEligible(Object x);

    boolean isMinimumPriceEligible(Object x);

    boolean isShippingFeeEligible(Object x);

    double getTotalPrice(Object x);

    double getTotalCashback();

    double getTotalShippingFee();
}
