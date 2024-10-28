package main.manager;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import main.manager.pojo.Workout;

public class WorkoutManager implements ManagerInterface<Workout>{
	
	private Firestore db = null;
	
	public  WorkoutManager() throws Exception {
		FileInputStream serviceAccount = new FileInputStream("src/main/resources/reto-1-grupo-2.json");

		FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
				.setProjectId("reto-1-grupo-2").setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

		db = firestoreOptions.getService();
	}
	
	@Override
	public List<Workout> getAll() throws Exception {
		ApiFuture<QuerySnapshot> future = db.collection("workouts").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		for (QueryDocumentSnapshot document : documents) {
		  System.out.println(document.getId() + " => " + document.toObject(Workout.class));		}
		return null;
	}

	@Override
	public boolean insert(Workout t) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Workout getOne(Workout t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modify(Workout t) throws Exception {
		return false;
	}

	@Override
	public void delete(Workout t) throws SQLException, Exception {
		// TODO Auto-generated method stub
		
	}

}
