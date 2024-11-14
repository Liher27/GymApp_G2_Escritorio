package main.view.pannels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import main.controller.BackUpsController;
import main.controller.ExerciseController;
import main.controller.UserController;
import main.manager.StatusSingleton;
import main.manager.UserManager;
import main.manager.pojo.Exercise;
import main.manager.pojo.Historic;
import main.manager.pojo.User;
import main.manager.pojo.Workout;
import main.threads.ExerciseThread;
import main.threads.WorkoutThread;

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
	private JLabel exerciseNumber = null;
	private JButton workoutStartBtn = null;
	private JButton workoutPauseBtn = null;
	private JButton endBtn = null;
	private JButton exercisesBackButton = null;
	private JButton seriesStartBtn = null;
	private JButton seriesPauseBtn = null;
	private Workout workout = null;
	private User user = null;
	private Historic historic = null;
	private List<Exercise> exercises = null;
	private ExerciseController exerciseController = null;

	private WorkoutThread workoutCro = null;
	private ExerciseThread exerciseCro = null;
	private JLabel countDown = null;
	private BackUpsController backUpsController;
	private UserController userController;
	

	public ExercisePannel() {
		backUpsController = new BackUpsController();
		userController = new UserController();
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
			exerciseController = new ExerciseController();
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
			JOptionPane.showMessageDialog(null, "No se ha podido cargar la informacion...", "Error!",
					JOptionPane.ERROR_MESSAGE);
		}
		workoutCronoLbl = new JLabel("00:00:00");
		workoutCronoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		workoutCronoLbl.setFont(new Font("Tahoma", Font.PLAIN, 36));
		workoutCronoLbl.setForeground(Color.white);
		workoutCronoLbl.setBounds(38, 319, 228, 64);
		add(workoutCronoLbl);

		workoutPauseBtn = new JButton("Pausar");
		workoutPauseBtn.setBounds(160, 392, 76, 41);
		add(workoutPauseBtn);
		workoutPauseBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				workoutCro.pauseTime();

			}
		});

		endBtn = new JButton("Parar");
		endBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					workoutCro.stopTimer();
					workout = StatusSingleton.getInstance().getWorkout();
					user = StatusSingleton.getInstance().getUser();
					historic = workoutCro.getLastWorkoutInfo();
					backUpsController.userBackups(workout, user, historic);
					userController.insertWorkoutHistory(historic, user);
				} catch (ParserConfigurationException | SAXException | IOException | TransformerException e1) {
					e1.printStackTrace();
				}
			}
		});
		endBtn.setBounds(38, 615, 160, 34);
		add(endBtn);

		workoutTimeLbl = new JLabel("Cronometro Workout");
		workoutTimeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		workoutTimeLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		workoutTimeLbl.setBounds(74, 276, 157, 46);
		workoutTimeLbl.setForeground(Color.white);
		add(workoutTimeLbl);

		exerciceScrollPane.setViewportView(table);

		workoutStartBtn = new JButton("Iniciar");
		workoutStartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runWorkoutCrono();
