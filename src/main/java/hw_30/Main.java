package hw_30;

import hw_30.daos.StudentDAO;
import hw_30.entities.Homework;
import hw_30.entities.Student;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("javaPro-persistence-unit");
        StudentDAO dao = new StudentDAO(emf);


        try {
            Student student1 = new Student("John", "Cena", "john.cena@gmail.com");
            Student student2 = new Student("Jane", "Doe", "jane.doe@gmail.com");
            Student student3 = new Student("Jack", "Sparrow", "jack.sparrow@gmail.com");

            Homework hw1 = new Homework("Math", LocalDate.of(2025, 1, 20), 56);
            Homework hw2 = new Homework("English", LocalDate.of(2025, 1, 22), 99);
            Homework hw3 = new Homework("Physics", LocalDate.of(2025, 1, 18), 78);

            student1.addHomework(hw1);
            student1.addHomework(hw2);
            student1.addHomework(hw3);

            student3.addHomework(hw3);
            student3.addHomework(hw1);

            student2.addHomework(hw3);

            dao.save(student1);
            dao.save(student2);
            dao.save(student3);

            System.out.println("Saved students: ");
            dao.findAll().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}