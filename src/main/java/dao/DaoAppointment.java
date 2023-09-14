package dao;

import domain.Appointment;

public class DaoAppointment extends AbstractJpaDao{
    public DaoAppointment(){
        setClazz(Appointment.class);
    }
}
