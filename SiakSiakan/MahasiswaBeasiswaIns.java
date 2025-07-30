public class MahasiswaBeasiswaIns extends Mahasiswa {

    public MahasiswaBeasiswaIns(String Nama, String NPM, int Semester, boolean StatusMhs){
        super(Nama,NPM,Semester,StatusMhs);
    }

    public void BayarUkt(){
        this.setStatusBayar("Lunas");
        System.out.println("UKT telah dibayar telat oleh instansi");
    }
}
