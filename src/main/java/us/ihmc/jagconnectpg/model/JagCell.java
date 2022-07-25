package us.ihmc.jagconnectpg.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Node")
@Table(name = "Node")
@Data
public class JagCell {

    @Id
    @Column(name = "node_pk", nullable = false)
    private String id;


    @Column(name = "node_urn", nullable = true)
    private String urn;


    @Column(name = "node_child_id", nullable = true)
    private String childId;


    @Column(name = "node_parent_id", nullable = true)
    private String parentId;


    @Column(name = "node_project_id", nullable = true)
    private String projectId;


    @JsonProperty("isLocked")
    @Column(name = "node_is_locked", nullable = true)
    private Boolean isLocked;


    @JsonProperty("isExpanded")
    @Column(name = "node_is_expanded", nullable = true)
    private Boolean isExpanded;


    @Column(name = "node_x", nullable = true)
    private int x;
    @Column(name = "node_y", nullable = true)
    private int y;


    @Column(name = "node_con_name", nullable = true)
    private String contextualName;


    @Column(name = "node_con_desc", nullable = true)
    private String contextualDescription;


    @OneToMany(
            mappedBy = "jagCell",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Subscription> subscriptions = new ArrayList<>();


    @Column(name = "node_return_value", nullable = true)
    private String returnValue;

    @Column(name = "node_return_state", nullable = true)
    private String returnState;


    @Column(name = "node_test_return_value", nullable = true)
    private String testReturnValue;

    @Column(name = "node_test_return_state", nullable = true)
    private String testReturnState;


    @OneToMany(
            mappedBy = "jagCell",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    public List<JagCell> children = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="node_child_parent_fk", nullable=true)
    @JsonBackReference
    private JagCell jagCell;

}


