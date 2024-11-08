package main.view.pannels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.controller.BackUpsController;
import main.manager.StatusSingleton;
import main.manager.pojo.Historic;
import main.manager.pojo.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HistoryPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	
	private DefaultTableModel historyTbale = null;
	private User userProfile = null;
	private List<Historic> historicList = new ArrayList<>();

	public HistoryPanel() {
		setBounds(0, 0, 1230, 700);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Entrenamiento historiar");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 64));
		lblNewLabel.setBounds(253, 61, 801, 55);
		add(lblNewLabel);

		JScrollPane historyScrollPane = new JScrollPane();
		historyScrollPane.setBounds(319, 233, 576, 199);
		add(historyScrollPane);

		historyTbale = new DefaultTableModel();
		historyTbale.addColumn("WorkoutName");
		historyTbale.addColumn("Nivel");
		historyTbale.addColumn("ExerciseNumber");
		historyTbale.addColumn("ExerciseName");
		historyTbale.addColumn("Rest");
		historyTbale.addColumn("SerieNumber");

		table = new JTable(historyTbale);

		historyScrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatusSingleton.getInstance().changeToWorkoutsPannel();
			}
		});
		btnNewButton.setBounds(512, 498, 186, 55);
		add(btnNewButton);
		
		this.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				historyTbale.setRowCount(0);
				try {
					userProfile = StatusSingleton.getInstance().getUser();
					BackUpsController backUpsController = new BackUpsController();
					historicList = backUpsController.getBackUpsList(userProfile);
					fillExercisePanel(historyTbale,historicList);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		
	}
	
	private void fillExercisePanel(DefaultTableModel historicTable, List<Historic> historic) {
		
		if(historicTable.getRowCount() == 0) {
			for(Historic historics : historic ) {
				Object[] line = {historics.getWorkoutName(),historics.getLevel(),historics.getExerciseNumber(),historics.getExerciseName()
						,historics.getRestTime(),historics.getSeriesNumber()};
				historicTable.addRow(line);
			}
		}
		
	}
	public JPanel getHistoryPanel() {
		return this;
	}
}
