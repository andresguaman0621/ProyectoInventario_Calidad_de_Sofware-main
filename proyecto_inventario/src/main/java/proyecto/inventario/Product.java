package proyecto.inventario;

/**
 * Representa un producto con nombre, cantidad y precio.
 * Esta clase sigue el principio de responsabilidad única (SRP).
 */
public class Product {
  private final String name;
  private int quantity;
  private double price;

  /**
   * Crea un producto con nombre, cantidad y precio.
   * @param name nombre del producto
   * @param quantity cantidad disponible
   * @param price precio unitario
   */
  public Product(String name, int quantity, double price) {
    this.name = name;
    this.quantity = quantity;
    this.price = price;
  }

  // Getters
  public String getName() {
    return name;
  }

  public int getQuantity() {
    return quantity;
  }

  public double getPrice() {
    return price;
  }

  // Setters
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  // Mostrar detalles del producto
  public String getDetails() {
    return "Product: " + name + ", Quantity: " + quantity + ", Price: $" + price;
  }
}