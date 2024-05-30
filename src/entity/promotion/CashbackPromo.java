package entity.promotion;

import domain.Customer;
import domain.Promotion;
import entity.Guest;
import entity.Member;
import entity.Order;
import java.util.Date;
import java.util.Calendar;

import java.util.Calendar;
import java.util.Date;

public class CashbackPromo extends Promotion {
    int cashback;

    public CashbackPromo(String promoCode, Date begin, Date end, int discountPercent, int maxDiscount, int minimumPurchase) {
        super(promoCode, begin, end, discountPercent, maxDiscount, minimumPurchase);
    }

    @Override
    public boolean isCustomerEligible(Object x) {
        if (x instanceof Guest) {
            return false;
        } else if (x instanceof Member member) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(member.getRegistrationDate());

            calendar.add(Calendar.DAY_OF_YEAR, 30);

            Date currentDate = new Date();

            if (!currentDate.after(calendar.getTime())) {
                return false;
            }

            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean isMinimumPriceEligible(Object x) {
        if (x instanceof Order order && order.getSubTotalPrice() > super.getMinimumPurchase()){
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
    public double getTotalCashback(Order order) {
        Order dumpOrder = order;
        int cashback = super.getDiscountPercent() * dumpOrder.getSubTotalPrice()/100;
        if (cashback > super.getMaxDiscount()) cashback = super.getMaxDiscount();
        dumpOrder.getPromotion().setTotalDiscount(cashback);
        return cashback;
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
