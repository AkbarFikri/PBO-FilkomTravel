import javax.imageio.metadata.IIOMetadataFormatImpl;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Vehicle[] tumpukanKendaraan = new Vehicle[5];

        for (int i = 0; i < tumpukanKendaraan.length; i++) {
            Vehicle dump = new Vehicle();
            dump.nama = "dummy "+ String.valueOf(i);
            dump.isRent = false;
            dump.kapasitas = 5;
            dump.nama = "Mobil percobaan";
            dump.jenis = "Kecil";
            dump.platKendaraan = String.valueOf(i+1);
            tumpukanKendaraan[i] = dump;
        }

        System.out.println("Selamat datang di Filkom Travel");
        System.out.println("Silahkan login terlebih dahulu untuk menikmati layanan kami!");
        System.out.print("Username: ");
        String usernameIn = in.next();
        System.out.print("Password: ");
        String passwordIn = in.next();

        Member user = new Member(usernameIn, passwordIn);
        char dec='y';
        do {
            int iterator = 1;
            for (int i = 0; i < tumpukanKendaraan.length; i++) {
                if (tumpukanKendaraan[i].isRent){
                    continue;
                }else{
                    System.out.println(iterator + ". " + tumpukanKendaraan[i].nama);
                    System.out.println("kapasitas: " + tumpukanKendaraan[i].kapasitas);
                    System.out.println("plat: "+tumpukanKendaraan[i].platKendaraan);
                    iterator++;
                }
            }
            iterator = 1;
            System.out.print("kendaraan yang disewa: ");
            int choose = in.nextInt();
            System.out.print("Lama sewa (dalam hari): ");
            int lama = in.nextInt();
            for (int i = 0; i < tumpukanKendaraan.length; i++) {
                if (tumpukanKendaraan[i].isRent){
                    iterator++;
                    continue;
                }else{
                    if (iterator>=choose){
                        user.pesanTravel(tumpukanKendaraan[iterator-1], lama, user);
                        break;
                    }
                    iterator++;
                }
            }

            System.out.println("apakah ingin lanjut? (y/n)");
            dec = in.next().charAt(0);
            iterator = 1;
        } while(dec != 'n');

        System.out.println("Terimakasih telah memakai layanan kami!");
    }
}