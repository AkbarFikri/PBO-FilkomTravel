package entity;

//import domain.Customer;
//import domain.Promotion;

import domain.*;
import entity.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    private LocalDate orderDate;
    private ArrayList<OrderItem> orderItems = new ArrayList<>();
    private int totalPrice, subTotalPrice;
    private Promotion promotion;
    private boolean isCheckOut;

    public Order() {
        this.orderDate = LocalDate.now();
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

    // CO blm jadi gatau knp error
    public void countSubTotal() {
        for (int i = 0; i < orderItems.size(); i++) {
            subTotalPrice += orderItems.get(i).getVehicle().getPrice() * orderItems.get(i).getRentalTime();
        }
    }

    public void checkOut() {
        this.isCheckOut = true;
        System.out.println("Checkout berhasil!");
        printDetails();
    }

    public void printDetails() {
        // if (isCheckOut) {
        // System.out.println("\n|-------------------------------------------|");
        // System.out.println("|\t\t\t Order Details \t\t\t|");
        // System.out.println("|-------------------------------------------|");
        // System.out.printf("|%-20s : %-17s\t|\n", "Tanggal", this.orderDate);
        // System.out.printf("|%-20s : %-17s\t|\n", "Lama Sewa", this.rentalTime + "
        // hari");
        // System.out.printf("|%-20s : %-17s\t|\n", "Jenis Mobil",
        // this.vehicle.getJenis());
        // System.out.printf("|%-20s : %-17s\t|\n", "Kapasitas",
        // this.vehicle.getKapasitas());
        // System.out.printf("|%-20s : %-17s\t|\n", "Status", this.getStatus());
        // System.out.printf("|%-20s : Rp. %-,13d\t|\n", "Harga Awal",
        // this.originalPrice);
        // System.out.printf("|%-20s : Rp. %-,13d\t|\n", "Total Promo",
        // this.promoPrice);
        // System.out.printf("|%-20s : Rp. %-,13d\t|\n", "Total Harga",
        // this.totalPrice);
        // System.out.printf("|%-20s : %-17s\t|\n", "Plat",
        // this.vehicle.getPlatKendaraan());
        // System.out.println("|___________________________________________|\n");
        // } else {
        // System.out.println("Anda perlu checkout terlebih dahulu untuk melihat details
        // dari pesanan anda!");
        // }
    }

    public void applyPromo(Promotion promo, Customer customer) {
        // if (!promo.isCustomerEligible(customer)) {
        // System.out.println("Mohon maaf, promo hanya dapat digunakan oleh Member.");
        // return;
        // }

        if (!promo.isMinimumPriceEligible(this)) {
            System.out.println("Mohon maaf, harga total belum cukup untuk promo.");
            return;
        }

    }
}
