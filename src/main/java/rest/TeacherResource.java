package rest;

import dao.TeacherDAO;
import domain.Teacher;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("teacher")
@Produces({"application/json", "application/xml"})
public class TeacherResource {

    TeacherDAO teacherDao = new TeacherDAO();
    @GET
    @Path("{teacherId}")
    public Teacher getTeacherById(@PathParam("teacherId") Long teacherId)  {
        // return pet
        return (Teacher) teacherDao.findOne(teacherId);
    }

    @GET
    public List<Teacher> getAllTeachers()  {
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

    @DELETE
    @Path("{id}")
    @Consumes("application/json")
    public Response deleteTeacher(
            @PathParam("id") Long teacherId) {
        // add teacher
        try{
            Teacher teacherToDelete = (Teacher) teacherDao.findOne(teacherId);
            teacherDao.delete(teacherToDelete);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return Response.ok().entity("SUCCESS").build();
    }

}