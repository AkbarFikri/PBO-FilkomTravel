import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;
public class Kuitansi {
    private String id;
    private User pemesan;
    private LocalDate tanggalPesan;
    private int lamaPesan;
    private Vehicle kendaraan;

    public Kuitansi(Vehicle kendaraan,int lamaSewa, User pemesan){
        this.kendaraan = kendaraan;
        this.tanggalPesan = LocalDate.now();
        this.lamaPesan = lamaSewa;
        this.pemesan = pemesan;
    }
    public Kuitansi(User pemesan, LocalDate tanggalPesan, int lamaPesan){
        this.pemesan = pemesan;
        this.tanggalPesan = tanggalPesan;
        this.lamaPesan = lamaPesan;
    }

    public void cetak(){
        System.out.println("|-------------------------------------------|");
        System.out.println("|\t\t    Kuitansi Pemesanan   \t\t\t|");
        System.out.println("|-------------------------------------------|");
        System.out.printf("|%-20s : %-17s\t|\n", "Nama Pemesan", this.pemesan.getUsername());
        System.out.printf("|%-20s : %-17s\t|\n", "Tanggal", this.tanggalPesan);
        System.out.printf("|%-20s : %-17s\t|\n", "Lama Sewa", this.lamaPesan + " hari");
        System.out.printf("|%-20s : %-17s\t|\n", "Tanggal Kembali", this.tanggalPesan.plusDays(lamaPesan));
        System.out.printf("|%-20s : %-17s\t|\n", "Jenis Mobil", this.kendaraan.getJenis());
        System.out.printf("|%-20s : %-17s\t|\n", "Kapasitas", this.kendaraan.getKapasitas());
        System.out.printf("|%-20s : %-,17d\t|\n", "Harga", this.kendaraan.getHarga());
        System.out.printf("|%-20s : %-17s\t|\n", "Plat", this.kendaraan.getPlatKendaraan());
        System.out.println("|___________________________________________|");

    }

    public LocalDate getTanggalPesan() {
        return tanggalPesan;
    }

    public User getPemesan() {
        return pemesan;
    }

    public int getLamaPesan() {
        return lamaPesan;
    }

    public Vehicle getKendaraan() {
        return kendaraan;
    }

}