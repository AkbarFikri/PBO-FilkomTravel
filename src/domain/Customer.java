package domain;

import entity.Order;
//gatau kenapa gabisa import OrderItem
import entity.*;
import entity.promotion.CashbackPromo;
import utils.customException.InsufficientBalanceException;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public abstract class Customer {
    private String name;
    private String id;
    private int balance;

    public ArrayList<Order> orders = new ArrayList<>();

    protected Customer(String name, String id, int balance) {
        this.name = name;
        this.id = id;
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public abstract void makeOrder(Vehicle vehicle, int qty, int year, int month, int date);

    public abstract void addToCart(Vehicle vehicle, int qty, int year, int month, int date);

    public boolean checkout() {
        this.orders.get(orders.size() - 1).countSubTotal();
        return true;
    }

    public Order getLastOrder() {
        return orders.get(orders.size() - 1);
    }

    public boolean isOrderItemExistInLastOrder(String MenuId) {
        if (this.orders.isEmpty()) return false;
        if (this.getLastOrder().getOrderItems().isEmpty()) return false;
        for (int j = 0; j < this.getLastOrder().getOrderItems().size(); j++) {
            if (this.getLastOrder()
                    .getOrderItems()
                    .get(j)
                    .getVehicle().getId()
                    .equals(MenuId)) {
                return true;
            }
        }
        return false;
    }

    public OrderItem getOrderItemById(String MenuId) {
        for (int j = 0; j < this.getLastOrder().getOrderItems().size(); j++) {
            if (this.getLastOrder()
                    .getOrderItems()
                    .get(j)
                    .getVehicle().getId()
                    .equals(MenuId)) {
                return this.getLastOrder().getOrderItems().get(j);
            }
        }
        return null;
    }

    public void checkOut(int OrderNum) throws InsufficientBalanceException {
        Order dumpOrder = getLastOrder();
        dumpOrder.setOrderNum(OrderNum);
        Date now = Date.from(Instant.now());
        dumpOrder.setOrderDate(now);
        dumpOrder.countTotal();
        if (dumpOrder.getTotalPrice() < balance){
            if (dumpOrder.getPromotion() instanceof CashbackPromo){
                int cashback = dumpOrder.getPromotion().getDiscountPercent() * dumpOrder.getSubTotalPrice()/100;
                if (cashback > dumpOrder.getPromotion().maxDiscount) cashback = dumpOrder.getPromotion().maxDiscount;
                balance = balance - dumpOrder.getSubTotalPrice() + cashback;
                dumpOrder.getPromotion().setTotalDiscount(cashback);
                dumpOrder.setCheckOut(true);
            }else {
                balance = balance - dumpOrder.getTotalPrice();
                dumpOrder.setCheckOut(true);
            }
        }else {
            throw new InsufficientBalanceException(String.valueOf(dumpOrder.getOrderNum() + " " + this.name + " INSUFFICIENT BALANCE"));
        }
    }

    public abstract void printOrder(Order order);

    public abstract void printOrder();

}
