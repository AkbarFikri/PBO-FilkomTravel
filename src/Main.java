import domain.Customer;
import domain.Promotion;
import entity.Guest;
import entity.Member;
import entity.Order;
import entity.Vehicle;
import entity.promotion.CashbackPromo;
import entity.promotion.DeliveryFeePromo;
import entity.promotion.PercentOffPromo;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    static Customer current;
    static ArrayList<Member> members = new ArrayList<Member>();
    static ArrayList<Vehicle> vehicleList = new ArrayList<>();
    static ArrayList<Promotion> promoList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Selamat datang di Filkom Travel!");
        boolean isFinish = false;
        InitData();

        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Masuk sebagai : ");
            System.out.println("1. Guest");
            System.out.println("2. Member");
            System.out.println("0. Exit");
            System.out.print("Masukkan pilihan menu anda : ");
            int pilihan = in.nextInt();
            in.nextLine();
            switch (pilihan) {
                case 0:
                    isFinish = true;
                    break;
                case 1:
                    GuestMenu();
                    break;
                case 2:
                    MemberMenu();
                    break;
                default:
                    System.out.println("Pilihan yang anda masukkan salah!");
                    break;
            }
        } while (!isFinish);

        System.out.println("Terimakasih sudah menggunakan applikasi Filkom Travel");
    }

    public static void GuestMenu() {
        clearScreen();
        System.out.println("== Masukkan data diri anda ==");
        System.out.print("Nama depan : ");
        String namaDepan = in.nextLine();
        System.out.print("Nama belakang : ");
        String namaBelakang = in.nextLine();
        Guest newGuest = new Guest(namaDepan, namaBelakang);
        current = newGuest;

        boolean isFinishGuestMenu = false;

        do {
            clearScreen();
            System.out.println("Pilihan Menu : ");
            System.out.println("1. Pesan Travel");
            System.out.println("2. Lihat Promo");
            System.out.println("3. Lihat Mobil");
            System.out.println("4. Lihat Order");
            System.out.println("0. Exit");
            System.out.print("Masukkan pilihan menu anda : ");
            int pilihan = in.nextInt();
            in.nextLine();
            switch (pilihan) {
                case 0:
                    isFinishGuestMenu = true;
                    break;
                case 1:
                    int iterator = 1;
                    System.out.printf(
                            "|No.|Nama kendaraan           |Kapasitas |Plat Kendaraan   |Jenis     |Harga       |\n");
                    System.out.println(
                            "------------------------------------------------------------------------------------");
                    for (int i = 0; i < vehicleList.size(); i++) {
                        Vehicle dummy = vehicleList.get(i);
                        if (dummy.getIsRent()) {
                            continue;
                        } else {
                            System.out.printf("|%-2d.|%-25s|%-10s|%-17s|%-10s|Rp. %-,8d|\n", iterator, dummy.getNama(),
                                    dummy.getKapasitas(), dummy.getPlatKendaraan(), dummy.getJenis(), dummy.getHarga());
                            iterator++;
                        }
                    }
                    System.out.print("Pilih kendaraan yang ingin disewa: ");
                    int choose = in.nextInt();
                    System.out.print("Lama sewa (dalam hari): ");
                    int lama = in.nextInt();
                    Guest dummy2 = (Guest) current;
                    current.makeOrder(vehicleList, lama, choose);
                    OrderMenu(dummy2.getLastOrder());
                    break;
                case 2:
                    System.out.printf(
                            "|No.|Nama Promo                    |Syarat Promo                                      |\n");
                    System.out.println(
                            "---------------------------------------------------------------------------------------");
                    for (int i = 0; i < promoList.size(); i++) {
                        Promotion dummy = promoList.get(i);
                        System.out.printf("|%-2d.|%-30s|%-50s|\n", i + 1, dummy.getName(), dummy.getSyarat());
                    }
                    System.out.print("Apakah ingin melanjutkan menu ? (y/n) ");
                    String check = in.nextLine();
                    if (check.equals("y")) {
                        break;
                    } else {
                        isFinishGuestMenu = true;
                    }
                    break;
                case 3:
                    int iterator3 = 1;
                    System.out.printf(
                            "|No.|Nama kendaraan           |Kapasitas |Plat Kendaraan   |Jenis     |Harga       |\n");
                    System.out.println(
                            "------------------------------------------------------------------------------------");
                    for (int i = 0; i < vehicleList.size(); i++) {
                        Vehicle dummy = vehicleList.get(i);
                        if (dummy.getIsRent()) {
                            continue;
                        } else {
                            System.out.printf("|%-2d.|%-25s|%-10s|%-17s|%-10s|Rp. %-,8d|\n", iterator3, dummy.getNama(),
                                    dummy.getKapasitas(), dummy.getPlatKendaraan(), dummy.getJenis(), dummy.getHarga());
                            iterator3++;
                        }
                    }
                    System.out.print("Apakah ingin melanjutkan menu ? (y/n) ");
                    String check2 = in.nextLine();
                    if (check2.equals("y")) {
                        break;
                    } else {
                        isFinishGuestMenu = true;
                    }
                    break;
                case 4:
                    Guest dummy = (Guest) current;
                    dummy.printListOrder();
                    System.out.print("Apakah ingin melanjutkan menu ? (y/n) ");
                    String check3 = in.nextLine();
                    if (check3.equals("y")) {
                        break;
                    } else {
                        isFinishGuestMenu = true;
                    }
                    break;
                default:
                    System.out.println("Pilihan yang anda masukkan salah!");
                    break;
            }
        } while (!isFinishGuestMenu);
    }

    public static void MemberMenu() {

    }

    public static void OrderMenu(Order order) {
        boolean isFinishOrderMenu = false;
        do {
            clearScreen();
            System.out.println("Pilihan Menu Order: ");
            System.out.println("1. Checkout");
            System.out.println("2. Gunakan Promo");
            System.out.println("3. Bayar");
            System.out.println("0. Exit");
            System.out.print("Masukkan pilihan menu anda : ");
            int pilihan = in.nextInt();
            in.nextLine();
            switch (pilihan) {
                case 0:
                    isFinishOrderMenu = true;
                    break;
                case 1:
                    order.checkOut();
                    isFinishOrderMenu = true;
                    break;
                case 2:
                    System.out.printf(
                            "|No.|Nama Promo                    |Syarat Promo                                      |\n");
                    System.out.println(
                            "---------------------------------------------------------------------------------------");
                    for (int i = 0; i < 1; i++) {
                        Promotion dummy = promoList.get(i);
                        System.out.printf("|%-2d.|%-30s|%-50s|\n", i + 1, dummy.getName(), dummy.getSyarat());
                    }
                    System.out.print("Pilih promo yang ingin digunakan: ");
                    int promo = in.nextInt();
                    in.nextLine();
                    order.applyPromo(promoList.get(promo - 1), current);
                    System.out.println("Promo berhasil digunakan.");
                    System.out.print("Apakah ingin melanjutkan menu ? (y/n) ");
                    String check = in.nextLine();
                    if (check.equals("y")) {
                        break;
                    } else {
                        isFinishOrderMenu = true;
                    }
                    break;
                case 3:
                    int iterator3 = 1;
                    System.out.printf(
                            "|No.|Nama kendaraan           |Kapasitas |Plat Kendaraan   |Jenis     |Harga       |\n");
                    System.out.println(
                            "------------------------------------------------------------------------------------");
                    for (int i = 0; i < vehicleList.size(); i++) {
                        Vehicle dummy = vehicleList.get(i);
                        if (dummy.getIsRent()) {
                            continue;
                        } else {
                            System.out.printf("|%-2d.|%-25s|%-10s|%-17s|%-10s|Rp. %-,8d|\n", iterator3, dummy.getNama(),
                                    dummy.getKapasitas(), dummy.getPlatKendaraan(), dummy.getJenis(), dummy.getHarga());
                            iterator3++;
                        }
                    }
                    System.out.print("Apakah ingin melanjutkan menu ? (y/n) ");
                    String check2 = in.nextLine();
                    if (check2.equals("y")) {
                        break;
                    } else {
                        isFinishOrderMenu = true;
                    }
                    break;
                case 4:
                    Guest dummy = (Guest) current;
                    dummy.printListOrder();
                    System.out.print("Apakah ingin melanjutkan menu ? (y/n) ");
                    String check3 = in.nextLine();
                    if (check3.equals("y")) {
                        break;
                    } else {
                        isFinishOrderMenu = true;
                    }
                    break;
                default:
                    System.out.println("Pilihan yang anda masukkan salah!");
                    break;
            }
        } while (!isFinishOrderMenu);
    }

    public static void InitData() {
        for (int i = 0; i < 5; i++) {
            Vehicle dump = new Vehicle();
            dump.setNama("Mobil " + (i + 1));
            dump.setRent(false);
            int dummyKapasitas = (int) (3 + (Math.random() * 10));
            dump.setKapasitas(dummyKapasitas);
            String jenis = dummyKapasitas > 7 ? "Sedang" : "Kecil";
            dump.setJenis(jenis);
            dump.setPlatKendaraan("N " + String.valueOf((int) (Math.random() * 10000)) + " XS");
            dump.setHarga(jenis.equals("Sedang") ? 300_000 : 150_000);
            vehicleList.add(dump);
        }

        promoList.add(new CashbackPromo("Cashback sebesar 10.000", "Total harga pemesanan lebih dari 150.000", 10000));
        promoList.add(new PercentOffPromo("Diskon pesanan sebesar 10%", "Total pesanan minimal 300.000"));
        promoList.add(new DeliveryFeePromo("Diskon ongkir sebesar 20.000", "Entah ini apaan"));
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception e) {
            System.err.println("Gagal clear screen.");
        }
    }
}