package main.view.pannels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import main.manager.ThreadsManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.controller.BackUpsController;
import main.manager.ExerciseManager;
import main.manager.StatusSingleton;
import main.manager.pojo.Exercise;
import main.manager.pojo.User;
import main.manager.pojo.Workout;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExercisePannel extends JPanel {
	private static final long serialVersionUID = 1L;

	private DefaultTableModel exerciseTable = null;
	private JScrollPane exerciceScrollPane = null;
	private JTable table;

	private JLabel exerciseCronoLbl = null;
	private JLabel exerciseTimeLbl = null;
	private JLabel restTimeCronoLbl = null;
	private JLabel restTimeLbl = null;
	private JLabel logoImage = null;
	private JLabel workoutCronoLbl;
	private JLabel workoutTimeLbl = null;
	private JLabel lblExerciseName = null;
	private JLabel lblWorkoutName = null;

	private JButton workoutStartBtn = null;
	private JButton workoutPauseBtn = null;
	private JButton endBtn = null;
	private JButton exercisesBackButton = null;
	private JButton seriesStartBtn = null;
	private JButton seriesPauseBtn = null;

	private ExerciseManager exerciseManager;
	private Workout workout = null;
	private User user = null;
	private Exercise exercise1 = null;
	private List<Exercise> exercises = null;

	private ThreadsManager workoutCro = null;

	public ExercisePannel() {

		setLayout(null);
		setBounds(0, 0, 1230, 700);
		setBackground(new Color(47, 47, 47));

		exerciceScrollPane = new JScrollPane();
		exerciceScrollPane.setBounds(319, 233, 576, 272);
		add(exerciceScrollPane);

		logoImage = new JLabel();
		logoImage.setIcon(new ImageIcon("src/main/resources/logo.png"));
		logoImage.setBounds(38, 27, 112, 112);
		add(logoImage);

		try {
			exerciseManager = new ExerciseManager();
			exerciseTable = new DefaultTableModel();
			exerciseTable.addColumn("Exercise Name");
			exerciseTable.addColumn("Image");
			exerciseTable.addColumn("restTime");
			exerciseTable.addColumn("seriesNumber");
			table = new JTable(exerciseTable);

			table.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					StatusSingleton.getInstance().setExercise(exercises.get(table.getSelectedRow()));

				}
			});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, null, "No se ha podido cargar la informacion...",
					JOptionPane.ERROR_MESSAGE);
		}
		workoutCronoLbl = new JLabel("00:00:00");
		workoutCronoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		workoutCronoLbl.setFont(new Font("Tahoma", Font.PLAIN, 36));
		workoutCronoLbl.setForeground(Color.white);
		workoutCronoLbl.setBounds(38, 290, 271, 64);
		add(workoutCronoLbl);

		workoutPauseBtn = new JButton("Pausar");
		workoutPauseBtn.setBounds(164, 361, 76, 41);
		add(workoutPauseBtn);
		workoutPauseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				workoutCro.pauseTime();
				
			}
		});

		endBtn = new JButton("Parar");
		endBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				workoutCro.stopTimer();
				
			}
		});
		endBtn.setBounds(38, 615, 160, 34);
		add(endBtn);

		workoutTimeLbl = new JLabel("Cronometro Workout");
		workoutTimeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		workoutTimeLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		workoutTimeLbl.setBounds(78, 233, 157, 46);
		workoutTimeLbl.setForeground(Color.white);
		add(workoutTimeLbl);

		exerciceScrollPane.setViewportView(table);

		workoutStartBtn = new JButton("Iniciar");
		workoutStartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				BackUpsController backUpsController = new BackUpsController();
