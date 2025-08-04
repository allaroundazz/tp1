public class Apel extends Buah {
    public Apel(double harga) {
        super("Apel", 55200.0);
    }
    
    @Override
    public String caraMakan(){
        return "Dicuci dulu, kulitnya bisa dimakan.";
    }

    public static void main(String[] args) {
        Apel Apel = new Apel(55200.0);
        System.out.println("Hallo, " + Apel.toString());
        System.out.println("Cara makan saya "+ Apel.caraMakan());
        System.out.println("Harga saya: " + Apel.getHargaInRupiah()+ " per kilo") ;
    }

}

