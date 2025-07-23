public class Farmer extends User{
    private int uang;

    public Farmer(String role, String username, String password) {
        super(role, username, password);
    }
    public void receivedMoney(int harga){
        this.uang += harga;
        
    }
    public int getUang() {
        return uang;
    }
    public void setUang(int uang) {
        this.uang = uang;
    }
    
    
    
}
