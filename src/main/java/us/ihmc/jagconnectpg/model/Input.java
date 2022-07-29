package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;

@Entity(name = "Consume")
@Table(name = "Consume")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
@EqualsAndHashCode
@Builder

public class Input {
    @Id
    @Column(name = "consume_pk", nullable = false)
    private String name;

    @Column(name = "consume_type", nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="consume_activity_fk", nullable=false)
    @JsonBackReference
    private Activity activity;

}
