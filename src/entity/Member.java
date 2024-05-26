package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import domain.Customer;
import domain.Vehicle;

public class Member extends Customer {
    Date registrationDate;

    public Member(String id, String name, String registrationDate, int firstBalance) {
        super(name, id, firstBalance);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.registrationDate = formatter.parse(registrationDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void makeOrder(Vehicle vehicle, int qty, int year, int month, int date) {
        Order order = new Order();
        order.addItems(vehicle, qty, year, month, date);

        if (qty == 1) {
            System.out.println("ADD_TO_CART SUCCESS: " + qty + " day " + vehicle.getName() + " "
                    + vehicle.getPlatNumber() + " (NEW)");
        } else {
            System.out.println("ADD_TO_CART SUCCESS: " + qty + " days " + vehicle.getName() + " "
                    + vehicle.getPlatNumber() + " (NEW)");
        }
    }

    @Override
    public void addToCart(Vehicle vehicle, int qty, int year, int month, int date) {
        orders.get(orders.size() - 1).addItems(vehicle, qty, year, month, date);
    }

    @Override
    public void print() {}

    public void printHistory() {}

}
