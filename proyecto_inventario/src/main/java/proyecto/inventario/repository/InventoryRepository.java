// src/main/java/proyecto/inventario/repository/InventoryRepository.java

package proyecto.inventario.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import proyecto.inventario.Product;

/**
 * Responsable sólo de almacenar y entregar la lista de Product.
 */
public class InventoryRepository {
  private final List<Product> products = new ArrayList<>();

  public void add(Product product) {
    products.add(product);
  }

  public List<Product> findAll() {
    return Collections.unmodifiableList(products);
  }
}
