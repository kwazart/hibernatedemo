package model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="developers")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @ManyToOne
    @JoinColumn(name="specialty_id", nullable = false)
    private Specialty specialty;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "DeveloperSkills",
            joinColumns = { @JoinColumn(name = "developer_id") },
            inverseJoinColumns = { @JoinColumn(name = "skill_id")}
    )
    private List<Skill> skills;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Override
    public String toString() {
        return  id +
                "\t" + firstName + '\t' +
                "\t" + lastName + '\t' +
                "\t" + specialty +
                "\t" + skills +
                "\t" + status;
    }
}