package entity;

import java.time.LocalDate;

import domain.Promotion;
import utils.OrderStatus;

public class Order {
    // TODO Make a Order class based on requirement that provided in case study 2
    private LocalDate orderDate;
    private int orderNo, orderPrice, promoTotal, orderTotal;
    private Promotion promotion;
    private OrderStatus status;
    private boolean isCheckOut;

    public Order() {

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

    }

    public void printDetails() {
        if (isCheckOut) {
            System.out.println();
        }
    }

    public void applyPromo() {

    }

    public void pay() {

    }
}
