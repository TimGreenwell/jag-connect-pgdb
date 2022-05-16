package us.ihmc.jagconnectpg.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;

@Entity(name = "Assessment")
@Table(name = "assessment")
public class Assessment {

    private String urn;
    private Integer assessmentScore;
    private Agent agent;


    public Assessment(String urn, Integer assessmentScore, Agent agent) {
        this.urn = urn;
        this.assessmentScore = assessmentScore;
        this.agent = agent;
    }

    public Assessment() {
    }

    @Id
    public String getUrn() {
        return urn;
    }
    public void setUrn(String urn) {
        this.urn = urn;
    }




    @Column(name = "score", nullable = false)
    public Integer getAssessmentScore() {
        return assessmentScore;
    }
    public void setAssessmentScore(Integer assessmentScore) {
        this.assessmentScore = assessmentScore;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "urn='" + urn + '\'' +
                ", assessmentScore=" + assessmentScore +
                '}';
    }
}
