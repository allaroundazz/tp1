import java.util.ArrayList;

abstract class Mahasiswa{
    private String Nama;
    private String NPM;
    private int Semester;
    private ArrayList<String> MataKuliah;
    private boolean StatusMhs;
    private String statusBayar;


    public Mahasiswa(String Nama, String NPM, int Semester, boolean StatusMhs){
        this.Nama = Nama;
        this.NPM = NPM;
        this.Semester = Semester;
        this.StatusMhs = StatusMhs;
        this.MataKuliah = new ArrayList<String>();
    }

    public abstract void BayarUkt();

    public void PilihMatkul(String matkul){
        MataKuliah.add(matkul);
    }
    
    public String getStatusBayar() {
        return statusBayar;
    }

    public void setStatusBayar(String statusBayar) {
        this.statusBayar = statusBayar;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getNPM() {
        return NPM;
    }

    public void setNPM(String nPM) {
        NPM = nPM;
    }

    public int getSemester() {
        return Semester;
    }

    public void setSemester(int semester) {
        Semester = semester;
    }

    public ArrayList<String> getMataKuliah() {
        return MataKuliah;
    }

    public void setMataKuliah(ArrayList<String> mataKuliah) {
        MataKuliah = mataKuliah;
    }

    public boolean isStatusMhs() {
        return StatusMhs;
    }

    public void setStatusMhs(boolean statusMhs) {
        StatusMhs = statusMhs;
    }


}