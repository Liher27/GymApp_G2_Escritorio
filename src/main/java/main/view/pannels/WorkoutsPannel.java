package main.view.pannels;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.manager.WorkoutManager;
import main.manager.pojo.Workout;

public class WorkoutsPannel extends JPanel {
	
	private WorkoutManager workoutManager;
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	private List<Workout> workout = null;

	public WorkoutsPannel() {

		setLayout(null);
		setBounds(100, 100, 1215, 666);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 243, 576, 199);
		add(scrollPane);
		
		try {
			workoutManager = new WorkoutManager();
			tableModel = new DefaultTableModel();
			tableModel.addColumn("Workout Name");
			tableModel.addColumn("Exercice Number");
			tableModel.addColumn("Level");
			tableModel.addColumn("Video");
			table = new JTable(tableModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		scrollPane.setViewportView(table);
		
		this.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				try {
					
					workout = workoutManager.getAll();
					fillWorkoutPanel(tableModel,workout);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
	}
		
	private void fillWorkoutPanel(DefaultTableModel workoutTable, List<Workout> workout) {
		try {
			workout = new ArrayList<Workout>();
			if (tableModel.getRowCount() == 0) {
				for (Workout workouts: workout) {
					Object[] linea = { workouts.getWorkoutName(), workouts.getExerciseNumber(), workouts.getWorkoutLvl(),
							workouts.getVideoURL()};

					workoutTable.addRow(linea);

				}
			}

		} catch (Exception e) {
			throw e;
		}

	}
	public JPanel getWorkoutsPannel() {
		return this;
	}

}
