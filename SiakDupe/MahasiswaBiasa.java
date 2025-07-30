public class MahasiswaBiasa extends Mahasiswa {

    Pembayaran pay;
    public MahasiswaBiasa(String Nama, String NPM, int Semester, boolean StatusMhs, Pembayaran pay){
        super(Nama,NPM,Semester,StatusMhs);
        this.pay = pay;
      
    }
    public void BayarUkt(){
        if (pay instanceof Cicilan){
            System.out.println("Cicilan UKT sudah lunas dibayar (2 kali)");
            this.setStatusBayar("Cicilan");
        } else if( pay instanceof Lunas){
            System.out.println("UKT telah lunas dibayar");
            this.setStatusBayar("Lunas");
        }
    }
}
