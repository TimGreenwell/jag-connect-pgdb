package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Entity(name = "Activity")
@Table(name = "Activity")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
@EqualsAndHashCode
@Builder

public class Activity {
    @Id
    @Column(name = "activity_urn")
    private String urn;

    @Column(name = "activity_name", nullable = false)
    private String name;

    @Column(name = "activity_description", nullable = true)
    private String description;

    @Embedded
    private Connector connector = new Connector();

    @OneToMany(
            mappedBy = "activity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Input> inputs;

    @OneToMany(
            mappedBy = "activity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Output> outputs;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "activity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Subactivity> children;


    @OneToMany(
            mappedBy = "activity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Binding> bindings;

    @Column(name = "activity_author", nullable = true)
    private String author;

    @Column(name = "activity_created_date", nullable = true)
    private Date createdDate;

    @Column(name = "activity_modified_date", nullable = true)
    private Date modifiedDate;

    @Column(name = "activity_locked_by", nullable = true)
    private String lockedBy;

    @Column(name = "activity_is_locked", nullable = true)
    private Boolean isLocked;

    @Column(name = "activity_collapsed", nullable = true)
    private Boolean collapsed;

}

