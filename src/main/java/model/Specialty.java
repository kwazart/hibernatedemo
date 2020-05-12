package model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Specialties")
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "specialty")
    private Set<Developer> developers;
}