//				runExerciseCrono();
			}

		});
		workoutStartBtn.setBounds(74, 392, 76, 40);
		add(workoutStartBtn);

		lblExerciseName = new JLabel("SERIE ...");
		lblExerciseName.setHorizontalAlignment(SwingConstants.CENTER);
		lblExerciseName.setForeground(new Color(255, 193, 7));
		lblExerciseName.setFont(new Font("Segoe UI Semibold", Font.BOLD, 47));
		lblExerciseName.setBounds(319, 130, 576, 72);
		add(lblExerciseName);

		lblWorkoutName = new JLabel("WORKOUTNAME");
		lblWorkoutName.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorkoutName.setForeground(new Color(255, 193, 7));
		lblWorkoutName.setFont(new Font("Segoe UI Semibold", Font.BOLD, 64));
		lblWorkoutName.setBounds(324, 11, 532, 108);
		add(lblWorkoutName);

		exercisesBackButton = new JButton("Volver");
		exercisesBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatusSingleton.getInstance().changeToWorkoutsPannel();
			}

		});
		exercisesBackButton.setBounds(1045, 615, 160, 34);
		add(exercisesBackButton);

		seriesStartBtn = new JButton("Iniciar");
		seriesStartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runExerciseCrono();

			}
		});
		seriesStartBtn.setBounds(999, 392, 76, 40);
		add(seriesStartBtn);

		seriesPauseBtn = new JButton("Pausar");
		seriesPauseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exerciseCro.pauseTime();
			}
		});
		seriesPauseBtn.setBounds(1085, 392, 76, 41);
		add(seriesPauseBtn);

		exerciseCronoLbl = new JLabel("00:00:00");
		exerciseCronoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		exerciseCronoLbl.setForeground(Color.WHITE);
		exerciseCronoLbl.setFont(new Font("Tahoma", Font.PLAIN, 36));
		exerciseCronoLbl.setBounds(971, 319, 223, 64);
		add(exerciseCronoLbl);

		exerciseTimeLbl = new JLabel("Cronometro Serie");
		exerciseTimeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		exerciseTimeLbl.setForeground(Color.WHITE);

		exerciseTimeLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		exerciseTimeLbl.setBounds(1004, 276, 157, 46);
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

		exerciseNumber = new JLabel("");
		exerciseNumber.setHorizontalAlignment(SwingConstants.CENTER);
		exerciseNumber.setForeground(new Color(255, 193, 7));
		exerciseNumber.setFont(new Font("Segoe UI Semibold", Font.BOLD, 27));
		exerciseNumber.setBounds(905, 197, 331, 56);
		add(exerciseNumber);

		countDown = new JLabel("");
		countDown.setHorizontalAlignment(SwingConstants.CENTER);
		countDown.setForeground(new Color(255, 193, 7));
		countDown.setFont(new Font("Segoe UI Semibold", Font.BOLD, 27));
		countDown.setBounds(905, 130, 331, 56);
		add(countDown);
		this.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				exerciseTable.setRowCount(0);
				if (!StatusSingleton.getInstance().offline) {
					try {
						String id = StatusSingleton.getInstance().getWorkout().getWorkoutUID();
						exercises = exerciseController.getExercisesForWorkout(id);
						lblWorkoutName
								.setText("Workout: " + StatusSingleton.getInstance().getWorkout().getWorkoutName());
						fillExercisePanel(exerciseTable, exercises);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, null, "No se ha podido cargar la informacion...",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					exercises = StatusSingleton.getInstance().getBackupedWorkouts()
							.get(StatusSingleton.getInstance().getSelectedRow()).getExercises();
					fillExercisePanel(exerciseTable, exercises);

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

	// Exercise crono

	public void loadExerciseTime(String time) {
		exerciseCronoLbl.setText(time);
	}

	public void loadExerciseNameAndSerie(String exerciseName, int serieNumber) {
		lblExerciseName.setText(exerciseName);
		exerciseNumber.setText(String.valueOf("Repeticiones: " + serieNumber));
	}

	private void runExerciseCrono() {
		if (exerciseCro == null || !exerciseCro.isAlive()) {
			exerciseCro = new ExerciseThread("ExerciseTimer", exercises, this, workoutCro);
			exerciseCro.start();
		} else {
			exerciseCro.resumeTimer();
		}
	}

	public void resetExerciseTime() {
		exerciseCronoLbl.setText("00:00:00");

	}

	public void setCountDown(int seconds) {
		countDown.setText("Cuenta atras: " + seconds);
	}

	// Workout crono
	
	public void loadWorkoutTime(String time) {
		workoutCronoLbl.setText(time);
	}

	private void runWorkoutCrono() {
		if (workoutCro == null || !workoutCro.isAlive()) {
			workoutCro = new WorkoutThread("WorkoutTimer", exercises, this, exerciseCro);
			workoutCro.start();
		} else {
			workoutCro.resumeTimer();
		}
	}

	public void resetWorkoutTime() {
		workoutCronoLbl.setText("00:00:00");
	}

	// Rest Crono
	
	public void loadRestTime(int time) {
		restTimeCronoLbl.setText("00:00:" + time);
	}

	public void resetRestTimer() {
		restTimeCronoLbl.setText("00:00:00");
	}
}
