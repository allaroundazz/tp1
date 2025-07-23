import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CodPaymentTest {
    
    private CodPayment codPayment;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    @BeforeEach
    void setUp() {
        codPayment = new CodPayment();
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    
    @Test
    @DisplayName("Test pembayaran COD dengan jumlah positif")
    void testPembayaranDenganJumlahPositif() {
        // Given - Data yang diberikan
        int jumlahPembayaran = 10000;
        int totalYangDiharapkan = jumlahPembayaran + 2500; // 12500
        
        // When - Ketika method dipanggil
        int hasil = codPayment.pay(jumlahPembayaran);
        
        // Then - Maka hasilnya harus sesuai
        assertEquals(totalYangDiharapkan, hasil, "Total pembayaran harus termasuk biaya admin");
        assertTrue(outputStreamCaptor.toString().contains("Berhasil membayar dengan COD payment sebesar 12500"));
    }
    
    @Test
    @DisplayName("Test pembayaran COD dengan jumlah nol")
    void testPembayaranDenganJumlahNol() {
        // Given
        int jumlahPembayaran = 0;
        int totalYangDiharapkan = 2500; // Hanya biaya admin
        
        // When
        int hasil = codPayment.pay(jumlahPembayaran);
        
        // Then
        assertEquals(totalYangDiharapkan, hasil, "Pembayaran harus mengembalikan biaya admin saja ketika jumlah nol");
        assertTrue(outputStreamCaptor.toString().contains("Berhasil membayar dengan COD payment sebesar 2500"));
    }
    
    @Test
    @DisplayName("Test pembayaran COD dengan jumlah negatif")
    void testPembayaranDenganJumlahNegatif() {
        // Given
        int jumlahPembayaran = -1000;
        int totalYangDiharapkan = jumlahPembayaran + 2500; // 1500
        
        // When
        int hasil = codPayment.pay(jumlahPembayaran);
        
        // Then
        assertEquals(totalYangDiharapkan, hasil, "Pembayaran harus menangani jumlah negatif");
        assertTrue(outputStreamCaptor.toString().contains("Berhasil membayar dengan COD payment sebesar 1500"));
    }
    
    @Test
    @DisplayName("Test pembayaran COD dengan jumlah besar")
    void testPembayaranDenganJumlahBesar() {
        // Given
        int jumlahPembayaran = 1000000;
        int totalYangDiharapkan = jumlahPembayaran + 2500; // 1002500
        
        // When
        int hasil = codPayment.pay(jumlahPembayaran);
        
        // Then
        assertEquals(totalYangDiharapkan, hasil, "Pembayaran harus menangani jumlah besar dengan benar");
        assertTrue(outputStreamCaptor.toString().contains("Berhasil membayar dengan COD payment sebesar 1002500"));
    }
    
    @Test
    @DisplayName("Test biaya admin diterapkan dengan benar")
    void testBiayaAdminDiterapkan() {
        // Given
        int jumlahPembayaran = 5000;
        
        // When
        int hasil = codPayment.pay(jumlahPembayaran);
        
        // Then
        assertEquals(7500, hasil, "Biaya admin sebesar 2500 harus ditambahkan ke jumlah pembayaran");
    }
    
    @Test
    @DisplayName("Test format output konsol")
    void testFormatOutputKonsol() {
        // Given
        int jumlahPembayaran = 15000;
        
        // When
        codPayment.pay(jumlahPembayaran);
        
        // Then
        String output = outputStreamCaptor.toString().trim();
        assertEquals("Berhasil membayar dengan COD payment sebesar 17500", output);
    }
    
    @Test
    @DisplayName("Test pembayaran berulang")
    void testPembayaranBerulang() {
        // Test bahwa setiap pembayaran independen
        int hasil1 = codPayment.pay(1000);
        int hasil2 = codPayment.pay(2000);
        
        assertEquals(3500, hasil1, "Pembayaran pertama harus 1000 + 2500");
        assertEquals(4500, hasil2, "Pembayaran kedua harus 2000 + 2500");
    }
}
