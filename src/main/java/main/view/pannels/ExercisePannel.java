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
import main.manager.pojo.Exercise;
import main.manager.pojo.User;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExercisePannel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ExerciseManager exerciseManager;
	private DefaultTableModel exerciseTable = null;
	private JTable table;
	private List<Exercise> exercise = null;
	private User userLevel = null;

	public ExercisePannel() {
		setLayout(null);
		setBounds(100, 100, 1230, 700);

		JScrollPane exerciceScrollPane = new JScrollPane();
		exerciceScrollPane.setBounds(319, 233, 576, 199);
		add(exerciceScrollPane);

		JLabel lblCronometro = new JLabel("00:00:00");
		lblCronometro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCronometro.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblCronometro.setBounds(529, 593, 157, 64);
		add(lblCronometro);

		JButton btnPausar = new JButton("Pausar");
		btnPausar.setBounds(529, 443, 157, 64);
		add(btnPausar);

		JButton btnNewButton_1_1 = new JButton("Iniciar");
		btnNewButton_1_1.setBounds(316, 443, 157, 64);
		add(btnNewButton_1_1);

		JButton btnNewButton_1_1_1 = new JButton("Parar");
		btnNewButton_1_1_1.setBounds(738, 443, 157, 64);
		add(btnNewButton_1_1_1);

		JLabel lblNewLabel = new JLabel("Cronometro Workout");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(529, 536, 157, 46);
		add(lblNewLabel);

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

		JLabel lblNewLabel_1 = new JLabel("WORKOUT 0,1,2,3,4");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewLabel_1.setBounds(459, 153, 296, 52);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("EJERCICIOS");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 38));
		lblNewLabel_2.setBounds(504, 62, 207, 64);
		add(lblNewLabel_2);

		JButton btnNewButton_1_1_1_1 = new JButton("VOLVER ");
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				StatusSingleton.getInstance().changeToWorkoutsPannel();
			}

		});
		btnNewButton_1_1_1_1.setBounds(1063, 625, 157, 64);
		add(btnNewButton_1_1_1_1);
		this.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				try {
					userLevel = StatusSingleton.getInstance().getUser();
					int userLevelInt = userLevel.getUserLevel();
					System.out.println(userLevel.getUserLevel());
					exercise = exerciseManager.getExercisesFromWorkout(userLevelInt); 
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
					Object[] linea = { exercise.getExerciseName(), exercise.getExerciseImage(), exercise.getRest(),
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
