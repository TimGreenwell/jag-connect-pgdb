package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;

@Entity(name = "Performer")
@Table(name = "Performer")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
@EqualsAndHashCode
@Builder

public class Performer {
    @Id
    @Column(name = "performer_pk", nullable = false)
    private String id;

    @Column(name = "performer_name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="performer_team_fk", nullable=false)
    @JsonBackReference
    private Team team;

}