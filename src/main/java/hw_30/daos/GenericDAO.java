package hw_30.daos;

import hw_30.entities.Student;

import java.util.List;

public interface GenericDAO<T, ID> {

    void save(T entity);

    T findById(ID id);

    T findByEmail(String email);

    List<Student> findAll();

    Student update(T entity);

    boolean deleteById(ID id);

}

