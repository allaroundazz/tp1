import java.util.Scanner;

import javax.swing.plaf.synth.SynthRadioButtonUI;

public class BurhanSayur {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        Consument cust = new Consument("Konsumen", "Burhan", "BurhanSukaSayur123");
        Farmer farm = new Farmer("Farmer", "SayaPetani", "bayamKangkung123");
        Sayur say1 = new Sayur("Bayam", 2000);
        Sayur say2 = new Sayur("Kol", 5000);
        Sayur say3 = new Sayur("Brokoli", 10000);

        System.out.println("==WELCOME TO BURHAN SAYUR==");
        System.out.println("Pilih sayur:");
        System.out.println("1. " +say1);
        System.out.println("2. "+say2);
        System.out.println("3. " +say3);
        System.out.print("Pilih sayur: ");
        int Pilihsayur = s.nextInt();
        s.nextLine();

        Sayur SayurPilihan = null;
        if (Pilihsayur == 1) {
            SayurPilihan =say1;
            
        }else if (Pilihsayur == 2) {
            SayurPilihan = say2;
            
        }else if(Pilihsayur == 3){
            SayurPilihan = say3;
        }

        System.out.print("Berapa jumlah "+SayurPilihan.getNama()+" yang ingin dibeli: ");
        int JumSayur = s.nextInt();
        s.nextLine();

        System.out.println("Metode pembayaran: ");
        System.out.println("1. Transfer ");
        System.out.println("2. COD (biaya admin +2500)");
        System.out.print("Pilih pembayaran: ");
        int PilihBayar = s.nextInt();
        s.nextLine();

        Payment payment;
        if(PilihBayar==1){
            payment = new TransferPayment();
        } else {
            payment = new CodPayment();
        }

        cust.beli(SayurPilihan, farm, JumSayur, payment);

        

    }


    
}
