
package main.view.pannels;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.manager.StatusSingleton;
import main.manager.WorkoutManager;
import main.manager.pojo.User;
import main.manager.pojo.Workout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class WorkoutsPannel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTable table;

	private JLabel welcomeUserLbl = null;
	private JLabel userLvlBtn = null;
	private JLabel profileBtn = null;
	private JLabel logoImage = null;
	private JLabel chooseWorkoutLbl = null;
	private JLabel appTitleLbl = null;

	private JButton exitBtn = null;
	private JButton historicBtn = null;

	private JScrollPane workoutScrollPane = null;
	private DefaultTableModel workoutTable = null;

	private WorkoutManager workoutManager = null;

	private User userProfile = null;
	private List<Workout> workouts = null;

	public WorkoutsPannel() {
		setLayout(null);
		setBackground(new Color(48, 48, 48));
		setBounds(0, 0, 1230, 700);

		logoImage = new JLabel();
		logoImage.setIcon(new ImageIcon("src/main/resources/logo.png"));
		logoImage.setBounds(28, 11, 112, 112);
		add(logoImage);

		workoutScrollPane = new JScrollPane();
		workoutScrollPane.setBounds(222, 267, 789, 279);
		add(workoutScrollPane);

		try {
			workoutManager = new WorkoutManager();
			workoutTable = new DefaultTableModel();
			workoutTable.addColumn("Workout Name");
			workoutTable.addColumn("Exercise Number");
			workoutTable.addColumn("Level");
			workoutTable.addColumn("Video");

			table = new JTable(workoutTable);
			table.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					StatusSingleton.getInstance().setWorkout(workouts.get(table.getSelectedRow()));
					StatusSingleton.getInstance().changeToExercisePannel();
				}
			});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, null, "No hay workouts...", JOptionPane.ERROR_MESSAGE);
		}

		workoutScrollPane.setViewportView(table);

		profileBtn = new JLabel();
		profileBtn.setIcon(new ImageIcon("src/main/resources/profile.png"));
		profileBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StatusSingleton.getInstance().changeToProfilePannel();
			}
		});
		profileBtn.setBounds(1087, 11, 90, 90);
		add(profileBtn);

		exitBtn = new JButton("Cerrar sesion");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, null, "Adios!!", JOptionPane.INFORMATION_MESSAGE);
				StatusSingleton.getInstance().changeToLoginPannel();
			}
		});
		exitBtn.setBounds(1069, 621, 140, 34);
		add(exitBtn);

		chooseWorkoutLbl = new JLabel("ELIGE EL WORKOUT ");
		chooseWorkoutLbl.setFont(new Font("Tahoma", Font.PLAIN, 32));
		chooseWorkoutLbl.setForeground(Color.white);
		chooseWorkoutLbl.setBounds(457, 187, 303, 40);
		add(chooseWorkoutLbl);

		welcomeUserLbl = new JLabel("BIENVENIDO:");
		welcomeUserLbl.setFont(new Font("Tahoma", Font.PLAIN, 32));
		welcomeUserLbl.setForeground(Color.white);
		welcomeUserLbl.setBounds(457, 83, 352, 40);
		add(welcomeUserLbl);

		historicBtn = new JButton("Historial");
		historicBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatusSingleton.getInstance().changeToHistoricPannel();
			}
		});
		historicBtn.setBounds(28, 621, 140, 34);
		add(historicBtn);

		userLvlBtn = new JLabel("NIVEL ACTUAL:");
		userLvlBtn.setFont(new Font("Tahoma", Font.PLAIN, 32));
		userLvlBtn.setForeground(Color.white);
		userLvlBtn.setBounds(457, 136, 352, 40);
		add(userLvlBtn);

		appTitleLbl = new JLabel("ERREKAFIT");
		appTitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		appTitleLbl.setForeground(new Color(255, 193, 7));
		appTitleLbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 64));
		appTitleLbl.setBounds(346, 11, 487, 56);
		add(appTitleLbl);

		this.addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				try {
					userProfile = StatusSingleton.getInstance().getUser();
					int userLevel = userProfile.getUserLevel();
					userLvlBtn.setText("NIVEL ACTUAL: " + userLevel);
					System.out.println(userLevel);
					welcomeUserLbl.setText("BIENVENIDO, " + userProfile.getName().toUpperCase());
					workouts = workoutManager.getWorkoutsForUserLevel(userLevel);
					fillWorkoutTable(workoutTable, workouts);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, null, "No se ha podido cargar la informacion...", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void fillWorkoutTable(DefaultTableModel workoutTable, List<Workout> workouts) {
		workoutTable.setRowCount(0);
		try {
			for (Workout workout : workouts) {
				Object[] line = { workout.getWorkoutName(), workout.getExerciseNumber(), workout.getLevel(),
						workout.getVideo() };
				workoutTable.addRow(line);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, null, "No se ha podido llenar la tabla...", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setWorkoutTable(DefaultTableModel workoutTable) {
		this.workoutTable = workoutTable;
	}
}
