import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

// Test untuk interface Pembayaran
class PembayaranTest {
    
    @Test
    @DisplayName("Test konstanta NominalUKT dalam interface Pembayaran")
    void testNominalUKTConstant() {
        assertEquals(10000000, Pembayaran.NominalUKT);
    }
}

// Test untuk class Cicilan
class CicilanTest {
    private Cicilan cicilan;
    
    @BeforeEach
    void setUp() {
        cicilan = new Cicilan();
    }
    
    @Test
    @DisplayName("Test konstruktor Cicilan dan perhitungan angsuran")
    void testCicilanConstructor() {
        assertNotNull(cicilan);
        assertEquals(5000000, cicilan.Angsuran);
        assertEquals(Pembayaran.NominalUKT / 2, cicilan.Angsuran);
    }
    
    @Test
    @DisplayName("Test implementasi interface Pembayaran")
    void testImplementsPembayaran() {
        assertTrue(cicilan instanceof Pembayaran);
    }
}

// Test untuk class Lunas
class LunasTest {
    private Lunas lunas;
    
    @BeforeEach
    void setUp() {
        lunas = new Lunas();
    }
    
    @Test
    @DisplayName("Test konstruktor Lunas")
    void testLunasConstructor() {
        assertNotNull(lunas);
    }
    
    @Test
    @DisplayName("Test implementasi interface Pembayaran")
    void testImplementsPembayaran() {
        assertTrue(lunas instanceof Pembayaran);
    }
}

// Test untuk abstract class Mahasiswa melalui concrete implementation
class MahasiswaTest {
    private MahasiswaBiasa mahasiswaBiasa;
    private MahasiswaBeasiswa mahasiswaBeasiswa;
    private MahasiswaBeasiswaIns mahasiswaBeasiswaIns;
    private Cicilan cicilan;
    private Lunas lunas;
    
    @BeforeEach
    void setUp() {
        cicilan = new Cicilan();
        lunas = new Lunas();
        mahasiswaBiasa = new MahasiswaBiasa("John Doe", "2106123456", 3, true, cicilan);
        mahasiswaBeasiswa = new MahasiswaBeasiswa("Jane Doe", "2106123457", 2, true, 50);
        mahasiswaBeasiswaIns = new MahasiswaBeasiswaIns("Bob Smith", "2106123458", 4, true);
    }
    
    @Test
    @DisplayName("Test konstruktor Mahasiswa melalui MahasiswaBiasa")
    void testMahasiswaConstructor() {
        assertEquals("John Doe", mahasiswaBiasa.getNama());
        assertEquals("2106123456", mahasiswaBiasa.getNPM());
        assertEquals(3, mahasiswaBiasa.getSemester());
        assertTrue(mahasiswaBiasa.isStatusMhs());
        assertNotNull(mahasiswaBiasa.getMataKuliah());
        assertEquals(0, mahasiswaBiasa.getMataKuliah().size());
    }
    
    @Test
    @DisplayName("Test getter dan setter Nama")
    void testNamaGetterSetter() {
        mahasiswaBiasa.setNama("New Name");
        assertEquals("New Name", mahasiswaBiasa.getNama());
    }
    
    @Test
    @DisplayName("Test getter dan setter NPM")
    void testNPMGetterSetter() {
        mahasiswaBiasa.setNPM("2106999999");
        assertEquals("2106999999", mahasiswaBiasa.getNPM());
    }
    
    @Test
    @DisplayName("Test getter dan setter Semester")
    void testSemesterGetterSetter() {
        mahasiswaBiasa.setSemester(5);
        assertEquals(5, mahasiswaBiasa.getSemester());
    }
    
    @Test
    @DisplayName("Test getter dan setter StatusMhs")
    void testStatusMhsGetterSetter() {
        mahasiswaBiasa.setStatusMhs(false);
        assertFalse(mahasiswaBiasa.isStatusMhs());
    }
    
    @Test
    @DisplayName("Test getter dan setter StatusBayar")
    void testStatusBayarGetterSetter() {
        mahasiswaBiasa.setStatusBayar("Lunas");
        assertEquals("Lunas", mahasiswaBiasa.getStatusBayar());
    }
    
    @Test
    @DisplayName("Test PilihMatkul method")
    void testPilihMatkul() {
        mahasiswaBiasa.PilihMatkul("Matematika Diskrit");
        mahasiswaBiasa.PilihMatkul("Basis Data");
        
        assertEquals(2, mahasiswaBiasa.getMataKuliah().size());
        assertTrue(mahasiswaBiasa.getMataKuliah().contains("Matematika Diskrit"));
        assertTrue(mahasiswaBiasa.getMataKuliah().contains("Basis Data"));
    }
    
