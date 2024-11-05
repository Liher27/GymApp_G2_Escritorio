package main.view.pannels;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WorkoutsPannel extends JPanel {
	private User userProfile = null;
    private WorkoutManager workoutManager;
    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel workoutTable = null;

    private List<Workout> workouts = null;

    public WorkoutsPannel() {

        setLayout(null);
        setBounds(0, 0, 1230, 700);

        JScrollPane workoutScrollPane = new JScrollPane();
        workoutScrollPane.setBounds(220, 210, 789, 279);
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
                    StatusSingleton.getInstance().setSelectedRow(table.getSelectedRow());
                    StatusSingleton.getInstance().changeToExercisePannel();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        workoutScrollPane.setViewportView(table);

        JButton btnPerfil = new JButton("Perfil");
        btnPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StatusSingleton.getInstance().changeToProfilePannel();
            }
        });
        btnPerfil.setBounds(10, 583, 131, 64);
        add(btnPerfil);

        JButton btnNewButton = new JButton("Salir");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });
        btnNewButton.setBounds(1089, 583, 131, 64);
        add(btnNewButton);
        
        JLabel lblNewLabel = new JLabel("ELIGE EL WORKOUT ");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lblNewLabel.setBounds(463, 159, 303, 40);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("ERREKAFIT");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 38));
        lblNewLabel_1.setBounds(513, 102, 203, 40);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("BIENVENIDO:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lblNewLabel_2.setBounds(10, 53, 352, 40);
        add(lblNewLabel_2);
        
        JButton btnHistorial = new JButton("HISTORIAL");
        btnHistorial.setBounds(167, 583, 131, 64);
        add(btnHistorial);
        
        JLabel lblNewLabel_3 = new JLabel("NIVEL ACTUAL:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lblNewLabel_3.setBounds(10, 102, 352, 40);
        add(lblNewLabel_3);

        this.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                try {
            		userProfile = StatusSingleton.getInstance().getUser();
                	int userLevel = userProfile.getUserLevel(); 
                	workouts = workoutManager.getWorkoutsForUserLevel(userLevel);
                	fillWorkoutPanel(workoutTable, workouts);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void fillWorkoutPanel(DefaultTableModel workoutTable, List<Workout> workouts) {
        workoutTable.setRowCount(0); 
        try {
            for (Workout workout : workouts) {
                Object[] linea = {
                    workout.getWorkoutName(),
                    workout.getExerciseNumber(),
                    workout.getLevel(),
                    workout.getVideo()
                };
                workoutTable.addRow(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setWorkoutTable(DefaultTableModel workoutTable) {
        this.workoutTable = workoutTable;
    }
}
