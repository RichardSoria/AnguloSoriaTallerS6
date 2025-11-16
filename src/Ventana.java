import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class Ventana {
    private JPanel principal;
    private JTabbedPane tabbedPane1;
    private JTextField txtIDProducto;
    private JTextField txtPrecioProducto;
    private JComboBox cbcNombreProducto;
    private JTextField txtFechaVenta;
    private JButton btnCrearVenta;
    private JButton btnEditarVenta;
    private JButton btnEliminarVenta;
    private JTextField txtBuscarID;
    private JButton btnBuscarID;
    private JButton btnBuscarNombre;
    private JTextField txtBuscarNombre;
    private JList lstVentas;

    Venta venta = new Venta();
    int codigo = 0;
    int indice;
    LocalDate fechaActual = LocalDate.now();

    public void limpiarCampos() {
        txtIDProducto.setText("");
        txtPrecioProducto.setText("");
        txtFechaVenta.setText("");
        cbcNombreProducto.setSelectedIndex(0);
    }

    public void actualizarLista() {
        DefaultListModel dlm = new DefaultListModel();
        List<Producto> productos = venta.todos();
        for (Producto p : productos) {
            dlm.addElement(p.toString());
        }
        lstVentas.setModel(dlm);
    }

    public Ventana() {
        lstVentas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (lstVentas.getSelectedIndex() != -1) {
                    indice = lstVentas.getSelectedIndex();
                    Producto p = venta.todos().get(indice);
                    txtIDProducto.setText(""+p.getId());
                    cbcNombreProducto.setSelectedItem(p.getNombre());
                    txtFechaVenta.setText(p.getFecha());
                    txtPrecioProducto.setText(""+p.getPrecio());
                    JOptionPane.showMessageDialog(null, "La venta con el ID: " + p.getId() + " ha sido seleccionada.");
                }
            }
        });

        btnCrearVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar campos vacíos
                if (txtIDProducto.getText().isEmpty() || txtPrecioProducto.getText().isEmpty() || txtFechaVenta.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                    return;
                }
                // Obtener y validar datos
                int id;
                double precio;
                String nombre = cbcNombreProducto.getSelectedItem().toString();
                String fecha = txtFechaVenta.getText();
                // Validar campos numéricos
                try {
                    id = Integer.parseInt(txtIDProducto.getText());
                    precio = Double.parseDouble(txtPrecioProducto.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID y Precio deben ser numéricos.");
                    return;
                }
                if (precio <= 0) {
                    JOptionPane.showMessageDialog(null, "El precio debe ser un número positivo.");
                    return;
                }
                // Validar fecha (formato simple)
                if (!fecha.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    JOptionPane.showMessageDialog(null, "La fecha debe tener el formato AAAA-MM-DD.");
                    return;
                }
                // Validar rango de fecha (últimos 3 meses)
                String[] parts = fecha.split("-");
                int anio = Integer.parseInt(parts[0]);
                int mes = Integer.parseInt(parts[1]);
                int anioActual = fechaActual.getYear();
                int mesActual = fechaActual.getMonthValue();
                if (anio < anioActual || (anio == anioActual && mes < mesActual - 3) || mes > mesActual) {
                    JOptionPane.showMessageDialog(null, "La fecha debe estar dentro de los últimos 3 meses del actual año.");
                    return;
                }
                // Validar ID único
                if (id <= codigo) {
                    JOptionPane.showMessageDialog(null, "ID Inválido");
                } else {
                    Producto p = new Producto(id, nombre, fecha, precio);
                    venta.agregarVenta(p);
                    codigo = id;
                    JOptionPane.showMessageDialog(null, "Venta Ingresada");
                    limpiarCampos();
                    actualizarLista();
                }
            }
        });

        btnEliminarVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtIDProducto.getText());
                // Eliminar venta
                if (venta.eliminarVenta(id)){
                    JOptionPane.showMessageDialog(null, "Venta Eliminada.");
                    limpiarCampos();
                    actualizarLista();
                } else {
                    JOptionPane.showMessageDialog(null, "No existe el ID.");
                }
            }
        });
        btnBuscarID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar campo vacío
                if (txtBuscarID.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID para buscar.");
                    return;
                }
                // Obtener y validar ID
                int id;
                try {
                    id = Integer.parseInt(txtBuscarID.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un número entero.");
                    return;
                }
                // Buscar venta por ID
                String resultado = venta.buscarVentaID(id);
                if (resultado == null) {
                    JOptionPane.showMessageDialog(null, "No se encontró ninguna venta con ese ID.");
                } else {
                    JOptionPane.showMessageDialog(null, "Venta encontrada: " + resultado);
                }
            }
        });
        btnBuscarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar campo vacío
                if (txtBuscarNombre.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre para buscar.");
                    return;
                }
                // Obtener nombre
                String nombre = txtBuscarNombre.getText();
                // Buscar venta por nombre
                String resultado = venta.buscarVentaNombre(nombre);
                if (resultado == null) {
                    JOptionPane.showMessageDialog(null, "No se encontró la venta con ese nombre.");
                } else {
                    JOptionPane.showMessageDialog(null, "Venta encontrada: " + resultado);
                }
            }
        });
        btnEditarVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar campos vacíos
                if (txtIDProducto.getText().isEmpty() || txtPrecioProducto.getText().isEmpty() || txtFechaVenta.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                    return;
                }
                // Obtener y validar datos
                int id;
                double precio;
                String nombre = cbcNombreProducto.getSelectedItem().toString();
                String fecha = txtFechaVenta.getText();
                // Validar campos numéricos
                try {
                    id = Integer.parseInt(txtIDProducto.getText());
                    precio = Double.parseDouble(txtPrecioProducto.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID y Precio deben ser numéricos.");
                    return;
                }
                if (precio <= 0) {
                    JOptionPane.showMessageDialog(null, "El precio debe ser un número positivo.");
                    return;
                }
                // Validar fecha (formato simple)
                if (!fecha.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    JOptionPane.showMessageDialog(null, "La fecha debe tener el formato AAAA-MM-DD.");
                    return;
                }
                Producto p = new Producto(id, nombre, fecha, precio);
                // Editar venta
                if (venta.editarVenta(id, p)){
                    JOptionPane.showMessageDialog(null, "Venta Editada.");
                    limpiarCampos();
                    actualizarLista();
                } else {
                    JOptionPane.showMessageDialog(null, "No existe el ID.");
                }
            }
        });
    }
    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tienda en Línea - Gestión de Ventas");
        frame.setContentPane(new Ventana().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