    @Test
    @DisplayName("Test setMataKuliah method")
    void testSetMataKuliah() {
        java.util.ArrayList<String> newMatkul = new java.util.ArrayList<>();
        newMatkul.add("Pemrograman Web");
        newMatkul.add("Machine Learning");
        
        mahasiswaBiasa.setMataKuliah(newMatkul);
        assertEquals(2, mahasiswaBiasa.getMataKuliah().size());
        assertEquals(newMatkul, mahasiswaBiasa.getMataKuliah());
    }
}

// Test untuk MahasiswaBiasa
class MahasiswaBiasaTest {
    private MahasiswaBiasa mahasiswaBiasaCicilan;
    private MahasiswaBiasa mahasiswaBiasaLunas;
    private Cicilan cicilan;
    private Lunas lunas;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    
    @BeforeEach
    void setUp() {
        cicilan = new Cicilan();
        lunas = new Lunas();
        mahasiswaBiasaCicilan = new MahasiswaBiasa("John Doe", "2106123456", 3, true, cicilan);
        mahasiswaBiasaLunas = new MahasiswaBiasa("Jane Smith", "2106123457", 2, true, lunas);
        
        // Setup untuk capture output
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }
    
    @Test
    @DisplayName("Test konstruktor MahasiswaBiasa")
    void testMahasiswaBiasaConstructor() {
        assertEquals("John Doe", mahasiswaBiasaCicilan.getNama());
        assertEquals("2106123456", mahasiswaBiasaCicilan.getNPM());
        assertEquals(3, mahasiswaBiasaCicilan.getSemester());
        assertTrue(mahasiswaBiasaCicilan.isStatusMhs());
        assertEquals(cicilan, mahasiswaBiasaCicilan.pay);
    }
    
    @Test
    @DisplayName("Test BayarUkt dengan pembayaran Cicilan")
    void testBayarUktCicilan() {
        mahasiswaBiasaCicilan.BayarUkt();
        
        assertEquals("Cicilan", mahasiswaBiasaCicilan.getStatusBayar());
        String output = outputStream.toString();
        assertTrue(output.contains("Cicilan UKT sudah lunas dibayar (2 kali)"));
    }
    
    @Test
    @DisplayName("Test BayarUkt dengan pembayaran Lunas")
    void testBayarUktLunas() {
        mahasiswaBiasaLunas.BayarUkt();
        
        assertEquals("Lunas", mahasiswaBiasaLunas.getStatusBayar());
        String output = outputStream.toString();
        assertTrue(output.contains("UKT telah lunas dibayar"));
    }
    
    @Test
    @DisplayName("Test inheritance dari Mahasiswa")
    void testInheritance() {
        assertTrue(mahasiswaBiasaCicilan instanceof Mahasiswa);
    }
    
    void tearDown() {
        System.setOut(originalOut);
    }
}

// Test untuk MahasiswaBeasiswa
class MahasiswaBeasiswaTest {
    private MahasiswaBeasiswa mahasiswaBeasiswa;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    
    @BeforeEach
    void setUp() {
        mahasiswaBeasiswa = new MahasiswaBeasiswa("Alice Johnson", "2106123458", 4, true, 75);
        
        // Setup untuk capture output
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }
    
    @Test
    @DisplayName("Test konstruktor MahasiswaBeasiswa")
    void testMahasiswaBeasiswaConstructor() {
        assertEquals("Alice Johnson", mahasiswaBeasiswa.getNama());
        assertEquals("2106123458", mahasiswaBeasiswa.getNPM());
        assertEquals(4, mahasiswaBeasiswa.getSemester());
        assertTrue(mahasiswaBeasiswa.isStatusMhs());
        assertEquals(75, mahasiswaBeasiswa.Presentase);
    }
    
    @Test
    @DisplayName("Test BayarUkt untuk mahasiswa beasiswa")
    void testBayarUkt() {
        mahasiswaBeasiswa.BayarUkt();
        
        assertEquals("Lunas", mahasiswaBeasiswa.getStatusBayar());
        String output = outputStream.toString();
        assertTrue(output.contains("UKT telah dibayar! dengan presentase sebesar 75"));
    }
    
    @Test
    @DisplayName("Test inheritance dari Mahasiswa")
    void testInheritance() {
        assertTrue(mahasiswaBeasiswa instanceof Mahasiswa);
    }
    
