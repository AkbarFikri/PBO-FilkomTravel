package entity;

import domain.Customer;
import domain.Promotion;
import utils.OrderStatus;

import java.time.LocalDate;

public class Order {
    private LocalDate orderDate;
    private int rentalTime, orderNo, originalPrice, promoPrice, totalPrice;
    private Promotion promotion;
    private OrderStatus status;
    private boolean isCheckOut;
    private Vehicle vehicle;

    public Order(Vehicle vehicle, int rentalTime, int lastOrderNo) {
        this.vehicle = vehicle;
        this.orderDate = LocalDate.now();
        this.rentalTime = rentalTime;
        this.orderNo = lastOrderNo + 1;
        this.originalPrice = vehicle.getHarga();
        this.totalPrice = vehicle.getHarga();
        this.status = OrderStatus.UNPAID;
    }

    public String getStatus() {
        switch (status) {
            case UNPAID:
                return "UNPAID";
            case SUCCESSFUL:
                return "SUCCESSFUL";
            case CANCELLED:
                return "CANCELLED";
        }
        return "";
    }

    public void checkOut() {
        this.isCheckOut = true;
        this.status = OrderStatus.SUCCESSFUL;
        System.out.println("Checkout berhasil!");
        printDetails();

    }

    public void printDetails() {
        if (isCheckOut) {
            System.out.println();
        } else {
            System.out.println("Anda perlu checkout terlebih dahulu untuk melihat details dari pesanan anda!");
        }
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

        this.totalPrice = (int) promo.getTotalPrice(this);
    }

    public void pay() {
        this.status = OrderStatus.SUCCESSFUL;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
