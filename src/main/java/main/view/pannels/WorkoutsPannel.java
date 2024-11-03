package main.view.pannels;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.manager.ExerciseManager;
import main.manager.StatusSingleton;
import main.manager.WorkoutManager;
import main.manager.pojo.Exercise;
import main.manager.pojo.Workout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WorkoutsPannel extends JPanel {
	
	private WorkoutManager workoutManager;
	private ExerciseManager exerciseManager;
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel workoutTable = null;
	private DefaultTableModel exerciseTable = null;
	private List<Workout> workout = null;
	private List<Exercise> exercise = null;

	public WorkoutsPannel() {

		setLayout(null);
		setBounds(0, 0, 1230, 700);

		JScrollPane workoutScrollPane = new JScrollPane();
		
		workoutScrollPane.setBounds(32, 212, 576, 199);
		add(workoutScrollPane);
		
			try {
				workoutManager = new WorkoutManager();
				workoutTable = new DefaultTableModel();
				workoutTable.addColumn("Workout Name");
				workoutTable.addColumn("Exercice Number");
				workoutTable.addColumn("Level");
				workoutTable.addColumn("Video");
				table = new JTable(workoutTable);
				table.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						
					}

				});
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		workoutScrollPane.setViewportView(table);
		
		JScrollPane exerciceScrollPane = new JScrollPane();
		exerciceScrollPane.setBounds(32, 446, 576, 199);
		add(exerciceScrollPane);
		
			try {
				exerciseManager = new ExerciseManager();
				exerciseTable = new DefaultTableModel();
				exerciseTable.addColumn("Exercise Name");
				exerciseTable.addColumn("Image");
				exerciseTable.addColumn("restTime");
				exerciseTable.addColumn("seriesNumber");
				table = new JTable(exerciseTable);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		exerciceScrollPane.setViewportView(table);
		
		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatusSingleton.getInstance().changeToProfilePannel();
			}
		});
		btnPerfil.setBounds(1089, 11, 131, 64);
		add(btnPerfil);
		
		JLabel lblCronometro = new JLabel("00:00:00");
		lblCronometro.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblCronometro.setBounds(32, 50, 157, 64);
		add(lblCronometro);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(1030, 584, 157, 64);
		add(btnNewButton);
		
		JButton btnPausar = new JButton("Pausar");
		btnPausar.setBounds(745, 584, 157, 64);
		add(btnPausar);
		
		JButton btnNewButton_1_1 = new JButton("Iniciar");
		btnNewButton_1_1.setBounds(745, 449, 157, 64);
		add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Parar");
		btnNewButton_1_1_1.setBounds(1030, 449, 157, 64);
		add(btnNewButton_1_1_1);
		
		JLabel lblNewLabel = new JLabel("Cronometro Workout");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(32, 11, 157, 41);
		add(lblNewLabel);
		
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
		this.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				try {
					exercise = exerciseManager.getAll();
					fillExercisePanel(exerciseTable, exercise);
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
	private void fillExercisePanel(DefaultTableModel exerciseTable, List<Exercise> exercises) {
		try {
			if (exerciseTable.getRowCount() == 0) {
				for (Exercise exercise: exercises) {
					Object[] linea = { exercise.getExerciseName(), exercise.getExerciseImage(), exercise.getRest(),
							exercise.getSeriesNumber()};

					exerciseTable.addRow(linea);

				}
			}

		} catch (Exception e) {
			throw e;
		}

	}
	public void setWorkoutTable(DefaultTableModel workoutTable) {
		this.workoutTable = workoutTable;
	}
}
