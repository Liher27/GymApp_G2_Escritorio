package main.view.pannels;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
	private DefaultTableModel workoutTable = null;
	private List<Workout> workout = null;

	public WorkoutsPannel() {

		setLayout(null);
		setBounds(100, 100, 1215, 666);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 243, 576, 199);
		add(scrollPane);
		
		try {
			workoutManager = new WorkoutManager();
			workoutTable = new DefaultTableModel();
			getWorkoutTable().addColumn("Workout Name");
			getWorkoutTable().addColumn("Exercice Number");
			getWorkoutTable().addColumn("Level");
			getWorkoutTable().addColumn("Video");
			table = new JTable(workoutTable);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		scrollPane.setViewportView(table);
		
		this.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				try {
					workout = workoutManager.getAll();
					fillWorkoutPanel(workoutTable, workout);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
	}
		
	private void fillWorkoutPanel(DefaultTableModel workoutTable, List<Workout> workout) {
		try {
			if (workoutTable.getRowCount() == 0) {
				for (Workout workouts: workout) {
					Object[] linea = { workouts.getWorkoutName(), workouts.getExerciseNumber(), workouts.getLevel(),
							workouts.getVideo()};

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

	public DefaultTableModel getWorkoutTable() {
		return workoutTable;
	}

	public void setWorkoutTable(DefaultTableModel workoutTable) {
		this.workoutTable = workoutTable;
	}
	
}
