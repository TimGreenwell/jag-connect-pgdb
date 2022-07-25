package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
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

//  tlg - this might have been replaced by an elementCollection
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="assessment_agent_fk", nullable = false)
    @JsonBackReference
    private Agent agent;

}
