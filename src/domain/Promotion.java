package domain;
import java.util.Date;
import entity.*;
import java.util.Date;

public abstract class Promotion implements Applicable, Comparable<Promotion>{
    private String promoCode;
    private Date begin;
    private Date end;
    int totalDiscount;
    int discountPercent;
    int maxDiscount;
    int minimumPurchase;

    public Promotion (String promoCode, Date begin, Date end, int discountPercent, int maxDiscount, int minimumPurchase){
        this.promoCode = promoCode;
        this.begin = begin;
        this.end = end;
        this.discountPercent = discountPercent;
        this.maxDiscount = maxDiscount;
        this.minimumPurchase = minimumPurchase;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public int getMaxDiscount() {
        return maxDiscount;
    }

    public Date getBegin() {
        return begin;
    }

    public Date getEnd() {
        return end;
    }

    public int getMinimumPurchase() {
        return minimumPurchase;
    }

    public int getTotalDiscount() {return totalDiscount;}

    public void setTotalDiscount(int totalDiscount) {
        this.totalDiscount = totalDiscount;
    }
}
