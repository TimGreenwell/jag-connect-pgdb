package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.util.*;


@Entity(name = "Agent")
@Table(name = "Agent")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
@EqualsAndHashCode
@Builder

public class Agent {
    @Id
    @Column(name = "agent_pk")
    private String id;

    @Column(name = "agent_name", nullable = false)
    private String name;

    @Column(name = "agent_urn", nullable = false)
    private String urn;

    @Column(name = "agent_description", nullable = true)
    private String description;

    @Column(name = "agent_date_created", nullable = true)
    private Date dateCreated;

    @Column(name = "agent_is_locked", nullable = true)
    private Boolean isLocked;

    //   tlg - a possible alternative to the the ONeToMany for assessment (assessment class also)
    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name="activity")
    @Column(name="assessment")
    @CollectionTable(name="Agent_Assessments", joinColumns=@JoinColumn(name = "agent_pk"))
    private Map<String, Integer> assessments = new HashMap<>();

//    @OneToMany(
//            mappedBy = "agent",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true )
//    @JsonManagedReference
//    private List<Assessment> assessments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="agent_team_fk", nullable=false)
    @JsonBackReference
    private Team team;

}