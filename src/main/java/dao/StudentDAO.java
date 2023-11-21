package dao;

import domain.Student;

public class StudentDAO extends AbstractJpaDao{

    public StudentDAO(){
        setClazz(Student.class);
    }
}
