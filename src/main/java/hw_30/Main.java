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
            student1.addHomework(hw1);
            Homework hw2 = new Homework("English", LocalDate.of(2025, 1, 22), 99);
            student1.addHomework(hw2);
            Homework hw3 = new Homework("Physics", LocalDate.of(2025, 1, 18), 78);
            student1.addHomework(hw3);
            Homework hw4 = new Homework("Chemistry", LocalDate.of(2025, 1, 19), 90);
            student3.addHomework(hw4);
            Homework hw5 = new Homework("Science", LocalDate.of(2025, 1, 20), 90);
            student3.addHomework(hw5);
            Homework hw6 = new Homework("Biology", LocalDate.of(2025, 1, 21), 90);
            student2.addHomework(hw6);

            dao.save(student1);
            dao.save(student2);
            dao.save(student3);

            System.out.println("Saved students: ");
            dao.findAll().forEach(System.out::println);

//            Student foundStudent = dao.findById(3L);
//            System.out.println(foundStudent);

//            System.out.println(dao.update(new Student("Robert", "Downey Jr.", "tony.stark@gmail.com")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}