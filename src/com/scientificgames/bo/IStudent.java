package com.scientificgames.bo;

import java.util.List;

public interface IStudent {
	public int getId();
	public void setId(int id);
	public String getFirstName();
	public void setFirstName(String firstName);
	public String getLastName();
	public void setLastName(String lastName);
	public String getEmail();
	public void setEmail(String email);
	public double getAvgGrade();
	public void setAvgGrade(double avgGrade);
	public List<StudentClasses> getStudentClassesList();
	public void setStudentClassesList(List<StudentClasses> studentClassesList);
}
