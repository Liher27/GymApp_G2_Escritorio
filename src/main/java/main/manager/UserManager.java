package main.manager;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.firestore.v1.Document;

import main.manager.pojo.User;

public class UserManager implements ManagerInterface<User> {

	private Firestore db = null;

	public UserManager() throws Exception {
		FileInputStream serviceAccount = new FileInputStream("src/main/resources/reto-1-grupo-2.json");

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getOne(User user) throws Exception {
	    DocumentReference getUserRef = db.collection("users").document(user.getName());
	    ApiFuture<DocumentSnapshot> future = getUserRef.get();
	    DocumentSnapshot document = future.get();

	    if (document.exists()) {
	        user.setName(document.getString("name"));
	        user.setBirthDate(document.getDate("birthDate"));
	        user.setMail(document.getString("mail"));
	        user.setPass(document.getString("pass"));
	        user.setSurname(document.getString("surname"));
	        user.setUserLevel(((Number) document.get("level")).intValue());
	        return user;
	    } else {
	        throw new Exception("User not found");
	    }
	}

	@Override
	public boolean modify(User user) throws Exception {

		DocumentReference userRef = db.collection("users").document(user.getName());
		ApiFuture<WriteResult> initialResult = userRef.set(user);
		initialResult.get();

		Map<String, Object> updates = new HashMap<>();
		updates.put("birthDate", user.getBirthDate());
		updates.put("mail", user.getMail());
		updates.put("name", user.getName());
		updates.put("pass", user.getPass());
		updates.put("surname", user.getSurname());
		updates.put("trainer", user.isTrainer());

		ApiFuture<WriteResult> writeResult = userRef.update(updates);

		if (null != writeResult.get()) {
			db.close();
			return true;
		} else
			db.close();

		return false;
	}

	@Override
	public void delete(User user) throws SQLException, Exception {
		// TODO Auto-generated method stub

	}

}
