import enums.MissionStatus;

import javax.swing.*;
import java.awt.*;

public class RegistrarMisionUI extends JPanel {
    public RegistrarMisionUI() {
        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblPrioridad = new JLabel("Prioridad:");
        JTextField txtPrioridad = new JTextField();

        JLabel lblTipo = new JLabel("Tipo de misión:");
        String[] tipos = {"Colonización", "Exploración", "Recolección de datos"};
        JComboBox<String> cmbTipo = new JComboBox<>(tipos);

        JButton btnRegistrar = new JButton("Registrar");
        JLabel lblResultado = new JLabel("");

        add(lblNombre); add(txtNombre);
        add(lblPrioridad); add(txtPrioridad);
        add(lblTipo); add(cmbTipo);
        add(new JLabel()); add(btnRegistrar);
        add(lblResultado); add(new JLabel());

        btnRegistrar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                int prioridad = Integer.parseInt(txtPrioridad.getText());
                String tipo = cmbTipo.getSelectedItem().toString();

                Mision mision;
                if (tipo.equals("Colonización")) {
                    mision = new MisionColonizacion(nombre, prioridad);
                } else if (tipo.equals("Exploración")) {
                    mision = new MisionExploracion(nombre, prioridad);
                } else {
                    mision = new MisionRecoleccion(nombre, prioridad);
                }

                mision.acabarDeRegistrarDatos(nombre, prioridad, MissionStatus.PENDIENTE);
                Mision.getMisiones().add(mision);
                lblResultado.setText("✅ Misión registrada correctamente");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Prioridad debe ser un número.");
            }
        });
    }
}
