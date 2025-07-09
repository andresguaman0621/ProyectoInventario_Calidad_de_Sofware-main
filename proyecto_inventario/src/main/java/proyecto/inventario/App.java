// src/main/java/proyecto/inventario/App.java

package proyecto.inventario;

import proyecto.inventario.report.ConsoleInventoryReport;
import proyecto.inventario.report.CsvInventoryReport;
import proyecto.inventario.report.InventoryReport;

/**
 * Clase principal que ejecuta operaciones sobre el inventario
 * y genera reportes en consola y CSV.
 */
public class App {

  /**
   * Método principal que inicia la aplicación.
   *
   * @param args Argumentos de línea de comandos (no se utilizan)
   */
  public static void main(String[] args) {
    // Solo usamos las operaciones que necesitamos
    InventoryWriter writer = Inventory.getInstance();
    InventoryReader reader = Inventory.getInstance();

    writer.addProduct("Laptop", 5, 999.99);
    writer.addProduct("Monitor", 10, 199.99);

    // Imprimimos por consola
    InventoryReport consoleReport = new ConsoleInventoryReport();
    InventoryPrinter printer = new InventoryPrinter(consoleReport, reader);
    printer.print();

    // Generamos CSV sin modificar nada más
    InventoryReport csvReport = new CsvInventoryReport("inventario.csv");
    printer = new InventoryPrinter(csvReport, reader);
    printer.print();
  }
}
