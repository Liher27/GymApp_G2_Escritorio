package main.view.pannels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.controller.BackUpsController;
import main.controller.ExerciseController;
import main.controller.UserController;
import main.manager.StatusSingleton;
import main.manager.pojo.Exercise;
import main.manager.pojo.Historic;
import main.manager.pojo.User;
import main.manager.pojo.Workout;
import main.threads.ExerciseThread;
import main.threads.ExercisesTimerManage;
import main.threads.WorkoutThread;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExercisePannel extends JPanel {
	private static final long serialVersionUID = 1L;

	private DefaultTableModel exerciseTable = null;
	private JScrollPane exerciceScrollPane = null;
	private JTable table;
	private JLabel serieCronoLbl;
	private JLabel serieTimeLbl;
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
	private ExerciseThread serieCro = null;
	private ExercisesTimerManage exerciseCro = null;
	private JLabel countDown = null;
	private BackUpsController backUpsController;
	private UserController userController;
	private JLabel restTimeCronoLbl_1 = null;
	private JLabel CronometroEjercicio = null;
	private JLabel exerciseTimerCrono = null;
	

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
				if(serieCro != null && exerciseCro != null) {
				exerciseCro.pauseTime();
				serieCro.pauseTime();
				}

			}
		});

		endBtn = new JButton("Parar");
		endBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(workoutCro != null && exerciseCro != null && serieCro !=null) {
					workoutCro.interrupt();;
					exerciseCro.interrupt();
					serieCro.interrupt();
					}else {
						JOptionPane.showMessageDialog(null, "nigun ejercicio esta en curso");
					}
					workout = StatusSingleton.getInstance().getWorkout();
					user = StatusSingleton.getInstance().getUser();
					historic = workoutCro.getLastWorkoutInfo();
					backUpsController.userBackups(workout, user, historic);
					userController.insertWorkoutHistory(historic, user);
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
				workoutCro.stopTimer();
				if(exerciseCro != null) {
					exerciseCro.interrupt();
				}
			}

		});
		exercisesBackButton.setBounds(1045, 615, 160, 34);
		add(exercisesBackButton);

		seriesStartBtn = new JButton("Iniciar");
		seriesStartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runSerieCrono();

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

		serieCronoLbl = new JLabel("00:00:00");
		serieCronoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		serieCronoLbl.setForeground(Color.WHITE);
		serieCronoLbl.setFont(new Font("Tahoma", Font.PLAIN, 36));
		serieCronoLbl.setBounds(971, 319, 223, 64);
		add(serieCronoLbl);

		serieTimeLbl = new JLabel("Cronometro Serie");
		serieTimeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		serieTimeLbl.setForeground(Color.WHITE);

		serieTimeLbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		serieTimeLbl.setBounds(1004, 276, 157, 46);
		add(serieTimeLbl);

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
		
		JLabel lblTiempoUtilizado = new JLabel("Tiempo utilizado:");
		lblTiempoUtilizado.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiempoUtilizado.setForeground(Color.WHITE);
		lblTiempoUtilizado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTiempoUtilizado.setBounds(290, 544, 189, 46);
		add(lblTiempoUtilizado);
		
		restTimeCronoLbl_1 = new JLabel("0");
		restTimeCronoLbl_1.setHorizontalAlignment(SwingConstants.CENTER);
		restTimeCronoLbl_1.setForeground(Color.WHITE);
		restTimeCronoLbl_1.setFont(new Font("Tahoma", Font.PLAIN, 36));
		restTimeCronoLbl_1.setBounds(300, 590, 157, 64);
		add(restTimeCronoLbl_1);
		
		CronometroEjercicio = new JLabel("Cronometro Ejercicio");
		CronometroEjercicio.setHorizontalAlignment(SwingConstants.CENTER);
		CronometroEjercicio.setForeground(Color.WHITE);
		CronometroEjercicio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CronometroEjercicio.setBounds(787, 516, 157, 46);
		add(CronometroEjercicio);
		
		exerciseTimerCrono = new JLabel("00:00:00");
		exerciseTimerCrono.setHorizontalAlignment(SwingConstants.CENTER);
		exerciseTimerCrono.setForeground(Color.WHITE);
		exerciseTimerCrono.setFont(new Font("Tahoma", Font.PLAIN, 36));
		exerciseTimerCrono.setBounds(759, 560, 223, 64);
		add(exerciseTimerCrono);
		
		JButton exerciseStartBtn = new JButton("Iniciar");
		exerciseStartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runExerciseCrono();
			}
		});
		exerciseStartBtn.setBounds(787, 621, 76, 40);
		add(exerciseStartBtn);
		
		JButton exercisePauseBtn = new JButton("Pausar");
		exercisePauseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exerciseCro.pauseTime();
			}
		});
		exercisePauseBtn.setBounds(889, 621, 76, 41);
		add(exercisePauseBtn);
		this.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				exerciseTable.setRowCount(0);
				if (!StatusSingleton.getInstance().offline) {
					try {
						runWorkoutCrono();
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
			}  public void componentHidden(ComponentEvent e) {
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

	public void loadSerieTime(String time) {
		serieCronoLbl.setText(time);
	}

	public void loadExerciseNameAndSerie(String exerciseName, int serieNumber) {
		lblExerciseName.setText(exerciseName);
		exerciseNumber.setText(String.valueOf("Repeticiones: " + serieNumber));
	}

	private void runSerieCrono() {
		
			serieCro = new ExerciseThread("SerieTimer", exercises, this, workoutCro);
			serieCro.start();
		
	}
	
	private void runExerciseCrono() {
		exerciseCro = new ExercisesTimerManage("ExerciseTimer",this);
		
		exerciseCro.start();
	}
	
	
	public void loadExerciseTime(String time) {
		exerciseTimerCrono.setText(time);
	}

	public void resetExerciseTime() {
		serieCronoLbl.setText("00:00:00");

	}

	public void setCountDown(int seconds) {
		countDown.setText("Cuenta atras: " + seconds);
	}

	// Workout crono
	
	public void loadWorkoutTime(String time) {
		workoutCronoLbl.setText(time);
	}

	private void runWorkoutCrono() {
		
			workoutCro = new WorkoutThread("WorkoutTimer", exercises, this, serieCro);
			workoutCro.start();
		
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
	public void userUsedRestTime(int restTime) {
		restTimeCronoLbl_1.setText(""+restTime);
	}
}
