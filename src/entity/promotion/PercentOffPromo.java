package entity.promotion;

import domain.Customer;
import domain.Promotion;
import entity.Guest;

import java.util.Calendar;
import java.util.Date;

public class PercentOffPromo extends Promotion {
    public PercentOffPromo(String promoCode, Date begin, Date end, int discountPercent, int maxDiscount, int minimumPurchase) {
        super(promoCode, begin, end, discountPercent, maxDiscount, minimumPurchase);
    }

    @Override
    public boolean isCustomerEligible(Customer x) {
        if (x instanceof Guest) {
            return false;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(x.getDate);
            //kayaknya harus dikasih date di customer
            calendar.add(Calendar.DAY_OF_YEAR, 30);

            Date currentDate = new Date();

            if (!currentDate.after(calendar.getTime()) || currentDate.after(getEnd()) || currentDate.before(getBegin())){
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
