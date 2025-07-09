package proyecto.inventario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Singleton que gestiona internamente los productos.
 * Implementa solo las operaciones de lectura y escritura.
 */
public class Inventory implements InventoryReader, InventoryWriter {
  private final List<Product> products = new ArrayList<>();

  /** Constructor privado para Singleton. */
  private Inventory() {
  }

  /**
   * Holder idiom para inicialización perezosa y segura del singleton.
   */
  private static class InventoryHolder {
    private static final Inventory INSTANCE = new Inventory();
  }

  /**
   * Obtiene la instancia única del inventario.
   * 
   * @return instancia singleton de Inventory
   */
  public static Inventory getInstance() {
    return InventoryHolder.INSTANCE;
  }

  /**
   * Agrega un producto al inventario.
   * 
   * @param name nombre del producto
   * @param quantity cantidad disponible
   * @param price precio unitario
   */
  @Override
  public void addProduct(String name, int quantity, double price) {
    products.add(new Product(name, quantity, price));
    System.out.println("Product added.");
  }

  /**
   * Obtiene la lista de productos de forma no modificable.
   * 
   * @return lista inmutable de productos
   */
  @Override
  public List<Product> getProducts() {
    return Collections.unmodifiableList(products);
  }
}
