public class TransferPayment implements Payment{
    public int pay(int uang){
        System.out.println("berhasil membayar dengan transfer sebesar " + uang );
        return uang;
    }
    
}
