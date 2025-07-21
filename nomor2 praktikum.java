import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Menfessapp {

    // ===== SUPERCLASS =====
    public static class Menfess {
        protected String isiPesan;
        protected String username;
        protected LocalDateTime waktuKirim;
        protected boolean disembunyikan = false;
        protected int id; // ID unik untuk setiap menfess
        protected static int nextId = 1; // Counter untuk ID

        public Menfess(String isiPesan) {
            this.isiPesan = isiPesan;
            this.username = "anonim";
            this.waktuKirim = LocalDateTime.now();
            this.id = nextId++;
        }

        public void setWaktuKirim(LocalDateTime waktu) {
            this.waktuKirim = waktu;
        }

        public String getFormattedWaktu() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return waktuKirim.format(formatter);
        }

        public String tampilkan() {
            return "[Menfess] oleh " + username + ": \"" + isiPesan + "\"";
        }

        public int getId() {
            return id;
        }
    }

    // ===== SUBCLASS: CURHAT =====
    public static class CurhatFess extends Menfess {
        public CurhatFess(String username, String isiPesan) {
            super(isiPesan);
            this.username = username;
        }

        @Override
        public String tampilkan() {
            return "[Curhat] oleh " + username + ": \"" + isiPesan + "\"";
        }
    }

    // ===== SUBCLASS: CONFESSION =====
    public static class ConfessFess extends Menfess {
        private ArrayList<BalasanFess> daftarBalasan = new ArrayList<>();

        public ConfessFess(String penerima, String isiPesan) {
            super(isiPesan);
            this.username = penerima;
        }

        @Override
        public String tampilkan() {
            StringBuilder result = new StringBuilder();
            result.append("[Confession #").append(id).append("] oleh anonim: \"").append(isiPesan).append("\"")
                  .append("\nPenerima: ").append(username);
            
            // Tampilkan balasan jika ada
            if (!daftarBalasan.isEmpty()) {
                result.append("\n--- Balasan ---");
                for (BalasanFess balasan : daftarBalasan) {
                    result.append("\n").append(balasan.tampilkan());
                }
            }
            return result.toString();
        }

        public void tambahBalasan(BalasanFess balasan) {
            daftarBalasan.add(balasan);
        }

        public ArrayList<BalasanFess> getDaftarBalasan() {
            return daftarBalasan;
        }
    }

    // ===== SUBCLASS: BALASAN CONFESSION =====
    public static class BalasanFess extends Menfess {
        private int confessionId; // ID confession yang dibalas

        public BalasanFess(String penerima, String isiPesan, int confessionId) {
            super(isiPesan);
            this.username = penerima;
            this.confessionId = confessionId;
        }

        @Override
        public String tampilkan() {
            return "[Balasan] oleh " + username + ": \"" + isiPesan + "\"";
        }

        public int getConfessionId() {
            return confessionId;
        }
    }

    // ===== SUBCLASS: PROMO =====
    public static class PromosiFess extends Menfess {
        public PromosiFess(String username, String isiPesan) {
            super(isiPesan);
            this.username = username;
        }

        @Override
        public String tampilkan() {
            return "[Promo] oleh " + username + ": \"" + isiPesan + "\"";
        }
    }

    // ===== METHOD UNTUK MENJALANKAN PROGRAM =====
    public static ArrayList<Menfess> daftarFess = new ArrayList<>();
    public static ArrayList<Integer> daftarDelay = new ArrayList<>();

    public static void jalankanProgramMenfess() {
        Scanner input = new Scanner(System.in);

        System.out.print("Berapa fess yang ingin dijadwalkan? (maks 5): ");
        int jumlah = Integer.parseInt(input.nextLine());
        jumlah = Math.min(jumlah, 5);

        LocalDateTime waktuSekarang = LocalDateTime.now();

        for (int i = 0; i < jumlah; i++) {
            System.out.println("\nFess #" + (i + 1));
            System.out.print("Masukkan delay fess #" + (i + 1) + " (detik): ");
            int delay = Integer.parseInt(input.nextLine());
            daftarDelay.add(delay);

            System.out.print("Masukkan isi pesan: ");
            String isiPesan = input.nextLine();

            System.out.print("Masukkan tipe fess (curhat/confession/promo): ");
            String tipe = input.nextLine().toLowerCase();

            Menfess fess = null;

            switch (tipe) {
                case "curhat":
                    System.out.print("Masukkan username pengirim: ");
                    String userCurhat = input.nextLine();
                    fess = new CurhatFess(userCurhat, isiPesan);
                    break;
                case "confession":
                    System.out.print("Isi username penerima: ");
                    String penerima = input.nextLine();
                    fess = new ConfessFess(penerima, isiPesan);
                    break;
                case "promo":
                    System.out.print("Masukkan username pengirim: ");
                    String userPromo = input.nextLine();
                    fess = new PromosiFess(userPromo, isiPesan);
                    break;
                default:
                    System.out.println("Tipe tidak valid. Ulangi input.");
                    i--;
                    continue;
            }

            daftarFess.add(fess);
        }

        System.out.println("\nMengurutkan dan mengirimkan fess...");

        LocalDateTime waktuKirim = waktuSekarang;
        for (int i = 0; i < daftarFess.size(); i++) {
            int delay = daftarDelay.get(i);
            waktuKirim = waktuKirim.plusSeconds(delay);
            daftarFess.get(i).setWaktuKirim(waktuKirim);

            System.out.println("\nFess akan dikirim pada: " + daftarFess.get(i).getFormattedWaktu());
            System.out.println("Dengan pesan: " + daftarFess.get(i).isiPesan);
            System.out.println("Tipe: " + daftarFess.get(i).getClass().getSimpleName().replace("Fess", "").toLowerCase());

            if (daftarFess.get(i) instanceof ConfessFess) {
                System.out.println("Penerima: " + daftarFess.get(i).username);
            }
        }

        tampilkanDaftarMenfess(daftarFess, LocalDateTime.now());

        // Menu utama setelah melihat menfess
        menuUtama();
    }

    public static void menuUtama() {
        Scanner input = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Lihat Menfess");
            System.out.println("2. Balas Confession");
            System.out.println("3. Admin - Sembunyikan Fess");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            
            String pilihan = input.nextLine();
            
            switch (pilihan) {
                case "1":
                    tampilkanDaftarMenfess(daftarFess, LocalDateTime.now());
                    break;
                case "2":
                    balasConfession();
                    break;
                case "3":
                    sembunyikanFess(daftarFess);
                    break;
                case "4":
                    System.out.println("Terima kasih!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    public static void balasConfession() {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Masukkan username kamu: ");
        String username = input.nextLine();
        
        // Tampilkan confession yang ditujukan untuk user ini
        System.out.println("\n=== Confession untuk " + username + " ===");
        ArrayList<ConfessFess> confessionUntukUser = new ArrayList<>();
        
        for (Menfess fess : daftarFess) {
            if (fess instanceof ConfessFess && fess.username.equals(username) 
                && !fess.disembunyikan && !fess.waktuKirim.isAfter(LocalDateTime.now())) {
                confessionUntukUser.add((ConfessFess) fess);
                System.out.println(fess.tampilkan());
                System.out.println("Waktu Kirim: " + fess.getFormattedWaktu());
                System.out.println();
            }
        }
        
        if (confessionUntukUser.isEmpty()) {
            System.out.println("Tidak ada confession untuk kamu saat ini.");
            return;
        }
        
        System.out.print("Masukkan ID confession yang ingin kamu balas: ");
        try {
            int confessionId = Integer.parseInt(input.nextLine());
            
            // Cari confession dengan ID yang sesuai
            ConfessFess targetConfession = null;
            for (ConfessFess confession : confessionUntukUser) {
                if (confession.getId() == confessionId) {
                    targetConfession = confession;
                    break;
                }
            }
            
            if (targetConfession == null) {
                System.out.println("ID confession tidak ditemukan!");
                return;
            }
            
            System.out.print("Masukkan balasan kamu: ");
            String isiBalasan = input.nextLine();
            
            // Buat balasan
            BalasanFess balasan = new BalasanFess(username, isiBalasan, confessionId);
            targetConfession.tambahBalasan(balasan);
            daftarFess.add(balasan); // Tambahkan ke daftar umum juga
            
            System.out.println("Balasan berhasil dikirim! Pengirim confession akan dapat melihat balasan kamu secara anonim.");
            
            // Tampilkan confession beserta balasannya
            System.out.println("\n=== Confession + Balasan ===");
            System.out.println(targetConfession.tampilkan());
            System.out.println("Waktu Kirim: " + targetConfession.getFormattedWaktu());
            
        } catch (NumberFormatException e) {
            System.out.println("ID harus berupa angka!");
        }
    }

    public static void tampilkanDaftarMenfess(ArrayList<Menfess> daftarFess, LocalDateTime waktuSekarang) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nPilih salah satu daftar menfess!");
            System.out.println("1. Semua Menfess");
            System.out.println("2. Menfess Buat Kamu");
            System.out.println("3. Kembali ke Menu Utama");
            System.out.print("Masukkan pilihanmu: ");
            String pilihan = input.nextLine();

            if (pilihan.equals("1")) {
                System.out.println("\n== Semua Menfess Publik yang Sudah Terkirim ==");
                for (Menfess fess : daftarFess) {
                    if (!(fess instanceof ConfessFess) && !(fess instanceof BalasanFess) 
                        && !fess.waktuKirim.isAfter(waktuSekarang) && !fess.disembunyikan) {
                        System.out.println(fess.tampilkan());
                        System.out.println("Waktu kirim: " + fess.getFormattedWaktu());
                        System.out.println();
                    }
                }
            } else if (pilihan.equals("2")) {
                System.out.print("Masukkan username kamu: ");
                String username = input.nextLine();
                System.out.println("\n== Semua Menfess Confession untuk " + username + " ==");
                
                for (Menfess fess : daftarFess) {
                    if (fess instanceof ConfessFess && fess.username.equals(username)) {
                        if (!fess.waktuKirim.isAfter(waktuSekarang) && !fess.disembunyikan) {
                            System.out.println(fess.tampilkan());
                            System.out.println("Waktu Kirim: " + fess.getFormattedWaktu());
                            System.out.println();
                        } else if (fess.waktuKirim.isAfter(waktuSekarang)) {
                            System.out.println("\n// Menfess confession belum ditampilkan karena terdapat delay "
                                    + java.time.Duration.between(waktuSekarang, fess.waktuKirim).abs().getSeconds()
                                    + " detik. Menfess akan muncul jika delay telah berlalu atau waktu pengiriman sama dengan waktu yang sekarang");
                        }
                    }
                }
            } else if (pilihan.equals("3")) {
                break;
            } else {
                System.out.println("Pilihan tidak valid. Ulangi.");
            }

            System.out.print("\nIngin melihat lagi? (y/n): ");
            String ulang = input.nextLine().toLowerCase();
            if (!ulang.equals("y")) {
                break;
            }
        }
    }

    public static void sembunyikanFess(ArrayList<Menfess> daftarFess) {
        Scanner input = new Scanner(System.in);
        System.out.println("\n== Semua Menfess yang Sudah Terkirim ==");

        int index = 0;
        for (int i = 0; i < daftarFess.size(); i++) {
            Menfess f = daftarFess.get(i);
            if (!f.disembunyikan) {
                System.out.println("[" + index + "] " + f.tampilkan());
                index++;
            }
        }

        System.out.print("Masukkan username pemilik fess: ");
        String targetUsername = input.nextLine();
        System.out.print("Masukkan index fess yang ingin disembunyikan: ");
        int idx = Integer.parseInt(input.nextLine());

        int currentIndex = 0;
        for (Menfess f : daftarFess) {
            if (!f.disembunyikan && f.username.equals(targetUsername)) {
                if (currentIndex == idx) {
                    f.disembunyikan = true;
                    System.out.println("Fess berhasil disembunyikan.");
                    return;
                }
                currentIndex++;
            }
        }

        System.out.println("Fess tidak ditemukan atau tidak cocok.");
    }

    public static void main(String[] args) {
        jalankanProgramMenfess();
    }
}
