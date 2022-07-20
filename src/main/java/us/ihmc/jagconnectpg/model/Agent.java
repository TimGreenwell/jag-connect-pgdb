package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Entity(name = "Agent")
@Table(name = "Agent")
public class Agent implements Serializable {
    @Id
    @Column(name = "agent_pk")
    private String id;
    @Column(name = "agent_name", nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name="activity")
    @Column(name="assessment")
    @CollectionTable(name="agent_assessments", joinColumns=@JoinColumn(name = "agent_pk"))
    private Map<String, Integer> assessments = new HashMap<String, Integer>();



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="agent_team_fk", nullable=true)
    @JsonBackReference
    private Team team;

    public Agent(String id, String name, Map<String, Integer> assessments, Team team) {
        this.id = id;
        this.name = name;
        this.assessments = assessments;
        this.team = team;
    }

    public Agent() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public Map<String, Integer> getAssessments() {
        return assessments;
    }

    public void setAssessments(Map<String, Integer> assessments) {
        this.assessments = assessments;
    }

    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", assessments=" + assessments +
                '}';
    }
}