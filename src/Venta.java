import java.util.ArrayList;
import java.util.List;

public class Venta {
    // Atributos
    private List<Producto> productos;
    // Constructor
    public Venta() {
        productos = new ArrayList<Producto>();
    }
    // Métodos CRUD
    // Crear
    public void agregarVenta(Producto dato) {
        productos.add(dato);
    }
    // Actualizar
    public boolean editarVenta(int id, Producto dato) {
        // Búsqueda binaria
        // Inicializar índices
        int i = 0;
        int s = productos.size()-1;
        int c;
        // Búsqueda binaria
        while (i <= s) {
            // Calcular punto medio
            c = (i + s)/2;
            // Comparar ID
            if (id == productos.get(c).getId()) {
                // ID encontrando, actualizar
                productos.set(c, dato);
                return true;
            } else if (productos.get(c).getId() < id) {
                // Buscar en la mitad superior
                i = c + 1;
            } else {
                // Buscar en la mitad inferior
                s = c - 1;
            }
        }
        // ID no encontrado
        return false;
    }
    // Eliminar
    public boolean eliminarVenta(int id) {
        // Búsqueda binaria
        // Inicializar índices
        int i = 0;
        int s = productos.size()-1;
        int c;
        // Búsqueda binaria
        while (i <= s) {
            // Calcular punto medio
            c = (i + s)/2;
            // Comparar ID
            if (id == productos.get(c).getId()) {
                // ID encontrando, eliminar
                productos.remove(c);
                return true;
            } else if (productos.get(c).getId() < id) {
                // Buscar en la mitad superior
                i = c + 1;
            } else {
                // Buscar en la mitad inferior
                s = c - 1;
            }
        }
        // ID no encontrado
        return false;
    }
    // Buscar por ID
    public String buscarVentaID(int id) {
        // Búsqueda binaria
        // Inicializar índices
        int i = 0;
        int s = productos.size()-1;
        int c;
        // Búsqueda binaria
        while (i <= s) {
            // Calcular punto medio
            c = (i + s)/2;
            // Comparar ID
            if (id == productos.get(c).getId()) {
                // ID encontrando
                return productos.get(c).toString();
            } else if (productos.get(c).getId() < id) {
                // Buscar en la mitad superior
                i = c + 1;
            } else {
                // Buscar en la mitad inferior
                s = c - 1;
            }
        }
        // ID no encontrado
        return null;
    }
    // Buscar por Nombre
    public String buscarVentaNombre(String nombreProducto) {
        // Ordenar la lista por nombre antes de buscar
        ArrayList<Producto> ventasOrdenadas = new ArrayList<>(this.productos);
        ventasOrdenadas.sort((v1, v2) -> v1.getNombre().compareTo(v2.getNombre()));
        // Búsqueda binaria por nombre
        // Inicializar índices
        int i = 0;
        int s = ventasOrdenadas.size()-1;
        int c;
        // Búsqueda binaria
        while (i <= s) {
            // Calcular punto medio
            c = (i + s)/2;
            // Comparar nombres
            if (ventasOrdenadas.get(c).getNombre().compareTo(nombreProducto) == 0) {
                // Nombre encontrado
                return ventasOrdenadas.get(c).toString();
            } else if (ventasOrdenadas.get(c).getNombre().compareTo(nombreProducto) < 0) {
                // Buscar en la mitad superior
                i = c + 1;
            } else {
                // Buscar en la mitad inferior
                s = c - 1;
            }
        }
        // Nombre no encontrado
        return null;
    }
    // Listar todos
    public List<Producto> todos() {
        return productos;
    }

}


