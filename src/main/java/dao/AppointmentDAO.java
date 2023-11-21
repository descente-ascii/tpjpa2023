package dao;

import domain.Appointment;

public class AppointmentDAO extends AbstractJpaDao{
    public AppointmentDAO(){
        setClazz(Appointment.class);
    }
}
