package main.manager;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import main.manager.pojo.Exercise;

public class ExerciseManager implements ManagerInterface<Exercise> {
	private Firestore db = null;

	public ExerciseManager() throws Exception {
		FileInputStream serviceAccount = new FileInputStream("src/main/resources/reto-1-grupo-2.json");

		FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
				.setProjectId("reto-1-grupo-2").setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

		db = firestoreOptions.getService();
	}

	@Override
	public List<Exercise> getAll() throws Exception {
		DocumentReference workout = db.collection("workouts").document("workout_1");
		CollectionReference exercice = workout.collection("workoutExercises");
		List <Exercise> ret = new ArrayList<Exercise>();
		 List<QueryDocumentSnapshot> documentos = exercice.get().get().getDocuments();
			for (QueryDocumentSnapshot document : documentos) {
				
				Exercise exercise = new Exercise();
				exercise.setExerciseName(document.getString("exerciseName"));
				exercise.setExerciseImage(document.getString("image"));
				exercise.setRest(((Number) document.get("restTime")).intValue());
				exercise.setSeriesNumber(((Number) document.get("seriesNumber")).intValue());
				ret.add(exercise);
			}
		return ret;
	}

	@Override
	public boolean insert(Exercise t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Exercise getOne(Exercise t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modify(Exercise t) throws Exception {
		return false;
	}

	@Override
	public void delete(Exercise t) throws SQLException, Exception {
		// TODO Auto-generated method stub

	}

}
