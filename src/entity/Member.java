package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import domain.Customer;

public class Member extends Customer {
    public ArrayList<Order> orders = new ArrayList<>();
    public ArrayList<OrderItem> carts = new ArrayList<>();
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

    public int getBalance() {
        return balance;
    }

    @Override
    public void addToCart() {

    }

    @Override
    public void makeOrder() {

    }
}
