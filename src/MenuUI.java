import javax.swing.*;
import java.awt.*;

public class MenuUI {

    private static JPanel contentPanel;

    public static void main(String[] args) {
        // Main frame
        JFrame frame = new JFrame("🚀 Simulador Espacial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null); // center
        frame.setIconImage(new ImageIcon("icon.png").getImage());

        // Sidebar panel
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(7, 1, 10, 10));
        sidebar.setPreferredSize(new Dimension(200, 0));

        JButton btn1 = new JButton("Registrar Misión");
        JButton btn2 = new JButton("Registrar Nave");
        JButton btn3 = new JButton("Simular un Ciclo");
        JButton btn4 = new JButton("Mostrar Estado General");
        JButton btn5 = new JButton("Buscar Misión");
        JButton btn6 = new JButton("Ranking de Naves");
        JButton btn7 = new JButton("Salir");

        sidebar.add(btn1);
        sidebar.add(btn2);
        sidebar.add(btn3);
        sidebar.add(btn4);
        sidebar.add(btn5);
        sidebar.add(btn6);
        sidebar.add(btn7);

        // Right content panel (initial welcome message)
        contentPanel = new JPanel(new BorderLayout());
        JLabel welcome = new JLabel("Bienvenido al Simulador Espacial", SwingConstants.CENTER);
        welcome.setFont(new Font("Arial", Font.BOLD, 24));
        contentPanel.add(welcome, BorderLayout.CENTER);

        // Add event listeners
        btn1.addActionListener(e -> showContent(new RegistrarMisionUI()));
        btn2.addActionListener(e -> showContent(new RegistrarNaveUI()));
        btn3.addActionListener(e -> showMessage("Simulando ciclo..."));
        btn4.addActionListener(e -> showContent(new MostrarEstadoNavesUI()));
        btn5.addActionListener(e -> showMessage("Buscar misión - Pantalla en desarrollo"));
        btn6.addActionListener(e -> showMessage("Ranking de naves - Pantalla en desarrollo"));
        btn7.addActionListener(e -> System.exit(0));

        // Split pane (Sidebar + Content)
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebar, contentPanel);
        splitPane.setDividerLocation(200);
        frame.add(splitPane);

        frame.setVisible(true);
    }

    // Replace contentPanel with new panel
    public static void showContent(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Show text message panel
    public static void showMessage(String message) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(label, BorderLayout.CENTER);
        showContent(panel);
    }
}
