package us.ihmc.jagconnectpg.model;

import javax.persistence.*;

@Entity(name = "Assessment")
@Table(name = "ASSESSMENT")
public class Assessment {
    @Id
    @Column(name = "assessment_id", nullable = false)
    private String id;
    @Column(name = "assessment_score", nullable = false)
    private Integer assessmentScore;
    @ManyToOne(fetch = FetchType.LAZY)
    private Agent agent;


    public Assessment(String id, Integer assessmentScore, Agent agent) {
        this.id = id;
        this.assessmentScore = assessmentScore;
        this.agent = agent;
    }

    public Assessment() {
    }


    public String getUrn() {
        return id;
    }
    public void setUrn(String id) {
        this.id = id;
    }

    public Integer getAssessmentScore() {
        return assessmentScore;
    }
    public void setAssessmentScore(Integer assessmentScore) {
        this.assessmentScore = assessmentScore;
    }


    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "id='" + id + '\'' +
                ", assessmentScore=" + assessmentScore +
                '}';
    }
}
