package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Team")
@Table(name = "Team")
@Data
public class Team {
    @Id
    @Column(name = "team_pk", nullable = false)
    private String id;
    @Column(name = "team_name", nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "team",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Agent> agentIds = new ArrayList<>();

    @OneToMany(
            mappedBy = "team",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Performer> performers = new ArrayList<>();

}

