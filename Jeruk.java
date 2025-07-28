public class Jeruk extends Buah {
    public Jeruk(double harga) {
        super("Jeruk", 42500.0);
    }

    @Override
    public String caraMakan(){
        return "Dikupas dulu sebelum makan jadi kamu ga keselek";
    }

    public static void main(String[] args) {
        Jeruk jeruk = new Jeruk(42500.0);
        System.out.println("Hallo, " + jeruk.toString());
        System.out.println("Cara makan saya "+ jeruk.caraMakan());
        System.out.println("Harga saya: " + jeruk.getHargaInRupiah()+ " per kilo") ;
    }
}
