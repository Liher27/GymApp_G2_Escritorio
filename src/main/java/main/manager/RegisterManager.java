package main.manager;

import java.io.FileInputStream;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;

import main.manager.pojo.User;

public class RegisterManager implements ManagerInterface<User> {

	private Firestore db = null;

	public RegisterManager() throws Exception {
		FileInputStream serviceAccount = new FileInputStream(
				"src/main/resources/reto-1-grupo-2.json");

		FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
				.setProjectId("reto-1-grupo-2").setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

		db = firestoreOptions.getService();
	}

	@Override
	public List<User> getAll() throws Exception {
		return null;
	}

	@Override
	public boolean insert(User user) throws Exception {

		ApiFuture<WriteResult> writeResult = db.collection("users").document(user.getName()).create(user);

		if (null != writeResult.get()) {
			db.shutdown();
			return true;
		} else
			db.shutdown();

		return false;

	}

	@Override
	public User getOne(User user) throws Exception {
		return null;
	}

	@Override
	public boolean modify(User user) throws Exception {
		return false;
	}

	@Override
	public void delete(User user) throws Exception {

	}
}
