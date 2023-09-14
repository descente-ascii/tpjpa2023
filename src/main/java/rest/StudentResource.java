package rest;

import dao.DaoStudent;
import domain.Student;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("student")
@Produces({"application/json", "application/xml"})
public class StudentResource {

    private DaoStudent studentDao = new DaoStudent();
    @GET
    @Path("/{studentId}")
    public Student getStudentById(@PathParam("studentId") Long studentId)  {
        // return student
        return (Student) studentDao.findOne(studentId);
    }

    @GET
    @Path("/")
    public Student getStudent(Long studentId)  {
        return new Student();
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

    @POST
    @Consumes("application/json")
    public Response deleteStudent(
            @Parameter(description = "Student object that needs to be added to the store", required = true) Student student) {
        // add student
        try{
            studentDao.delete(student);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return Response.ok().entity("SUCCESS").build();
    }

}