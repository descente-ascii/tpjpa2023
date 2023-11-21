package rest;

import dao.AppointmentDAO;
import domain.Appointment;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("appointment")
@Produces({"application/json", "application/xml"})
public class AppointmentResource {

    AppointmentDAO appointmentDao = new AppointmentDAO();
    @GET
    @Path("{appointmentId}")
    public Appointment getAppointmentById(@PathParam("appointmentId") Long appointmentId)  {
        // return appointment
        return (Appointment) appointmentDao.findOne(appointmentId);
    }

    @GET
    public List<Appointment> getAllAppointments()  {
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

    @DELETE
    @Path("{id}")
    @Consumes("application/json")
    public Response deleteAppointment(@PathParam("id") Long appointmentId) {
        // delete appointment
        try{
            Appointment appointmentToDelete = (Appointment) appointmentDao.findOne(appointmentId);
            appointmentDao.delete(appointmentToDelete);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return Response.ok().entity("SUCCESS").build();
    }

}
