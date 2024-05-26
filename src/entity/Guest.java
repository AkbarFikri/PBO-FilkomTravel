package entity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
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

    @Override
    public void print() {
        System.out.println("Kode Pemesan: " + getId());
        System.out.println("Nama: " + getName());
        System.out.println("Nomor Pesanan: ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
        System.out.println("Tanggal Pesanan: " + sdf.format(getLastOrder().getOrderDate()));

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);

        System.out.printf("%3s | %-25s | %3s | %8s%n", "No", "Menu", "Dur.", "Subtotal");
        System.out.println("===============================================================");
        int i = 1;
        for (OrderItem orderitem : getLastOrder().getOrderItems()) {
            String menu = orderitem.getVehicle().getName().length() >= 20
                    ? orderitem.getVehicle().getName().substring(0, 20)
                    : orderitem.getVehicle().getName();
            String subtotal = formatter.format(orderitem.getVehicle().getPrice() * orderitem.getRentalTime());
            System.out.printf("%3d | %-25s | %3d | %8s%n", i, menu, orderitem.getRentalTime(), subtotal);
            System.out.printf("%5s%s%5s%n", "", orderitem.getDate(), " - ", orderitem.getEnd());
            i++;
        }
        System.out.println("===============================================================");
        String subtotal = formatter.format(getLastOrder().countSubTotal());
        String total = formatter.format(getLastOrder().countSubTotal());
        String balance = formatter.format(getBalance() - getLastOrder().countSubTotal());

        System.out.printf("%-32s: %14s%n", "Total", subtotal);
        if (getLastOrder().isCheckOut()) {
            // String discount = formatter.format(/*apply promo di sini */);
            System.out.printf("%-27s: %9s%n", "PROMO"/* , discount */);
        }

        System.out.printf("%-32s: %14s%n", "Total", total);
        System.out.printf("%-32s: %14s%n", "Sisa saldo", balance); // bukan Sisa saldo
        System.out.println("");
    }

    @Override
    public void printHistory() {
        System.out.println("Kode Pemesan: " + getId());
        System.out.println("Nama: " + getName());
        System.out.println("Saldo: " + getBalance());
        System.out.printf("%4s | %10s | %5s | %5s | %8s | %-8s%n", "No", "Nomor Pesanan", "Motor", "Mobil", "Subtotal",
                "PROMO");
        System.out.println("===============================================================");
        int i = 1;
        for (Order order : orders) {
            for (OrderItem orderitem : order.getOrderItems()) {
                System.out.printf("%4d %11d %5d | %5d | %8d | %-8s%n", i++/*
                                                                           * , nomorPesanan, jumlahMotor, jumlahMobil,
                                                                           * totalHarga, promoCode
                                                                           */);
            }
        }
        System.out.println("===============================================================");
    }

}