    @Test
    @DisplayName("Test dengan presentase beasiswa 0%")
    void testBeasiswaZeroPercent() {
        MahasiswaBeasiswa mahasiswaZero = new MahasiswaBeasiswa("Zero Percent", "2106000000", 1, true, 0);
        mahasiswaZero.BayarUkt();
        
        assertEquals("Lunas", mahasiswaZero.getStatusBayar());
        assertEquals(0, mahasiswaZero.Presentase);
    }
    
    @Test
    @DisplayName("Test dengan presentase beasiswa 100%")
    void testBeasiswaFullPercent() {
        MahasiswaBeasiswa mahasiswaFull = new MahasiswaBeasiswa("Full Scholarship", "2106100000", 1, true, 100);
        mahasiswaFull.BayarUkt();
        
        assertEquals("Lunas", mahasiswaFull.getStatusBayar());
        assertEquals(100, mahasiswaFull.Presentase);
    }
    
    void tearDown() {
        System.setOut(originalOut);
    }
}

// Test untuk MahasiswaBeasiswaIns
class MahasiswaBeasiswaInsTest {
    private MahasiswaBeasiswaIns mahasiswaBeasiswaIns;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    
    @BeforeEach
    void setUp() {
        mahasiswaBeasiswaIns = new MahasiswaBeasiswaIns("Corporate Scholar", "2106123459", 5, true);
        
        // Setup untuk capture output
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }
    
    @Test
    @DisplayName("Test konstruktor MahasiswaBeasiswaIns")
    void testMahasiswaBeasiswaInsConstructor() {
        assertEquals("Corporate Scholar", mahasiswaBeasiswaIns.getNama());
        assertEquals("2106123459", mahasiswaBeasiswaIns.getNPM());
        assertEquals(5, mahasiswaBeasiswaIns.getSemester());
        assertTrue(mahasiswaBeasiswaIns.isStatusMhs());
    }
    
    @Test
    @DisplayName("Test BayarUkt untuk mahasiswa beasiswa instansi")
    void testBayarUkt() {
        mahasiswaBeasiswaIns.BayarUkt();
        
        assertEquals("Lunas", mahasiswaBeasiswaIns.getStatusBayar());
        String output = outputStream.toString();
        assertTrue(output.contains("UKT telah dibayar telat oleh instansi"));
    }
    
    @Test
    @DisplayName("Test inheritance dari Mahasiswa")
    void testInheritance() {
        assertTrue(mahasiswaBeasiswaIns instanceof Mahasiswa);
    }
    
    void tearDown() {
        System.setOut(originalOut);
    }
}

// Integration Test - Test interaksi antar kelas
class IntegrationTest {
    
    @Test
    @DisplayName("Test integrasi semua jenis mahasiswa dengan polymorphism")
    void testPolymorphism() {
        Mahasiswa[] mahasiswaArray = {
            new MahasiswaBiasa("Student1", "NPM001", 1, true, new Cicilan()),
            new MahasiswaBiasa("Student2", "NPM002", 2, true, new Lunas()),
            new MahasiswaBeasiswa("Student3", "NPM003", 3, true, 50),
            new MahasiswaBeasiswaIns("Student4", "NPM004", 4, true)
        };
        
        // Test bahwa semua objek dapat diperlakukan sebagai Mahasiswa
        for (Mahasiswa mhs : mahasiswaArray) {
            assertNotNull(mhs);
            assertTrue(mhs instanceof Mahasiswa);
            
            // Test method yang ada di parent class
            mhs.PilihMatkul("Test Course");
            assertEquals(1, mhs.getMataKuliah().size());
            
            // Test abstract method implementation
            mhs.BayarUkt();
            assertNotNull(mhs.getStatusBayar());
        }
    }
    
    @Test
    @DisplayName("Test konstanta interface dapat diakses dari implementasi")
    void testInterfaceConstantAccess() {
        Cicilan c = new Cicilan();
        Lunas l = new Lunas();
        
        // Test bahwa konstanta dapat diakses melalui implementasi
        assertEquals(Pembayaran.NominalUKT, 10000000);
        assertEquals(c.Angsuran, Pembayaran.NominalUKT / 2);
    }
    
    @Test
    @DisplayName("Test multiple mata kuliah assignment")
    void testMultipleMataKuliahAssignment() {
        MahasiswaBiasa mhs = new MahasiswaBiasa("Test Student", "NPM123", 3, true, new Lunas());
        
        String[] courses = {
            "Matematika Diskrit",
            "Basis Data", 
            "Pemrograman Berorientasi Objek",
            "Sistem Operasi"
        };
        
        for (String course : courses) {
            mhs.PilihMatkul(course);
        }
        
        assertEquals(4, mhs.getMataKuliah().size());
        for (String course : courses) {
            assertTrue(mhs.getMataKuliah().contains(course));
        }
    }
}