import java.util.Date;
import java.util.List;

public class Member extends User{
    private List<Kuitansi> historyPemesanan;


    public Member (String username, String password){
        this.username = username;
        this.password = password;
    }
    public void pesanTravel(Vehicle kendaraan,Date tanggalSewa,int lamaSewa) {
        Kuitansi kuitansi = new Kuitansi();
    }

    public void batalkanPesanan() {
        // TODO Implement me!
    }

    public List<Kuitansi> getHistoryPemesanan() {
        return historyPemesanan;
    }

    public void setHistoryPemesanan(List<Kuitansi> historyPemesanan) {
        this.historyPemesanan = historyPemesanan;
    }
}
