package main.controller;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import main.manager.pojo.Workout;

public class BackUpsController {
	
	public void userBackups(Workout workout) {
		int i = 0;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
		DocumentBuilder db = factory.newDocumentBuilder();
		Element users = null , workouts = null, exercise = null, elem = null;
		
		Document xmldoc = db.newDocument();
		users = xmldoc.createElement("user");
		workouts = xmldoc.createElement("workouts");
		exercise = xmldoc.createElement("exercise");
		
		xmldoc.appendChild(users);
		
		workouts.setAttribute("id", "" +i);
		elem = xmldoc.createElement("Workoutname");
		elem.setTextContent(workout.getExerciseName());
		workouts.appendChild(elem);
		
		elem = xmldoc.createElement("nivel");
		elem.setTextContent("" +workout.getWorkoutLvl());
		workouts.appendChild(elem);
		
		elem = xmldoc.createElement("exercise number");
		elem.setTextContent("" + workout.getExerciseNumber());
		workouts.appendChild(elem);
		users.appendChild(workouts);
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();	
		
		
		
	} catch (ParserConfigurationException e) {
		e.printStackTrace();
	} catch (TransformerConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
}
