package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity(name = "Assessment")
@Table(name = "Assessment")
@Data
public class Assessment {
    @Id
    @Column(name = "assessment_pk", nullable = false)
    private String id;

    @Column(name = "assessment_score", nullable = false)
    private Integer assessmentScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="assessment_agent_fk", nullable=false)
    @JsonBackReference
    private Agent agent;

//    public Assessment(String id, Integer assessmentScore, Agent agent) {
//        this.id = id;
//        this.assessmentScore = assessmentScore;
//        this.agent = agent;
//    }
//
//    public Assessment() {
//    }
//
//
//    public String getUrn() {
//        return id;
//    }
//    public void setUrn(String id) {
//        this.id = id;
//    }
//
//    public Integer getAssessmentScore() {
//        return assessmentScore;
//    }
//    public void setAssessmentScore(Integer assessmentScore) {
//        this.assessmentScore = assessmentScore;
//    }
//
//
//    public Agent getAgent() {
//        return agent;
//    }
//
//    public void setAgent(Agent agent) {
//        this.agent = agent;
//    }
//
//    @Override
//    public String toString() {
//        return "Assessment{" +
//                "id='" + id + '\'' +
//                ", assessmentScore=" + assessmentScore +
//                '}';
//    }
}
