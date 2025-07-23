public class CodPayment implements Payment{
    int adminCod = 2500;
     public int pay(int uang){
        System.out.println("Berhasil membayar dengan COD payment sebesar " + (uang+adminCod));
        return uang+adminCod;
     }

    
}
