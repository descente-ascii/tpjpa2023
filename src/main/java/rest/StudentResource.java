package rest;

import dao.StudentDAO;
import domain.Student;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("student")
@Produces({"application/json", "application/xml"})
public class StudentResource {

    private StudentDAO studentDao = new StudentDAO();
    @GET
    @Path("{studentId}")
    public Student getStudentById(@PathParam("studentId") Long studentId)  {
        // return student
        return (Student) studentDao.findOne(studentId);
    }

    @GET
    public List<Student> getAllStudents()  {
        return studentDao.findAll();
    }


    @POST
    @Consumes("application/json")
    public Response addStudent(
            @Parameter(description = "Student object that needs to be added to the store", required = true) Student student) {
        // add Student
        try{
            studentDao.save(student);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return Response.ok().entity("SUCCESS").build();
    }

    @DELETE
    @Path("{id}")
    @Consumes("application/json")
    public Response deleteStudent(
            @PathParam("id") Long studentId) {
        // add student
        try{
            Student studentToDelete = (Student) studentDao.findOne(studentId);
            studentDao.delete(studentToDelete);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return Response.ok().entity("SUCCESS").build();
    }

}