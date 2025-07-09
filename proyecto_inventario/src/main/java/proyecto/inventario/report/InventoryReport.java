package proyecto.inventario.report;

import java.util.List;
import proyecto.inventario.Product;

/**
 * Interfaz para generar reportes de inventario.
 */
public interface InventoryReport {
  void generateReport(List<Product> products);
}
