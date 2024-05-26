package entity;

import java.util.ArrayList;
import domain.Customer;
import domain.Vehicle;

public class Guest extends Customer {

    public Guest(String id, int firstBalance) {
        super("Guest", id, firstBalance);
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
        getLastOrder().addItems(vehicle, qty, year, month, date);

        if (qty == 1) {
            System.out.println("ADD_TO_CART SUCCESS: " + qty + " day " + vehicle.getName() + " "
                    + vehicle.getPlatNumber() + " (NEW)");
        } else {
            System.out.println("ADD_TO_CART SUCCESS: " + qty + " days " + vehicle.getName() + " "
                    + vehicle.getPlatNumber() + " (NEW)");
        }
    }

    // public void printListOrder() {
    // System.out.println("|No.|Nomor Pesanan |Status |Total Harga |");
    // System.out.println("--------------------------------------------------");
    // for (int i = 0; i < orders.size(); i++) {
    // Order dummy = orders.get(i);
    // System.out.printf("|%-2d.|%-14d|%-12s| Rp.%-,12d|\n", i + 1,
    // dummy.getOrderNo(), dummy.getStatus(),
    // dummy.getTotalPrice());
    // }
    // }

}
