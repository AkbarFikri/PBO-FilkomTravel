import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;
public class Kuitansi {
    String id;
    User pemesan;
    LocalDate tanggalPesan;
    int lamaPesan;
    Vehicle kendaraan;

    public Kuitansi(){}
    public Kuitansi(User pemesan, LocalDate tanggalPesan, int lamaPesan){
        this.pemesan = pemesan;
        this.tanggalPesan = tanggalPesan;
        this.lamaPesan = lamaPesan;
    }

    public void cetak(){
        System.out.println("\nKuitansi pemesanan:");
        System.out.println("Nama: "+this.pemesan.getUsername());
        System.out.println("Tanggal: "+tanggalPesan);
        System.out.println("Lama Memesan: "+lamaPesan+" hari");
        System.out.println("Tanggal kembali: "+tanggalPesan.plusDays(lamaPesan)+"\n");

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