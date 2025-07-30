import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Halo mahasiswa UI!");
        System.out.println("Silahkan isi daftar diri dibawah ini!");

        System.out.println("Nama diri: ");
        String nama = s.nextLine();

        System.out.println("NPM");
        String npm = s.nextLine();

        System.out.println("Semester ke-: ");
        int sem = s.nextInt();
        s.nextLine();

        System.out.println("Penerima beasiswa (Tidak/Instansi/Biasa): ");
        String skull = s.nextLine();

        Mahasiswa mhs = null;
        if (skull.equalsIgnoreCase("Tidak")){
            System.out.println("Metode pembayaran (Cicilan/Lunas): ");
            String Met = s.nextLine();
            Pembayaran bayar; 
            if (Met.equalsIgnoreCase("Cicilan")){
                bayar = new Cicilan();
            } else{
                bayar = new Lunas();
            }



            mhs = new MahasiswaBiasa(nama, npm, sem, true, bayar);
        }else if (skull.equalsIgnoreCase("Instansi")){
            mhs = new MahasiswaBeasiswaIns(nama, npm, sem, true);

        }else if(skull.equalsIgnoreCase("Biasa")){
            System.out.println("Berapa persen beasiswa yang kamu terima: ");
            int persen = s.nextInt();
            s.nextLine();

            mhs = new MahasiswaBeasiswa(nama, npm, sem, false, persen);
            
        }

        boolean sudahBayar=false;

        while(true) {
            System.out.println("Selamat datang di SIAK-NG");
            System.out.println("1. Pembayaran");
            System.out.println("2. Pilih Mata Kuliah");
            System.out.println("Masukkan opsi yang dipilih: ");
            int pilih = s.nextInt();
            s.nextLine();
    
            if (pilih == 1){
                mhs.BayarUkt();
                sudahBayar=true;
            } else{
                if(sudahBayar==false){
                    System.out.println("Status pembayaran harus sudah dibayar.");
                    continue;
                }
                System.out.println("--Dibawah ini adalah list mata kuliah yang bisa diambil: ");
                System.out.println("Semester 1:\r\n" + //
                                    "\r\n" + //
                                    "Matematika Dasar I | Senin | 08:00 - 09:40\r\n" + //
                                    "Pengantar Teknologi Informasi | Selasa | 10:00 - 11:40\r\n" + //
                                    "Algoritma dan Pemrograman I | Rabu | 13:00 - 14:40\r\n" + //
                                    "Bahasa Inggris I | Kamis | 15:00 - 16:40\r\n" + //
                                    "\r\n" + //
                                    "Semester 2:\r\n" + //
                                    "\r\n" + //
                                    "Matematika Dasar II | Senin | 08:00 - 09:40\r\n" + //
                                    "Struktur Data | Selasa | 10:00 - 11:40\r\n" + //
                                    "Algoritma dan Pemrograman II | Rabu | 13:00 - 14:40\r\n" + //
                                    "Fisika Dasar | Kamis | 15:00 - 16:40\r\n" + //
                                    "\r\n" + //
                                    "Semester 3:\r\n" + //
                                    "\r\n" + //
                                    "Matematika Diskrit | Senin | 08:00 - 09:40\r\n" + //
                                    "Basis Data | Selasa | 10:00 - 11:40\r\n" + //
                                    "Pemrograman Berorientasi Objek | Rabu | 13:00 - 14:40\r\n" + //
                                    "Sistem Operasi | Kamis | 15:00 - 16:40\r\n" + //
                                    "\r\n" + //
                                    "Semester 4:\r\n" + //
                                    "\r\n" + //
                                    "Analisis dan Desain Algoritma | Senin | 08:00 - 09:40\r\n" + //
                                    "Jaringan Komputer | Selasa | 10:00 - 11:40\r\n" + //
                                    "Rekayasa Perangkat Lunak | Rabu | 13:00 - 14:40\r\n" + //
                                    "Statistika dan Probabilitas | Kamis | 15:00 - 16:40\r\n" + //
                                    "\r\n" + //
                                    "Semester 5:\r\n" + //
                                    "\r\n" + //
                                    "Kecerdasan Buatan | Senin | 08:00 - 09:40\r\n" + //
                                    "Pemrograman Web | Selasa | 10:00 - 11:40\r\n" + //
                                    "Keamanan Sistem Informasi | Rabu | 13:00 - 14:40\r\n" + //
                                    "Manajemen Proyek TI | Kamis | 15:00 - 16:40\r\n" + //
                                    "\r\n" + //
                                    "Semester 6:\r\n" + //
                                    "\r\n" + //
                                    "Machine Learning | Senin | 08:00 - 09:40\r\n" + //
                                    "Pemrograman Mobile | Selasa | 10:00 - 11:40\r\n" + //
                                    "Sistem Terdistribusi | Rabu | 13:00 - 14:40\r\n" + //
                                    "Interaksi Manusia dan Komputer | Kamis | 15:00 - 16:40\r\n" + //
                                    "\r\n" + //
                                    "Semester 7:\r\n" + //
                                    "\r\n" + //
                                    "Data Mining | Senin | 08:00 - 09:40\r\n" + //
                                    "Cloud Computing | Selasa | 10:00 - 11:40\r\n" + //
                                    "Etika Profesi | Rabu | 13:00 - 14:40\r\n" + //
                                    "Metodologi Penelitian | Kamis | 15:00 - 16:40\r\n" + //
                                    "\r\n" + //
                                    "Semester 8:\r\n" + //
                                    "\r\n" + //
                                    "Tugas Akhir | Senin | 08:00 - 09:40\r\n" + //
                                    "Seminar | Selasa | 10:00 - 11:40\r\n" + //
                                    "Kerja Praktik | Rabu | 13:00 - 14:40\r\n" + //
                                    "Technopreneurship | Kamis | 15:00 - 16:40");
                        System.out.println("Berapa mata kuliah yang ingin diambil: ");
                        int inputmatkul = s.nextInt();
                        s.nextLine();
    
                    for (int i = 0; i < inputmatkul; i++) {
                        System.out.println("Mata kuliah yang ingin diambil (sesuaikan dengan semester kamu): ");
                        String pilihan = s.nextLine();
                        mhs.PilihMatkul(pilihan);
                    }

                    break;
    
            }
        }


        System.out.println("\n---INFORMASI MAHASISWA---");
        System.out.println("Nama: " + mhs.getNama());
        System.out.println("NPM: " + mhs.getNPM());
        System.out.println("Status pembayaran: " + mhs.getStatusBayar());
        System.out.println("Mata kuliah yang diambil untuk semester " + mhs.getSemester());

        for (int i = 0; i < mhs.getMataKuliah().size(); i++) {
            System.out.println(mhs.getMataKuliah().get(i));
        }

    }
}
