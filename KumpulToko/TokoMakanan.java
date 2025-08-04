import java.util.ArrayList;

public class TokoMakanan {
    private ArrayList<BarangDagangan> dagangan;
    public TokoMakanan(ArrayList<BarangDagangan> dagangan) {
        this.dagangan = dagangan;
        }
        public void cetakAllDagangan(){
            System.out.println(dagangan.toString());
        }
        public static void main(String[] args) {
            Buah apel = new Apel(55200.0);
            Buah jeruk = new Jeruk(42500.0);
            Rendang rendangPadang = new Rendang(25000);
            DagingAyam ayamKremes = new DagingAyam(30000);
    
            ArrayList<BarangDagangan> dagangan = new ArrayList<>();
    
            dagangan.add(apel);
            dagangan.add(jeruk);
            dagangan.add(rendangPadang);
            dagangan.add(ayamKremes);
    
            TokoMakanan kantinBalgebun = new TokoMakanan(dagangan);

        kantinBalgebun.cetakAllDagangan();

    }



    
}
