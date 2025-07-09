package proyecto.inventario.report;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import proyecto.inventario.Product;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Unit tests for CsvInventoryReport class.
 */
public class CsvInventoryReportTest extends TestCase {
    
    private CsvInventoryReport report;
    private String testFilePath;
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CsvInventoryReportTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(CsvInventoryReportTest.class);
    }
    
    /**
     * Set up test fixtures, run before every test method.
     */
    protected void setUp() throws Exception {
        super.setUp();
        testFilePath = "test_inventory.csv";
        report = new CsvInventoryReport(testFilePath);
        
        // Clean up any existing test file
        Path path = Paths.get(testFilePath);
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    /**
     * Tear down test fixtures, run after every test method.
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        
        // Clean up test file
        try {
            Path path = Paths.get(testFilePath);
            if (Files.exists(path)) {
                Files.delete(path);
            }
        } catch (IOException e) {
            // Ignore cleanup errors
        }
        
        report = null;
    }
    
    /**
     * Test generateReport with empty list
     */
    public void testGenerateReportWithEmptyList() throws IOException {
        List<Product> products = new ArrayList<Product>();
        report.generateReport(products);
        
        Path path = Paths.get(testFilePath);
        assertTrue(Files.exists(path));
        
        List<String> lines = Files.readAllLines(path);
        assertEquals(1, lines.size()); // Only header line
        assertEquals("Name,Quantity,Price", lines.get(0));
    }
    
    /**
     * Test generateReport with single product
     */
    public void testGenerateReportWithSingleProduct() throws IOException {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("Laptop", 5, 999.99));
        
        report.generateReport(products);
        
        Path path = Paths.get(testFilePath);
        assertTrue(Files.exists(path));
        
        List<String> lines = Files.readAllLines(path);
        assertEquals(2, lines.size()); // Header + 1 product
        assertEquals("Name,Quantity,Price", lines.get(0));
        assertEquals("Laptop,5,999.99", lines.get(1));
    }
    
    /**
     * Test generateReport with multiple products
     */
    public void testGenerateReportWithMultipleProducts() throws IOException {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("Laptop", 5, 999.99));
        products.add(new Product("Mouse", 10, 25.50));
        products.add(new Product("Keyboard", 8, 75.00));
        
        report.generateReport(products);
        
        Path path = Paths.get(testFilePath);
        assertTrue(Files.exists(path));
        
        List<String> lines = Files.readAllLines(path);
        assertEquals(4, lines.size()); // Header + 3 products
        assertEquals("Name,Quantity,Price", lines.get(0));
        assertEquals("Laptop,5,999.99", lines.get(1));
        assertEquals("Mouse,10,25.50", lines.get(2));
        assertEquals("Keyboard,8,75.00", lines.get(3));
    }
    
    /**
     * Test generateReport with product having zero quantity
     */
    public void testGenerateReportWithZeroQuantity() throws IOException {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("OutOfStock", 0, 50.00));
        
        report.generateReport(products);
        
        Path path = Paths.get(testFilePath);
        assertTrue(Files.exists(path));
        
        List<String> lines = Files.readAllLines(path);
        assertEquals(2, lines.size());
        assertEquals("Name,Quantity,Price", lines.get(0));
        assertEquals("OutOfStock,0,50.00", lines.get(1));
    }
    
    /**
     * Test generateReport with product having zero price
     */
    public void testGenerateReportWithZeroPrice() throws IOException {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("Free", 5, 0.0));
        
        report.generateReport(products);
        
        Path path = Paths.get(testFilePath);
        assertTrue(Files.exists(path));
        
        List<String> lines = Files.readAllLines(path);
        assertEquals(2, lines.size());
        assertEquals("Name,Quantity,Price", lines.get(0));
        assertEquals("Free,5,0.00", lines.get(1));
    }
    
    /**
     * Test generateReport with product having empty name
     */
    public void testGenerateReportWithEmptyName() throws IOException {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("", 3, 100.0));
        
        report.generateReport(products);
        
        Path path = Paths.get(testFilePath);
        assertTrue(Files.exists(path));
        
        List<String> lines = Files.readAllLines(path);
        assertEquals(2, lines.size());
        assertEquals("Name,Quantity,Price", lines.get(0));
        assertEquals(",3,100.00", lines.get(1));
    }
    
    /**
     * Test generateReport with decimal price formatting
     */
    public void testGenerateReportDecimalFormatting() throws IOException {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("TestProduct", 1, 123.456));
        
        report.generateReport(products);
        
        Path path = Paths.get(testFilePath);
        assertTrue(Files.exists(path));
        
        List<String> lines = Files.readAllLines(path);
        assertEquals(2, lines.size());
        assertEquals("Name,Quantity,Price", lines.get(0));
        assertEquals("TestProduct,1,123.46", lines.get(1)); // Rounded to 2 decimal places
    }
    
    /**
     * Test that report implements InventoryReport interface
     */
    public void testImplementsInventoryReport() {
        assertTrue(report instanceof InventoryReport);
    }
    
    /**
     * Test constructor with different file path
     */
    public void testConstructorWithDifferentPath() throws IOException {
        String alternativePath = "alternative_test.csv";
        CsvInventoryReport alternativeReport = new CsvInventoryReport(alternativePath);
        
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("Test", 1, 1.0));
        
        alternativeReport.generateReport(products);
        
        Path path = Paths.get(alternativePath);
        assertTrue(Files.exists(path));
        
        // Cleanup
        Files.delete(path);
    }
    
    /**
     * Test file creation in current directory
     */
    public void testFileCreation() throws IOException {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("Test", 1, 1.0));
        
        report.generateReport(products);
        
        Path path = Paths.get(testFilePath);
        assertTrue(Files.exists(path));
        assertTrue(Files.isRegularFile(path));
        assertTrue(Files.size(path) > 0);
    }
}