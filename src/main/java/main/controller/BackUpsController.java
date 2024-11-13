package main.controller;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import main.manager.pojo.Exercise;
import main.manager.pojo.Historic;
import main.manager.pojo.User;
import main.manager.pojo.Workout;

public class BackUpsController {

	private Date date = null;
	private Historic historic = null;
	private ArrayList<Historic> historicList = new ArrayList<>();

	public void userBackups(Workout workout, User user, Historic historic)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
		try {
			File file = new File("C:\\Trastero\\" + user.getName() + ".xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			Element users = null, workOut = null, elem = null;
			if (file.exists()) {
				Document doc = db.parse(file);
				doc.setXmlStandalone(true);
				Node workoutToContinue = doc.getElementsByTagName("User").item(0);
				Element singleWorkout = doc.createElement("Workout");
				workoutToContinue.appendChild(singleWorkout);
				Element workoutName = doc.createElement("WorkoutNombre");
				workoutName.setTextContent(workout.getWorkoutName());
				singleWorkout.appendChild(workoutName);
				Element levelWorkout = doc.createElement("Nivel");
				levelWorkout.setTextContent(String.valueOf(workout.getLevel()));
				singleWorkout.appendChild(levelWorkout);
				Element totalTime = doc.createElement("TiempoTotal");
				totalTime.setTextContent(String.valueOf(historic.getTotalTime()));
				singleWorkout.appendChild(totalTime);
				Element proviedTime = doc.createElement("TiempoPrevisto");
				proviedTime.setTextContent(String.valueOf(historic.getProvidedTime()));
				singleWorkout.appendChild(proviedTime);
				Element finishDate = doc.createElement("Fecha");
				finishDate.setTextContent(dateCoverter(historic.getFinishDate()));
				singleWorkout.appendChild(finishDate);
				Element workoutPercent = doc.createElement("EjerciciosCompletado");
				workoutPercent.setTextContent(historic.getExercisePercent());
				singleWorkout.appendChild(workoutPercent);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(file);
				transformer.transform(source, result);
			} else {
				Document xmldoc = db.newDocument();
				users = xmldoc.createElement("User");
				workOut = xmldoc.createElement("Workout");
				xmldoc.appendChild(users);
				users.setAttribute("user", user.getName());
				elem = xmldoc.createElement("WorkoutNombre");
				elem.setTextContent(workout.getWorkoutName());
				workOut.appendChild(elem);
				elem = xmldoc.createElement("Nivel");
				elem.setTextContent(String.valueOf(workout.getLevel()));
				workOut.appendChild(elem);
				elem = xmldoc.createElement("TiempoTotal");
				elem.setTextContent(String.valueOf(historic.getTotalTime()));
				workOut.appendChild(elem);
				elem = xmldoc.createElement("TiempoPrevisto");
				elem.setTextContent(String.valueOf(historic.getProvidedTime()));
				workOut.appendChild(elem);
				elem = xmldoc.createElement("Fecha");
				elem.setTextContent(dateCoverter(historic.getFinishDate()));
				workOut.appendChild(elem);
				elem = xmldoc.createElement("EjerciciosCompletado");
				elem.setTextContent(historic.getExercisePercent());
				workOut.appendChild(elem);
				users.appendChild(workOut);

				TransformerFactory tFactory = TransformerFactory.newInstance();
				Transformer transformer = tFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(xmldoc);
				StreamResult result = new StreamResult(file);
				transformer.transform(source, result);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (TransformerException e3) {
			e3.printStackTrace();
		}
	}

	public List<Historic> getBackUpsList(User user) {
			
		try {
			

			File file = new File("C:\\Trastero\\" + user.getName() + ".xml");
			if (file.exists()) {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = factory.newDocumentBuilder();
				Document doc = db.parse(file);

				NodeList workoutNodes = doc.getElementsByTagName("Workout");

				for (int i = 0; i < workoutNodes.getLength(); i++) {
					Element workoutElement = (Element) workoutNodes.item(i);

					String workoutName = workoutElement.getElementsByTagName("WorkoutNombre").item(0).getTextContent();
					int level = Integer.parseInt(workoutElement.getElementsByTagName("Nivel").item(0).getTextContent());
					int totalTime = Integer
							.parseInt(workoutElement.getElementsByTagName("TiempoTotal").item(0).getTextContent());
					int proviedTime = Integer
							.parseInt(workoutElement.getElementsByTagName("TiempoPrevisto").item(0).getTextContent());

					String finishDate = workoutElement.getElementsByTagName("Fecha").item(0).getTextContent();

					try {
						SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
						date = newFormat.parse(finishDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					String workoutPercent = workoutElement.getElementsByTagName("EjerciciosCompletado").item(0)
							.getTextContent();
					historic = new Historic();
					historic.setWorkoutName(workoutName);
					historic.setLevel(level);
					historic.setTotalTime(totalTime);
					historic.setProvidedTime(proviedTime);
					historic.setFinishDate(date);
					historic.setExercisePercent(workoutPercent);
					historicList.add(historic);
				}
				
			}
			
		}catch(ParserConfigurationException | SAXException | IOException e1) {
			JOptionPane.showMessageDialog(null, "No existe nigun historicos");
		}
		return historicList;
		
	}

	private String dateCoverter(Date date) {
		SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
		String formattedDate = targetFormat.format(date);
		return formattedDate;

	}

}