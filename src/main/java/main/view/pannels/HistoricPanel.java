package main.view.pannels;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.controller.BackUpsController;
import main.manager.StatusSingleton;
import main.manager.pojo.Historic;
import main.manager.pojo.User;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class HistoricPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTable table;
	private DefaultTableModel historicTable = null;
	private JButton historicBackBtn = null;

	private JLabel historicTitle = null;
	private JScrollPane historyScrollPane = null;

	private User userProfile = null;
	private List<Historic> historicList = null;

	private BackUpsController backUpsController = null;

	private JLabel logoImage = null;

	public HistoricPanel() {
		backUpsController = new BackUpsController();
		setBackground(new Color(48, 48, 48));
		setBounds(0, 0, 1230, 700);
		setLayout(null);

		logoImage = new JLabel();
		logoImage.setIcon(new ImageIcon("src/main/resources/logo.png"));
		logoImage.setBounds(38, 27, 112, 112);
		add(logoImage);

		historicTitle = new JLabel("Historial de entrenamientos");
		historicTitle.setForeground(new Color(255, 193, 7));
		historicTitle.setHorizontalAlignment(SwingConstants.CENTER);
		historicTitle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 60));
		historicTitle.setBounds(253, 61, 801, 55);
		add(historicTitle);

		historyScrollPane = new JScrollPane();
		historyScrollPane.setBounds(319, 233, 576, 199);
		add(historyScrollPane);

		historicTable = new DefaultTableModel();
		historicTable.addColumn("WorkoutNombre");
		historicTable.addColumn("Nivel");
		historicTable.addColumn("TiempoTotal");
		historicTable.addColumn("TiempoPrevisto");
		historicTable.addColumn("Fecha");
		historicTable.addColumn("Porcentaje");

		table = new JTable(historicTable);

		historyScrollPane.setViewportView(table);

		historicBackBtn = new JButton("Volver");
		historicBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatusSingleton.getInstance().changeToWorkoutsPannel();
			}
		});
		historicBackBtn.setBounds(512, 498, 186, 55);
		add(historicBackBtn);

		this.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				historicTable.setRowCount(0);

				historicList = new ArrayList<>();
				userProfile = StatusSingleton.getInstance().getUser();
				historicList = backUpsController.getBackUpsList(userProfile);
				fillExercisePanel(historicTable, historicList);

			}
		});
	}

	private void fillExercisePanel(DefaultTableModel historicTable, List<Historic> historics) {
		historicTable.setRowCount(0);
		for (int j = 0; j < historics.size(); j++) {
			SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
			String formattedDate = targetFormat.format(historics.get(j).getFinishDate());
			Object[] line = { historics.get(j).getWorkoutName(), historics.get(j).getLevel(),
					format(historics.get(j).getTotalTime()), format(historics.get(j).getProvidedTime()), formattedDate,
					historics.get(j).getExercisePercent() };
			historicTable.addRow(line);
		}
	}

	private String format(int elapsed) {
		int hour, minute, second;

		second = (elapsed % 60);
		elapsed = elapsed / 60;

		minute = (elapsed % 60);
		elapsed = elapsed / 60;

		hour = (elapsed % 60);

		return String.format("%02d:%02d:%02d", hour, minute, second);
	}
}