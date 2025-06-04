import javax.swing.*;
import java.awt.*;

public class RegistrarNaveUI extends JPanel {

    public RegistrarNaveUI() {
        setLayout(new GridLayout(10, 2, 10, 10));

        // Form components
        JLabel lblNombre = new JLabel("Nombre de la nave:");
        JTextField txtNombre = new JTextField();

        JLabel lblAutMax = new JLabel("Autonomía máxima:");
        JTextField txtAutMax = new JTextField();

        JLabel lblAutActual = new JLabel("Autonomía actual:");
        JTextField txtAutActual = new JTextField();

        JLabel lblCarga = new JLabel("Capacidad de carga:");
        JTextField txtCarga = new JTextField();

        JLabel lblSensores = new JLabel("¿Sensores científicos?");
        JCheckBox chkSensores = new JCheckBox();

        JLabel lblExpTec = new JLabel("Experiencia técnica:");
        JTextField txtExpTec = new JTextField();

        JLabel lblExpCien = new JLabel("Experiencia científica:");
        JTextField txtExpCien = new JTextField();

        JLabel lblExpEst = new JLabel("Experiencia estratégica:");
        JTextField txtExpEst = new JTextField();

        JButton btnRegistrar = new JButton("Registrar Nave");
        JLabel lblResultado = new JLabel("");

        // Add components to panel
        add(lblNombre); add(txtNombre);
        add(lblAutMax); add(txtAutMax);
        add(lblAutActual); add(txtAutActual);
        add(lblCarga); add(txtCarga);
        add(lblSensores); add(chkSensores);
        add(lblExpTec); add(txtExpTec);
        add(lblExpCien); add(txtExpCien);
        add(lblExpEst); add(txtExpEst);
        add(new JLabel()); add(btnRegistrar);
        add(lblResultado); add(new JLabel());

        // Register button logic
        btnRegistrar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                int autonomiaMaxima = Integer.parseInt(txtAutMax.getText());
                int autonomiaActual = Integer.parseInt(txtAutActual.getText());
                int capacidadCarga = Integer.parseInt(txtCarga.getText());
                boolean sensoresCientificos = chkSensores.isSelected();
                int experienciaTecnica = Integer.parseInt(txtExpTec.getText());
                int experienciaCientifica = Integer.parseInt(txtExpCien.getText());
                int experienciaEstrategica = Integer.parseInt(txtExpEst.getText());

                int experienciaTotal = experienciaTecnica + experienciaCientifica + experienciaEstrategica;

                NavesEspaciales nave = new NavesEspaciales(
                        nombre,
                        autonomiaMaxima,
                        autonomiaActual,
                        capacidadCarga,
                        sensoresCientificos,
                        experienciaTotal,
                        experienciaTecnica,
                        experienciaCientifica,
                        experienciaEstrategica
                );

                NavesEspaciales.getNaves().add(nave);
                lblResultado.setText("✅ Nave registrada exitosamente.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingresa todos los campos numéricos correctamente.");
            }
        });
    }
}
