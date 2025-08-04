public class TokoBuah {
    public static void cetakDeskripsi(Buah Buah) {
            System.out.println("Hallo, " + Buah.toString());
            System.out.println("Cara makan saya " + Buah.caraMakan());
            System.out.println("Harga saya: " + Buah.getHargaInRupiah()+ " per kilo");
        }
    
    public static void main(String[] args) {
        Buah apel = new Apel(55200.0);
        Buah jeruk = new Jeruk(42500.0);

        TokoBuah tokoBuahSegar = new TokoBuah();
        TokoBuah.cetakDeskripsi(jeruk);
        TokoBuah.cetakDeskripsi(apel);
    }
}
