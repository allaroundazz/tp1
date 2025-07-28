import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Buah {
    private String namaBuah;
    private double harga;

    public Buah(String namaBuah, double harga) {
        this.namaBuah = namaBuah;
        this.harga = harga;
    }

    public String toString() {
        return namaBuah;
    }

    public String caraMakan(){
        return "Cara makan buah";
    }

    public String getHargaInRupiah() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) numberFormat).getDecimalFormatSymbols();
        decimalFormatSymbols .setCurrencySymbol("Rp. ");
        ((DecimalFormat) numberFormat).setDecimalFormatSymbols(decimalFormatSymbols);
        return numberFormat.format(harga).trim();
    }
}

