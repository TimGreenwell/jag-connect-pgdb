package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;

@Entity(name = "Assessment")
@Table(name = "Assessment")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
@EqualsAndHashCode
@Builder

public class Assessment {
    @Id
    @Column(name = "assessment_pk", nullable = false)
    private String id;

    @Column(name = "assessment_score", nullable = false)
    private Integer assessmentScore;

//  tlg - this might have been replaced by an elementCollection
    // note to me: As this is just a map - it makes more sense to use an element
    // collection rather than a bidirectional relationship...
    // @TODO The same goes for Input and Output and maybe Binding and Subscriptions

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="assessment_agent_fk", nullable = false)
    @JsonBackReference
    private Agent agent;

}
