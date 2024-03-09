import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Vehicle[] tumpukanKendaraan = new Vehicle[5];

        for (int i = 0; i < tumpukanKendaraan.length; i++) {
            Vehicle dump = new Vehicle();
            dump.id = "aosjdhoaisj";
            dump.isRent = false;
            dump.kapasitas = 5;
            dump.nama = "Mobil percobaan";
            tumpukanKendaraan[i] = dump;
        }

        System.out.println("Selamat datang di Filkom Travel");
        System.out.println("Silahkan login terlebih dahulu untuk menikmati layanan kami!");
        System.out.print("Username: ");
        String usernameIn = in.next();
        System.out.print("Password: ");
        String passwordIn = in.next();

        User user = new Member(usernameIn, passwordIn);
        int choose;
        do {
            System.out.println("menu: ");
            System.out.println("1. Pesan travel");
            System.out.println("2. batalkan travel");
            System.out.println("3. History pemesanan");
            System.out.println("0. Exit");
            System.out.print("Masukkan pilihan menu: ");
            choose = in.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("Pilih kendaraan yang anda inginkan");
                    int iterator = 1;
                    for (Vehicle dump:tumpukanKendaraan) {
                        System.out.printf("%d. %s\n", iterator, dump.nama);
                        iterator++;
                    }
                    iterator = 1;
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        } while(choose != 0);


    }
}