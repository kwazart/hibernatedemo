package model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(mappedBy = "skills")
    private Set<Developer> developers = new HashSet<>();

    @Override
    public String toString() {
        return  id +
                "\t" + name +
                "\t" + status;
    }
}
