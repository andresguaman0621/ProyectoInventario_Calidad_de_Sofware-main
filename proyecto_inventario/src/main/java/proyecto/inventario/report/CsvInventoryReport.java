package proyecto.inventario.report;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import proyecto.inventario.Product;

/**
 * Reporte que genera un archivo CSV con los productos.
 */
public class CsvInventoryReport implements InventoryReport {
  private final String path;

  public CsvInventoryReport(String path) {
    this.path = path;
  }

  @Override
  public void generateReport(List<Product> products) {
    try (
        BufferedWriter writer = Files.newBufferedWriter(
            Paths.get(path), StandardCharsets.UTF_8)
    ) {
      writer.write(String.format("Name,Quantity,Price%n"));
      for (Product p : products) {
        writer.write(
            String.format("%s,%d,%.2f%n", p.getName(), p.getQuantity(), p.getPrice())
        );
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
