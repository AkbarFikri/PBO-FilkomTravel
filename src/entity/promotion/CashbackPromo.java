package entity.promotion;

import domain.Promotion;

public class CashbackPromo extends Promotion {
    @Override
    public boolean isCustomerEligible(Object x) {
        return false;
    }

    @Override
    public boolean isMinimumPriceEligible(Object x) {
        return false;
    }

    @Override
    public boolean isShippingFeeEligible(Object x) {
        return false;
    }

    @Override
    public double getTotalPrice() {
        return 0;
    }

    @Override
    public double getTotalCashback() {
        return 0;
    }

    @Override
    public double getTotalShippingFee() {
        return 0;
    }

    @Override
    public int compareTo(Promotion o) {
        return 0;
    }
}
