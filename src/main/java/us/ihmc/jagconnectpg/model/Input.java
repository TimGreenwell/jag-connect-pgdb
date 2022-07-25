package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;

@Entity(name = "Consume")
@Table(name = "Consume")
@Data
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
