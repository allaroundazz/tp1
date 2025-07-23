import java.util.ArrayList;

public class Consument extends User {
    private ArrayList<Sayur> ListSayur = new ArrayList<>(); 

    public Consument(String role, String username, String password) {
        super(role, username, password);
    }
    public void beli(Sayur s, Farmer f, int jumlah, Payment payment){
        System.out.println("Telah berhasil membeli "+s.getNama()+" sebanyak "+jumlah);

        for(int i=0;i<jumlah;i++){
            ListSayur.add(s);
        }
        
        int hargaPayment = payment.pay(s.getHarga()*jumlah);
        f.receivedMoney(hargaPayment);

    }

    
    
}
