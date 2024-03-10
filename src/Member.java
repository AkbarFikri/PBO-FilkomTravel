import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Member extends User{
    private List<Kuitansi> historyPemesanan = new ArrayList<>();

    public Member (String username, String password){
        this.username = username;
        this.password = password;
    }
    public void pesanTravel(Vehicle kendaraan,int lamaSewa, Member pemesan) {
        Kuitansi kuitansi = new Kuitansi(kendaraan, lamaSewa, pemesan);
        historyPemesanan.add(kuitansi);
        kuitansi.cetak();
    }

}
