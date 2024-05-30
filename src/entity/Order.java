package entity;

import domain.*;
import entity.promotion.PercentOffPromo;
import utils.customException.InvalidApplyPromoException;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private Date orderDate;
    private ArrayList<OrderItem> orderItems = new ArrayList<>();
    private int OrderNum, totalPrice, subTotalPrice;
    private Promotion promotion;
    private boolean isCheckOut;

    public Order() {
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addItems(Vehicle vehicle, int qty, int year, int month, int date) {
        orderItems.add(new OrderItem(vehicle, qty, year, month, date));
    }

    public boolean isCheckOut() {
        return isCheckOut;
    }

    public int countSubTotal() {
        int dumpTotal = 0;
        for (int i = 0; i < orderItems.size(); i++) {
            dumpTotal += orderItems.get(i).getVehicle().getPrice() * orderItems.get(i).getRentalTime();
        }
        subTotalPrice = dumpTotal;
        return subTotalPrice;
    }

    public void countTotal() {
        countSubTotal();
        if (promotion instanceof PercentOffPromo){
            totalPrice = subTotalPrice - (subTotalPrice*promotion.getDiscountPercent()/100);
            this.promotion.setTotalDiscount(subTotalPrice*promotion.getDiscountPercent()/100);
        }else {
            totalPrice = subTotalPrice;
        }
    }

    public OrderItem getOrderItemById(String MenuId) {
        for (int i = 0; i < orderItems.size(); i++) {
            if (orderItems.get(i).getVehicle().getId().equals(MenuId)) {
                return orderItems.get(i);
            }
        }
        throw new NullPointerException();
    }

    public void deleteItemById(String MenuId) {
        for (int i = 0; i < orderItems.size(); i++) {
            if (orderItems.get(i).getVehicle().getId().equals(MenuId)) {
                orderItems.remove(i);
            }
        }
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getSubTotalPrice() {
        return countSubTotal();
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public void setCheckOut(boolean checkOut) {
        isCheckOut = checkOut;
    }

    public int getOrderNum() {
        return OrderNum;
    }

    public void setOrderNum(int orderNum) {
        OrderNum = orderNum;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void applyPromo(Promotion promo, Customer customer) throws InvalidApplyPromoException {
        if (!promo.isMinimumPriceEligible(this)) throw new InvalidApplyPromoException(promo.getPromoCode());
        if (!promo.isCustomerEligible(customer)) throw new InvalidApplyPromoException(promo.getPromoCode());
        Date currentDate = new Date();
        if (!currentDate.after(promo.getEnd()) || !currentDate.before(promo.getBegin())){
            this.setPromotion(promo);
            this.getPromotion().setTotalDiscount(this.getPromotion().getDiscountPercent() * this.getTotalPrice()/100);
            System.out.println("APPLY_PROMO SUCCESS: " + promo.getPromoCode());
        }else{
            throw new InvalidApplyPromoException(promo.getPromoCode() + " is EXPIRED");
        }
    }

}
