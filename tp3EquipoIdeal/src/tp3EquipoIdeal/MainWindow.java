package tp3EquipoIdeal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {

    private List<Persona> disponibles;
    private Incompatibilidades incomp;
    private AppController controlador;
    private DefaultListModel<Persona> modeloPersonas;
    private JComboBox<Persona> cbIncomp1, cbIncomp2;
    private DefaultListModel<String> modeloIncompVisual; 
    private JSpinner spLider, spArquitecto, spProgramador, spTester;
    private JTextArea txtSalida;

    public MainWindow() {
        disponibles = new ArrayList<>();
        incomp = new Incompatibilidades();
        controlador = new AppController();
        
        setTitle("Sistema HR - El Equipo Ideal");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("1. Personas", crearPanelPersonas());
        tabbedPane.addTab("2. Incompatibilidades", crearPanelIncompatibilidades());
        tabbedPane.addTab("3. Requerimientos", crearPanelRequerimientos());
        tabbedPane.addTab("4. Resolver", crearPanelResolver());

        add(tabbedPane);
    }

    private JPanel crearPanelPersonas() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(1, 7, 5, 5));
        JTextField txtNombre = new JTextField();
        JComboBox<Rol> cbRol = new JComboBox<>(Rol.values());
        JSpinner spCalificacion = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1)); // Notas del 1 al 5
        JButton btnAgregar = new JButton("Agregar");

        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(txtNombre);
        formPanel.add(new JLabel("Rol:"));
        formPanel.add(cbRol);
        formPanel.add(new JLabel("Nota (1-5):"));
        formPanel.add(spCalificacion);
        formPanel.add(btnAgregar);

        // Lista Central
        modeloPersonas = new DefaultListModel<>();
        JList<Persona> listaVisualPersonas = new JList<>(modeloPersonas);
        
        // Acción del botón
        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            if (!nombre.isEmpty()) {
                Persona p = new Persona(nombre, (Rol) cbRol.getSelectedItem(), (Integer) spCalificacion.getValue());
                if (!disponibles.contains(p)) {
                    disponibles.add(p);
                    modeloPersonas.addElement(p);
                    cbIncomp1.addItem(p); // Actualiza combobox de la pestaña 2
                    cbIncomp2.addItem(p);
                    txtNombre.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Esa persona ya existe.");
                }
            }
        });

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(listaVisualPersonas), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelIncompatibilidades() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new FlowLayout());
        cbIncomp1 = new JComboBox<>();
        cbIncomp2 = new JComboBox<>();
        JButton btnIncomp = new JButton("Marcar Incompatibles");

        formPanel.add(new JLabel("Persona 1:"));
        formPanel.add(cbIncomp1);
        formPanel.add(new JLabel("Persona 2:"));
        formPanel.add(cbIncomp2);
        formPanel.add(btnIncomp);

        modeloIncompVisual = new DefaultListModel<>();
        JList<String> listaVisualIncomp = new JList<>(modeloIncompVisual);

        btnIncomp.addActionListener(e -> {
            Persona p1 = (Persona) cbIncomp1.getSelectedItem();
            Persona p2 = (Persona) cbIncomp2.getSelectedItem();
            
            if (p1 != null && p2 != null && !p1.equals(p2)) {
                incomp.agregarIncompatibilidad(p1, p2);
                modeloIncompVisual.addElement("⚡ " + p1.getNombre() + " NO puede trabajar con " + p2.getNombre());
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione dos personas distintas.");
            }
        });

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(listaVisualIncomp), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelRequerimientos() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        spLider = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        spArquitecto = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        spProgramador = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        spTester = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));

        panel.add(new JLabel("Cant. Líderes de Proyecto:")); panel.add(spLider);
        panel.add(new JLabel("Cant. Arquitectos:")); panel.add(spArquitecto);
        panel.add(new JLabel("Cant. Programadores:")); panel.add(spProgramador);
        panel.add(new JLabel("Cant. Testers:")); panel.add(spTester);

        return panel;
    }

    private JPanel crearPanelResolver() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnResolver = new JButton("🚀 GENERAR EQUIPO IDEAL");
        btnResolver.setFont(new Font("Arial", Font.BOLD, 14));
        btnResolver.setBackground(new Color(46, 204, 113));
        btnResolver.setForeground(Color.BLACK);

        txtSalida = new JTextArea();
        txtSalida.setEditable(false);
        txtSalida.setFont(new Font("Monospaced", Font.PLAIN, 13));

        btnResolver.addActionListener(e -> {
            txtSalida.setText(""); 

            Requerimientos req = new Requerimientos();
            req.setCupo(Rol.LIDER_DE_PROYECTO, (Integer) spLider.getValue());
            req.setCupo(Rol.ARQUITECTO, (Integer) spArquitecto.getValue());
            req.setCupo(Rol.PROGRAMADOR, (Integer) spProgramador.getValue());
            req.setCupo(Rol.TESTER, (Integer) spTester.getValue());

            controlador.onBotonResolverClick(disponibles, incomp, req, mensaje -> {
                txtSalida.append(mensaje + "\n");
            });
        });

        panel.add(btnResolver, BorderLayout.NORTH);
        panel.add(new JScrollPane(txtSalida), BorderLayout.CENTER);
        return panel;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow ventana = new MainWindow();
            ventana.setVisible(true);
        });
    }
}
