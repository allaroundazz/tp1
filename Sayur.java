public class Sayur {
    private String nama;
    private int harga;
    public Sayur(String nama, int harga) {
        this.nama = nama;
        this.harga = harga;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public int getHarga() {
        return harga;
    }
    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String toString(){
        return "Nama: " + nama + " Harga: "+ harga;
    }
}
