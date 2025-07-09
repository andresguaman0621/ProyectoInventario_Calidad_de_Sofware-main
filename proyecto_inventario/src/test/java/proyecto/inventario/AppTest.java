package proyecto.inventario;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import proyecto.inventario.report.ConsoleInventoryReport;
import proyecto.inventario.report.CsvInventoryReport;
import proyecto.inventario.report.InventoryReport;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Unit test for App class.
 */
public class AppTest extends TestCase {
    
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }
    
    /**
     * Set up test fixtures, run before every test method.
     */
    protected void setUp() throws Exception {
        super.setUp();
        
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
        outputStream = null;
        
        // Clean up any created CSV files
        try {
            Path csvPath = Paths.get("inventario.csv");
            if (Files.exists(csvPath)) {
                Files.delete(csvPath);
            }
        } catch (Exception e) {
            // Ignore cleanup errors
        }
    }

    /**
     * Test that App main method runs without exceptions
     */
    public void testAppMainMethod() {
        try {
            App.main(new String[]{});
            // If we get here, main method executed without throwing exceptions
            assertTrue(true);
        } catch (Exception e) {
            fail("App.main() should not throw exceptions: " + e.getMessage());
        }
    }
    
    /**
     * Test that App main method produces console output
     */
    public void testAppMainMethodProducesOutput() {
        App.main(new String[]{});
        
        String output = outputStream.toString();
        assertNotNull(output);
        assertTrue(output.length() > 0);
        
        // Should contain "Product added" messages
        assertTrue(output.contains("Product added"));
        
        // Should contain product details
        assertTrue(output.contains("Product: Laptop"));
        assertTrue(output.contains("Product: Monitor"));
    }
    
    /**
     * Test that App main method creates CSV file
     */
    public void testAppMainMethodCreatesCsvFile() {
        App.main(new String[]{});
        
        Path csvPath = Paths.get("inventario.csv");
        assertTrue(Files.exists(csvPath));
    }
    
    /**
     * Test that App main method works with empty arguments
     */
    public void testAppMainMethodWithEmptyArgs() {
        try {
            App.main(new String[]{});
            assertTrue(true);
        } catch (Exception e) {
            fail("App.main() should handle empty arguments: " + e.getMessage());
        }
    }
    
    /**
     * Test that App main method works with null arguments
     */
    public void testAppMainMethodWithNullArgs() {
        try {
            App.main(null);
            assertTrue(true);
        } catch (Exception e) {
            fail("App.main() should handle null arguments: " + e.getMessage());
        }
    }
    
    /**
     * Test App class existence and main method signature
     */
    public void testAppClassExists() {
        try {
            Class<?> appClass = Class.forName("proyecto.inventario.App");
            assertNotNull(appClass);
            
            // Test main method exists with correct signature
            java.lang.reflect.Method mainMethod = appClass.getMethod("main", String[].class);
            assertNotNull(mainMethod);
            
            // Test main method is static
            assertTrue(java.lang.reflect.Modifier.isStatic(mainMethod.getModifiers()));
            
            // Test main method is public
            assertTrue(java.lang.reflect.Modifier.isPublic(mainMethod.getModifiers()));
        } catch (Exception e) {
            fail("App class should exist with proper main method: " + e.getMessage());
        }
    }
    
    /**
     * Test that App integrates with Inventory singleton
     */
    public void testAppIntegratesWithInventory() {
        // Get inventory instance before running app
        Inventory inventory = Inventory.getInstance();
        int initialSize = inventory.getProducts().size();
        
        App.main(new String[]{});
        
        // Verify products were added
        int finalSize = inventory.getProducts().size();
        assertTrue(finalSize >= initialSize + 2); // At least 2 products added
    }
}
