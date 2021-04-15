package com.security.mssecurity.user;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private static final String COLLECTION_NAME = "user";
	
	@Autowired
	private StorageService service;
	
	/**
	 * 
	 * @return List All Users
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public List<User> findAll() throws ExecutionException, InterruptedException {	
		ApiFuture<QuerySnapshot> future =
				FirestoreClient.getFirestore().collection(COLLECTION_NAME).get(); // .whereEqualTo("timestampDelete", true).get();		
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<User> listProduct = new ArrayList<>();
        for (DocumentSnapshot document : documents) {
        	User user = new User();
        	user.setId(document.getId());
        	user.setName(document.getString("name"));
        	user.setEmail(document.getString("email"));
        	user.setEnable(document.getBoolean("enable"));
        	user.setRole(document.getString("role"));
        	listProduct.add(user);  	
        }
        return listProduct;
	}
	
	/**
	 * 
	 * @param email
	 * @return User for email
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public User findByEmail(String email) throws ExecutionException, InterruptedException {
		
		CollectionReference ref = FirestoreClient.getFirestore().collection(COLLECTION_NAME);
		Query query = ref.whereEqualTo("email", email);
		ApiFuture<QuerySnapshot> querySnapshot = query.get();
		User user = new User();
		for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
			  user.setId(document.getId());
			  user.setName(document.getString("name"));
			  user.setEmail(document.getString("email"));
			  user.setPassword(document.getString("password"));
			  user.setEnable(document.getBoolean("enable"));
			  user.setRole(document.getString("role"));
		}
		return user;
	}
	
	/**
	 * 
	 * @param name
	 * @return User for name
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public User findByName(String name) throws ExecutionException, InterruptedException {
			
			CollectionReference ref = FirestoreClient.getFirestore().collection(COLLECTION_NAME);
			Query query = ref.whereEqualTo("name", name);
			ApiFuture<QuerySnapshot> querySnapshot = query.get();
			User user = new User();
			for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
				  user.setId(document.getId());
				  user.setName(document.getString("name"));
				  user.setEmail(document.getString("email"));
				  user.setPassword(document.getString("password"));
				  user.setEnable(document.getBoolean("enable"));
				  user.setRole(document.getString("role"));
			}
			return user;
		}
	
	
	/**
	 * 
	 * @param User
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public void save(User user) throws ExecutionException, InterruptedException{
		DocumentReference addedDocRef = FirestoreClient.getFirestore().collection(COLLECTION_NAME).document();
//		product.setTimestampCreate(Timestamp.now());
//		product.setTimestampDelete(null);
//		product.setTimestampUpdate(null);
		user.setId(addedDocRef.getId());
		ApiFuture<WriteResult> create = addedDocRef.create(user);
//		ApiFuture<WriteResult> createTimestamp = addedDocRef.update("timestampCreate", FieldValue.serverTimestamp());
//		ApiFuture<WriteResult> deleteTimestamp = addedDocRef.update("timestampDelete", false);
//	    ApiFuture<WriteResult> updateTimestamp = addedDocRef.update("timestampUpdate", false);
//	    ApiFuture<WriteResult> id = addedDocRef.update("id", addedDocRef.getId());
//		Object url = service.upload(multipartFile);
//		System.out.print(url);

	}
	
	
	/**
	 * 
	 * @param ID and User
	 * @param User
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public void update(String id, User obj) throws ExecutionException, InterruptedException{
		DocumentReference docRef = (DocumentReference) FirestoreClient.getFirestore().collection(COLLECTION_NAME).document(id);
		ApiFuture<WriteResult> update = docRef.update(
                "name", obj.getName(),
                "password", obj.getPassword(),
                "enable", obj.getEnable(),
                "role", obj.getRole()
        );
	}
	
	
	/**
	 * 
	 * @param ID
	 */
	public void delete(String id) {
		DocumentReference docRef = (DocumentReference) FirestoreClient.getFirestore().collection(COLLECTION_NAME).document(id);
		docRef.delete();
	}

}
