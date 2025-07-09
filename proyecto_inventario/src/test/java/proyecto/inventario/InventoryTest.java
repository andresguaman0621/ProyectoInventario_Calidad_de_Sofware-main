package proyecto.inventario;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.List;

/**
 * Unit tests for Inventory class.
 */
public class InventoryTest extends TestCase {
    
    private Inventory inventory;
    
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public InventoryTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(InventoryTest.class);
    }
    
    /**
     * Set up test fixtures, run before every test method.
     */
    protected void setUp() throws Exception {
        super.setUp();
        inventory = Inventory.getInstance();
    }

    /**
     * Tear down test fixtures, run after every test method.
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        // Since it's a singleton, we can't reset it completely
        // but we can clear the products for testing
        inventory = null;
    }
    
    /**
     * Test singleton pattern - should return same instance
     */
    public void testSingletonPattern() {
        Inventory instance1 = Inventory.getInstance();
        Inventory instance2 = Inventory.getInstance();
        assertSame(instance1, instance2);
    }
    
    /**
     * Test getInstance returns non-null instance
     */
    public void testGetInstanceNotNull() {
        assertNotNull(Inventory.getInstance());
    }
    
    /**
     * Test addProduct functionality
     */
    public void testAddProduct() {
        int initialSize = inventory.getProducts().size();
        inventory.addProduct("Mouse", 50, 25.99);
        List<Product> products = inventory.getProducts();
        assertEquals(initialSize + 1, products.size());
        
        // Find the added product
        Product addedProduct = null;
        for (Product p : products) {
            if ("Mouse".equals(p.getName())) {
                addedProduct = p;
                break;
            }
        }
        
        assertNotNull(addedProduct);
        assertEquals("Mouse", addedProduct.getName());
        assertEquals(50, addedProduct.getQuantity());
        assertEquals(25.99, addedProduct.getPrice(), 0.01);
    }
    
    /**
     * Test addProduct with different values
     */
    public void testAddProductWithDifferentValues() {
        int initialSize = inventory.getProducts().size();
        inventory.addProduct("Keyboard", 30, 75.50);
        List<Product> products = inventory.getProducts();
        assertEquals(initialSize + 1, products.size());
        
        // Find the added product
        Product addedProduct = null;
        for (Product p : products) {
            if ("Keyboard".equals(p.getName())) {
                addedProduct = p;
                break;
            }
        }
        
        assertNotNull(addedProduct);
        assertEquals("Keyboard", addedProduct.getName());
        assertEquals(30, addedProduct.getQuantity());
        assertEquals(75.50, addedProduct.getPrice(), 0.01);
    }
    
    /**
     * Test getProducts returns unmodifiable list
     */
    public void testGetProductsReturnsUnmodifiableList() {
        List<Product> products = inventory.getProducts();
        assertNotNull(products);
        
        // Try to modify the list - should throw UnsupportedOperationException
        try {
            products.add(new Product("Test", 1, 1.0));
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // Expected behavior
        }
    }
    
    /**
     * Test multiple products addition
     */
    public void testAddMultipleProducts() {
        int initialSize = inventory.getProducts().size();
        
        inventory.addProduct("Headphones", 20, 150.00);
        inventory.addProduct("Webcam", 15, 89.99);
        
        List<Product> products = inventory.getProducts();
        assertEquals(initialSize + 2, products.size());
        
        // Verify both products are in the list
        boolean foundHeadphones = false;
        boolean foundWebcam = false;
        
        for (Product p : products) {
            if ("Headphones".equals(p.getName())) {
                foundHeadphones = true;
                assertEquals(20, p.getQuantity());
                assertEquals(150.00, p.getPrice(), 0.01);
            } else if ("Webcam".equals(p.getName())) {
                foundWebcam = true;
                assertEquals(15, p.getQuantity());
                assertEquals(89.99, p.getPrice(), 0.01);
            }
        }
        
        assertTrue(foundHeadphones);
        assertTrue(foundWebcam);
    }
    
    /**
     * Test addProduct with empty name
     */
    public void testAddProductWithEmptyName() {
        int initialSize = inventory.getProducts().size();
        inventory.addProduct("", 10, 50.0);
        List<Product> products = inventory.getProducts();
        assertEquals(initialSize + 1, products.size());
        
        // Find the product with empty name
        Product emptyNameProduct = null;
        for (Product p : products) {
            if ("".equals(p.getName())) {
                emptyNameProduct = p;
                break;
            }
        }
        
        assertNotNull(emptyNameProduct);
        assertEquals("", emptyNameProduct.getName());
        assertEquals(10, emptyNameProduct.getQuantity());
        assertEquals(50.0, emptyNameProduct.getPrice(), 0.01);
    }
    
    /**
     * Test addProduct with zero quantity
     */
    public void testAddProductWithZeroQuantity() {
        int initialSize = inventory.getProducts().size();
        inventory.addProduct("ZeroQuantity", 0, 25.99);
        List<Product> products = inventory.getProducts();
        assertEquals(initialSize + 1, products.size());
        
        // Find the product with zero quantity
        Product zeroQuantityProduct = null;
        for (Product p : products) {
            if ("ZeroQuantity".equals(p.getName())) {
                zeroQuantityProduct = p;
                break;
            }
        }
        
        assertNotNull(zeroQuantityProduct);
        assertEquals("ZeroQuantity", zeroQuantityProduct.getName());
        assertEquals(0, zeroQuantityProduct.getQuantity());
        assertEquals(25.99, zeroQuantityProduct.getPrice(), 0.01);
    }
    
    /**
     * Test addProduct with zero price
     */
    public void testAddProductWithZeroPrice() {
        int initialSize = inventory.getProducts().size();
        inventory.addProduct("ZeroPrice", 5, 0.0);
        List<Product> products = inventory.getProducts();
        assertEquals(initialSize + 1, products.size());
        
        // Find the product with zero price
        Product zeroPriceProduct = null;
        for (Product p : products) {
            if ("ZeroPrice".equals(p.getName())) {
                zeroPriceProduct = p;
                break;
            }
        }
        
        assertNotNull(zeroPriceProduct);
        assertEquals("ZeroPrice", zeroPriceProduct.getName());
        assertEquals(5, zeroPriceProduct.getQuantity());
        assertEquals(0.0, zeroPriceProduct.getPrice(), 0.01);
    }
}