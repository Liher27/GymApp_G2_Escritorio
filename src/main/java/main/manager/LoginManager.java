package main.manager;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import main.manager.pojo.User;

public class LoginManager implements ManagerInterface<User> {

	private Firestore db = null;

	public LoginManager() throws Exception {
		FileInputStream serviceAccount = new FileInputStream("src/main/resources/reto-1-grupo-2.json");

		FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
				.setProjectId("reto-1-grupo-2").setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

		db = firestoreOptions.getService();
	}

	public boolean validateUser(User user) throws Exception {
		CollectionReference usersRef = db.collection("users");

		ApiFuture<QuerySnapshot> query = usersRef.whereEqualTo("name", user.getName()).get();
		List<QueryDocumentSnapshot> documents = query.get().getDocuments();

		try {
			if (!documents.isEmpty()) {
				QueryDocumentSnapshot document = documents.get(0);

				String storedPassword = document.getString("pass");

				if (storedPassword.equals(user.getPass())) {
					user.setBirthDate(document.getDate("birthDate"));
					user.setMail(document.getString("mail"));
					user.setName(document.getString("name"));
					user.setPass(document.getString("pass"));
					user.setSurname(document.getString("surname"));
					user.setTrainer(document.getBoolean("trainer"));
					user.setUserLevel(document.getLong("userLevel").intValue());
					StatusSingleton.getInstance().setUser(user);
					return true;
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			db.close();
		}
		return false;

	}

	@Override
	public List<User> getAll() throws SQLException, Exception {
		return null;
	}

	@Override
	public boolean insert(User user) throws Exception {
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
