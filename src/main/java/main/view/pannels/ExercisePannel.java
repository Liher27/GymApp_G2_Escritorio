package main.view.pannels;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
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
	private ExerciseManager exerciseManager;
	private Workout workout = null;
	private User user = null;
	private Exercise exercise1 = null;
	private DefaultTableModel exerciseTable = null;
	private JTable table;
	private List<Exercise> exercise = null;
	private JLabel lblCronometro = null;
	private JLabel lblCronometroWorkoutTime;
	private JLabel lblCronometroWorkout = null;
	private JLabel lblWorkoutlevel = null;
	private JLabel lblEjercicios = null;
	private JButton btnIniciar = null;
	private JButton btnPausar = null;
	private JButton btnParar = null;
	private JButton btnVolver = null;

	public ExercisePannel() {

		setLayout(null);
		setBounds(0, 0, 1230, 700);

		JScrollPane exerciceScrollPane = new JScrollPane();
		exerciceScrollPane.setBounds(275, 202, 680, 296);
		add(exerciceScrollPane);

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
					StatusSingleton.getInstance().setExercise(exercise.get(table.getSelectedRow()));

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		lblCronometroWorkoutTime = new JLabel("00:00:00");
		lblCronometroWorkoutTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblCronometroWorkoutTime.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblCronometroWorkoutTime.setBounds(36, 68, 157, 64);
		add(lblCronometroWorkoutTime);

		btnPausar = new JButton("Pausar");
		btnPausar.setBounds(117, 353, 76, 41);
		add(btnPausar);

		btnParar = new JButton("Parar");
		btnParar.setBounds(78, 404, 76, 41);
		add(btnParar);

		lblCronometroWorkout = new JLabel("Cronometro Workout");
		lblCronometroWorkout.setHorizontalAlignment(SwingConstants.CENTER);
		lblCronometroWorkout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCronometroWorkout.setBounds(36, 22, 157, 46);
		add(lblCronometroWorkout);

		exerciceScrollPane.setViewportView(table);

		btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BackUpsController backUpsController = new BackUpsController();
				workout = StatusSingleton.getInstance().getWorkout();
				user = StatusSingleton.getInstance().getUser();
				exercise1 = StatusSingleton.getInstance().getExercise();
				backUpsController.userBackups(workout, user, exercise1);
			}
		});
		btnIniciar.setBounds(32, 353, 76, 40);
		add(btnIniciar);

		lblWorkoutlevel = new JLabel("WORKOUT 0,1,2,3,4");
		lblWorkoutlevel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblWorkoutlevel.setBounds(447, 131, 296, 52);
		add(lblWorkoutlevel);

		lblEjercicios = new JLabel("EJERCICIOS");
		lblEjercicios.setFont(new Font("Tahoma", Font.PLAIN, 38));
		lblEjercicios.setBounds(496, 56, 207, 64);
		add(lblEjercicios);

		btnVolver = new JButton("VOLVER ");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatusSingleton.getInstance().changeToWorkoutsPannel();
			}
		});
		btnVolver.setBounds(1015, 594, 157, 64);
		add(btnVolver);
		
		JLabel lblCronometroExercise = new JLabel("Cronometro Ejercicio");
		lblCronometroExercise.setHorizontalAlignment(SwingConstants.CENTER);
		lblCronometroExercise.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCronometroExercise.setBounds(36, 254, 157, 19);
		add(lblCronometroExercise);
		
		JLabel lblCronometroExerciseTime = new JLabel("00:00:00");
		lblCronometroExerciseTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblCronometroExerciseTime.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblCronometroExerciseTime.setBounds(36, 284, 157, 64);
		add(lblCronometroExerciseTime);
		
		JLabel lblCronometroRest = new JLabel("Cronometro Descanso");
		lblCronometroRest.setHorizontalAlignment(SwingConstants.CENTER);
		lblCronometroRest.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCronometroRest.setBounds(36, 496, 157, 46);
		add(lblCronometroRest);
		
		JLabel lblCronometroRestTime = new JLabel("00:00:00");
		lblCronometroRestTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblCronometroRestTime.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblCronometroRestTime.setBounds(36, 535, 157, 64);
		add(lblCronometroRestTime);
		
		JButton btnIniciar_1 = new JButton("Iniciar");
		btnIniciar_1.setBounds(36, 146, 76, 40);
		add(btnIniciar_1);
		
		JButton btnPausar_1 = new JButton("Pausar");
		btnPausar_1.setBounds(117, 146, 76, 41);
		add(btnPausar_1);
		
		JButton btnParar_1 = new JButton("Parar");
		btnParar_1.setBounds(78, 197, 76, 41);
		add(btnParar_1);
		this.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				exerciseTable.setRowCount(0);
				try {
					String id = StatusSingleton.getInstance().getWorkout().getWorkoutUID();
					exercise = exerciseManager.getExercisesForWorkout(id);
					fillExercisePanel(exerciseTable, exercise);
				} catch (Exception e1) {
					e1.printStackTrace();
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
			throw e;
		}

	}
}
