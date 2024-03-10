import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Member extends User{
    private List<Kuitansi> historyPemesanan;


    public Member (String username, String password){
        this.username = username;
        this.password = password;
    }
    public void pesanTravel(Vehicle kendaraan,int lamaSewa, User pemesan) {
        Kuitansi kuitansi = new Kuitansi();
        kuitansi.kendaraan = kendaraan;
        kuitansi.tanggalPesan = LocalDate.now();
        kuitansi.lamaPesan = lamaSewa;
        kuitansi.pemesan = pemesan;
        kuitansi.kendaraan.isRent = true;
        kuitansi.cetak();
    }

}
