package proyecto.inventario;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit tests for Product class.
 */
public class ProductTest extends TestCase {
    
    private Product product;
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ProductTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ProductTest.class);
    }
    
    /**
     * Set up test fixtures, run before every test method.
     */
    protected void setUp() throws Exception {
        super.setUp();
        product = new Product("Laptop", 10, 999.99);
    }

    /**
     * Tear down test fixtures, run after every test method.
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        product = null;
    }
    
    /**
     * Test product constructor and getters
     */
    public void testProductCreation() {
        assertEquals("Laptop", product.getName());
        assertEquals(10, product.getQuantity());
        assertEquals(999.99, product.getPrice(), 0.01);
    }
    
    /**
     * Test product constructor with different values
     */
    public void testProductCreationWithDifferentValues() {
        Product monitor = new Product("Monitor", 5, 299.50);
        assertEquals("Monitor", monitor.getName());
        assertEquals(5, monitor.getQuantity());
        assertEquals(299.50, monitor.getPrice(), 0.01);
    }
    
    /**
     * Test quantity setter
     */
    public void testSetQuantity() {
        product.setQuantity(15);
        assertEquals(15, product.getQuantity());
    }
    
    /**
     * Test quantity setter with zero
     */
    public void testSetQuantityToZero() {
        product.setQuantity(0);
        assertEquals(0, product.getQuantity());
    }
    
    /**
     * Test price setter
     */
    public void testSetPrice() {
        product.setPrice(1199.99);
        assertEquals(1199.99, product.getPrice(), 0.01);
    }
    
    /**
     * Test price setter with zero
     */
    public void testSetPriceToZero() {
        product.setPrice(0.0);
        assertEquals(0.0, product.getPrice(), 0.01);
    }
    
    /**
     * Test getDetails method
     */
    public void testGetDetails() {
        String expected = "Product: Laptop, Quantity: 10, Price: $999.99";
        assertEquals(expected, product.getDetails());
    }
    
    /**
     * Test getDetails after modifying quantity
     */
    public void testGetDetailsAfterQuantityChange() {
        product.setQuantity(20);
        String expected = "Product: Laptop, Quantity: 20, Price: $999.99";
        assertEquals(expected, product.getDetails());
    }
    
    /**
     * Test getDetails after modifying price
     */
    public void testGetDetailsAfterPriceChange() {
        product.setPrice(1500.00);
        String expected = "Product: Laptop, Quantity: 10, Price: $1500.0";
        assertEquals(expected, product.getDetails());
    }
    
    /**
     * Test product with empty name
     */
    public void testProductWithEmptyName() {
        Product emptyProduct = new Product("", 5, 100.0);
        assertEquals("", emptyProduct.getName());
        assertEquals(5, emptyProduct.getQuantity());
        assertEquals(100.0, emptyProduct.getPrice(), 0.01);
    }
    
    /**
     * Test product name immutability
     */
    public void testProductNameImmutability() {
        String originalName = product.getName();
        assertEquals("Laptop", originalName);
        // Name should remain the same as there's no setter
        assertEquals("Laptop", product.getName());
    }
}