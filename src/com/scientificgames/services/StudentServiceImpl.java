package com.scientificgames.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.log4j.Logger;

import com.scientificgames.bo.Student;
import com.scientificgames.bo.IStudent;

public class StudentServiceImpl {
	
	final static Logger logger = Logger.getLogger(StudentServiceImpl.class);
	public Map<Integer, IStudent> studentMap = new TreeMap<Integer, IStudent>();
	
	/**
	 * getSearch - handles the search of first name, last name, average grade condition, and
	 * average grade to search for. 
	 * 
	 * @param  firstName to search
	 * @param  lastName to search
	 * @param  avgGradeCon to search average grade condition (=,<,<=,>,> 
	 * @param  avgGrade to search average grade
	 * @return List<IStudent> of student data searched
	 */
	
	public List<IStudent> getSearch(String firstName, String lastName, String avgGradeCon, double avgGrade){
		List<IStudent> studentList = new ArrayList<IStudent>();
	    boolean searchFlag = false;
	    Collection<IStudent> c = studentMap.values();
	    Iterator<IStudent> itr = c.iterator();
	    while(itr.hasNext()){
	    	IStudent student = (Student)itr.next();
	    	if(firstName.length()>0 && firstName.toUpperCase().indexOf(student.getFirstName().toUpperCase())>=0){
	    		searchFlag = true;
	    	}
	    	if(lastName.length()>0 && lastName.toUpperCase().indexOf(student.getLastName().toUpperCase())>=0){
	    		searchFlag = true;
	    	}
	    	if(Double.toString(avgGrade).length()>0){
	    		if(avgGradeCon.equals("==") || avgGradeCon.equals("")){
	    	   		if(student.getAvgGrade()==avgGrade){
	    	   			searchFlag = true;
	    	   		}
	    	   	} else if(avgGradeCon.equals(">=")){
	    	   		if(student.getAvgGrade()>=avgGrade){
    	   				searchFlag = true;
    	   			}
	    	   	} else if(avgGradeCon.equals(">")){
	    	   		if(student.getAvgGrade()>avgGrade){
    	   				searchFlag = true;
    	   			}	
	    	   	} else if(avgGradeCon.equals("<=")){
	    	   		if(student.getAvgGrade()<=avgGrade){
    	   				searchFlag = true;
    	   			}	
	    	   	} else if(avgGradeCon.equals("<")){
	    	   		if(student.getAvgGrade()<avgGrade){
    	   				searchFlag = true;
    	   			}
	    	   	}
	    	}
	    	if(searchFlag){
	    		studentList.add(student);
	    		searchFlag=false;
	    	}
	    }
	    if(logger.isDebugEnabled()){
	    	logger.debug("SIZE STUDENTLIST: "+studentList.size());
	    }
	    return studentList;
	}
	
	
}
