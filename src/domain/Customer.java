package domain;

import entity.Order;
import domain.*;
//gatau kenapa gabisa import OrderItem
import entity.*;
import entity.promotion.CashbackPromo;

import java.util.ArrayList;

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
        orders.get(orders.size() - 1).countSubTotal();
        return true;
    }

    public Order getLastOrder() {
        return orders.get(orders.size() - 1);
    }

    public boolean isOrderItemExistInLastOrder(String MenuId) {
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
    public boolean checkOut(){
        Order dumpOrder = getLastOrder();
        dumpOrder.countTotal();
        if (dumpOrder.getTotalPrice()<balance){
            if (dumpOrder.getPromotion() instanceof CashbackPromo){
                int cashback = dumpOrder.getPromotion().getDiscountPercent()*dumpOrder.getTotalPrice()/100;
                balance = balance - dumpOrder.getTotalPrice() + cashback;
                dumpOrder.setCheckOut(true);
                return true;
            }else {
                balance = balance - dumpOrder.getTotalPrice();
                dumpOrder.setCheckOut(true);
                return true;
            }
        }else {
            return false;
        }
    }
  
    public abstract void print();

    public abstract void printHistory();

}
