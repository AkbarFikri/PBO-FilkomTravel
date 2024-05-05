package domain;

public interface Applicable {
    boolean isCustomerEligible(Object x);
    boolean isMinimumPriceEligible(Object x);
    boolean isShippingFeeEligible(Object x);
    double getTotalPrice();
    double getTotalCashback();
    double getTotalShippingFee();
}
