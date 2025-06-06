package com.example.proyecto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MostrarEstadoNavesUI extends JPanel {

    public MostrarEstadoNavesUI() {
        setLayout(new BorderLayout());

        // Define table columns
        String[] columnNames = {"Nombre de Nave", "Total", "Científica", "Técnica", "Estratégica"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Fill table rows
        for (NavesEspaciales nave : NavesEspaciales.getNaves()) {
            Object[] row = {
                    nave.getNombre(),
                    nave.getExperienciaTotal(),
                    nave.getExperienciaCientifica(),
                    nave.getExperienciaTecnica(),
                    nave.getExperienciaEstrategica()
            };
            model.addRow(row);
        }

        // Table and scroll
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add to panel
        add(scrollPane, BorderLayout.CENTER);
    }
}
