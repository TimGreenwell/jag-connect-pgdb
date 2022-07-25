package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


@Entity(name = "Agent")
@Table(name = "Agent")
@Data
public class Agent implements Serializable {
    @Id
    @Column(name = "agent_pk")
    private String id;

    @Column(name = "agent_name", nullable = false)
    private String name;

    //   tlg - a possible alternative to the the ONeToMany for assessment (assessment class also)
//    @ElementCollection(fetch = FetchType.EAGER)
//    @MapKeyColumn(name="activity")
//    @Column(name="assessment")
//    @CollectionTable(name="agent_assessments", joinColumns=@JoinColumn(name = "agent_pk"))
//    private Map<String, Integer> assessments = new HashMap<String, Integer>();

    @OneToMany(
            mappedBy = "agent",
            cascade = CascadeType.ALL,
            orphanRemoval = true )
    @JsonManagedReference
    private List<Assessment> assessments = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="agent_team_fk", nullable=false)
    @JsonBackReference
    private Team team;

}