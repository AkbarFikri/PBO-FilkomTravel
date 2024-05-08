package entity.promotion;

import domain.Promotion;
import entity.Guest;
import entity.Order;

public class CashbackPromo extends Promotion {

    int cashback;

    public CashbackPromo(String name, String syarat, int cashback) {
        super(name, syarat);
        this.cashback = cashback;
    }

    @Override
    public boolean isCustomerEligible(Object x) {
        if (x instanceof Guest) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isMinimumPriceEligible(Object x) {
        Order orderX = (Order) x;
        if (orderX.getTotalPrice() > 150000) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isShippingFeeEligible(Object x) {
        return false;
    }

    @Override
    public double getTotalPrice(Object x) {
        Order orderX = (Order) x;
        return orderX.getTotalPrice() - this.cashback;
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
