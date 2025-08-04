public class MahasiswaBeasiswa extends Mahasiswa {
    int Presentase;
    public MahasiswaBeasiswa(String Nama, String NPM, int Semester, boolean StatusMhs, int presentase){
        super(Nama,NPM,Semester,StatusMhs);
        this.Presentase = presentase;
    }

    public void BayarUkt(){
        this.setStatusBayar("Lunas");
        System.out.println("UKT telah dibayar! dengan presentase sebesar " + Presentase);
    }
}
