package rest;

import dao.DaoAppointment;
import domain.Appointment;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("appointment")
@Produces({"application/json", "application/xml"})
public class AppointmentResource {

    DaoAppointment appointmentDao = new DaoAppointment();
    @GET
    @Path("/{appointmentId}")
    public Appointment getAppointmentById(@PathParam("appointmentId") Long appointmentId)  {
        // return pet
        return (Appointment) appointmentDao.findOne(appointmentId);
    }

    @GET
    @Path("/")
    public List<Appointment> getAllAppointment()  {
        return appointmentDao.findAll();
    }


    @POST
    @Consumes("application/json")
    public Response addAppointment(
            @Parameter(description = "Appointment object that needs to be added to the store", required = true) Appointment appointment) {
        // add appointment
        try{
            appointmentDao.save(appointment);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return Response.ok().entity("SUCCESS").build();
    }

    @POST
    @Consumes("application/json")
    public Response deleteAppointment(
            @Parameter(description = "Appointment object that needs to be added to the store", required = true) Appointment appointment) {
        // add appointment
        try{
            appointmentDao.delete(appointment);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return Response.ok().entity("SUCCESS").build();
    }

}
