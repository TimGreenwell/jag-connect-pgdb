package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "Agent")
@Table(name = "Agent")
@Data
public class Agent implements Serializable {
    @Id
    @Column(name = "agent_pk")
    private String id;


    @Column(name = "agent_name", nullable = false)
    private String name;


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

//    public String getId() {
//        return id;
//    }
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//
//
//    public List<Assessment> getAssessments() {
//        return assessments;
//    }
//    public void setAssessments(List<Assessment> assessments) {
//        this.assessments = assessments;
//    }
//
//
//    public Team getTeam() {
//        return team;
//    }
//    public void setTeam(Team team) {
//        this.team = team;
//    }
//
//    @Override
//    public String toString() {
//        return "Agent{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", assessments=" + assessments +
//                '}';
//    }
}