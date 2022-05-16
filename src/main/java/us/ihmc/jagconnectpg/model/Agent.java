package us.ihmc.jagconnectpg.model;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity(name = "Agent")
@Table(name = "agent")
public class Agent {

    private String id;
    private String name;
    private List<Assessment> assessments = new ArrayList<>();
    private Team team;

    public Agent(String id, String name, List<Assessment> assessments, Team team) {
        this.id = id;
        this.name = name;
        this.assessments = assessments;
        this.team = team;
    }

    public Agent() {
    }

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "assessments", nullable = true)
    @OneToMany(
            mappedBy = "agent",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    public List<Assessment> getAssessments() {
        return assessments;
    }
    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }

    @ManyToOne(fetch = FetchType.LAZY)
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