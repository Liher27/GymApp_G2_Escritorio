package main.manager;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import main.manager.pojo.Exercise;

public class ExerciseManager implements ManagerInterface<Exercise> {
	private Firestore db = null;

	public ExerciseManager() throws Exception {
		FileInputStream serviceAccount = new FileInputStream(
				"src/main/resources/reto-1-grupo-2.json");

		FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
				.setProjectId("reto-1-grupo-2").setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

		db = firestoreOptions.getService();
	}

	@Override
	public List<Exercise> getAll() throws Exception {
		List<Exercise> ret = new ArrayList<Exercise>();
		try {
			DocumentReference workout = db.collection("workouts").document("workout_1");
			CollectionReference exercice = workout.collection("workoutExercises");

			List<QueryDocumentSnapshot> documentos = exercice.get().get().getDocuments();
			for (QueryDocumentSnapshot document : documentos) {

				Exercise exercise = new Exercise();
				exercise.setExerciseName(document.getString("exerciseName"));
				exercise.setImage(document.getString("image"));
				exercise.setRestTime(((Number) document.get("restTime")).intValue());
				exercise.setSeriesNumber(((Number) document.get("seriesNumber")).intValue());
				ret.add(exercise);
			}

		} catch (Exception e) {
			throw e;
		} finally {
		}
		return ret;
	}
	public List<Exercise> getExercisesForWorkout(String workoutId) throws ExecutionException, InterruptedException {
	    
		 CollectionReference workoutsRef = db.collection("workouts");
		 
		 DocumentReference workoutExercisesRef = workoutsRef.document(workoutId);
		 
		 CollectionReference a = workoutExercisesRef.collection("workoutExercises");
		
	    List<QueryDocumentSnapshot> exerciseDocuments = a.get().get().getDocuments();
	    
	    List<Exercise> exercises = new ArrayList<>();
	    
	    for (QueryDocumentSnapshot document : exerciseDocuments) {
	        Exercise exercise = document.toObject(Exercise.class);
	        
	        exercise.setExerciseName((String) document.get("exerciseName"));
	        exercise.setSeriesNumber(((Number) document.get("seriesNumber")).intValue());
	        exercise.setRestTime(((Number) document.get("restTime")).intValue());
	        exercise.setImage((String) document.get("image"));
	        
	        exercises.add(exercise);
	    }
	    
	    return exercises;
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

	public List<Exercise> getExercisesFromWorkout(int userLevel) throws Exception {
		List<Exercise> allExercises = new ArrayList<>();
		try {
			CollectionReference workoutsRef = db.collection("workouts");
			ApiFuture<QuerySnapshot> future = workoutsRef.whereLessThanOrEqualTo("level", userLevel).get();
			List<QueryDocumentSnapshot> workoutDocuments = future.get().getDocuments();

			for (QueryDocumentSnapshot workoutDocument : workoutDocuments) {
				CollectionReference exercisesRef = workoutDocument.getReference().collection("workoutExercises");
				ApiFuture<QuerySnapshot> exercisesFuture = exercisesRef.get();
				List<QueryDocumentSnapshot> exerciseDocuments = exercisesFuture.get().getDocuments();

				for (QueryDocumentSnapshot exerciseDocument : exerciseDocuments) {
					Exercise exercise = new Exercise();

					exercise.setExerciseName(exerciseDocument.getString("exerciseName"));
					exercise.setImage(exerciseDocument.getString("image"));
					exercise.setRestTime(exerciseDocument.getLong("restTime").intValue());
					exercise.setSeriesNumber(exerciseDocument.getLong("seriesNumber").intValue());

					allExercises.add(exercise);
				}

			}

		} catch (Exception e) {
			throw e;
		} finally {
		}
		return allExercises;
	}
}
