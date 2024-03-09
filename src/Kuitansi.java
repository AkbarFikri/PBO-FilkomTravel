import java.util.*;
public class Kuitansi {
    String id;
    User pemesan;
    Date tanggalPesan;
    int lamaPesan;
    Vehicle kendaraan;


    public Kuitansi(User pemesan, Date tanggalPesan, int lamaPesan){
        this.pemesan = pemesan;
        this.tanggalPesan = tanggalPesan;
        this.lamaPesan = lamaPesan;
    }

    public void cetak(){
        System.out.println("Nama: "+this.pemesan.getUsername());
        System.out.println("Tanggal: "+tanggalPesan);
        System.out.println("Lama Memesan: "+lamaPesan+" hari");
    }

    public Date getTanggalPesan() {
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