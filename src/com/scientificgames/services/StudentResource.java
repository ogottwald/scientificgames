package com.scientificgames.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.scientificgames.bo.IStudent;
import com.scientificgames.bo.Student;
import com.scientificgames.util.StudentSingleton;

import javax.ws.rs.core.MediaType;

@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Path("/student")
public class StudentResource extends StudentServiceImpl {
	
	  final static Logger logger = Logger.getLogger(StudentResource.class);
	  ObjectMapper mapper = new ObjectMapper();
	  String jsonInString = "";
	  
	  @Context
	  private HttpServletRequest request;
	  
	  public StudentResource() throws ClassNotFoundException {
		StudentSingleton studentSingleton = getStudentSingleton();
		studentMap = (TreeMap<Integer, IStudent>) studentSingleton.studentMap;
	  }

	  @GET
	  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	  public  String  getStudents() throws JsonGenerationException, JsonMappingException, IOException {
		 List<IStudent> studentList = new ArrayList<IStudent>();
	     studentList.addAll(studentMap.values());
	     jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(studentList);
	     return jsonInString;
	  }

	  @GET
	  @Path("{id}")
	  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	  public String  getStudent(@PathParam("id") int cId) throws JsonGenerationException, JsonMappingException, IOException {
		  jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(studentMap.get(cId));
		  return jsonInString;
	  }

	  @GET
	  @Path("search")
	  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	  public String getServiceSearch(@QueryParam("firstName") String firstName,
				@QueryParam("lastName") String lastName,@QueryParam("avgGradeCon") String avgGradeCon,
				@QueryParam("avgGrade") double avgGrade) throws JsonGenerationException, JsonMappingException, IOException {
		List<IStudent> studentList = getSearch(firstName, lastName, avgGradeCon, avgGrade);
	    jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(studentList);
	    return jsonInString;
	  }
	  
	  @POST
	  @Path("add")
	  @Produces("text/plain")
	  @Consumes("application/json")
	  public String addStudent(Student student) {
	        studentMap.put(new Integer(studentMap.size()+1), student);
	    return "Student " + student.getFirstName() + " added";
	  }
	  
	  public StudentSingleton getStudentSingleton() throws ClassNotFoundException{
		  StudentSingleton studentSingleton = null;
		  studentSingleton = new StudentSingleton().getInstance();
		  return studentSingleton;
	  }

}
