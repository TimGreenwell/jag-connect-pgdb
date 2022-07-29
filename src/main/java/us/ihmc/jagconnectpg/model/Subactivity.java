package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;

@Entity(name = "Subactivity")
@Table(name = "Subactivity")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
@EqualsAndHashCode
@Builder


public class Subactivity {
    @Id
    @Column(name = "subactivity_pk", nullable = false)
    private String id;

    @Column(name = "subactivity_urn", nullable = false)
    private String urn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="subactivity_parent_fk")
    @JsonBackReference
    private Activity activity;

}
