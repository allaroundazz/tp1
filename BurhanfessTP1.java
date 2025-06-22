import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BurhanfessTP1 {
    public static void main(String[] args) {
        //Mencetak bagian Ascii art sebagai pembuka program
        System.out.print("\n##########################################################\n" + //
                        "#                                                        #\n" + //
                        "#                                                        #\n" + //
                        "#  ____             _                  __                #\n" + //
                        "# | __ ) _   _ _ __| |__   __ _ _ __  / _| ___  ___ ___  #\n" + //
                        "# |  _ \\| | | | '__| '_ \\ / _` | '_ \\| |_ / _ \\/ __/ __| #\n" + //
                        "# | |_) | |_| | |  | | | | (_| | | | |  _|  __/\\__ \\__ \\ #\n" + //
                        "# |____/ \\__,_|_|  |_| |_|\\__,_|_| |_|_|  \\___||___/___/ #\n" + //
                        "#                                                        #\n" + //
                        "#                                                        #\n" + //
                        "##########################################################\n\n ");
        //*********************************************************************************************************************************
        Scanner minta = new Scanner(System.in);
        int codevibe = 0;

        System.out.println("Masukan username kamu: ");
        String uname = minta.next();
        
        System.out.println("\nPilih Mode (0 = if-else, 1 = switch-mode):");
        int mode = minta.nextInt();

        //input 1
        System.out.println("\nKetik 1 kalau kamu tipe orang yang tidak ingin diketahui orang lain saat mengirim menfess ");
        int input1 = minta.nextInt();
        if (input1 == 1) {codevibe+=1;}

        //input 2
        System.out.println("\nApakah kamu tipe orang yang suka pakai kode saat ingin menyanmpaikan perasaan? ");
        String input2 = minta.next();
        if (input2.equalsIgnoreCase("ya")) {
            codevibe += 2;}

        //input 3
        System.out.println("\nKetik 1 kalau kamu suka kirim menfess yang ambigu agar menimbulkan rasa penasaran.");
        int input3 = minta.nextInt();
        if (input3 == 1) {codevibe += 4;} 

        //input 4
        System.out.println("\nApakah kamu tipe orang yang aktif mengirim manfess penuh emosi sampi menjadi bahan obrolan? (ya) (tidak)");
        String input4 = minta.next();
        if (input4.equalsIgnoreCase("ya")) {codevibe += 8;} 

        //input 5 
        System.out.println("\nKetik satu kalau kamu jarang kirim menfess, tapi sekalinya membuat langsung bikin ramai.");
        int input5 = minta.nextInt();
        if (input5 == 1) {codevibe += 16;}
        
        //*********************************************************************************************************************************
        //mencetak kriteria user berdasarkan kodevibenya
        System.out.println("\nTotal kodevibe kamu adalah " + codevibe + " jadi: ");
        if (mode == 0) {
            if (codevibe <= 5) {
                System.out.println("\n    Kamu tipe 'pengagum diam-diam'. MenFess-mu jarang, tapi kalau muncul bikin kaget.\n");
            } if (codevibe > 5 && codevibe <= 10) {
                System.out.println("\n    Kamu tibe 'semi-aktif'. Kadang muncul dengan kode, kadang ngilang.\n");
            } if (codevibe > 10 && codevibe <= 15) {
                System.out.println("\n    Kamu tipe 'suka bikin penasaran'. MenFess-mu bikin orang mikir, tapi kadang bikin bingung.\n");
            } if (codevibe > 15 && codevibe <= 20) {
                System.out.println("\n    Kamu tibe 'suka bikin drama'. MenFess-mu bikin orang penasaran, tapi terlalu banyak drama.\n");
            } if (codevibe > 20 && codevibe <= 25) {
                System.out.println("\n    Kamu tipe 'suka bikin orang mikir'.MenFess-mu bikin orang penasaran, tapi kadang bikin mereka mikir\n");
            } if (codevibe > 25 && codevibe <= 31) {
                System.out.println("\n    Kamu tipe 'rahasia'. MenFess-mu jarang muncul, tapi kalau muncul bikin orang penasaran siapa yang ngirim.\n");
            }
        }
        //*********************************************************************************************************************************
        if (mode == 1) {
            switch (codevibe/6) {
                case 0:
                    System.out.println("\n    Kamu tipe 'pengagum diam-diam'. MenFess-mu jarang, tapi kalau muncul bikin kaget. : via Switch-Case\n");
                    break;
                case 1:
                    System.out.println("\n    Kamu tibe 'semi-aktif'. Kadang muncul dengan kode, kadang ngilang. : via Switch-Case\n");
                    break;
                case 2:
                    System.out.println("\n    Kamu tipe 'suka bikin penasaran'. MenFess-mu bikin orang mikir, tapi kadang bikin bingung. : via Switch-Case\n");
                    break;
                case 3:
                    System.out.println("\n    Kamu tipe 'suka bikin drama'. MenFess-mu bikin orang penasaran, tapi terlalu banyak drama. : via Switch-Case\n");
                    break;
                case 4:
                    System.out.println("\n    Kamu tipe 'suka bikin orang mikir'. MenFess-mu bikin orang penasaran, tapi kadang bikin mereka mikir : via Switch-Case\n");
                    break;
                case 5:
                    System.out.println("\n    Kamu tipe 'rahasia'. MenFess-mu jarang muncul, tapi kalau muncul bikin orang penasaran siapa yang ngirim. : via Switch-Case\n");
                    break;
                }  
            }
            //*********************************************************************************************************************************
            LocalDateTime now = LocalDateTime.now();
            int tahun = now.getYear();
            int bulan = now.getMonthValue();
            int hari = now.getDayOfMonth();
            int jam = now.getHour();
            int menit = now.getMinute();
            int detik = now.getSecond();

            System.out.println("Masukan mode pengiriman fess (0 = sekarang, 1 = masa depan):");
            int modekirim = minta.nextInt();

            if (modekirim == 0) {
                LocalDateTime sekarangbanget = LocalDateTime.of(tahun, bulan, hari, jam, menit, detik);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy, 'pukul' HH:mm:ss");
                DateTimeFormatter formatterresi = DateTimeFormatter.ofPattern("d MMMM yyyy");
                System.out.println("\n    Fess dikirimkan sekarang pada: " + sekarangbanget.format(formatter)+ "\n");

                System.out.println("\n============================");
                System.out.println("        RESI FESS        ");
                System.out.println("============================");
                System.out.println("Pengirim:  " + uname + "\n");
                System.out.println("Waktu Kirim:  " + sekarangbanget.format(formatterresi) + "\n");
                System.out.println("Status:  Fess Terkirim");
                System.out.println("============================");
            }
            if (modekirim == 1) {
                System.out.println("\nMasukan jumlah detik dari sekarang hingga fess dikirim: ");
                int detikirim = minta.nextInt();
                
                int hitungdetik = detikirim % 60;
                int hitungmenit = (detikirim / 60) % 60;
                int hitungjam = (detikirim / 3600) % 24;
                int hitunghari = detikirim / 86400;
                detik += hitungdetik;
                if (detik >= 60) {
                    detik -= 60;
                    menit ++;
                } 
                menit += hitungmenit;

                if (menit >= 60) {
                    menit -= 60;
                    jam ++;
                } 
                jam += hitungjam;

                if( jam >= 60) {
                    jam -= 60;
                    hari ++;
                } 
                hari += hitunghari;

                //membuat conditional jumlah hari perbulan dan apabila ada tahun kabisat
                int hariperbulan = 31;
                if (bulan == 4 || bulan == 6 || bulan == 9 || bulan == 11) {
                    hariperbulan = 30;
                } 
                else if (bulan == 2) {
                    if ((tahun % 4 == 0 && tahun % 100 != 0) || tahun % 400 == 0) {hariperbulan = 29;}
                    else {hariperbulan = 28;}
                }

                if (hari > hariperbulan) {
                    hari -= hariperbulan;
                    bulan++;
                    if (bulan > 12) {
                        bulan = 1;
                        tahun++;
                    }
                }
                LocalDateTime waktukirim = LocalDateTime.of(tahun, bulan, hari, jam, menit, detik);
                DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMMM yyyy, 'pukul' HH:mm:ss");
                DateTimeFormatter formatresi = DateTimeFormatter.ofPattern("d MMMM yyyy");
                System.out.println("\n    Fess akan dikirimkan pada: " + waktukirim.format(format));  

                System.out.println("\n============================");
                System.out.println("        RESI FESS        ");
                System.out.println("============================");
                System.out.println("Pengirim:  " + uname + "\n");
                System.out.println("Waktu Kirim:  " + waktukirim.format(formatresi) + "\n");
                System.out.println("Status:  Menunggu pengiriman");
                System.out.println("============================");
            }
    }
}