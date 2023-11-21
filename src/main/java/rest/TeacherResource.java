package rest;

import dao.TeacherDAO;
import domain.Teacher;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("teacher")
@Produces({"application/json", "application/xml"})
public class TeacherResource {

    TeacherDAO teacherDao = new TeacherDAO();
    @GET
    @Path("/{teacherId}")
    public Teacher getTeacherById(@PathParam("teacherId") Long teacherId)  {
        // return pet
        return (Teacher) teacherDao.findOne(teacherId);
    }

    @GET
    @Path("/")
    public List<Teacher> getAllTeachers(Long teacherId)  {
        return teacherDao.findAll();
    }


    @POST
    @Consumes("application/json")
    public Response addTeacher(
            @Parameter(description = "Teacher object that needs to be added to the store", required = true) Teacher teacher) {
        // add teacher
        try{
            teacherDao.save(teacher);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return Response.ok().entity("SUCCESS").build();
    }

    @POST
    @Consumes("application/json")
    public Response deleteTeacher(
            @Parameter(description = "Teacher object that needs to be added to the store", required = true) Teacher teacher) {
        // add teacher
        try{
            teacherDao.delete(teacher);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return Response.ok().entity("SUCCESS").build();
    }

}