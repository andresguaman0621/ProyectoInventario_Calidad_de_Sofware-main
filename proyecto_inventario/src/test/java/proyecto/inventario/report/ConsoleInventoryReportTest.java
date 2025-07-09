package proyecto.inventario.report;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import proyecto.inventario.Product;
import java.util.List;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit tests for ConsoleInventoryReport class.
 */
public class ConsoleInventoryReportTest extends TestCase {
    
    private ConsoleInventoryReport report;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ConsoleInventoryReportTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ConsoleInventoryReportTest.class);
    }
    
    /**
     * Set up test fixtures, run before every test method.
     */
    protected void setUp() throws Exception {
        super.setUp();
        report = new ConsoleInventoryReport();
        
        // Capture System.out for testing
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Tear down test fixtures, run after every test method.
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        // Restore original System.out
        System.setOut(originalOut);
        report = null;
        outputStream = null;
    }
    
    /**
     * Test generateReport with empty list
     */
    public void testGenerateReportWithEmptyList() {
        List<Product> products = new ArrayList<Product>();
        report.generateReport(products);
        
        String output = outputStream.toString();
        assertEquals("", output);
    }
    
    /**
     * Test generateReport with single product
     */
    public void testGenerateReportWithSingleProduct() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("Laptop", 5, 999.99));
        
        report.generateReport(products);
        
        String output = outputStream.toString();
        assertTrue(output.contains("Product: Laptop, Quantity: 5, Price: $999.99"));
    }
    
    /**
     * Test generateReport with multiple products
     */
    public void testGenerateReportWithMultipleProducts() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("Laptop", 5, 999.99));
        products.add(new Product("Mouse", 10, 25.50));
        products.add(new Product("Keyboard", 8, 75.00));
        
        report.generateReport(products);
        
        String output = outputStream.toString();
        assertTrue(output.contains("Product: Laptop, Quantity: 5, Price: $999.99"));
        assertTrue(output.contains("Product: Mouse, Quantity: 10, Price: $25.5"));
        assertTrue(output.contains("Product: Keyboard, Quantity: 8, Price: $75.0"));
    }
    
    /**
     * Test generateReport with product having zero quantity
     */
    public void testGenerateReportWithZeroQuantity() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("OutOfStock", 0, 50.00));
        
        report.generateReport(products);
        
        String output = outputStream.toString();
        assertTrue(output.contains("Product: OutOfStock, Quantity: 0, Price: $50.0"));
    }
    
    /**
     * Test generateReport with product having zero price
     */
    public void testGenerateReportWithZeroPrice() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("Free", 5, 0.0));
        
        report.generateReport(products);
        
        String output = outputStream.toString();
        assertTrue(output.contains("Product: Free, Quantity: 5, Price: $0.0"));
    }
    
    /**
     * Test generateReport with product having empty name
     */
    public void testGenerateReportWithEmptyName() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("", 3, 100.0));
        
        report.generateReport(products);
        
        String output = outputStream.toString();
        assertTrue(output.contains("Product: , Quantity: 3, Price: $100.0"));
    }
    
    /**
     * Test generateReport output formatting
     */
    public void testGenerateReportOutputFormatting() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("TestProduct", 1, 123.45));
        
        report.generateReport(products);
        
        String output = outputStream.toString();
        String expectedOutput = "Product: TestProduct, Quantity: 1, Price: $123.45";
        assertTrue(output.contains(expectedOutput));
    }
    
    /**
     * Test that report implements InventoryReport interface
     */
    public void testImplementsInventoryReport() {
        assertTrue(report instanceof InventoryReport);
    }
    
    /**
     * Test generateReport method exists and is callable
     */
    public void testGenerateReportMethodExists() {
        List<Product> products = new ArrayList<Product>();
        // This should not throw any exception
        report.generateReport(products);
    }
}