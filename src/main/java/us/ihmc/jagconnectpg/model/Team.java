package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

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
    private List<Agent> agentIds = new ArrayList<>();

    @OneToMany(
            mappedBy = "team",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Performer> performers = new ArrayList<>();

    @JsonManagedReference
    public void setAgentIds(List<Agent> agentIds) {
        this.agentIds = agentIds;
    }

    @JsonManagedReference
    public void setPerformers(List<Performer> performers) {
        this.performers = performers;
    }
}

