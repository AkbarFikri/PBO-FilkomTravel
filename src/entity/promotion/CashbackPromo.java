package entity.promotion;

import domain.Customer;
import domain.Promotion;
import entity.Guest;
import entity.Order;
import java.util.Date;
import java.util.Calendar;

public class CashbackPromo extends Promotion {

    int cashback;

    public CashbackPromo(String promoCode, Date begin, Date end, int discountPercent, int maxDiscount, int minimumPurchase) {
        super(promoCode, begin, end, discountPercent, maxDiscount, minimumPurchase);
    }

    @Override
    public boolean isCustomerEligible(Customer x) {
        if (x instanceof Guest) {
            return false;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(x.getDate);

            calendar.add(Calendar.DAY_OF_YEAR, 30);

            Date currentDate = new Date();

            if (!currentDate.after(calendar.getTime()){
                return false;
            }

            return isMinimumPriceEligible(x);
        }

    }

    @Override
    public boolean isMinimumPriceEligible(Customer x) {
        if (x.getLastOrder().getSubTotalPrice()>getMinimumPurchase()){
            return true;
        }else{
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
        return 0.0;
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
