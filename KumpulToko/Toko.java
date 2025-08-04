import java.util.ArrayList;

public class Toko<T extends Buah> {
    ArrayList<T> listT;
    public void cetakDeskripsi(T t) {
        System.out.println("Hallo, " + t.toString());
        System.out.println("Cara makan saya " + t.caraMakan());
        System.out.println("Harga saya: " + t.getHargaInRupiah()+ " per kilo");
    }

    public int hitungJumlahDagangan() {
        return listT.size();
    }

    public int hitungNilaiDagangan() {
        int hargatot = 0;
        for (int i = 0; i< listT.size(); i++) {
            hargatot += listT.get(i).getHarga();
        }
        return hargatot;
    }
    

}
