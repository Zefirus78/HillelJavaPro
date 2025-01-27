package hw_30.daos;

import hw_30.entities.Student;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

public class StudentDAO implements GenericDAO<Student, Long> {

    @PersistenceContext
    private EntityManagerFactory emf;

    public StudentDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void save(Student student) {
        Objects.requireNonNull(student, "Parameter [student] must not be null!");
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(student);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new PersistenceException("Cannot save student info %s".formatted(student), ex);
        } finally {
            em.close();
        }
    }

    @Override
    public Student findById(Long id) {
        return emf.createEntityManager()
                .createQuery("from Student s where s.id = :id", Student.class)
                .setParameter("id", Objects.requireNonNull(id))
                .getSingleResult();
    }

    @Override
    public Student findByEmail(String email) {
        return emf.createEntityManager()
                .createQuery("from Student s where s.email = :email", Student.class)
                .setParameter("email", Objects.requireNonNull(email))
                .getSingleResult();
    }

    @Override
    public List<Student> findAll() {
        return emf.createEntityManager()
                .createQuery("from Student", Student.class)
                .getResultList();
    }

    @Override
    public Student update(Student student) {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(student);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new IllegalArgumentException ("Cannot update student info %s".formatted(student), ex);
        } finally {
            em.close();
        }
        return student;
    }

    @Override
    public boolean deleteById(Long id) {
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Student student = findById(id);
        try {
            tx.begin();
            em.remove(student);
            tx.commit();
            return true;
        } catch (Exception ex) {
            tx.rollback();
            throw new IllegalArgumentException ("Cannot delete student info %s".formatted(student), ex);
        } finally {
            em.close();
        }
    }
}
