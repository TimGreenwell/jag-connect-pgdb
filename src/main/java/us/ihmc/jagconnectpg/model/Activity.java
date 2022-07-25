package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "Activity")
@Table(name = "Activity")
@Data
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
    private List<Input> inputs = new ArrayList<>();

    @OneToMany(
            mappedBy = "activity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Output> outputs = new ArrayList<>();


    @OneToMany(
            mappedBy = "activity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Subactivity> children = new ArrayList<>();

    @OneToMany(
            mappedBy = "activity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Binding> bindings = new ArrayList<>();

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

//    public void setChildren(List<JagActivityChild> children) {
//        this.children.clear();
//        this.children.addAll(children);
//    }
//
//    public Connector getConnector() {
//        return connector;
//    }
//    public void setConnector(Connector connector) {
//        this.connector.setExecution(connector.getExecution());
//        this.connector.setReturns(connector.getReturns());
//        this.connector.setOperator(connector.getOperator());
//    }
//
//    public void setInputs(List<Input> inputs) {
//        this.inputs.clear();
//        this.inputs.addAll(inputs);
//    }
//
//    public List<Output> getOutputs() {
//        return outputs;
//    }
//    public void setOutputs(List<Output> outputs) {
//        this.outputs.clear();
//        this.outputs.addAll(outputs);
//    }
//
//    public List<Binding> getBindings() {
//        return bindings;
//    }
//    public void setBindings(List<Binding> bindings) {
//        this.bindings.clear();
//        this.bindings.addAll(bindings);
//    }
//


