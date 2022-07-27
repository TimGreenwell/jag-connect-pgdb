package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity(name = "Produce")
@Table(name = "Produce")
@Data

public class Output {
    @Id
    @Column(name = "produce_pk", nullable = false)
    private String name;

    @Column(name = "produce_type", nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="produce_activity_fk", nullable=false)
    private Activity activity;

    @JsonBackReference
    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}