package main.manager;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;

import main.manager.pojo.User;

public class RegisterManager implements ManagerInterface<User> {

	private Firestore db = null;

	public RegisterManager() throws Exception {
		FileInputStream serviceAccount = new FileInputStream(
				"C:\\Users\\in2dm3-v\\git\\GymAppEscritorio\\src\\main\\resources\\reto-1-grupo-2.json");

		FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
				.setProjectId("reto-1-grupo-2").setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

		db = firestoreOptions.getService();
	}

	@Override
	public List<User> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(User user) throws Exception {
		DocumentReference recordRef = db.collection("users").document(user.getName());

		ApiFuture<WriteResult> writeResult = recordRef.set(user);

		if (null != writeResult.get()) {
			db.close();
			return true;
		} else
			db.close();
		return false;

	}

	@Override
	public User getOne(User t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modify(User t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User t) throws SQLException, Exception {
		// TODO Auto-generated method stub

	}
}
