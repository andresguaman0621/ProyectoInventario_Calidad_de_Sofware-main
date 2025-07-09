package proyecto.inventario.report;

import java.util.List;
import proyecto.inventario.Product;

/**
 * Reporte que imprime los productos por consola.
 */
public class ConsoleInventoryReport implements InventoryReport {
  @Override
  public void generateReport(List<Product> products) {
    for (Product p : products) {
      System.out.println(p.getDetails());
    }
  }
}