import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit Tests untuk semua kelas dalam sistem TokoMakanan
 * Coverage minimal 80%
 */
public class TokoMakananTest {
    
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    
    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }
    
    void tearDown() {
        System.setOut(originalOut);
    }

    // ============ Tests untuk BarangDagangan ============
    @Test
    @DisplayName("Test BarangDagangan - getter dan setter harga")
    void testBarangDaganganHarga() {
        // Menggunakan concrete class untuk testing abstract class
        Rendang rendang = new Rendang(25000);
        
        assertEquals(25000, rendang.getHarga());
        
        rendang.setHarga(30000);
        assertEquals(30000, rendang.getHarga());
    }
    
    @Test
    @DisplayName("Test BarangDagangan - harga default")
    void testBarangDaganganHargaDefault() {
        DagingAyam ayam = new DagingAyam(0);
        ayam.setHarga(0); // Reset to default
        assertEquals(0, ayam.getHarga());
    }

    // ============ Tests untuk Buah ============
    @Test
    @DisplayName("Test Buah - constructor dan getter")
    void testBuahConstructor() {
        Buah buah = new Buah("Mangga", 45000.0);
        
        assertEquals("Mangga", buah.toString());
        assertEquals("Cara makan buah", buah.caraMakan());
        assertTrue(buah.getHargaInRupiah().contains("45.000"));
    }
    
    @Test
    @DisplayName("Test Buah - format harga Rupiah")
    void testBuahFormatHarga() {
        Buah buah = new Buah("Pisang", 15000.0);
        String hargaFormatted = buah.getHargaInRupiah();
        
        assertTrue(hargaFormatted.startsWith("Rp."));
        assertTrue(hargaFormatted.contains("15.000"));
    }

    // ============ Tests untuk Apel ============
    @Test
    @DisplayName("Test Apel - constructor dan methods")
    void testApelConstructorAndMethods() {
        Apel apel = new Apel(55200.0);
        
        assertEquals("Apel", apel.toString());
        assertEquals("Dicuci dulu, kulitnya bisa dimakan.", apel.caraMakan());
        assertTrue(apel.getHargaInRupiah().contains("55.200"));
    }
    
    @Test
    @DisplayName("Test Apel - main method output")
    void testApelMainMethod() {
        Apel.main(new String[]{});
        String output = outputStream.toString();
        
        assertTrue(output.contains("Hallo, Apel"));
        assertTrue(output.contains("Cara makan saya Dicuci dulu, kulitnya bisa dimakan."));
        assertTrue(output.contains("Harga saya: Rp. 55.200"));
        
        tearDown();
    }

    // ============ Tests untuk Jeruk ============
    @Test
    @DisplayName("Test Jeruk - constructor dan methods")
    void testJerukConstructorAndMethods() {
        Jeruk jeruk = new Jeruk(42500.0);
        
        assertEquals("Jeruk", jeruk.toString());
        assertEquals("Dikupas dulu sebelum makan jadi kamu ga keselek", jeruk.caraMakan());
        assertTrue(jeruk.getHargaInRupiah().contains("42.500"));
    }
    
    @Test
    @DisplayName("Test Jeruk - main method output")
    void testJerukMainMethod() {
        Jeruk.main(new String[]{});
        String output = outputStream.toString();
        
        assertTrue(output.contains("Hallo, Jeruk"));
        assertTrue(output.contains("Cara makan saya Dikupas dulu sebelum makan jadi kamu ga keselek"));
        assertTrue(output.contains("Harga saya: Rp. 42.500"));
        
        tearDown();
    }

    // ============ Tests untuk Rendang ============
    @Test
    @DisplayName("Test Rendang - constructor")
    void testRendangConstructor() {
        Rendang rendang = new Rendang(25000);
        assertEquals(25000, rendang.getHarga());
    }
    
    @Test
    @DisplayName("Test Rendang - setter harga")
    void testRendangSetHarga() {
        Rendang rendang = new Rendang(25000);
        rendang.setHarga(28000);
        assertEquals(28000, rendang.getHarga());
    }

    // ============ Tests untuk DagingAyam ============
    @Test
    @DisplayName("Test DagingAyam - constructor")
    void testDagingAyamConstructor() {
        DagingAyam ayam = new DagingAyam(30000);
        assertEquals(30000, ayam.getHarga());
    }
    
    @Test
    @DisplayName("Test DagingAyam - setter harga")
    void testDagingAyamSetHarga() {
        DagingAyam ayam = new DagingAyam(30000);
        ayam.setHarga(35000);
        assertEquals(35000, ayam.getHarga());
    }

    // ============ Tests untuk TokoBuah ============
    @Test
    @DisplayName("Test TokoBuah - cetakDeskripsi dengan Apel")
    void testTokoBuahCetakDeskripsiApel() {
        Buah apel = new Apel(55200.0);
        TokoBuah.cetakDeskripsi(apel);
        
        String output = outputStream.toString();
        assertTrue(output.contains("Hallo, Apel"));
        assertTrue(output.contains("Cara makan saya Dicuci dulu, kulitnya bisa dimakan."));
        assertTrue(output.contains("Harga saya: Rp. 55.200"));
        
        tearDown();
    }
    
    @Test
    @DisplayName("Test TokoBuah - cetakDeskripsi dengan Jeruk")
    void testTokoBuahCetakDeskripsiJeruk() {
        Buah jeruk = new Jeruk(42500.0);
        TokoBuah.cetakDeskripsi(jeruk);
        
        String output = outputStream.toString();
        assertTrue(output.contains("Hallo, Jeruk"));
        assertTrue(output.contains("Cara makan saya Dikupas dulu sebelum makan jadi kamu ga keselek"));
        assertTrue(output.contains("Harga saya: Rp. 42.500"));
        
        tearDown();
    }
    
    @Test
    @DisplayName("Test TokoBuah - main method")
    void testTokoBuahMainMethod() {
        TokoBuah.main(new String[]{});
        String output = outputStream.toString();
        
        // Should contain output for both jeruk and apel
        assertTrue(output.contains("Hallo, Jeruk"));
        assertTrue(output.contains("Hallo, Apel"));
        
        tearDown();
    }

    // ============ Tests untuk Toko<T> ============
    @Test
    @DisplayName("Test Toko - cetakDeskripsi")
    void testTokoCetakDeskripsi() {
        Toko<Apel> tokoApel = new Toko<>();
        Apel apel = new Apel(55200.0);
        
        tokoApel.cetakDeskripsi(apel);
        String output = outputStream.toString();
        
        assertTrue(output.contains("Hallo, Apel"));
        assertTrue(output.contains("Cara makan saya Dicuci dulu, kulitnya bisa dimakan."));
        assertTrue(output.contains("Harga saya: Rp. 55.200"));
        
        tearDown();
    }
    
    @Test
    @DisplayName("Test Toko - hitungJumlahDagangan")
    void testTokoHitungJumlahDagangan() {
        Toko<Buah> toko = new Toko<>();
        toko.listT = new ArrayList<>();
        
        assertEquals(0, toko.hitungJumlahDagangan());
        
        toko.listT.add(new Apel(55200.0));
        toko.listT.add(new Jeruk(42500.0));
        
        assertEquals(2, toko.hitungJumlahDagangan());
    }
    
    @Test
    @DisplayName("Test Toko - hitungNilaiDagangan")
    void testTokoHitungNilaiDagangan() {
        Toko<Buah> toko = new Toko<>();
        toko.listT = new ArrayList<>();
        
        // Test dengan list kosong
        assertEquals(0, toko.hitungNilaiDagangan());
        
        // Test dengan beberapa item
        Apel apel = new Apel(55200.0);
        Jeruk jeruk = new Jeruk(42500.0);
        
        toko.listT.add(apel);
        toko.listT.add(jeruk);
        
        // Harga dalam Buah adalah double, tapi getHarga() returns int dari BarangDagangan
        // Perlu set harga di parent class
        apel.setHarga(55200);
        jeruk.setHarga(42500);
        
        int expectedTotal = 55200 + 42500;
        assertEquals(expectedTotal, toko.hitungNilaiDagangan());
    }

    // ============ Tests untuk TokoMakanan ============
    @Test
    @DisplayName("Test TokoMakanan - constructor")
    void testTokoMakananConstructor() {
        ArrayList<BarangDagangan> dagangan = new ArrayList<>();
        dagangan.add(new Apel(55200.0));
        dagangan.add(new Jeruk(42500.0));
        
        TokoMakanan toko = new TokoMakanan(dagangan);
        assertNotNull(toko);
    }
    
    @Test
    @DisplayName("Test TokoMakanan - cetakAllDagangan")
    void testTokoMakananCetakAllDagangan() {
        ArrayList<BarangDagangan> dagangan = new ArrayList<>();
        Apel apel = new Apel(55200.0);
        Jeruk jeruk = new Jeruk(42500.0);
        
        dagangan.add(apel);
        dagangan.add(jeruk);
        
        TokoMakanan toko = new TokoMakanan(dagangan);
        toko.cetakAllDagangan();
        
        String output = outputStream.toString();
        // Output should contain the toString() representation of the ArrayList
        assertTrue(output.contains("["));
        assertTrue(output.contains("]"));
        
        tearDown();
    }
    
    @Test
    @DisplayName("Test TokoMakanan - main method")
    void testTokoMakananMainMethod() {
        TokoMakanan.main(new String[]{});
        
        String output = outputStream.toString();
        // Should print the dagangan ArrayList
        assertTrue(output.contains("["));
        assertTrue(output.contains("]"));
        
        tearDown();
    }
    
    @Test
    @DisplayName("Test TokoMakanan - dengan dagangan kosong")
    void testTokoMakananDenganDaganganKosong() {
        ArrayList<BarangDagangan> dagangan = new ArrayList<>();
        TokoMakanan toko = new TokoMakanan(dagangan);
        
        toko.cetakAllDagangan();
        String output = outputStream.toString();
        
        assertEquals("[]" + System.lineSeparator(), output);
        tearDown();
    }

    // ============ Tests untuk BisaDimakan Interface ============
    @Test
    @DisplayName("Test BisaDimakan interface implementation")
    void testBisaDimakanInterface() {
        // Test bahwa classes yang implement interface berfungsi dengan benar
        Apel apel = new Apel(55200.0);
        Jeruk jeruk = new Jeruk(42500.0);
        
        // Test polymorphism
        assertNotNull(apel.caraMakan());
        assertNotNull(jeruk.caraMakan());
        
        // Test bahwa method caraMakan() di-override dengan benar
        assertNotEquals("Cara makan buah", apel.caraMakan());
        assertNotEquals("Cara makan buah", jeruk.caraMakan());
    }

    // ============ Integration Tests ============
    @Test
    @DisplayName("Integration Test - Complete TokoMakanan workflow")
    void testCompleteTokoMakananWorkflow() {
        // Create items
        Buah apel = new Apel(55200.0);
        Buah jeruk = new Jeruk(42500.0);
        Rendang rendang = new Rendang(25000);
        DagingAyam ayam = new DagingAyam(30000);
        
        // Create dagangan list
        ArrayList<BarangDagangan> dagangan = new ArrayList<>();
        dagangan.add(apel);
        dagangan.add(jeruk);
        dagangan.add(rendang);
        dagangan.add(ayam);
        
        // Create toko
        TokoMakanan toko = new TokoMakanan(dagangan);
        
        // Test functionality
        assertNotNull(toko);
        assertEquals(4, dagangan.size());
        
        // Test printing
        toko.cetakAllDagangan();
        String output = outputStream.toString();
        assertTrue(output.length() > 0);
        
        tearDown();
    }
    
    @Test
    @DisplayName("Edge Case - Harga 0 dan negatif")
    void testEdgeCaseHarga() {
        // Test harga 0
        Rendang rendangGratis = new Rendang(0);
        assertEquals(0, rendangGratis.getHarga());
        
        // Test harga negatif (edge case)
        DagingAyam ayamHutang = new DagingAyam(-1000);
        assertEquals(-1000, ayamHutang.getHarga());
        
        // Test Buah dengan harga 0
        Buah buahGratis = new Buah("BuahGratis", 0.0);
        assertTrue(buahGratis.getHargaInRupiah().contains("0"));
    }
    
    @Test
    @DisplayName("Test Polymorphism - BarangDagangan")
    void testPolymorphismBarangDagangan() {
        BarangDagangan item1 = new Rendang(25000);
        BarangDagangan item2 = new DagingAyam(30000);
        BarangDagangan item3 = new Apel(55200.0);
        
        assertEquals(25000, item1.getHarga());
        assertEquals(30000, item2.getHarga());
        // Apel extends Buah extends BarangDagangan
        assertNotNull(item3);
    }
}