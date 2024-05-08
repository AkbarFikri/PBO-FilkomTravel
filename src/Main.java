import domain.Customer;
import entity.Guest;
import entity.Member;
import entity.Vehicle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    static Customer current;
    static ArrayList<Member> members = new ArrayList<Member>();
    static ArrayList<Vehicle> vehicleList = new ArrayList<>();

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
                    isFinish = true;
                    break;
                case 2:
                    MemberMenu();
                    isFinish = true;
                    break;
                default:
                    System.out.println("Pilihan yang anda masukkan salah!");
                    break;
            }
        } while (!isFinish);

        System.out.println("Terimakasih sudah menggunakan applikasi Filkom Travel");
    }

    public static void GuestMenu() {
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
            System.out.println("0. Exit");
            System.out.print("Masukkan pilihan menu anda : ");
            int pilihan = in.nextInt();
            switch (pilihan) {
                case 0:
                    isFinishGuestMenu = true;
                    break;
                case 1:
                    int iterator = 1;
                    System.out.printf("|No.|Nama kendaraan           |Kapasitas |Plat Kendaraan   |Jenis     |Harga       |\n");
                    System.out.println("------------------------------------------------------------------------------------");
                    for (int i = 0; i < vehicleList.size(); i++) {
                        Vehicle dummy = vehicleList.get(i);
                        if (dummy.getIsRent()){
                            continue;
                        }else{
                            System.out.printf("|%-2d.|%-25s|%-10s|%-17s|%-10s|Rp. %-,8d|\n", iterator, dummy.getNama(), dummy.getKapasitas(), dummy.getPlatKendaraan(), dummy.getJenis(), dummy.getHarga());
                            iterator++;
                        }
                    }
                    System.out.print("Pilih kendaraan yang ingin disewa: ");
                    int choose = in.nextInt();
                    System.out.print("Lama sewa (dalam hari): ");
                    int lama = in.nextInt();
                    current.makeOrder(vehicleList, lama, choose);
                    break;
                case 2:

                    break;
                case 3:

                    break;
                default:
                    System.out.println("Pilihan yang anda masukkan salah!");
                    break;
            }
        } while (!isFinishGuestMenu);
    }

    public static void MemberMenu() {

    }

    public static void InitData() {
        for (int i = 0; i < 5; i++) {
            Vehicle dump = new Vehicle();
            dump.setNama("Mobil "+ (i + 1));
            dump.setRent(false);
            int dummyKapasitas = (int) (3 + (Math.random() * 10));
            dump.setKapasitas(dummyKapasitas);
            String jenis = dummyKapasitas > 7 ? "Sedang" : "Kecil";
            dump.setJenis(jenis);
            dump.setPlatKendaraan("N " + String.valueOf((int) (Math.random() * 10000)) + " XS");
            dump.setHarga(jenis.equals("Sedang") ? 300_000 : 150_000);
            vehicleList.add(dump);
        }
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