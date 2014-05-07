package es.unileon.ulebank.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class InvestmentsWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public InvestmentsWindow() {
		JTable tableMovements = new JTable(new DefaultTableModel(
				new Object[][] {
						{ "Ejemplo1", "Ejemplo1", "Ejemplo1", "Ejemplo1" },
						{ "Ejemplo2", "Ejemplo2", "Ejemplo2", "Ejemplo2" },
						{ "Ejemplo3", "Ejemplo3", "Ejemplo3", "Ejemplo3" },
						{ "Ejemplo4", "Ejemplo4", "Ejemplo4", "Ejemplo4" },
						{ "Ejemplo5", "Ejemplo5", "Ejemplo5", "Ejemplo5" },
						{ "Ejemplo6", "Ejemplo6", "Ejemplo6", "Ejemplo6" },
						{ "Ejemplo7", "Ejemplo7", "Ejemplo7", "Ejemplo7" },
						{ "Ejemplo8", "Ejemplo8", "Ejemplo8", "Ejemplo8" }, },
				new String[] { "Fecha", "Empresa", "Número de acciones",
						"Precio" }));
		tableMovements.setSurrendersFocusOnKeystroke(true);
		tableMovements.setColumnSelectionAllowed(true);
		tableMovements.setCellSelectionEnabled(true);
		tableMovements.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableMovements.getColumnModel().getColumn(1).setPreferredWidth(180);

		tableMovements.setPreferredScrollableViewportSize(new Dimension(250,
				100));
		JScrollPane scrollPane = new JScrollPane(tableMovements);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		setSize(800, 400);
	}
}
