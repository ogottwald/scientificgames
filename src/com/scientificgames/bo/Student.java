package com.scientificgames.bo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import org.apache.log4j.Logger;

@XmlRootElement
public class Student implements IStudent{
	
	final static Logger logger = Logger.getLogger(Student.class);
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private double avgGrade;
	
	String pattern = "###,###.###";
	DecimalFormat decimalFormat = new DecimalFormat(pattern);
	private List<StudentClasses> studentClassesList = new ArrayList<StudentClasses>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getAvgGrade() {
		return avgGrade;
	}
	public void setAvgGrade(double avgGrade) {
		double formatAvgGrade = Double.parseDouble(decimalFormat.format(avgGrade));
		this.avgGrade = formatAvgGrade;
	}
	public List<StudentClasses> getStudentClassesList() {
		return studentClassesList;
	}
	public void setStudentClassesList(List<StudentClasses> studentClassesList) {
		this.studentClassesList = studentClassesList;
	} 

	
}
