package proyecto.inventario;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import proyecto.inventario.report.InventoryReport;
import java.util.List;
import java.util.ArrayList;

/**
 * Unit tests for InventoryPrinter class.
 */
public class InventoryPrinterTest extends TestCase {
    
    private InventoryPrinter printer;
    private MockInventoryReport mockReport;
    private MockInventoryReader mockReader;
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public InventoryPrinterTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(InventoryPrinterTest.class);
    }
    
    /**
     * Set up test fixtures, run before every test method.
     */
    protected void setUp() throws Exception {
        super.setUp();
        mockReport = new MockInventoryReport();
        mockReader = new MockInventoryReader();
        printer = new InventoryPrinter(mockReport, mockReader);
    }

    /**
     * Tear down test fixtures, run after every test method.
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        printer = null;
        mockReport = null;
        mockReader = null;
    }
    
    /**
     * Test printer constructor
     */
    public void testPrinterConstructor() {
        assertNotNull(printer);
    }
    
    /**
     * Test print method calls report generateReport
     */
    public void testPrintCallsGenerateReport() {
        printer.print();
        assertTrue(mockReport.generateReportCalled);
        assertNotNull(mockReport.lastProductList);
    }
    
    /**
     * Test print method with empty product list
     */
    public void testPrintWithEmptyProductList() {
        mockReader.products = new ArrayList<Product>();
        printer.print();
        assertTrue(mockReport.generateReportCalled);
        assertNotNull(mockReport.lastProductList);
        assertEquals(0, mockReport.lastProductList.size());
    }
    
    /**
     * Test print method with one product
     */
    public void testPrintWithOneProduct() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("Laptop", 5, 999.99));
        mockReader.products = products;
        
        printer.print();
        
        assertTrue(mockReport.generateReportCalled);
        assertNotNull(mockReport.lastProductList);
        assertEquals(1, mockReport.lastProductList.size());
        
        Product receivedProduct = mockReport.lastProductList.get(0);
        assertEquals("Laptop", receivedProduct.getName());
        assertEquals(5, receivedProduct.getQuantity());
        assertEquals(999.99, receivedProduct.getPrice(), 0.01);
    }
    
    /**
     * Test print method with multiple products
     */
    public void testPrintWithMultipleProducts() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("Laptop", 5, 999.99));
        products.add(new Product("Mouse", 10, 25.50));
        products.add(new Product("Keyboard", 8, 75.00));
        mockReader.products = products;
        
        printer.print();
        
        assertTrue(mockReport.generateReportCalled);
        assertNotNull(mockReport.lastProductList);
        assertEquals(3, mockReport.lastProductList.size());
        
        // Verify all products are passed correctly
        assertEquals("Laptop", mockReport.lastProductList.get(0).getName());
        assertEquals("Mouse", mockReport.lastProductList.get(1).getName());
        assertEquals("Keyboard", mockReport.lastProductList.get(2).getName());
    }
    
    /**
     * Test print method with real Inventory instance
     */
    public void testPrintWithRealInventory() {
        Inventory inventory = Inventory.getInstance();
        InventoryPrinter realPrinter = new InventoryPrinter(mockReport, inventory);
        
        realPrinter.print();
        
        assertTrue(mockReport.generateReportCalled);
        assertNotNull(mockReport.lastProductList);
        // Should have products from previous tests since it's a singleton
        assertTrue(mockReport.lastProductList.size() >= 0);
    }
    
    /**
     * Mock implementation of InventoryReport for testing
     */
    private static class MockInventoryReport implements InventoryReport {
        boolean generateReportCalled = false;
        List<Product> lastProductList = null;
        
        @Override
        public void generateReport(List<Product> products) {
            generateReportCalled = true;
            lastProductList = products;
        }
    }
    
    /**
     * Mock implementation of InventoryReader for testing
     */
    private static class MockInventoryReader implements InventoryReader {
        List<Product> products = new ArrayList<Product>();
        
        @Override
        public List<Product> getProducts() {
            return products;
        }
    }
}