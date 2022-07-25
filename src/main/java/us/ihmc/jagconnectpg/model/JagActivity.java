package us.ihmc.jagconnectpg.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Activity")
@Table(name = "Activity")
@Data
public class JagActivity {
    @Id
    @Column(name = "activity_urn")
    private String urn;

    @Column(name = "activity_description", nullable = true)
    private String description;

    @Column(name = "activity_name", nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "jagActivity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<JagActivityChild> children = new ArrayList<>();

    @OneToMany(
            mappedBy = "jagActivity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Input> inputs = new ArrayList<>();

    @OneToMany(
            mappedBy = "jagActivity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Output> outputs = new ArrayList<>();

    @OneToMany(
            mappedBy = "jagActivity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Binding> bindings = new ArrayList<>();

    @Embedded
    private Connector connector = new Connector();

    @Column(name = "activity_is_locked", nullable = true)
    private Boolean isLocked;

    @Column(name = "activity_collapsed", nullable = true)
    private Boolean collapsed;

    @Column(name = "activity_author", nullable = true)
    private String author;

    @Column(name = "activity_locked_by", nullable = true)
    private String lockedBy;

    @Column(name = "activity_created_date", nullable = true)
    private Date createdDate;

    @Column(name = "activity_modified_date", nullable = true)
    private Date modifiedDate;


//
//
//    public JagActivity(String urn,
//                       String description,
//                       String name,
//                       List<JagActivityChild> children,
//                       List<Input> inputs,
//                       List<Output> outputs,
//                       List<Binding> bindings,
//                       Connector connector,
//                       String author,
//                       String lockedBy,
//                       Date createdDate,
//                       Date modifiedDate,
//                       Boolean isLocked,
//                       Boolean collapsed) {
//        this.urn = urn;
//        this.description = description;
//        this.name = name;
//        this.children = children;
//        this.inputs = inputs;
//        this.outputs = outputs;
//        this.bindings = bindings;
//        this.connector = connector;
//        this.author = author;
//        this.lockedBy = lockedBy;
//        this.createdDate = createdDate;
//        this.modifiedDate = modifiedDate;
//        this.isLocked = isLocked;
//        this.collapsed = collapsed;
//    }
//
//    public JagActivity() { }
//
//    public String getUrn() {
//        return urn;
//    }
//    public void setUrn(String urn) {
//        this.urn = urn;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//
////    public String getType() {
////        return type;
////    }
////    public void setType(String type) {
////        this.type = type;
////    }
//
//    public List<JagActivityChild> getChildren() {
//        return children;
//    }
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
//    //@Column(name = "activity_inputs", nullable = true)
//
//    public List<Input> getInputs() {
//        return inputs;
//    }
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
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getLockedBy() {
//        return lockedBy;
//    }
//
//    public void setLockedBy(String lockedBy) {
//        this.lockedBy = lockedBy;
//    }
//
//    public Date getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(Date createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public Date getModifiedDate() {
//        return modifiedDate;
//    }
//
//    public void setModifiedDate(Date modifiedDate) {
//        this.modifiedDate = modifiedDate;
//    }
//
//    public Boolean getLocked() {
//        return isLocked;
//    }
//
//    public void setLocked(Boolean locked) {
//        isLocked = locked;
//    }
//
//    public Boolean getCollapsed() {
//        return collapsed;
//    }
//
//    public void setCollapsed(Boolean collapsed) {
//        this.collapsed = collapsed;
//    }
//
//    @Override
//    public String toString() {
//        return "JagActivity{" +
//                "urn='" + urn + '\'' +
//                ", description='" + description + '\'' +
//                ", name='" + name + '\'' +
//                ", children=" + children +
//                ", inputs=" + inputs +
//                ", outputs=" + outputs +
//                ", bindings=" + bindings +
//                ", connector=" + connector +
//                ", isLocked=" + isLocked +
//                ", collapsed=" + collapsed +
//                ", author='" + author + '\'' +
//                ", lockedBy='" + lockedBy + '\'' +
//                ", createdDate=" + createdDate +
//                ", modifiedDate=" + modifiedDate +
//                '}';
//    }
}

