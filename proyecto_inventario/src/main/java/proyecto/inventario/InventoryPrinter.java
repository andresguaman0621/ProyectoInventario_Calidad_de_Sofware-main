package proyecto.inventario;

import java.util.List;
import proyecto.inventario.report.InventoryReport;

/**
 * Solo depende de la interfaz de lectura + del reporte,
 * no conoce addProduct ni la implementación concreta.
 */
public class InventoryPrinter {
  private final InventoryReport report;
  private final InventoryReader reader;

  public InventoryPrinter(InventoryReport report, InventoryReader reader) {
    this.report = report;
    this.reader = reader;
  }

  public void print() {
    List<Product> products = reader.getProducts();
    report.generateReport(products);
  }
}
