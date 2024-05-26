package entity;

import java.util.ArrayList;
import domain.Customer;

public class Guest extends Customer {
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<OrderItem> carts = new ArrayList<>();

    @Override
    public void addToCart() {

    }

    @Override
    public void makeOrder() {

    }

    public Guest(String id, int firstBalance) {
        super("Guest", id, firstBalance);
    }

//    public void printListOrder() {
//        System.out.println("|No.|Nomor Pesanan |Status      |Total Harga     |");
//        System.out.println("--------------------------------------------------");
//        for (int i = 0; i < orders.size(); i++) {
//            Order dummy = orders.get(i);
//            System.out.printf("|%-2d.|%-14d|%-12s| Rp.%-,12d|\n", i + 1, dummy.getOrderNo(), dummy.getStatus(),
//                    dummy.getTotalPrice());
//        }
//    }

}
