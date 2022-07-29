package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Entity(name = "Subscription")
@Table(name = "Subscription")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
@EqualsAndHashCode
@Builder

public class Subscription {
    @Id
    @Column(name = "subscription_pk", nullable = false)
    private String name;

    @Column(name = "subscription_lrt", nullable = true)
    private Date lastReportTime;

    @Column(name = "subscription_data", nullable = true)
    private String lastReportData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="subscription_node_fk", nullable=false)
    @JsonBackReference
    private Node node;

}
