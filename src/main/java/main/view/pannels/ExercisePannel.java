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

import main.manager.ExerciseManager;
import main.manager.StatusSingleton;
import main.manager.WorkoutManager;
import main.manager.pojo.Exercise;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExercisePannel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ExerciseManager exerciseManager;
	private WorkoutManager workoutManager;
	private DefaultTableModel exerciseTable = null;
	private JTable table;
	private List<Exercise> exercise = null;
	private JLabel lblCronometro = null;
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
		exerciceScrollPane.setBounds(319, 233, 576, 199);
		add(exerciceScrollPane);

		lblCronometro = new JLabel("00:00:00");
		lblCronometro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCronometro.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblCronometro.setBounds(529, 593, 157, 64);
		add(lblCronometro);

		btnPausar = new JButton("Pausar");
		btnPausar.setBounds(529, 443, 157, 64);
		add(btnPausar);

		btnIniciar = new JButton("Iniciar");
		btnIniciar.setBounds(316, 443, 157, 64);
		add(btnIniciar);

		btnParar = new JButton("Parar");
		btnParar.setBounds(738, 443, 157, 64);
		add(btnParar);

		lblCronometroWorkout = new JLabel("Cronometro Workout");
		lblCronometroWorkout.setHorizontalAlignment(SwingConstants.CENTER);
		lblCronometroWorkout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCronometroWorkout.setBounds(529, 536, 157, 46);
		add(lblCronometroWorkout);

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

		lblWorkoutlevel = new JLabel("WORKOUT 0,1,2,3,4");
		lblWorkoutlevel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblWorkoutlevel.setBounds(459, 153, 296, 52);
		add(lblWorkoutlevel);

		lblEjercicios = new JLabel("EJERCICIOS");
		lblEjercicios.setFont(new Font("Tahoma", Font.PLAIN, 38));
		lblEjercicios.setBounds(504, 62, 207, 64);
		add(lblEjercicios);

		btnVolver = new JButton("VOLVER ");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				StatusSingleton.getInstance().changeToWorkoutsPannel();
			}

		});
		btnVolver.setBounds(1063, 625, 157, 64);
		add(btnVolver);
		this.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
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

	private void limpiarTabla() {
		// TODO Auto-generated method stub
	}
}
