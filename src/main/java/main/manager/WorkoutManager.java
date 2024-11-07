package main.manager;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import main.manager.pojo.Exercise;
import main.manager.pojo.Workout;

public class WorkoutManager implements ManagerInterface<Workout> {

	private Firestore db = null;

	public WorkoutManager() throws Exception {
		FileInputStream serviceAccount = new FileInputStream("src/main/resources/reto-1-grupo-2.json");

		FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
				.setProjectId("reto-1-grupo-2").setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

		db = firestoreOptions.getService();
	}

	public List<Workout> getAll() throws Exception {
		ApiFuture<QuerySnapshot> future = db.collection("workouts").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		List<Workout> ret = new ArrayList<Workout>();
		try {
			for (QueryDocumentSnapshot document : documents) {
				Workout workout = new Workout();
				System.out.println(document.getId());
				workout.setWorkoutUID(document.getId());
				workout.setExerciseNumber(((Number) document.get("exerciseNumber")).intValue());
				workout.setLevel(((Number) document.get("level")).intValue());
				workout.setVideo(document.getString("video"));
				workout.setWorkoutName(document.getString("workoutName"));
				ret.add(workout);
			}

		} catch (Exception e) {
			throw e;
		} finally {
		}
		return ret;
	}

	@Override
	public boolean insert(Workout workout) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	public List<Workout> getWorkoutsForUserLevel(int userLevel) throws ExecutionException, InterruptedException {

	    CollectionReference workoutsRef = db.collection("workouts");
	    Query query = workoutsRef.whereLessThanOrEqualTo("level", userLevel);

	    List<QueryDocumentSnapshot> workoutDocuments = query.get().get().getDocuments();
	    List<Workout> workouts = new ArrayList<>();

	    for (QueryDocumentSnapshot document : workoutDocuments) {
	        Workout workout = document.toObject(Workout.class);
			workout.setWorkoutUID(document.getId());
	        workout.setWorkoutName(document.getString("workoutName"));
	        workout.setLevel(((Number) document.get("level")).intValue());
	        workout.setExerciseNumber(((Number) document.get("exerciseNumber")).intValue());
	        workout.setVideo(document.getString("video"));
	        workouts.add(workout);
	    }

	    return workouts;
	}
	
	public Workout getWorkoutFromLevel(int userLevel) throws ExecutionException, InterruptedException{
		CollectionReference workoutsRef = db.collection("workouts");
		   Query query = workoutsRef.whereLessThanOrEqualTo("level", userLevel);

		   List<QueryDocumentSnapshot> workoutDocuments = query.get().get().getDocuments();
		   
		   Workout workout = new Workout();
		   
		 for (QueryDocumentSnapshot document : workoutDocuments) {

		       workout.setWorkoutId(((Number) document.get("workoutId")).intValue());
		       workout.setWorkoutName(document.getString("workoutName"));
		       workout.setLevel(((Number) document.get("level")).intValue());
		       workout.setExerciseNumber(((Number) document.get("exerciseNumber")).intValue());
		       workout.setVideo(document.getString("video"));
		       StatusSingleton.getInstance().setWorkout(workout);
		   }

		return workout;

		}

	

	@Override
	public Workout getOne(Workout workout) throws Exception {
		DocumentReference getUserRef = db.collection("workouts").document(workout.getWorkoutName());
		ApiFuture<DocumentSnapshot> future = getUserRef.get();
		DocumentSnapshot document = future.get();
		try {
			if (document.exists()) {
				workout.setWorkoutName(document.getString("workoutName"));
				workout.setLevel(((Number) document.get("level")).intValue());
				workout.setExerciseNumber(((Number) document.get("exerciseNumber")).intValue());
				workout.setVideo(document.getString("video"));

				@SuppressWarnings("unchecked")
				List<Map<String, Object>> exercisesData = (List<Map<String, Object>>) document.get("exercises");
				List<Exercise> exercises = new ArrayList<>();
				for (Map<String, Object> exerciseData : exercisesData) {
					Exercise exercise = new Exercise();
					exercise.setExerciseName((String) exerciseData.get("exerciseName"));
					exercise.setSeriesNumber(((Number) exerciseData.get("seriesNumber")).intValue());
					exercise.setRestTime(((Number) exerciseData.get("rest")).intValue());
					exercise.setImage((String) exerciseData.get("image"));

					exercises.add(exercise);
				}
				workout.setExercises(exercises);

			}

		} catch (Exception e) {
			throw e;
		} finally {
		}
		return workout;
	}

	@Override
	public boolean modify(Workout workout) throws Exception {
		return false;
	}

	@Override
	public void delete(Workout workout) throws SQLException, Exception {
		// TODO Auto-generated method stub

	}

}
