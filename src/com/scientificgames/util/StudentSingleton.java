package com.scientificgames.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.scientificgames.bo.IStudent;
import com.scientificgames.bo.Student;
import com.scientificgames.bo.StudentClasses;

public class StudentSingleton {

	final static Logger logger = Logger.getLogger(StudentSingleton.class);
	private static StudentSingleton studentSingleton = null;
	private static boolean firstThread = true;
	
	public static Map<Integer, IStudent> studentMap = new TreeMap<Integer, IStudent>();
	
	/**
	 * Singleton Pattern - for web solution the data will be loaded for 1 time
	 * usage.  loadStudents method called to load students_classes.json
	 *  
	 * @return StudentSingleton instance
	 */
	public static StudentSingleton getInstance(){
		if(studentSingleton == null){
			loadStudents();
			studentSingleton = new StudentSingleton();
		}
		return studentSingleton;
	}
	
	/**
	 * LoadStudents - loads students from the students_classes.json from call to loadStudentFile method.
	 *   Path for student_classes.json is in the config.properties as property studentfile
	 *   Used org.json to load the student_classes.json file.  Creating a TreeMap of Student Objects
	 *   via interface IStudent for direct reference by student generated id.  The Student class has a 
	 *   List of StudentClasses Objects.  Mapping for the student classes with id, grade, and
	 *   className which was defined in the JSON.
	 */
	public static void loadStudents(){
		if(firstThread) {
			firstThread = false;	
			//String jsonStudent = "{\"students\": [{\"first\": \"John\",\"last\": \"Smith\",\"email\": \"johnsmith@mailinator.com\",\"studentClasses\": [{\"id\": 1,\"grade\": 4},{\"id\": 2,\"grade\": 3}]},{\"first\": \"John\",\"last\": \"Smith\",\"email\": \"johnsmith@mailinator.com\",\"studentClasses\": [{\"id\": 1,\"grade\": 4},{\"id\": 2,\"grade\": 3}]}],\"classes\": {\"1\": \"Math 101\",\"2\": \"English 101\"}}";
			String jsonStudent = loadStudentFile();
			JSONObject outerStudentObject = new JSONObject(jsonStudent);
		    	
			Map<String, String> classesMap = new HashMap<String, String>();
			JSONObject classesObject = outerStudentObject.getJSONObject("classes");
			for(int i = 0; i<classesObject.names().length(); i++){
				classesMap.put(classesObject.names().getString(i), 
						classesObject.get(classesObject.names().getString(i)).toString());
				}
		    
			JSONArray jsonStudentArray = outerStudentObject.getJSONArray("students");
			for (int i = 0, size = jsonStudentArray.length(); i < size; i++){
				IStudent student = new Student();
				JSONObject studentObjectInArray = jsonStudentArray.getJSONObject(i);
				String[] elementStudentNames = JSONObject.getNames(studentObjectInArray);
				for (String elementStudentName : elementStudentNames){
					String elementStudentValue="";
					if(elementStudentName.equals("studentClasses")){
						List<StudentClasses> studentClassesList = new ArrayList<StudentClasses>();
						JSONArray jsonClassesArray = studentObjectInArray.getJSONArray(elementStudentName);
						double totalGrade = (double) 0.0;
						int cntGrade = 0;
						for(int c =0; c< jsonClassesArray.length(); c++){
							JSONObject classesInArray = jsonClassesArray.getJSONObject(c);
							String[] elementClassesNames = JSONObject.getNames(classesInArray);
							int id = 0;
							double grade = (double) 0.0;
							
							for (String elementClassesName : elementClassesNames){
								if(elementClassesName.equals("id")){
									id = classesInArray.getInt(elementClassesName);
								} else if(elementClassesName.equals("grade")) {
									cntGrade++;
									grade = classesInArray.getDouble(elementClassesName);
									totalGrade=totalGrade+grade;
									StudentClasses studentClasses = new StudentClasses(id, grade, 
											(String)classesMap.get(Integer.toString(id).trim()));
									studentClassesList.add(studentClasses);
								}
							}
							student.setAvgGrade(totalGrade/cntGrade);
							student.setStudentClassesList(studentClassesList);
						}
					} else {
						elementStudentValue = studentObjectInArray.getString(elementStudentName);
						if(elementStudentName.equals("first")){
							student.setId(i);
							student.setFirstName(elementStudentValue);
						} else if(elementStudentName.equals("last")){
							student.setLastName(elementStudentValue);
						} else if(elementStudentName.equals("email")){
							student.setEmail(elementStudentValue);
						}
					}
				}
				studentMap.put(new Integer(i), student);
			}
		}
	}
	
	/**
	 * LoadStudentFile - Path for student_classes.json is in the config.properties as property studentfile
	 *   in StudentProperties.
	 *   
	 * @return json data from student_classes.json
	 */
	public static String loadStudentFile(){
		String jsonStudent = "";
	  	BufferedReader br = null;
		StudentProperties studentProperties = new StudentProperties();
		try {
	  		String line;
	  		br = new BufferedReader(new FileReader(studentProperties.getProperty("studentfile")));
	  		while ((line = br.readLine()) != null) {
	  			jsonStudent += line + "\n";
	  		}
	  	} catch (IOException e) {
	  		e.printStackTrace();
	  	} finally {
	  		try {
	  			if (br != null)
	  				br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
	  	return jsonStudent; 
	}

}
