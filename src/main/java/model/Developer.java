package model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="developers")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @ManyToOne
    @JoinColumn(name="specialty_id", insertable = false, updatable = false)
    private Specialty specialty;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "DeveloperSkills",
            joinColumns = { @JoinColumn(name = "developer_id") },
            inverseJoinColumns = { @JoinColumn(name = "skill_id")}
    )
    private List<Skill> skills;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;
}
