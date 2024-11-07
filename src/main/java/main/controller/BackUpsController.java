package main.controller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import main.manager.pojo.Exercise;
import main.manager.pojo.User;
import main.manager.pojo.Workout;

public class BackUpsController {

	public void userBackups(Workout workout, User user, Exercise exercise1) {
		File file = new File(
				"C:\\Trastero\\" + user.getName() + ".xml");
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			Element users = null, workouts = null, workOut = null, exercise = null, elem = null, elemExercise = null;
			if (file.exists()) {
				Document doc = db.parse(file);
				doc.setXmlStandalone(true);
				Node workoutToContinue = doc.getElementsByTagName("User").item(0);
				Element workoutList = doc.createElement("Workouts");
				workoutToContinue.appendChild(workoutList);
				Element singleWorkout = doc.createElement("Workout");
				workoutList.appendChild(singleWorkout);
				Element workoutName = doc.createElement("WorkoutName");
				workoutName.setTextContent(workout.getWorkoutName());
				singleWorkout.appendChild(workoutName);
				Element levelWorkout = doc.createElement("Nivel");
				levelWorkout.setTextContent(String.valueOf(workout.getLevel()));
				singleWorkout.appendChild(levelWorkout);
				Element exerciseNumber = doc.createElement("ExerciseNumber");
				exerciseNumber.setTextContent(String.valueOf(workout.getExerciseNumber()));
				singleWorkout.appendChild(exerciseNumber);
				Element exerciseOnWorkout = doc.createElement("Exercise");
				singleWorkout.appendChild(exerciseOnWorkout);
				Element exerciseName = doc.createElement("ExericeName");
				exerciseName.setTextContent(exercise1.getExerciseName());
				exerciseOnWorkout.appendChild(exerciseName);
				Element exerciseRest = doc.createElement("Rest");
				exerciseRest.setTextContent(String.valueOf(exercise1.getRestTime()));
				exerciseOnWorkout.appendChild(exerciseRest);
				
				Element serieNumber = doc.createElement("SerieNumber");
				serieNumber.setTextContent(String.valueOf(exercise1.getSeriesNumber()));
				exerciseOnWorkout.appendChild(serieNumber);
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(file);
				transformer.transform(source, result);
			} else {
				Document xmldoc = db.newDocument();
				users = xmldoc.createElement("User");
				workouts = xmldoc.createElement("Workouts");
				workOut = xmldoc.createElement("Workout");
				exercise = xmldoc.createElement("Exercise");
				
				xmldoc.appendChild(users);
				users.setAttribute("user", user.getName());
				users.appendChild(workouts);
				elem = xmldoc.createElement("WorkoutName");
				elem.setTextContent(workout.getWorkoutName());
				workOut.appendChild(elem);
				elem = xmldoc.createElement("Nivel");
				elem.setTextContent(String.valueOf(workout.getLevel()));
				workOut.appendChild(elem);
				elem = xmldoc.createElement("ExerciseNumber");
				elem.setTextContent(String.valueOf(workout.getExerciseNumber()));
				workOut.appendChild(elem);
				workouts.appendChild(workOut);
				elemExercise = xmldoc.createElement("ExerciseName");
				elemExercise.setTextContent("exerciseName 1");
				exercise.appendChild(elemExercise);
				elemExercise = xmldoc.createElement("Rest");
				elemExercise.setTextContent("rest" + 1);
				exercise.appendChild(elemExercise);
				elemExercise = xmldoc.createElement("SerieNumber");
				elemExercise.setTextContent("serie" + 3);
				exercise.appendChild(elemExercise);
				workOut.appendChild(exercise);
				
				TransformerFactory tFactory = TransformerFactory.newInstance();
				Transformer transformer = tFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(xmldoc);
				StreamResult result = new StreamResult(file);
				transformer.transform(source, result);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}

}