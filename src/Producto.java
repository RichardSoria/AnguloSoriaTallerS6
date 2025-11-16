public class Producto {
    private int id;
    private String nombre;
    private String fecha;
    private double precio;

    public Producto(int id, String nombre, String fecha, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "  Producto ID: " + id + " Nombre: " + nombre + " Fecha de Venta: " + fecha + " Precio: " + precio;
    }
}