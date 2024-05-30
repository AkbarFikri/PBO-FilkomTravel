package entity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import domain.Customer;
import domain.Vehicle;
import entity.promotion.CashbackPromo;
import entity.promotion.PercentOffPromo;

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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public void makeOrder(Vehicle vehicle, int qty, int year, int month, int date) {
        Order order = new Order();
        order.addItems(vehicle, qty, year, month, date);

        super.orders.add(order);

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
            System.out.println("ADD_TO_CART SUCCESS: " + qty+ " day " + vehicle.getName() + " "
                    + vehicle.getPlatNumber() + " (NEW)");
        } else {
            System.out.println("ADD_TO_CART SUCCESS: " + qty + " days " + vehicle.getName() + " "
                    + vehicle.getPlatNumber() + " (NEW)");
        }
    }

    @Override
    public void printOrder(Order order) {
        System.out.println("Kode Pemesan: " + getId());
        System.out.println("Nama: " + getName());
        if (getLastOrder().isCheckOut()) {
            System.out.println("Nomor Pesanan: " + order.getOrderNum());
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
            System.out.println("Tanggal Pesanan: " + sdf.format(order.getOrderDate()));
        }
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
        System.out.printf("%3s | %-25s | %3s | %8s \n", "No", "Menu", "Dur.", "Subtotal");
        System.out.println("==================================================");
        int i = 1;
        for (OrderItem orderitem : order.getOrderItems()) {
            String menu = orderitem.getVehicle().getName().length() >= 20 ? orderitem.getVehicle().getName().substring(0, 20) : orderitem.getVehicle().getName();
            String subtotal = formatter.format(orderitem.getVehicle().getPrice() * orderitem.getRentalTime());
            System.out.printf("%3d | %-25s | %4d | %8s \n", i, menu, orderitem.getRentalTime(), subtotal);
            System.out.printf("%5s %5s\n", " ", sdf2.format(orderitem.getDate()) + " - " + sdf2.format(orderitem.getEnd()));
            i++;
        }
        System.out.println("==================================================");
        String subtotal = formatter.format(order.countSubTotal());
        order.countTotal();
        String total = formatter.format(order.getTotalPrice());
        String balance = formatter.format(this.getBalance());

        System.out.printf("%-32s: %15s\n", "Sub Total", subtotal);
        if (order.getPromotion() != null) {
            if (this.getLastOrder().getPromotion() instanceof CashbackPromo) {
                String discount = formatter.format(order.getPromotion().getTotalCashback(order));
                System.out.printf("%-32s: %15s\n", "PROMO: " + order.getPromotion().getPromoCode(), discount);
            } else if (this.getLastOrder().getPromotion() instanceof PercentOffPromo) {
                String discount = formatter.format(order.getPromotion().getTotalDiscount());
                System.out.printf("%-32s: %15s\n", "PROMO: " + order.getPromotion().getPromoCode(), "-" + discount);
            }

        }
        System.out.println("==================================================");

        System.out.printf("%-32s: %15s%n", "Total", total);
        System.out.printf("%-32s: %15s%n", "Saldo", balance);

        System.out.println();
    }

    @Override
    public void printOrder() {
        System.out.println("Kode Pemesan: " + this.getId());
        System.out.println("Nama: " + this.getName());
        System.out.println("Saldo: " + getBalance());
        System.out.printf("%4s| %10s | %5s | %5s | %8s | %-8s%n", "No", "Nomor Pesanan", "Motor", "Mobil", "Subtotal", "PROMO");
        System.out.println("==========================================================");
        int i = 1;
        for (Order order : orders) {
            if (order.isCheckOut()) {
                int mobil = 0;
                int motor = 0;
                for (OrderItem orderitem : order.getOrderItems()) {
                    if (orderitem.getVehicle() instanceof Motorcycle) {
                        motor++;
                    } else {
                        mobil++;
                    }
                }
                System.out.printf("%4d| %13d | %5d | %5d | %8d | %-8s\n", i, order.getOrderNum(), motor, mobil, order.getSubTotalPrice(), order.getPromotion() != null ? order.getPromotion().getPromoCode() : "-");
            }
        }
        System.out.println("==========================================================");
    }
}
