import javax.imageio.metadata.IIOMetadataFormatImpl;
import java.util.*;


public class Main {
    private static Scanner in = new Scanner(System.in);
    private static char dec='y';
    private static int choose = 0;
    private static ArrayList<Vehicle> tumpukanKendaraan = new ArrayList<>();

    private static void pesanTravel(Member user) {
        int iterator = 1;
        System.out.printf("|No.|Nama kendaraan           |Kapasitas |Plat Kendaraan   |Jenis     |Harga   |\n");
        System.out.println("--------------------------------------------------------------------------------");
        for (int i = 0; i < tumpukanKendaraan.size(); i++) {
            Vehicle dummy = tumpukanKendaraan.get(i);
            if (dummy.getIsRent()){
                continue;
            }else{
                System.out.printf("|%-2d.|%-25s|%-10s|%-17s|%-10s|%-,8d|\n", iterator, dummy.getNama(), dummy.getKapasitas(), dummy.getPlatKendaraan(), dummy.getJenis(), dummy.getHarga());
                iterator++;
            }
        }
        iterator = 1;
        System.out.print("kendaraan yang disewa: ");
        int choose = in.nextInt();
        System.out.print("Lama sewa (dalam hari): ");
        int lama = in.nextInt();
        for (int i = 0; i < tumpukanKendaraan.size(); i++) {
            if (tumpukanKendaraan.get(i).getIsRent()){
                iterator++;
                continue;
            }else{
                if (iterator >= choose){
                    user.pesanTravel(tumpukanKendaraan.get(iterator - 1), lama, user);
                    tumpukanKendaraan.get(iterator - 1).setRent(true);
                    break;
                }
                iterator++;
            }

        }
        System.out.print("apakah ingin lanjut? (y/n): ");
        dec = in.next().charAt(0);
        iterator = 1;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            Vehicle dump = new Vehicle();
            dump.setNama("dummy "+ String.valueOf(i));
            dump.setRent(false);
            int dummyKapasitas = (int) (3 + (Math.random() * 10));
            dump.setKapasitas(dummyKapasitas);
            dump.setNama("Mobil percobaan");
            String jenis = dummyKapasitas > 7 ? "Sedang" : "Kecil";
            dump.setJenis(jenis);
            dump.setPlatKendaraan("N " + String.valueOf((int) (Math.random() * 10000)) + " XS");
            dump.setHarga(jenis.equals("Sedang") ? 300_000 : 150_000);
            tumpukanKendaraan.add(dump);
        }

        System.out.println("Selamat datang di Filkom Travel");
        System.out.println("Silahkan login terlebih dahulu untuk menikmati layanan kami!");
        System.out.print("Username: ");
        String usernameIn = in.next();
        System.out.print("Password: ");
        String passwordIn = in.next();

        Member user = new Member(usernameIn, passwordIn);
        char dec='y';
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
                    pesanTravel(user);
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        } while(dec != 'n');

        System.out.println("Terimakasih telah memakai layanan kami!");
    }
}