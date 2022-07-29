package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;

@Entity(name = "Binding")
@Table(name = "Binding")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
@EqualsAndHashCode
@Builder

public class Binding {
    @Id
    @GeneratedValue
    @Column(name = "binding_id")
    private String id;

    @Column(name = "binding_in", nullable = false)
    private String in;

    @Column(name = "binding_out", nullable = false)
    private String out;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="binding_activity_fk", nullable=false)
    @JsonBackReference
    private Activity activity;

}


