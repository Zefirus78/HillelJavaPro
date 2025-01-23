package hw_30.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Homeworks")
public class Homework {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    @Column(name = "description")
    private String description;
    @Setter
    @Getter
    @Column(name = "deadline")
    private LocalDate deadline;
    @Getter
    @Setter
    @Column(name = "mark")
    private int mark;
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Homework() {}

    public Homework(String description, LocalDate deadline, int mark) {
        this.description = description;
        this.deadline = deadline;
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Homework homework = (Homework) o;
        return Objects.equals(id, homework.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