//				workout = StatusSingleton.getInstance().getWorkout();
//				user = StatusSingleton.getInstance().getUser();
//				exercise1 = StatusSingleton.getInstance().getExercise();
//				backUpsController.userBackups(workout, user, exercise1);

				runCronometro();
			}

		});
		workoutStartBtn.setBounds(64, 361, 76, 40);
		add(workoutStartBtn);

		lblExerciseName = new JLabel("SERIE ...");
		lblExerciseName.setHorizontalAlignment(SwingConstants.CENTER);
		lblExerciseName.setForeground(new Color(255, 193, 7));
		lblExerciseName.setFont(new Font("Segoe UI Semibold", Font.BOLD, 47));
		lblExerciseName.setBounds(319, 146, 576, 56);
		add(lblExerciseName);

		lblWorkoutName = new JLabel("WORKOUTNAME");
		lblWorkoutName.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorkoutName.setForeground(new Color(255, 193, 7));
		lblWorkoutName.setFont(new Font("Segoe UI Semibold", Font.BOLD, 64));
		lblWorkoutName.setBounds(324, 27, 532, 56);
		add(lblWorkoutName);

		exercisesBackButton = new JButton("Volver");
		exercisesBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTable();
				StatusSingleton.getInstance().changeToWorkoutsPannel();
			}

		});
		exercisesBackButton.setBounds(1045, 615, 160, 34);
		add(exercisesBackButton);

		seriesStartBtn = new JButton("Iniciar");
		seriesStartBtn.setBounds(995, 361, 76, 40);
		add(seriesStartBtn);

		seriesPauseBtn = new JButton("Pausar");
		seriesPauseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				workoutCronoLbl.setText("00:00:00");
			}
		});
		seriesPauseBtn.setBounds(1093, 361, 76, 41);
		add(seriesPauseBtn);

		exerciseCronoLbl = new JLabel("00:00:00");
		exerciseCronoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		exerciseCronoLbl.setForeground(Color.WHITE);
		exerciseCronoLbl.setFont(new Font("Tahoma", Font.PLAIN, 36));
		exerciseCronoLbl.setBounds(995, 286, 174, 64);
		add(exerciseCronoLbl);

		exerciseTimeLbl = new JLabel("Cronometro Serie");
		exerciseTimeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		exerciseTimeLbl.setForeground(Color.WHITE);
		exerciseTimeLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		exerciseTimeLbl.setBounds(998, 240, 157, 46);
		add(exerciseTimeLbl);

		restTimeCronoLbl = new JLabel("00:00:00");
		restTimeCronoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		restTimeCronoLbl.setForeground(Color.WHITE);
		restTimeCronoLbl.setFont(new Font("Tahoma", Font.PLAIN, 36));
		restTimeCronoLbl.setBounds(529, 590, 157, 64);
		add(restTimeCronoLbl);

		restTimeLbl = new JLabel("Cronometro de descanso");
		restTimeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		restTimeLbl.setForeground(Color.WHITE);
		restTimeLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		restTimeLbl.setBounds(513, 544, 189, 46);
		add(restTimeLbl);
		this.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				exerciseTable.setRowCount(0);
				try {
					String id = StatusSingleton.getInstance().getWorkout().getWorkoutUID();
					exercises = exerciseManager.getExercisesForWorkout(id);
					fillExercisePanel(exerciseTable, exercises);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, null, "No se ha podido cargar la informacion...",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}

	private void fillExercisePanel(DefaultTableModel exerciseTable, List<Exercise> exercises) {
		try {
			if (exerciseTable.getRowCount() == 0) {
				for (Exercise exercise : exercises) {
					Object[] linea = { exercise.getExerciseName(), exercise.getImage(), exercise.getRestTime(),
							exercise.getSeriesNumber() };
					exerciseTable.addRow(linea);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, null, "No se ha podido cargar la informacion de la tabla...",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void loadTime(String time) {
		workoutCronoLbl.setText(time);
	}
	
		
	
	public void changeButtonText() {
		workoutPauseBtn.setText("Iniciar");
	}
	public void changeButtonTextTo() {
		workoutPauseBtn.setText("Pausar");
	}

	private void runCronometro() {
		if (workoutCro == null || !workoutCro.isAlive()) {
			workoutCro = new ThreadsManager("WorkoutTimer", this);
			workoutCro.start();
		} else {
			workoutCro.resumeTimer();
		}
	}
	public void resetTimerFiled() {
		workoutCronoLbl.setText("00:00:00");
	}

	private void clearTable() {
	}
}
