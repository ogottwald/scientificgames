package com.scientificgames.bo;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

@XmlRootElement(name = "studentClassesSet")
public class StudentClasses {
	
	final static Logger logger = Logger.getLogger(StudentClasses.class);
	private int id;
	private double grade;
	public String className;
	
	public StudentClasses(int id, double grade, String className){
		setId(id);
		setGrade(grade);
		setClassName(className);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	
}
